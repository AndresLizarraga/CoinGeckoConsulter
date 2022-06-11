package com.litesoftwares.coingecko.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name="btc_consult")
public class BTC_Consult {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private BigDecimal current_price;
	
	private BigDecimal pc_24h;
	
	private BigDecimal pc_percentage_24h;
	
	private BigDecimal market_cap;
	
	private BigDecimal mc_24h;
	
	private BigDecimal mc_percentage_24h;
	
	private Date last_updated;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getCurrent_price() {
		return current_price;
	}

	public void setCurrent_price(BigDecimal current_price) {
		this.current_price = current_price;
	}

	public BigDecimal getPc_24h() {
		return pc_24h;
	}

	public void setPc_24h(BigDecimal pc_24h) {
		this.pc_24h = pc_24h;
	}

	public BigDecimal getPc_percentage_24h() {
		return pc_percentage_24h;
	}

	public void setPc_percentage_24h(BigDecimal pc_percentage_24h) {
		this.pc_percentage_24h = pc_percentage_24h;
	}

	public BigDecimal getMarket_cap() {
		return market_cap;
	}

	public void setMarket_cap(BigDecimal market_cap) {
		this.market_cap = market_cap;
	}

	public BigDecimal getMc_24h() {
		return mc_24h;
	}

	public void setMc_24h(BigDecimal mc_24h) {
		this.mc_24h = mc_24h;
	}

	public BigDecimal getMc_percentage_24h() {
		return mc_percentage_24h;
	}

	public void setMc_percentage_24h(BigDecimal mc_percentage_24h) {
		this.mc_percentage_24h = mc_percentage_24h;
	}

	public Date getLast_updated() {
		return last_updated;
	}

	public void setLast_updated(Date last_updated) {
		this.last_updated = last_updated;
	}

	@Override
	public String toString() {
		return "BTC_Consult [id=" + id + ", current_price=" + current_price + ", pc_24h=" + pc_24h
				+ ", pc_percentage_24h=" + pc_percentage_24h + ", market_cap=" + market_cap + ", mc_24h=" + mc_24h
				+ ", mc_percentage_24h=" + mc_percentage_24h + ", last_updated=" + last_updated + "]";
	}
}
