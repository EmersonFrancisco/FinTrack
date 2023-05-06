package com.fintrack.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fintrack.enums.WalletType;
import com.fintrack.exception.WalletValidateException;
import com.fintrack.model.user.User;
import com.fintrack.model.wallet.Wallet;
import com.fintrack.model.wallet.WalletRequestDTO;
import com.fintrack.model.wallet.WalletResponseDTO;
import com.fintrack.repositories.WalletRepositories;

@Service("walletService")
public class WalletServiceImpl implements WalletService {

	@Autowired
	WalletRepositories walletRepositories;
	
	@Autowired
	private ModelMapper modelMapper;
	
	private Wallet save(Wallet wallet) {
		return walletRepositories.save(wallet);
	}
	
	private void delete(Wallet wallet) {
		walletRepositories.delete(wallet);
	}

	private List<Wallet> findAllWalletByUser(User user) {
		return walletRepositories.findAllByUser(user);
	}
	
	private Wallet findWalletById(Integer idWallet) {
		return walletRepositories.findById(idWallet).orElse(null);
	}
	
	@Override
	public WalletResponseDTO validateAndCreateOrUpdateWallet(WalletRequestDTO walletRequest, User user) throws WalletValidateException {
		Wallet wallet;
		if(walletRequest.getId() != null && walletRequest.getId() > 0) {
			wallet = walletRepositories.findById(walletRequest.getId()).orElse(null);
			if(walletExistAndIsItFromTheUser(user, wallet)) {
				updateWalletBasicInfo(wallet, walletRequest);
			} else {
				throw new WalletValidateException("idWallet is not valid, does not exist or does not belong to your username!");
			}
			
		} else {
			wallet = createNewWallet(walletRequest, user);
		}
		wallet = save(wallet);
		return convertWalletToWalletResponse(wallet);
	}

	private void updateWalletBasicInfo(Wallet wallet, WalletRequestDTO walletRequest) throws WalletValidateException {
		WalletType walletType = validateAndGetWalletType(walletRequest.getType());
		wallet.setType(walletType);
		wallet.setName(walletRequest.getName());
	}

	private Wallet createNewWallet(WalletRequestDTO walletRequest, User user) throws WalletValidateException {
		WalletType walletType = validateAndGetWalletType(walletRequest.getType());
		Wallet wallet = new Wallet();
		wallet.setUser(user);
		wallet.setName(walletRequest.getName());
		wallet.setType(walletType);
		wallet.setBalance(0.0);
		return wallet;
	}
	
	private WalletResponseDTO convertWalletToWalletResponse(Wallet wallet) {
		return modelMapper.map(wallet, WalletResponseDTO.class);
	}

	private WalletType validateAndGetWalletType(String walletType) throws WalletValidateException {
		if(!WalletType.BANK.name().equals(walletType) &&
		   !WalletType.MONEY.name().equals(walletType)) {
			throw new WalletValidateException("WalletType is not a format valid (Valid format is 'BANK' or 'MONEY')");
		}
		return WalletType.valueOf(walletType);
	}

	@Override
	public void validateAndDeleteWallet(Integer idWallet, User user) throws WalletValidateException {
		Wallet wallet = walletRepositories.findById(idWallet).orElse(null);
		if(walletExistAndIsItFromTheUser(user, wallet)) {	
			delete(wallet);
		} else {
			throw new WalletValidateException("idWallet is not valid, does not exist or does not belong to your username!");
		}
	}

	@Override
	public List<WalletResponseDTO> getAllWalletResponseByUser(User user) {
		List<Wallet> listWallet = findAllWalletByUser(user);
		List<WalletResponseDTO> listWalletResponse = new ArrayList<>();
		for(Wallet wallet : listWallet) {
			listWalletResponse.add(convertWalletToWalletResponse(wallet));
		}
		return listWalletResponse;
	}

	@Override
	public WalletResponseDTO getWalletResponseById(Integer idWallet,User user) throws WalletValidateException {
		Wallet wallet = findWalletById(idWallet);
		if(walletExistAndIsItFromTheUser(user, wallet)) {	
			return convertWalletToWalletResponse(wallet);
		} else {
			throw new WalletValidateException("idWallet is not valid, does not exist or does not belong to your username!");
		}
	}

	private boolean walletExistAndIsItFromTheUser(User user, Wallet wallet) {
		return wallet != null && wallet.getUser().equals(user);
	}
}
