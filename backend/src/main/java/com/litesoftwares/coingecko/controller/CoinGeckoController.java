package com.litesoftwares.coingecko.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiRestClient;
import com.binance.api.client.domain.market.TickerStatistics;
import com.litesoftwares.coingecko.CoinGeckoApiClient;
import com.litesoftwares.coingecko.component.LogListener;
import com.litesoftwares.coingecko.constant.Currency;
import com.litesoftwares.coingecko.domain.Coins.CoinMarkets;
import com.litesoftwares.coingecko.impl.CoinGeckoApiClientImpl;

@RestController
@CrossOrigin(origins = "*")
public class CoinGeckoController {
	
	@Autowired
	private LogListener log;
	
    @Autowired
    JobLauncher jobLauncher;
 
    @Autowired
    Job processJob;

	@GetMapping(value="/consultar")
	public String consultant() {
		return "Welcome";
	}
	
	 @GetMapping(value="/invokeJob")
	    public String handle() throws Exception {
	 
	            JobParameters jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis())
	                    .toJobParameters();
	            jobLauncher.run(processJob, jobParameters);
	 
	        return "Batch job has been invoked";
	    }
	
	@GetMapping(value="/consultAllPrices")
	public ResponseEntity<Object> consultPrice() {

        CoinGeckoApiClient coinGeckoClient = new CoinGeckoApiClientImpl();

        List<CoinMarkets> coinMarkets = coinGeckoClient.getCoinMarkets(Currency.USD);
        try {
        BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance("",
        		"");
        BinanceApiRestClient binanceRestClient = factory.newRestClient();
        
        log.onInfo("Iniciando prueba de endpoint Binance...");
        binanceRestClient.ping();
        long serverTime = binanceRestClient.getServerTime();
        System.out.println(new Date(serverTime));
        List<TickerStatistics> stats = binanceRestClient.getAll24HrPriceStatistics();
        if (!stats.isEmpty() && stats != null) {
        	log.onInfo("El endpoint respondio correctamente...");
        	Map<String, TickerStatistics> tickers = tickerMap(stats);
        	
        	if (tickers.containsKey("BTCUSDT") ) {
        		log.onInfo( "El cambio porcentual de: " + tickers.get("BTCUSDT").getSymbol() + " es de: "  + tickers.get("BTCUSDT").getPriceChangePercent());
        	}
        	log.onInfo("La Lista de Trending coins es: " + coinGeckoClient.getTrending().toString());
        	log.onInfo(binanceRestClient.getAllPrices().toString());
        	
        	return ResponseEntity.ok(coinMarkets);
        } else {
        	throw new Exception();
        }
        
        }
        catch (Exception e) {
        	log.onFatal("Ocurrio un error al intentar conectarse a la Api: " +  e.getMessage());
        	e.printStackTrace();
        	System.out.println(e.getMessage());
        	return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
	}
	
	private Map<String, TickerStatistics> tickerMap(List<TickerStatistics> stats) {
		return stats.stream().collect(Collectors.toMap(TickerStatistics::getSymbol, Function.identity()));
	}
}
