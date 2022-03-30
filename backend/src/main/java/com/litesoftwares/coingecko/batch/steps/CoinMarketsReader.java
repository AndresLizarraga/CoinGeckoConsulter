package com.litesoftwares.coingecko.batch.steps;

import java.util.List;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.litesoftwares.coingecko.CoinGeckoApiClient;
import com.litesoftwares.coingecko.component.LogListener;
import com.litesoftwares.coingecko.constant.Currency;
import com.litesoftwares.coingecko.domain.Coins.CoinMarkets;
import com.litesoftwares.coingecko.exception.CoinGeckoApiException;
import com.litesoftwares.coingecko.impl.CoinGeckoApiClientImpl;

@Component
public class CoinMarketsReader implements ItemReader<CoinMarkets> {

	@Autowired
	private LogListener log;
	
	@Override
	public CoinMarkets read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		// TODO Auto-generated method stub
		   CoinGeckoApiClient coinGeckoClient = new CoinGeckoApiClientImpl();
		   List<CoinMarkets> coinMarkets = null;
		   try {
	        coinMarkets = coinGeckoClient.getCoinMarkets(Currency.USD);
		   } catch (CoinGeckoApiException e) {
			   log.onWarn("Atrapando excepcion de CoinGecko");
		   }
		     if (!coinMarkets.isEmpty()  && coinMarkets != null) {
		    	for (CoinMarkets cml : coinMarkets) {
		        		return cml;
		        }
		     }    
		        
	        return null;
		        
	}

}
