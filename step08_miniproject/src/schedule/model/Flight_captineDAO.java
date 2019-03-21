package schedule.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import schedule.exception.NotExistException;
import schedule.model.dto.Flight_captineDTO;
import schedule.model.util.DBUtil;

public class Flight_captineDAO {
	
	//기장 데이터 추가		C	예외처리: 등록되지않은 비행기 등록할때 
	public static String insertFlightCaptine(String flightId, String name,String airplaneNo) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		StringBuilder result = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("insert into flight_captine values(?,?,?)");
			pstmt.setString(1, flightId);
			pstmt.setString(2, name);
			pstmt.setString(3, airplaneNo);
			result = new StringBuilder();
			if(pstmt.executeUpdate() != 0) {
				result.append(" 기장 코드 = ");
				result.append(flightId); 
				result.append(" 기장 이름 = ");
				result.append(name);
				result.append(" 기장이 모는 비행기 코드 = ");
				result.append(airplaneNo);
			}
		}finally {
			DBUtil.close(con, pstmt);
		}
		return result.toString();
	}
	
	//기장 리스트 검색		R	
	public static ArrayList<Flight_captineDTO> getFlightCaptineList() throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Flight_captineDTO> data = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("select * from flight_captine");
			rset = pstmt.executeQuery();
			data = new ArrayList<Flight_captineDTO>();
			
			while(rset.next()) {
				data.add(new Flight_captineDTO(rset.getString(1), rset.getString(2), rset.getString(3)));
			}
		}finally {
			DBUtil.close(con, pstmt, rset);
		}
		return data;
	}
	//기장 개인 검색		R	예외처리: 기장이 존재하지 않음
	public static Flight_captineDTO getFlightCaptine(String captineId) throws SQLException, NotExistException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Flight_captineDTO data = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("select * from flight_captine where flight_attendant_id = ?");
			pstmt.setString(1, captineId);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				data = new Flight_captineDTO(rset.getString(1), rset.getString(2), rset.getString(3));
			}else {
				throw new NotExistException("그러한 아이디를 가지고있는 기장은 존재하지 않습니다");
			}
		}finally {
			DBUtil.close(con, pstmt, rset);
		}
		return data;
	}
	//기장이 모는 비행기 수정		U 예외처리: airplane 부모 테이블에 비행기 기종 등록안된걸 바꾸려고하면 에러,그러한 id이름을 가진사람은없다
	public static String updateFlightCaptine(String captineId,String airplane) throws SQLException, NotExistException {
		Connection con = null;
		PreparedStatement pstmt = null;
		StringBuilder result = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("update flight_captine set airplane = ? where flight_attendant_id = ?");
			pstmt.setString(1, airplane);
			pstmt.setString(2, captineId);
			result = new StringBuilder();
			if(pstmt.executeUpdate() != 0) {
				result.append(" 기장 코드 = ");
				result.append(captineId); 
				result.append(" 기장이 모는 비행기 코드 = ");
				result.append(airplane);
			}else {
				throw new NotExistException(captineId + "라는 아이디를 가진 기장님은 존재하지 않습니다.");
			}
		}finally {
			DBUtil.close(con, pstmt);
		}
		return result.toString();
	}
	
	//기장 데이터 삭제 				D 비행 스케줄에 잡혀있지 않아야 데이터를 삭제할 수있음 
	public static void deleteFlightCaptine(String captineId) throws SQLException, NotExistException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("delete from flight_captine where flight_attendant_id = ? and \r\n" + 
					"(select flight_captine.flight_attendant_id from flight_captine  \r\n" + 
					"left outer join schedule on flight_captine.airplane = schedule.flight_no \r\n" + 
					"where schedule.flight_no is null) = ?");
			pstmt.setString(1, captineId);
			pstmt.setString(2, captineId);
			if(pstmt.executeUpdate() != 0) {
				throw new NotExistException("기장 아이디를 삭제하였습니다.");
			}else {
				throw new NotExistException("기장은 비행 스케줄이 잡혀있어 삭제가 불가능합니다.");
			}
		}finally {
			DBUtil.close(con, pstmt);
		}
	}
	
}
