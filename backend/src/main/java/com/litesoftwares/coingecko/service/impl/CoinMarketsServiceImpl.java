package com.litesoftwares.coingecko.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.litesoftwares.coingecko.CoinGeckoApiClient;
import com.litesoftwares.coingecko.constant.Currency;
import com.litesoftwares.coingecko.domain.Coins.CoinMarkets;
import com.litesoftwares.coingecko.exception.CoinGeckoApiException;
import com.litesoftwares.coingecko.impl.CoinGeckoApiClientImpl;
import com.litesoftwares.coingecko.service.CoinMarketsService;

@Service
public class CoinMarketsServiceImpl implements CoinMarketsService {

	@Override
	public List<CoinMarkets> getCoinMarkets() throws CoinGeckoApiException {
		// TODO Auto-generated method stub
        CoinGeckoApiClient coinGeckoClient = new CoinGeckoApiClientImpl();
        List<CoinMarkets> coinMarkets = null;
        try {
        coinMarkets = coinGeckoClient.getCoinMarkets(Currency.USD);
		
        	if (!coinMarkets.isEmpty() && coinMarkets != null) {
        		return coinMarkets;
        	} else {
        		throw new CoinGeckoApiException("Error calling CoinGecko Api", null);
        	}
        
        	}
        		catch (CoinGeckoApiException e) {
        	e.printStackTrace();
        }
        
		return coinMarkets;
	}

}
