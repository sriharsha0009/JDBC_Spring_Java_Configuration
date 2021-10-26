package persistence;

import java.sql.SQLException;

import bean.Card;

public interface CardDao {
	int storeDetails(Card passenger) throws ClassNotFoundException, SQLException;
//	boolean swipeIn(int cardId) throws ClassNotFoundException, SQLException;
	
	int rechargeCard(int cardId, int addAmount) throws  SQLException;
	boolean checkCardDetails(int cardId,int choice) throws  SQLException;
	//int updateAmount(int cardId, int amount) throws  SQLException;
	int returnCardId()throws  SQLException;
	int updateFare(int cardId, int fare) throws SQLException;
	int updateCardBalance(int cardId, int amount) throws SQLException;

}
