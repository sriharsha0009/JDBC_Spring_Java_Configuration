package persistence;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import bean.Transaction;
import helper.TransactionRowMapper;

@Repository
public class TransactionDaoImpl implements TransactionDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public int swipeInStatus(int cardId) throws  SQLException {		
		String query="SELECT TransactionId FROM Transaction WHERE CardId =? AND DestinationStationId=?";
		int transactionId=0;
		try {
		transactionId=jdbcTemplate.queryForObject(query, Integer.class, cardId,0);
		}catch(EmptyResultDataAccessException e) {
			transactionId =0;
			return transactionId;
		}
		return transactionId;
	}
	
	@Override
	public boolean insertSwipeIn(int cardId, int stationId) throws  SQLException {
		
		LocalDateTime current = LocalDateTime.now();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"); 
		String formatedDateTime = current.format(format);
		String query = "INSERT INTO TRANSACTION(CARDID,SOURCESTATIONID,SWIPEINTIME) VALUES(?,?,?)";
		int rows = jdbcTemplate.update(query, cardId, stationId, formatedDateTime);
		if (rows > 0)
			return true;
		return false;
	}
	
	
	public int[] swipeOut(int cardId, int stationId) throws SQLException {
		String query="SELECT * FROM Transaction WHERE CardId =? AND DESTINATIONSTATIONID=?";
		Transaction transaction=jdbcTemplate.queryForObject(query, new TransactionRowMapper(), cardId,0);
		int[] details = new int[] {transaction.getSourceStationId(),transaction.getTransacationId()};
		return details;
		
	}
	
	
	@Override
	public boolean swipeOutUpdate(int destinationId,int transactionId, int fare) throws SQLException {
		LocalDateTime current = LocalDateTime.now();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"); 
		String currentDate = current.format(format);
		String query = "UPDATE Transaction SET DestinationStationId = ?,SwipeOutTime=?,Fare=? WHERE TransactionId =?";
		int rows = jdbcTemplate.update(query, destinationId, currentDate, fare, transactionId);
		if (rows > 0)
			return true;
		return false;
	}
	
	public String checkPenality(int transactionId) throws  SQLException {
		
		String query="SELECT SwipeInTime FROM Transaction where TransactionId = ?";
		String swipeInTime=jdbcTemplate.queryForObject(query, String.class, transactionId);
		return swipeInTime;
		
	}

	@Override
	public boolean updatePenality(int transactionId, int amount) throws  SQLException {

		String query = "UPDATE Transaction SET Penality = ? WHERE TransactionId =?";
		int rows = jdbcTemplate.update(query, amount, transactionId);
		if (rows > 0)
			return true;
		return false;
	}


	@Override
	public Transaction displayDetails(int transactionId) throws  SQLException {
		
		String query="SELECT * FROM Transaction WHERE TransactionId=?";
		Transaction transaction=jdbcTemplate.queryForObject(query, new TransactionRowMapper(), transactionId);
		return transaction;
		
	}


}
