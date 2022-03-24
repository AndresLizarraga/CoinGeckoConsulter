package com.litesoftwares.coingecko.batch.steps;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;

import com.litesoftwares.coingecko.component.LogListener;

public class ConsultPricesFirstTask implements Tasklet {

	private static LogListener log;
	
	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		// TODO Auto-generated method stub
		
		log.onInfo("Ejecutando Task...");
		return RepeatStatus.FINISHED;
	}

}
