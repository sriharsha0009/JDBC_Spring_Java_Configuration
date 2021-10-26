package helper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import bean.Station;

public class StationRowMapper implements RowMapper<Station> {

	@Override
	public Station mapRow(ResultSet rs, int rowNum) throws SQLException {
		int id = rs.getInt("StationId");
		String name = rs.getString("StationName");
		int priority = rs.getInt("Priority");
		Station station = new Station(id,name,priority);
		return station;
	}

}
