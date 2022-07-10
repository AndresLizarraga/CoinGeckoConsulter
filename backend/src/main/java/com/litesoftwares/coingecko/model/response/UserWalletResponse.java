package com.litesoftwares.coingecko.model.response;

import java.math.BigDecimal;

import com.litesoftwares.coingecko.model.UserWallet;

public class UserWalletResponse {

	private String status;
	
	private String message;
	
	private String asset;
	
	private BigDecimal free_balance;
	
	private BigDecimal locked_balance;
	
	private String assetPair;
	
	private BigDecimal avg_traded_price;
	
	public UserWalletResponse() {
		this.free_balance = BigDecimal.ZERO;
		this.locked_balance = BigDecimal.ZERO;
		this.avg_traded_price = BigDecimal.ZERO;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAsset() {
		return asset;
	}

	public void setAsset(String asset) {
		this.asset = asset;
	}

	public BigDecimal getFree_balance() {
		return free_balance;
	}

	public void setFree_balance(BigDecimal free_balance) {
		this.free_balance = free_balance;
	}

	public BigDecimal getLocked_balance() {
		return locked_balance;
	}

	public void setLocked_balance(BigDecimal locked_balance) {
		this.locked_balance = locked_balance;
	}

	public String getAssetPair() {
		return assetPair;
	}

	public void setAssetPair(String assetPair) {
		this.assetPair = assetPair;
	}
	
	public BigDecimal getAvg_traded_price() {
		return avg_traded_price;
	}

	public void setAvg_traded_price(BigDecimal avg_traded_price) {
		this.avg_traded_price = avg_traded_price;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public UserWalletResponse buildFromUserWallet(UserWallet uW) {
		if (uW.getSymbol() != null && uW.getAssetPair() != null) {
			this.status = "OK";
			this.free_balance = uW.getFree_balance();
			this.locked_balance = uW.getBlocked_balance();
			this.avg_traded_price = uW.getAverage_traded_price();
			this.asset = uW.getSymbol();
			this.assetPair = uW.getAssetPair();
			this.message = "Successfully updated!";
		}
		return this;
	}
}
