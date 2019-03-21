package schedule.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import schedule.exception.NotExistException;
import schedule.model.dto.AirplanDTO;
import schedule.model.util.DBUtil;


public class AirplaneDAO {
	
	//비행기 추가		C	예외처리 추가필요
	public static String insertAirPlane(String airplaneId, String name )  throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		StringBuilder result = null;
		try{
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("insert into flight values(?, ?)");
			pstmt.setString(1, airplaneId);
			pstmt.setString(2, name);
			result = new StringBuilder();
			if(pstmt.executeUpdate() != 0) {
				result.append(" 비행기 코드 = ");
				result.append(airplaneId); 
				result.append(name);
				result.append(" 비행기 기종을 저장하셨습니다.");
			}
		}finally{
			DBUtil.close(con, pstmt);
		}
		return result.toString();
	}
	
	//비행기 전체 리스트 출력	R	예외처리 추가필요
	public static ArrayList<AirplanDTO> getAirPlaneList() throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<AirplanDTO> data = null;
		try{
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("select * from flight");
			rset = pstmt.executeQuery();
			data = new ArrayList<>();
			while(rset.next()) {
				data.add(new AirplanDTO(rset.getString(1), rset.getString(2)));
			}
		}finally{
			DBUtil.close(con, pstmt);
		}
		return data;
	}
	//비행기 한개 출력	예외 추가 처리 필요
	public static AirplanDTO getAirPlane(String airplaneId) throws SQLException, NotExistException{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		AirplanDTO data = null;
		try{
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("select * from flight where flight_no = ?");
			pstmt.setString(1, airplaneId);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				data = new AirplanDTO(rset.getString(1), rset.getString(2));
			}else {
				throw new NotExistException("등록되어있는 비행기ID가 아닙니다.");
			}
		}finally{
			DBUtil.close(con, pstmt);
		}
		return data;
	}
	
	// 비행기 이름 수정 U 예외처리 추가필요 
	public static String updateAirPlane(String airplaneId, String name) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		StringBuilder msg = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("update flight set name = ? where flight_no = ?");
			pstmt.setString(1, name);
			pstmt.setString(2, airplaneId);
			msg = new StringBuilder();
			if(pstmt.executeUpdate() != 0) {
				msg.append(" 비행기 코드 = ");
				msg.append(airplaneId);
				msg.append(" 비행기 기종명을 ");
				msg.append(name);
				msg.append("으로 변경합니다.");
			}
		} finally {
			DBUtil.close(con, pstmt);
		}
		return msg.toString();
	}

	// 비행기 삭제 D 예외처리 추가필요  1.참조키하는중이라 삭제 불가능 예외 2.flight_no 존재하지 않음
	public static String deleteAirPlane(String airplaneId) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		StringBuilder msg = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("delete from flight where flight_no = ?");
			pstmt.setString(1, airplaneId);
			msg = new StringBuilder();
			if(pstmt.executeUpdate() != 0) {
				msg.append(" 비행기 코드 = ");
				msg.append(airplaneId);
				msg.append("를 제거하는데 성공하였습니다. ");
			}
		} finally {
			DBUtil.close(con, pstmt);
		}
		return msg.toString();
	}
	
}