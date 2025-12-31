package com.miniproject.mini.project.config;

import com.miniproject.mini.project.entity.Entries;
import com.miniproject.mini.project.entity.Spectator;
import com.miniproject.mini.project.entity.SpectatorStatistics;
import com.miniproject.mini.project.enums.Nationality;
import com.miniproject.mini.project.enums.TicketType;
import com.miniproject.mini.project.model.SpectatorEntry;
import com.miniproject.mini.project.repository.EntriesRepository;
import com.miniproject.mini.project.repository.GeneralStatisticsRepository;
import com.miniproject.mini.project.repository.SpectatorRepository;
import com.miniproject.mini.project.repository.SpectatorStatisticsRepository;
import org.springframework.batch.core.job.Job;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.Step;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.infrastructure.item.ItemProcessor;
import org.springframework.batch.infrastructure.item.ItemReader;
import org.springframework.batch.infrastructure.item.ItemWriter;
import org.springframework.batch.infrastructure.item.json.JacksonJsonObjectReader;
import org.springframework.batch.infrastructure.item.json.JsonItemReader;
import org.springframework.batch.infrastructure.item.json.builder.JsonItemReaderBuilder;
import org.springframework.batch.infrastructure.item.xml.StaxEventItemReader;
import org.springframework.batch.infrastructure.item.xml.builder.StaxEventItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.oxm.xstream.XStreamMarshaller;
import java.util.Map;

@Configuration
public class BatchConfig {

    @Autowired
    SpectatorRepository spectatorRepository;
    @Autowired
    EntriesRepository entriesRepository;
    @Autowired
    SpectatorStatisticsRepository spectatorStatisticsRepository;
    @Autowired
    GeneralStatisticsRepository generalStatisticsRepository;

    @Bean
    public JsonItemReader<SpectatorEntry> jsonItemReader(){
        return new JsonItemReaderBuilder<SpectatorEntry>()
                .jsonObjectReader(new JacksonJsonObjectReader<>(SpectatorEntry.class))
                .resource(new ClassPathResource("data/spectators.json"))
                .build();
    }

    @Bean
    public XStreamMarshaller spectatorMarshaller() {
        XStreamMarshaller marshaller = new XStreamMarshaller();
        marshaller.setAliases(Map.of("spectator", SpectatorEntry.class)); // mappe <spectator> → SpectatorEntry
        return marshaller;
    }

    @Bean
    public StaxEventItemReader<SpectatorEntry> xmlItemReader() {
        return new StaxEventItemReaderBuilder<SpectatorEntry>()
                .name("xmlSpectatorReader")
                .resource(new ClassPathResource("data/spectators.xml")) // chemin du fichier XML
                .addFragmentRootElements("spectator") // chaque fragment = un spectateur
                .unmarshaller(spectatorMarshaller())
                .build();
    }

    @Bean
    public ItemProcessor<SpectatorEntry, ProcessedSpectator> processor() {

        return spectatorEntry -> {

            // 1. Validation
            if (spectatorEntry.getAge() < 0) {
                throw new IllegalArgumentException("Age invalide");
            }

            // 2. Création Spectator
            Spectator spectator = Spectator.builder().id(spectatorEntry.getSpectatorId())
                            .age(spectatorEntry.getAge())
                                    .nationality(Nationality.valueOf(spectatorEntry.getNationality()))
                                            .build();

            Entries entry = Entries.builder().spectator(spectator)
                    .matchId(spectatorEntry.getMatchId())
                    .entryTime(spectatorEntry.getEntryTime())
                    .gate(spectatorEntry.getGate())
                    .ticketNumber(spectatorEntry.getTicketNumber())
                    .ticketType(TicketType.valueOf(spectatorEntry.getTicketType()))
                    .build();

            String category="";
            int totalMatches = entriesRepository.countBySpectator(spectator)+1;
            if(totalMatches==1)
                category = "Première visite";
            if(totalMatches==2 || totalMatches==3)
                category = "Spectateur occasionnel";
            if(totalMatches>=4 && totalMatches<=6)
                category = "Spectateur régulier";
            if(totalMatches>=6)
                category = "Super fan";

            int totalMatchsofCAN = entriesRepository.countTotalMatchs();
            double loyality = (double) totalMatches / totalMatchsofCAN;

            SpectatorStatistics spectatorStatistics = SpectatorStatistics.builder()
                    .spectator(spectator)
                    .matchs(totalMatches)
                    .category(category)
                    .loyalty(loyality)
                    .economyTickets(entriesRepository.totalEconomyTicketsBySpectator(spectator))
                    .premiumTickets(entriesRepository.totalPremiumTicketsBySpectator(spectator))
                            .standardTickets(entriesRepository.totalStandardTicketsBySpectator(spectator))
                                    .vipTickets(entriesRepository.totalVipTicketsBySpectator(spectator))
                                            .build();

            return new ProcessedSpectator(spectator, entry, spectatorStatistics);
        };
    }


    @Bean
    public ItemWriter<ProcessedSpectator> writer() {
        return items -> {
            for (ProcessedSpectator ps : items) {
                spectatorRepository.save(ps.getSpectator());
                entriesRepository.save(ps.getEntry());
                spectatorStatisticsRepository.save(ps.getSpectatorStatistics());
            }
        };
    }

    @Bean
    public Step xmlStep(JobRepository jobRepository) {

        return new StepBuilder("xml-import-step", jobRepository)
                .<SpectatorEntry, ProcessedSpectator>chunk(10)
                .reader(xmlItemReader())
                .processor(processor())
                .writer(writer())
                .build();
    }

    @Bean
    public Step jsonStep(JobRepository jobRepository) {

        return new StepBuilder("json-import-step", jobRepository)
                .<SpectatorEntry, ProcessedSpectator>chunk(10)
                .reader(jsonItemReader())
                .processor(processor())
                .writer(writer())
                .build();
    }


    @Bean
    public Job job(JobRepository jobRepository, Step jsonStep, Step xmlStep){
        return new JobBuilder("importSpectators", jobRepository)
                .start(jsonStep)
                .next(xmlStep)
                .build();
    }

}


