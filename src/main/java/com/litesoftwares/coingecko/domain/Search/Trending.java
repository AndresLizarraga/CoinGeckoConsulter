package com.litesoftwares.coingecko.domain.Search;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Trending {
    @JsonProperty("coins")
    private List<TrendingCoin> coins = null;
    @JsonProperty("exchanges")
    private List<Object> exchanges = null;
    
	public List<TrendingCoin> getCoins() {
		return coins;
	}
	public void setCoins(List<TrendingCoin> coins) {
		this.coins = coins;
	}
	public List<Object> getExchanges() {
		return exchanges;
	}
	public void setExchanges(List<Object> exchanges) {
		this.exchanges = exchanges;
	}
   
}
