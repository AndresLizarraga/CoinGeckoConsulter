package com.litesoftwares.coingecko.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.litesoftwares.coingecko.CoinGeckoApiClient;
import com.litesoftwares.coingecko.component.LogListener;
import com.litesoftwares.coingecko.constant.Currency;
import com.litesoftwares.coingecko.domain.Coins.CoinMarkets;
import com.litesoftwares.coingecko.exception.CoinGeckoApiException;
import com.litesoftwares.coingecko.impl.CoinGeckoApiClientImpl;
import com.litesoftwares.coingecko.model.BTC_Consult;
import com.litesoftwares.coingecko.repository.BTC_ConsultDao;
import com.litesoftwares.coingecko.service.BTC_ConsultService;

@Service
public class BTC_ConsultServiceImpl implements BTC_ConsultService {
	
	@Autowired
	private BTC_ConsultDao btcConsultDao;
	
	@Autowired
	private LogListener log;
	
	@Override
	public void updateData() throws CoinGeckoApiException {
		log.onInfo("Starting BTC price database update...");
		CoinGeckoApiClient coinGeckoClient = new CoinGeckoApiClientImpl();
        List<CoinMarkets> coinMarkets = null;
        List<BTC_Consult> btcc = (List<BTC_Consult>) btcConsultDao.findAll();
        coinMarkets = coinGeckoClient.getCoinMarkets(Currency.USD);
    	if (!coinMarkets.isEmpty()) {
	        if (btcc.isEmpty()) {
	    		log.onInfo("The is no BTC entity created yet, creating a new one...");
	        	CoinMarkets coinMarket = coinMarkets.get(0);
	        	log.onInfo("The updated BTC info is: " + coinMarket.toString());
	        	BTC_Consult btc_c = buildFromCm(coinMarket);
	        	if (btc_c != null) {
	        		log.onInfo("Saving new entity...");
	        		btcConsultDao.save(btc_c);
	        	} else {
	        		log.onWarn("We could not create a new BTC entity");
	        	}
	        } else {
	        	log.onInfo("Updating prices...");
	        	BTC_Consult btc_consult = btcConsultDao.findById(1L).orElse(null); 	
			    CoinMarkets coinMarket = coinMarkets.get(0);
			    btc_consult = cmToBTC_Consult(coinMarket, btc_consult);
			    btcConsultDao.save(btc_consult);
			    log.onInfo("BTC price updated successfully!");
			    log.onInfo("BTC price is " + btc_consult.getCurrent_price().toString());
	        }
    	}
	}
	
	private BTC_Consult buildFromCm(CoinMarkets cm) {
		BTC_Consult btc = new BTC_Consult();
		btc.setCurrent_price(BigDecimal.valueOf(cm.getCurrentPrice()));
		btc.setPc_24h(BigDecimal.valueOf(cm.getPriceChange24h()));
		btc.setPc_percentage_24h(BigDecimal.valueOf(cm.getPriceChangePercentage24h()));
		btc.setMarket_cap(BigDecimal.valueOf(cm.getMarketCap()));
		btc.setMc_24h(BigDecimal.valueOf(cm.getMarketCapChange24h()));
		btc.setMc_percentage_24h(BigDecimal.valueOf(cm.getMarketCapChangePercentage24h()));
		btc.setLast_updated(new Date());
		return btc;
	}
	
	private BTC_Consult cmToBTC_Consult(CoinMarkets cm, BTC_Consult btc) {
		btc.setCurrent_price(BigDecimal.valueOf(cm.getCurrentPrice()));
		btc.setPc_24h(BigDecimal.valueOf(cm.getPriceChange24h()));
		btc.setPc_percentage_24h(BigDecimal.valueOf(cm.getPriceChangePercentage24h()));
		btc.setMarket_cap(BigDecimal.valueOf(cm.getMarketCap()));
		btc.setMc_24h(BigDecimal.valueOf(cm.getMarketCapChange24h()));
		btc.setMc_percentage_24h(BigDecimal.valueOf(cm.getMarketCapChangePercentage24h()));
		btc.setLast_updated(new Date());
		return btc;
	}
	
	@Scheduled(fixedRate = 30000)
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void updateBTCPrices() {
		this.updateData();
	}
	

}
