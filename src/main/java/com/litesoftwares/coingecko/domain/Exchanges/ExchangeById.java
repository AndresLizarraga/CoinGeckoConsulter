package com.litesoftwares.coingecko.domain.Exchanges;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.litesoftwares.coingecko.domain.Shared.Ticker;
import lombok.*;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode(callSuper = false)
public class ExchangeById extends Exchanges{
    @JsonProperty("tickers")
    private List<Ticker> tickers;
    @JsonProperty("status_updates")
   
    private List<Object> statusUpdates;
    
	public List<Ticker> getTickers() {
		return tickers;
	}
	public void setTickers(List<Ticker> tickers) {
		this.tickers = tickers;
	}
	public List<Object> getStatusUpdates() {
		return statusUpdates;
	}
	public void setStatusUpdates(List<Object> statusUpdates) {
		this.statusUpdates = statusUpdates;
	}
	@Override
	public String toString() {
		return "ExchangeById [tickers=" + tickers + ", statusUpdates=" + statusUpdates + "]";
	}
	
}
