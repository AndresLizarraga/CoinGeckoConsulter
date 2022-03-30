package com.litesoftwares.coingecko.batch.steps;

import org.springframework.batch.item.ItemProcessor;

import com.litesoftwares.coingecko.domain.Coins.CoinMarkets;

public class CoinMarketsProcessor implements ItemProcessor<CoinMarkets, CoinMarkets> {

	@Override
	public CoinMarkets process(CoinMarkets data) throws Exception {
		return data;
	}

}