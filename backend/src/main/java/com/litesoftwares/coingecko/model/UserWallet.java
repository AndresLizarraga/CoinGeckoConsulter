package com.litesoftwares.coingecko.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name="user_wallet")
public class UserWallet {

	@Id
	@GeneratedValue
	private Long id;
	
	private String symbol;
	
	private BigDecimal current_balance;
	
	private BigDecimal blocked_balance;
	
	private BigDecimal last_order_price;
	
	private BigDecimal average_traded_price;
	
	private BigDecimal is_in_lost;
	
	private BigDecimal roi;
	
	private Date last_updated;

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public BigDecimal getCurrent_balance() {
		return current_balance;
	}

	public void setCurrent_balance(BigDecimal current_balance) {
		this.current_balance = current_balance;
	}

	public BigDecimal getBlocked_balance() {
		return blocked_balance;
	}

	public void setBlocked_balance(BigDecimal blocked_balance) {
		this.blocked_balance = blocked_balance;
	}

	public BigDecimal getLast_order_price() {
		return last_order_price;
	}

	public void setLast_order_price(BigDecimal last_order_price) {
		this.last_order_price = last_order_price;
	}

	public BigDecimal getAverage_traded_price() {
		return average_traded_price;
	}

	public void setAverage_traded_price(BigDecimal average_traded_price) {
		this.average_traded_price = average_traded_price;
	}

	public BigDecimal getIs_in_lost() {
		return is_in_lost;
	}

	public void setIs_in_lost(BigDecimal is_in_lost) {
		this.is_in_lost = is_in_lost;
	}

	public BigDecimal getRoi() {
		return roi;
	}

	public void setRoi(BigDecimal roi) {
		this.roi = roi;
	}

	public Date getLast_updated() {
		return last_updated;
	}

	public void setLast_updated(Date last_updated) {
		this.last_updated = last_updated;
	}
}
