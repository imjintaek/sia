package schedule.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import schedule.exception.NotExistException;
import schedule.model.AirplaneDAO;
import schedule.model.Flight_captineDAO;
import schedule.model.ScheduleDAO;
import schedule.model.dto.AirplanDTO;
import schedule.model.dto.Flight_captineDTO;
import schedule.model.dto.ScheduleDTO;
import schedule.view.RunningEndView;

public class PlainScheduleController {

	//CREATE////////////////////////////////////////////////////////////////////////
	public static void insertAirPlane(String airplane_id, String name) {
		try {
			RunningEndView.showMessage(AirplaneDAO.insertAirPlane(airplane_id, name));
		} catch (SQLException e) {
			RunningEndView.showError(e.getMessage());
		}
	}
	
	public static void insertFlight_captain(String flight_id, String name, String airplane_no) {
		try {
			RunningEndView.showMessage(Flight_captineDAO.insertFlightCaptine(flight_id, name, airplane_no));
		} catch (SQLException e) {
			RunningEndView.showMessage(e.getMessage());
		}
	}
	
	public static void insertSchedule(String flight_no,String destination,String date,String gate) {
		try {
			RunningEndView.showMessage(ScheduleDAO.insertSchedule(flight_no, destination, date, gate));
		} catch (SQLException | NotExistException e) {
			RunningEndView.showMessage(e.getMessage());
		}
	}
	
	
	//READ//////////////////////////////////////////////////////////////////////
	public static void getAirPlaneList()  {
		try {
			RunningEndView.dataListView(AirplaneDAO.getAirPlaneList());
		} catch (SQLException e) {
			RunningEndView.showError(e.getMessage());
		}
	}

	public static void getFlight_captainList()  {
		try {
			RunningEndView.dataListView(Flight_captineDAO.getFlightCaptineList());
		} catch (SQLException e) {
			RunningEndView.showError(e.getMessage());
		}
	}

	public static void getScheduleList()  {
		try {
			RunningEndView.dataListView(ScheduleDAO.getScheduleList());
		} catch (SQLException e) {
			RunningEndView.showError(e.getMessage());
		}
	}

	public static void getAirplane(String airplane_id) {
		try {
			RunningEndView.dataView(AirplaneDAO.getAirPlane(airplane_id));
		} catch (SQLException | NotExistException e) {
			RunningEndView.showError(e.getMessage());
		}
	}

	public static void getFlight_captain(String captine_id) {
		try {
			RunningEndView.dataView(Flight_captineDAO.getFlightCaptine(captine_id));
		} catch (SQLException | NotExistException e) {
			RunningEndView.showError(e.getMessage());
		}
	}

	public static void getSchedule(String destination) {
		try {
			RunningEndView.dataListView(ScheduleDAO.getSchedule(destination));
		} catch (SQLException | NotExistException e) {
			RunningEndView.showError(e.getMessage());
		}
	}
	
	//UPDATE///////////////////////////////////////////////////////////////////////////////
	public static void updateAirPlane(String airplane_id, String name) {
		try {
			RunningEndView.showMessage(AirplaneDAO.updateAirPlane(airplane_id,name));
		} catch (SQLException e) {
			RunningEndView.showError(e.getMessage());
		}
	}

	public static void updateFlight_captine(String captine_id,String airplane) {
		try {
			RunningEndView.showMessage(Flight_captineDAO.updateFlightCaptine(captine_id, airplane));
		} catch (SQLException | NotExistException e) {
			RunningEndView.showError(e.getMessage());
		}
	}

	public static void updateSchedule(String flight_no, String time) {
		try {
			ScheduleDAO.updateSchedule(flight_no, time);
		} catch (SQLException | NotExistException e) {
			RunningEndView.showError(e.getMessage());
		}
	}

	//DELETE///////////////////////////////////////////////////////////////////////////////
	public static void deleteSchedule(String flight_no) {
		try {
			ScheduleDAO.deleteSchedule(flight_no);
		} catch (SQLException | NotExistException e) {
			RunningEndView.showError(e.getMessage());
		}
	}

	public static void deleteFlight_captine(String captine_id) {
		try {
			Flight_captineDAO.deleteFlightCaptine(captine_id);
		} catch (SQLException | NotExistException e) {
			RunningEndView.showError(e.getMessage());
		}
	}

	public static void deleteAirPlane(String airplane_id) {
		try {
			RunningEndView.showMessage(AirplaneDAO.deleteAirPlane(airplane_id));
		} catch (SQLException e) {
			RunningEndView.showError(e.getMessage());
		}
	}
}
