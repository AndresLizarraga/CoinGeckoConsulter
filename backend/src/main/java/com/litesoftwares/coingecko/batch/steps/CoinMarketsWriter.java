package com.litesoftwares.coingecko.batch.steps;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

import com.litesoftwares.coingecko.domain.Coins.CoinMarkets;

public class CoinMarketsWriter implements ItemWriter<CoinMarkets> {

	@Override
	public void write(List<? extends CoinMarkets> messages) throws Exception {
		for (CoinMarkets msg : messages) {
			System.out.println("Writing the data " + msg);
		}
	}

}