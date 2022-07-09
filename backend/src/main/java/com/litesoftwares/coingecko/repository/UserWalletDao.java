package com.litesoftwares.coingecko.repository;

import org.springframework.data.repository.CrudRepository;

import com.litesoftwares.coingecko.model.UserWallet;

public interface UserWalletDao extends CrudRepository<UserWallet, Long> {

}
