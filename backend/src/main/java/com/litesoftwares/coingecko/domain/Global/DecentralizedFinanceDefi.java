package com.litesoftwares.coingecko.domain.Global;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DecentralizedFinanceDefi {
    @JsonProperty("data")
    private DecentralizedFinanceDefiData data;

	public DecentralizedFinanceDefiData getData() {
		return data;
	}

	public void setData(DecentralizedFinanceDefiData data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "DecentralizedFinanceDefi [data=" + data + "]";
	}
}


