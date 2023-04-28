package com.fintrack.service;

import java.util.List;

import com.fintrack.exception.WalletValidateException;
import com.fintrack.model.user.User;
import com.fintrack.model.wallet.WalletRequestDTO;
import com.fintrack.model.wallet.WalletResponseDTO;

public interface WalletService {
	
	WalletResponseDTO validateAndCreateOrUpdateWallet(WalletRequestDTO walletRequest, User user) throws WalletValidateException;

	void validateAndDeleteWallet(Integer idWallet, User user) throws WalletValidateException;

	List<WalletResponseDTO> getAllWalletResponseByUser(User user);

	WalletResponseDTO getWalletResponseByUser(Integer idWallet, User user) throws WalletValidateException;

}
