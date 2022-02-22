package com.litesoftwares.coingecko.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiRestClient;
import com.binance.api.client.domain.general.Asset;
import com.binance.api.client.domain.market.TickerStatistics;
import com.litesoftwares.coingecko.CoinGeckoApiClient;
import com.litesoftwares.coingecko.component.LogListener;
import com.litesoftwares.coingecko.constant.Currency;
import com.litesoftwares.coingecko.domain.Coins.CoinMarkets;
import com.litesoftwares.coingecko.domain.ExchangeRates.ExchangeRates;
import com.litesoftwares.coingecko.impl.CoinGeckoApiClientImpl;

import ch.qos.logback.classic.Logger;

@RestController
public class CoinGeckoController {
	
	@Autowired
	private LogListener log;

	@GetMapping(value="/consultar")
	public String consultant() {
		return "Welcome";
	}
	
	@GetMapping(value="/consultAllPrices")
	public ResponseEntity<Object> consultPrice() {

        CoinGeckoApiClient coinGeckoClient = new CoinGeckoApiClientImpl();

        ExchangeRates exchangeRates = coinGeckoClient.getExchangeRates();
        List<CoinMarkets> coinMarkets = coinGeckoClient.getCoinMarkets(Currency.USD);
        try {
        BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance("mPlT2nexpHcb64r3rXOKxim14GgfpmwdCxKWjeiQfzryh0pSUEjzCpTp2lqFA6x4",
        		"RZPtgGD0oEV8yo1EqSH4XCQXlfUMM1sKTagVeqrkn5oGO6CRiGEWQ4EzLkOiuKCF");
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
        	
        	return ResponseEntity.ok(coinGeckoClient.getPrice("bitcoin", Currency.USD));
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
