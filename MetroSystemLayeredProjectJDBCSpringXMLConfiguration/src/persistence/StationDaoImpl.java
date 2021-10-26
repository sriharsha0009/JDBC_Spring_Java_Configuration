package persistence;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import bean.Station;
import helper.StationRowMapper;

@Repository
public class StationDaoImpl implements StationDao {
	

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Override
	public int checkStation(String stationName) throws  SQLException {

		String query="SELECT StationId FROM StationsInformation WHERE StationName = ?";
		int station=jdbcTemplate.queryForObject(query, Integer.class, stationName);
		return station;
		
	}	
	@Override
	public int[] fareCalculate(int sourceStationId, int stationId) throws SQLException {

		
		String query="SELECT * FROM StationsInformation WHERE StationId IN(?,?)";
		List<Station> station=jdbcTemplate.query(query, new StationRowMapper(), sourceStationId, stationId);
		int[] priority = new int[2];
		int i=0;
		for(Station s: station) 
			priority[i++]=s.getPriority();
		return priority;

	}


	@Override
	public ArrayList<String> getAllStations() throws SQLException {
		
		String query="SELECT * FROM StationsInformation";
		List<Station> stations=jdbcTemplate.query(query, new StationRowMapper());
		ArrayList<String> stationss= new ArrayList<String>();
		for(Station station: stations) 
			stationss.add(station.getStationName());
		return stationss;

	}

}
