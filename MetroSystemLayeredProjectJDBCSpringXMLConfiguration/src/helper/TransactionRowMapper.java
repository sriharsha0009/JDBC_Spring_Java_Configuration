package helper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import bean.Station;
import bean.Transaction;


public class TransactionRowMapper implements RowMapper<Transaction>{

	@Override
	public Transaction mapRow(ResultSet rs, int rowNum) throws SQLException {
		int id = rs.getInt("TransactionId");
		int cardId = rs.getInt("CardId");
		int sourceStationId = rs.getInt("SourceStationId");
		int destinationStationId = rs.getInt("DestinationStationId");
		String swipeInTime = rs.getString("SwipeInTime");
		String swipeOutTime = rs.getString("SwipeOutTime");
		int fare = rs.getInt("Fare");
		int penality = rs.getInt("Penality");
		Transaction transaction = new Transaction(id,cardId,sourceStationId, destinationStationId, swipeInTime, swipeOutTime, fare, penality);
		return transaction;
	}

}
