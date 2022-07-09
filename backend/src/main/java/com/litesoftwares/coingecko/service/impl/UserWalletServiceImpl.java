package com.litesoftwares.coingecko.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiRestClient;
import com.binance.api.client.domain.account.AssetBalance;
import com.binance.api.client.domain.account.Trade;
import com.binance.api.client.domain.market.TickerStatistics;
import com.binance.api.client.exception.BinanceApiException;
import com.litesoftwares.coingecko.component.LogListener;
import com.litesoftwares.coingecko.model.UserWallet;
import com.litesoftwares.coingecko.model.dto.UserWalletDTO;
import com.litesoftwares.coingecko.model.response.UserWalletResponse;
import com.litesoftwares.coingecko.repository.UserWalletDao;
import com.litesoftwares.coingecko.service.UserWalletService;

@Service
public class UserWalletServiceImpl implements UserWalletService{

	@Autowired
	private UserWalletDao uWDao;
	
	@Autowired
	private LogListener log;
	
	private float avg;
	
	@Override
	public UserWalletResponse createUserWalletData(String apiKey, String secret, String asset, String stable) {
		log.onInfo("Starting wallet update.");
		UserWalletResponse uWR = new UserWalletResponse();
		try {
			
		log.onInfo("Consulting Binance API.");
		BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance(apiKey,
        		secret);
		BinanceApiRestClient binanceRestClient = factory.newRestClient();
		if (binanceRestClient != null) {
			log.onInfo("Successful api query.");
			log.onInfo("Collecting account information...");
			UserWalletDTO uWDTO = buildUserWalletFromBinanceAPI(binanceRestClient, asset, stable);
			UserWallet userWallet = new UserWallet(); 
			userWallet = userWallet.build(uWDTO);
			log.onInfo("Saving wallet information.");
			uWDao.save(userWallet);
			log.onInfo("Wallet saved successfully!");
			uWR = uWR.buildFromUserWallet(userWallet);
			return uWR;
		} else {
			uWR.setStatus("ERROR");
			throw new BinanceApiException("Ocurrio un problema al consultar la API de binance.");
		}
		} catch (Exception e) {
			log.onFatal("Ocurrio un problema al consultar el servicio. Motivo: " + e.getMessage());
			e.printStackTrace();
			return uWR;
		}
	}
	
	private UserWalletDTO buildUserWalletFromBinanceAPI(BinanceApiRestClient binanceAPI, String asset, String stable) {
		UserWalletDTO uWDTO = new UserWalletDTO();
		//Update the Current Balance of an Asset in the user wallet
		AssetBalance assetbalance = binanceAPI.getAccount().getAssetBalance(asset);
		String assetPair = asset.concat(stable);
		uWDTO = updateBalanceFromAB(uWDTO,assetbalance, asset);
		//List of the last 10 trades from asset
		List<Trade> tradeHistoryList = binanceAPI.getMyTrades(assetPair);
		uWDTO =updateTradesFromAB(uWDTO, tradeHistoryList);
		//Consult the current price of an Asset
		TickerStatistics tS = binanceAPI.get24HrPriceStatistics(assetPair);
		boolean isLost = isInLost(uWDTO,tS);
		uWDTO.setIs_in_lost(isLost);
		uWDTO.setAssetPair(assetPair);
		return uWDTO;
	}
	
	private UserWalletDTO updateBalanceFromAB(UserWalletDTO uW, AssetBalance aB, String asset) {
		uW.setSymbol(asset);
		uW.setFree_balance(new BigDecimal(aB.getFree()));
		uW.setBlocked_balance(new BigDecimal(aB.getLocked()));
		return uW;
	}
	
	private UserWalletDTO updateTradesFromAB(UserWalletDTO uW, List<Trade> tradeHistory) {
		uW.setLast_traded_price_date(longTodateConverter(tradeHistory.get(tradeHistory.size()-1).getTime()));
		uW.setLast_traded_price(new BigDecimal(tradeHistory.get(tradeHistory.size()-1).getPrice()));
		uW.setAverage_traded_price(calculateAverageTradePrice(tradeHistory));
		return uW;
	}
	
	private Date longTodateConverter(long time) {
		return new Date(time);
	}
	
	private BigDecimal calculateAverageTradePrice(List<Trade> tradeHistory) {
		this.avg = 0;
		tradeHistory.stream().forEach((p) -> {
			this.avg = this.avg + Float.parseFloat(p.getPrice());
		});
		double totalAvg = avg / tradeHistory.size();
		
		return new BigDecimal(totalAvg).setScale(8);
	}
	
	private boolean isInLost(UserWalletDTO uW, TickerStatistics tS) {
		double lastPrice = Float.parseFloat(tS.getLastPrice());
		if (BigDecimal.valueOf(lastPrice).compareTo(uW.getAverage_traded_price()) == 1) {
		return false;
		} else {
			return true;
		}
	}
	
}
