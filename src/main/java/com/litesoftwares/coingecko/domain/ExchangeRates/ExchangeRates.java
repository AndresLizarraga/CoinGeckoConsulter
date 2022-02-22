package com.litesoftwares.coingecko.domain.ExchangeRates;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExchangeRates {
    @JsonProperty("rates")
    private Map<String, Rate> rates;

	public Map<String, Rate> getRates() {
		return rates;
	}

	public void setRates(Map<String, Rate> rates) {
		this.rates = rates;
	}

	@Override
	public String toString() {
		return "ExchangeRates [rates=" + rates + "]";
	}
}