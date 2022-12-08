package com.litesoftwares.coingecko.service;

import com.litesoftwares.coingecko.model.response.UserWalletResponse;

public interface UserWalletService {

	public UserWalletResponse createUserWalletData(String apiKey, String secret, String asset, String stable);
	
}
