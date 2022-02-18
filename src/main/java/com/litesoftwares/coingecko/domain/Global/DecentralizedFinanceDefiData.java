package com.litesoftwares.coingecko.domain.Global;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DecentralizedFinanceDefiData {

    @JsonProperty("defi_market_cap")
    private String defiMarketCap;
    @JsonProperty("eth_market_cap")
    private String ethMarketCap;
    @JsonProperty("defi_to_eth_ratio")
    private String defiToEthRatio;
    @JsonProperty("trading_volume_24h")
    private String tradingVolume24h;
    @JsonProperty("defi_dominance")
    private String defiDominance;
    @JsonProperty("top_coin_name")
    private String topCoinName;
    @JsonProperty("top_coin_defi_dominance")
    private double topCoinDefiDominance;
    
    public String getDefiMarketCap() {
		return defiMarketCap;
	}

	public void setDefiMarketCap(String defiMarketCap) {
		this.defiMarketCap = defiMarketCap;
	}

	public String getEthMarketCap() {
		return ethMarketCap;
	}

	public void setEthMarketCap(String ethMarketCap) {
		this.ethMarketCap = ethMarketCap;
	}

	public String getDefiToEthRatio() {
		return defiToEthRatio;
	}

	public void setDefiToEthRatio(String defiToEthRatio) {
		this.defiToEthRatio = defiToEthRatio;
	}

	public String getTradingVolume24h() {
		return tradingVolume24h;
	}

	public void setTradingVolume24h(String tradingVolume24h) {
		this.tradingVolume24h = tradingVolume24h;
	}

	public String getDefiDominance() {
		return defiDominance;
	}

	public void setDefiDominance(String defiDominance) {
		this.defiDominance = defiDominance;
	}

	public String getTopCoinName() {
		return topCoinName;
	}

	public void setTopCoinName(String topCoinName) {
		this.topCoinName = topCoinName;
	}

	public double getTopCoinDefiDominance() {
		return topCoinDefiDominance;
	}

	public void setTopCoinDefiDominance(double topCoinDefiDominance) {
		this.topCoinDefiDominance = topCoinDefiDominance;
	}

	@Override
	public String toString() {
		return "DecentralizedFinanceDefiData [defiMarketCap=" + defiMarketCap + ", ethMarketCap=" + ethMarketCap
				+ ", defiToEthRatio=" + defiToEthRatio + ", tradingVolume24h=" + tradingVolume24h + ", defiDominance="
				+ defiDominance + ", topCoinName=" + topCoinName + ", topCoinDefiDominance=" + topCoinDefiDominance
				+ "]";
	}
}