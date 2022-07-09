package com.litesoftwares.coingecko.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiRestClient;
import com.binance.api.client.domain.account.AssetBalance;
import com.binance.api.client.domain.market.TickerStatistics;
import com.litesoftwares.coingecko.CoinGeckoApiClient;
import com.litesoftwares.coingecko.component.LogListener;
import com.litesoftwares.coingecko.constant.Currency;
import com.litesoftwares.coingecko.domain.Coins.CoinMarkets;
import com.litesoftwares.coingecko.impl.CoinGeckoApiClientImpl;
import com.litesoftwares.coingecko.model.response.UserWalletResponse;
import com.litesoftwares.coingecko.request.UserWalletRequest;
import com.litesoftwares.coingecko.service.UserWalletService;

@RestController
@CrossOrigin(origins = "*")
public class CoinGeckoController {
	
	@Autowired
	private LogListener log;

	@Autowired
	private UserWalletService uWS;
	
	@GetMapping(value="/consultar")
	public String consultant() {
		return "Welcome";
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

	@PostMapping(path="/createUserWallet")
	public ResponseEntity<Object> createUserWallet(@RequestBody UserWalletRequest uWR) {
		UserWalletResponse response;
		try {
			response = uWS.createUserWalletData(uWR.getApiKey(), uWR.getSecret(), uWR.getAsset(), uWR.getStable());
			if (response != null) {
			log.onInfo("The information has been updated correctly.");
			return ResponseEntity.ok(response);
			} else { 
				return ResponseEntity.badRequest().body("There has been a problem trying to create the UserWallet object");
			}
		}
		catch (Exception e) {
			
		}
		return null;
	}
	
	@PostMapping(value="/cO")
	public ResponseEntity<Object> consultObject(@RequestBody UserWalletRequest uWR) {
        BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance(uWR.getApiKey(),
        		uWR.getSecret());
        BinanceApiRestClient bRC = factory.newRestClient();
    	AssetBalance assetBalance = bRC.getAccount().getAssetBalance("BTC");
		return ResponseEntity.ok(assetBalance);
	}
	
	private Map<String, TickerStatistics> tickerMap(List<TickerStatistics> stats) {
		return stats.stream().collect(Collectors.toMap(TickerStatistics::getSymbol, Function.identity()));
	}
}
