package com.litesoftwares.coingecko.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.litesoftwares.coingecko.model.dto.UserWalletDTO;

@Entity
@Table (name="user_wallet")
public class UserWallet {

	@Id
	@GeneratedValue
	private Long id;
	
	private String symbol;
	
	@Column(precision=12, scale=8)
	private BigDecimal free_balance;
	
	@Column(precision=12, scale=8)
	private BigDecimal total_balance;
	
	@Column(precision=12, scale=8)
	private BigDecimal blocked_balance;
	
	@Column(precision=10, scale=2)
	private BigDecimal last_traded_price;
	
	private Date last_traded_price_date; 
	
	@Column(precision=10, scale=2)
	private BigDecimal average_traded_price;
	
	private boolean is_in_lost;
	
	@Column(precision=10, scale=2)
	private BigDecimal roi;
	
	private Date last_updated;
	
	private String depositKey;
	
	private String assetPair;
	
	public UserWallet() {
		this.free_balance= BigDecimal.ZERO;
		this.total_balance = BigDecimal.ZERO;
		this.blocked_balance = BigDecimal.ZERO;
		this.last_traded_price = BigDecimal.ZERO;
		this.average_traded_price = BigDecimal.ZERO;
		this.roi = BigDecimal.ZERO;
	}
	
	public UserWallet build(UserWalletDTO uWDTO) {
		this.symbol = uWDTO.getSymbol();
		this.free_balance= uWDTO.getFree_balance();
		this.total_balance = uWDTO.getTotal_balance();
		this.blocked_balance = uWDTO.getBlocked_balance();
		this.last_traded_price = uWDTO.getLast_traded_price();
		this.last_traded_price_date = uWDTO.getLast_traded_price_date();
		this.average_traded_price = uWDTO.getAverage_traded_price();
		this.is_in_lost = uWDTO.isIs_in_lost();
		this.roi = uWDTO.getRoi();
		this.depositKey = uWDTO.getDepositKey();
		this.assetPair = uWDTO.getAssetPair();
		return this;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public BigDecimal getFree_balance() {
		return free_balance;
	}

	public void setFree_balance(BigDecimal free_balance) {
		this.free_balance = free_balance;
	}

	public BigDecimal getTotal_balance() {
		return total_balance;
	}

	public void setTotal_balance(BigDecimal total_balance) {
		this.total_balance = total_balance;
	}

	public BigDecimal getBlocked_balance() {
		return blocked_balance;
	}

	public void setBlocked_balance(BigDecimal blocked_balance) {
		this.blocked_balance = blocked_balance;
	}

	public BigDecimal getLast_order_price() {
		return last_traded_price;
	}

	public void setLast_order_price(BigDecimal last_order_price) {
		this.last_traded_price = last_order_price;
	}

	public BigDecimal getAverage_traded_price() {
		return average_traded_price;
	}

	public void setAverage_traded_price(BigDecimal average_traded_price) {
		this.average_traded_price = average_traded_price;
	}

	public boolean isIs_in_lost() {
		return is_in_lost;
	}

	public void setIs_in_lost(boolean is_in_lost) {
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

	public Date getLast_order_price_date() {
		return last_traded_price_date;
	}

	public void setLast_order_price_date(Date last_order_price_date) {
		this.last_traded_price_date = last_order_price_date;
	}

	public BigDecimal getLast_traded_price() {
		return last_traded_price;
	}

	public void setLast_traded_price(BigDecimal last_traded_price) {
		this.last_traded_price = last_traded_price;
	}

	public Date getLast_traded_price_date() {
		return last_traded_price_date;
	}

	public void setLast_traded_price_date(Date last_traded_price_date) {
		this.last_traded_price_date = last_traded_price_date;
	}

	public String getDepositKey() {
		return depositKey;
	}

	public void setDepositKey(String depositKey) {
		this.depositKey = depositKey;
	}

	public String getAssetPair() {
		return assetPair;
	}

	public void setAssetPair(String assetPair) {
		this.assetPair = assetPair;
	}
}
