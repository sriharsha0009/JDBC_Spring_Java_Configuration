package persistence;

import java.sql.SQLException;

import bean.Transaction;

public interface TransactionDao {

	boolean insertSwipeIn(int cardId, int stationId) throws  SQLException;
	int[] swipeOut(int cardId, int stationId) throws SQLException;
	boolean swipeOutUpdate(int destinationId,int transactionId, int fare) throws SQLException;
	int swipeInStatus(int cardId) throws  SQLException;
	String checkPenality(int transactionId) throws SQLException;
	boolean updatePenality(int transactionId, int amount) throws  SQLException;
Transaction displayDetails(int transactionId)throws  SQLException;
}
