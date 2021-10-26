package service;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bean.Card;
import persistence.CardDao;
import persistence.CardDaoImpl;

@Service
public class CardServiceImpl implements CardService {
	
	@Autowired
	private CardDao cardDao;
	

	@Override
	public int storeCardDetails(Card card) throws ClassNotFoundException, SQLException {
		return cardDao.storeDetails(card);
	}

	@Override
	public int rechargeCard(int cardId, int amount) throws ClassNotFoundException, SQLException {
		
		return cardDao.rechargeCard(cardId,amount);
	}

	@Override
	public boolean checkCard(int cardId,int choice) throws ClassNotFoundException, SQLException {
		return cardDao.checkCardDetails(cardId,choice);
	}

	@Override
	public int updateFare(int cardId, int fare) throws ClassNotFoundException, SQLException {
		return cardDao.updateFare(cardId, fare);
		
	}

	@Override
	public int checkFare(int cardNumber, int fare) throws ClassNotFoundException, SQLException {
		return cardDao.updateFare(cardNumber, fare);
	}

	@Override
	public int updateCardBalance(int cardNumber, int amount) throws ClassNotFoundException, SQLException {
		return cardDao.updateCardBalance(cardNumber,amount);
	}


}
