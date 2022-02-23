package com.litesoftwares.coingecko.domain.Search;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TrendingCoinItem {
    @JsonProperty("id")
    private String id;
    @JsonProperty("coin_id")
    private int coinId;
    @JsonProperty("name")
    private String name;
    @JsonProperty("symbol")
    private String symbol;
    @JsonProperty("market_cap_rank")
    private int marketCapRank;
    @JsonProperty("thumb")
    private String thumb;
    @JsonProperty("small")
    private String small;
    @JsonProperty("large")
    private String large;
    @JsonProperty("slug")
    private String slug;
    @JsonProperty("price_btc")
    private double priceBtc;
    @JsonProperty("score")
    private int score;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getCoinId() {
		return coinId;
	}
	public void setCoinId(int coinId) {
		this.coinId = coinId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public int getMarketCapRank() {
		return marketCapRank;
	}
	public void setMarketCapRank(int marketCapRank) {
		this.marketCapRank = marketCapRank;
	}
	public String getThumb() {
		return thumb;
	}
	public void setThumb(String thumb) {
		this.thumb = thumb;
	}
	public String getSmall() {
		return small;
	}
	public void setSmall(String small) {
		this.small = small;
	}
	public String getLarge() {
		return large;
	}
	public void setLarge(String large) {
		this.large = large;
	}
	public String getSlug() {
		return slug;
	}
	public void setSlug(String slug) {
		this.slug = slug;
	}
	public double getPriceBtc() {
		return priceBtc;
	}
	public void setPriceBtc(double priceBtc) {
		this.priceBtc = priceBtc;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	@Override
	public String toString() {
		return "TrendingCoinItem [id=" + id + ", coinId=" + coinId + ", name=" + name + ", symbol=" + symbol
				+ ", marketCapRank=" + marketCapRank + ", thumb=" + thumb + ", small=" + small + ", large=" + large
				+ ", slug=" + slug + ", priceBtc=" + priceBtc + ", score=" + score + "]";
	}
}
