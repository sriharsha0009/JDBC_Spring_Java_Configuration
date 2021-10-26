package service;

import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bean.Card;
import bean.Station;
import persistence.StationDao;
import persistence.StationDaoImpl;
import persistence.TransactionDao;
import persistence.TransactionDaoImpl;

@Service
public class StationServiceImpl implements StationService {

	@Autowired
	StationDao stationDao;
	@Autowired
	TransactionService transactionService ;
	@Autowired
	CardService cardService;


	@Override
	public int checkStation(String stationName) throws ClassNotFoundException, SQLException {
		return stationDao.checkStation(stationName);
	}

	@Override
	public int swipeOutCard(int cardId, int stationId,int choice) throws SQLException, ClassNotFoundException {
		int[] details =  transactionService.swipeOut(cardId, stationId);
		int[] priority = stationDao.fareCalculate(details[0], stationId);
		int fare =(priority[0]>priority[1]) ? (priority[0]-priority[1])*5 : (priority[1]-priority[0])*5;
		if(details[0]==stationId)
			fare=0;
		int fareTotal = cardService.updateFare(cardId, fare);
		if(fareTotal>0 && choice==2) {				
			if(transactionService.swipeOutUpdate(stationId,details[1], fare))				
				return fareTotal;
		}
		return fareTotal;
	}

	@Override
	public ArrayList<String> getStations() throws SQLException, ClassNotFoundException {
		return stationDao.getAllStations();
	}

}
