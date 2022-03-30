package com.litesoftwares.coingecko.batch;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.litesoftwares.coingecko.batch.steps.CoinMarketsProcessor;
import com.litesoftwares.coingecko.batch.steps.CoinMarketsReader;
import com.litesoftwares.coingecko.batch.steps.CoinMarketsWriter;
import com.litesoftwares.coingecko.batch.steps.ConsultPricesFirstTask;
import com.litesoftwares.coingecko.batch.steps.Processor;
import com.litesoftwares.coingecko.batch.steps.Reader;
import com.litesoftwares.coingecko.batch.steps.Writer;
import com.litesoftwares.coingecko.domain.Coins.CoinMarkets;

@Configuration
@EnableBatchProcessing
public class BotJobConfig extends DefaultBatchConfigurer {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	  
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Bean
	public Job processJob() {
		return jobBuilderFactory.get("processJob")
				.incrementer(new RunIdIncrementer()).listener(listener())
				.flow(orderStep1()).end().build();
	}
	
	@Bean
	public Job coinMarketsJob() {
		return jobBuilderFactory.get("coinMarketsJob")
				.incrementer(new RunIdIncrementer()).listener(listener())
				.flow(orderStep2()).end().build();
	}

	@Bean
	public Step orderStep1() {
		return stepBuilderFactory.get("orderStep1").<String, String> chunk(1)
				.reader(new Reader())
				.processor(new Processor())
				.writer(new Writer()).build();
	}
	
	@Bean
	public Step orderStep2() {
		return stepBuilderFactory.get("orderStep2").<CoinMarkets, CoinMarkets> chunk(100)
				.reader(new CoinMarketsReader())
				.processor(new CoinMarketsProcessor())
				.writer(new CoinMarketsWriter()).build();
	}

	@Bean
	public JobExecutionListener listener() {
		return new JobCompletionListener();
	}
	
////	@Bean
//	public Step step1() {
//	    
//	   return (Step) stepBuilderFactory.get("paso1")
//			   .tasklet(new ConsultPricesFirstTask()).build();
//	  }
//    
//    @Bean Job botConsultJob() {
//        
//        return jobBuilderFactory.get("botConsultJob")
//            .start(step1())
////            .next()
////            .next()
//            .build();
//        
//      }
	
    @Override
    public void setDataSource(DataSource dataSource) {
        //This BatchConfigurer ignores any DataSource
    }
}
