package com.litesoftwares.coingecko.domain.Search;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TrendingCoin {
    @JsonProperty("item")
    private TrendingCoinItem item;

	public TrendingCoinItem getItem() {
		return item;
	}

	public void setItem(TrendingCoinItem item) {
		this.item = item;
	}

	@Override
	public String toString() {
		return "TrendingCoin [item=" + item + "]";
	}
}
