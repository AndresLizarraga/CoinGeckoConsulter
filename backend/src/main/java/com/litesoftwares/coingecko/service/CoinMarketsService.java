package com.litesoftwares.coingecko.service;

import java.util.List;

import com.litesoftwares.coingecko.domain.Coins.CoinMarkets;
import com.litesoftwares.coingecko.exception.CoinGeckoApiException;

public interface CoinMarketsService {

	public List <CoinMarkets> getCoinMarkets() throws CoinGeckoApiException;
	
}
