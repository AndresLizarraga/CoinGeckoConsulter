package com.litesoftwares.coingecko.domain.Shared;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Ticker {
    @JsonProperty("base")
    private String base;
    @JsonProperty("target")
    private String target;
    @JsonProperty("market")
    private Market market;
    @JsonProperty("last")
    private double last;
    @JsonProperty("volume")
    private double volume;
    @JsonProperty("converted_last")
    private Map<String, String> convertedLast;
    @JsonProperty("converted_volume")
    private Map<String, String> convertedVolume;
    @JsonProperty("trust_score")
    private String trustScore;
    @JsonProperty("bid_ask_spread_percentage")
    private double bidAskSpreadPercentage;
    @JsonProperty("timestamp")
    private String timestamp;
    @JsonProperty("last_traded_at")
    private String lastTradedAt;
    @JsonProperty("last_fetch_at")
    private String lastFetchAt;
    @JsonProperty("is_anomaly")
    private boolean isAnomaly;
    @JsonProperty("is_stale")
    private boolean isStale;
    @JsonProperty("trade_url")
    private String tradeUrl;
    @JsonProperty("coin_id")
    private String coinId;
	
    public String getBase() {
		return base;
	}
	public void setBase(String base) {
		this.base = base;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public Market getMarket() {
		return market;
	}
	public void setMarket(Market market) {
		this.market = market;
	}
	public double getLast() {
		return last;
	}
	public void setLast(double last) {
		this.last = last;
	}
	public double getVolume() {
		return volume;
	}
	public void setVolume(double volume) {
		this.volume = volume;
	}
	public Map<String, String> getConvertedLast() {
		return convertedLast;
	}
	public void setConvertedLast(Map<String, String> convertedLast) {
		this.convertedLast = convertedLast;
	}
	public Map<String, String> getConvertedVolume() {
		return convertedVolume;
	}
	public void setConvertedVolume(Map<String, String> convertedVolume) {
		this.convertedVolume = convertedVolume;
	}
	public String getTrustScore() {
		return trustScore;
	}
	public void setTrustScore(String trustScore) {
		this.trustScore = trustScore;
	}
	public double getBidAskSpreadPercentage() {
		return bidAskSpreadPercentage;
	}
	public void setBidAskSpreadPercentage(double bidAskSpreadPercentage) {
		this.bidAskSpreadPercentage = bidAskSpreadPercentage;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getLastTradedAt() {
		return lastTradedAt;
	}
	public void setLastTradedAt(String lastTradedAt) {
		this.lastTradedAt = lastTradedAt;
	}
	public String getLastFetchAt() {
		return lastFetchAt;
	}
	public void setLastFetchAt(String lastFetchAt) {
		this.lastFetchAt = lastFetchAt;
	}
	public boolean isAnomaly() {
		return isAnomaly;
	}
	public void setAnomaly(boolean isAnomaly) {
		this.isAnomaly = isAnomaly;
	}
	public boolean isStale() {
		return isStale;
	}
	public void setStale(boolean isStale) {
		this.isStale = isStale;
	}
	public String getTradeUrl() {
		return tradeUrl;
	}
	public void setTradeUrl(String tradeUrl) {
		this.tradeUrl = tradeUrl;
	}
	public String getCoinId() {
		return coinId;
	}
	public void setCoinId(String coinId) {
		this.coinId = coinId;
	}
}
