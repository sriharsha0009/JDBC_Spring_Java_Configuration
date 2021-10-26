package persistence;

import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import bean.Card;
import exception.CardNotFoundException;
import exception.MinimumBalance;

@Repository
public class CardDaoImpl implements CardDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Override
	public int storeDetails(Card card) throws  SQLException {
		
		String query = "INSERT INTO Card(CardHolderName, CreationDate, Balance) VALUES(?,?,?)";
		int rows = jdbcTemplate.update(query, card.getHolderName(), card.getCreationDate(), card.getBalance());
		if (rows > 0)
			return returnCardId();
		return 0;
	}
	
	@Override
	public int returnCardId() throws  SQLException {
		String query = "SELECT MAX(CardId) from card";
		int cardId=jdbcTemplate.queryForObject(query, Integer.class);
		return cardId;
	}

	
	@Override
	public int rechargeCard(int cardId,int addAmount) throws  SQLException {		
		String query = "SELECT Balance FROM Card WHERE CardId =?";
		int amount = jdbcTemplate.queryForObject(query, Integer.class, cardId);
		amount+=addAmount;
		return amount;		
	}

	@Override
	public boolean checkCardDetails(int cardId,int choice) throws  SQLException {

		String query = "SELECT Balance FROM Card WHERE CardId =?";
		int amount = jdbcTemplate.queryForObject(query, Integer.class, cardId);
		if(amount<0)
			throw new CardNotFoundException("No card with this number");
		if(amount<20&&choice==2)
			throw new MinimumBalance("Balance is below 20.");
		return true;
		
	}


	@Override
	public int updateFare(int cardId, int fare) throws SQLException {
		String query="SELECT Balance FROM Card WHERE CardId =?";
		int amount = jdbcTemplate.queryForObject(query, Integer.class, cardId);
		return amount-fare;
		
	}

	@Override
	public int updateCardBalance(int cardId, int amount) throws SQLException {
		String query = "UPDATE Card SET Balance = ? WHERE CardId =?";
		int rows = jdbcTemplate.update(query, amount, cardId);
		if (rows > 0)
			return amount;
		return 0;
	}
	
	
}
