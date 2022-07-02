package com.litesoftwares.coingecko.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiRestClient;
import com.binance.api.client.domain.account.AssetBalance;
import com.binance.api.client.domain.account.TradeHistoryItem;
import com.litesoftwares.coingecko.component.LogListener;
import com.litesoftwares.coingecko.model.UserWallet;
import com.litesoftwares.coingecko.repository.UserWalletDao;
import com.litesoftwares.coingecko.service.UserWalletService;

@Service
public class UserWalletServiceImpl implements UserWalletService{
	
	private static final String BTC = "btc"; 

	@Autowired
	private UserWalletDao uWDao;
	
	@Autowired
	private LogListener log;
	
	@Override
	public void createUserWalletData(String apiKey, String secret) {
		try {
		BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance(apiKey,
        		secret);
		
		BinanceApiRestClient bRC = factory.newRestClient();
		UserWallet uW = buildBTCFromBARC(bRC);
		
		
		} catch (Exception e) {
			
		}
	}
	
	private UserWallet buildBTCFromBARC(BinanceApiRestClient barc) {
		UserWallet nUW = new UserWallet();
		AssetBalance ab = barc.getAccount().getAssetBalance(BTC);
		List<TradeHistoryItem> tHI = barc.getTrades(BTC, Integer.valueOf(500));
		//Update current and blocked balances
		nUW = updateBalanceFromAB(nUW,ab);
		nUW =updateTradesFromAB(nUW, tHI) ;
		
		return nUW;
	}
	
	private UserWallet updateBalanceFromAB(UserWallet uW, AssetBalance aB) {
		uW.setSymbol(BTC);
		uW.setCurrent_balance(BigDecimal.valueOf(Long.valueOf(aB.getFree())));
		uW.setBlocked_balance(BigDecimal.valueOf(Long.valueOf(aB.getLocked())));
		return uW;
	}
	
	private UserWallet updateTradesFromAB(UserWallet uW, List<TradeHistoryItem> tH) {
//		uW.setLast_traded_price(tH.get());
		
		
//		uW.setCurrent_balance(BigDecimal.valueOf(Long.valueOf(aB.getFree())));
		return uW;
	}
	
}
