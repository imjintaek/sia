package schedule.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import schedule.exception.NotExistException;
import schedule.model.dto.ScheduleDTO;
import schedule.model.util.DBUtil;

public class ScheduleDAO {
	//스케줄 추가		C		예외처리
	public static String insertSchedule(String flightNo,String destination,String date,String gate) throws SQLException, NotExistException {
		Connection con = null;
		PreparedStatement pstmt = null;
		StringBuilder result = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("insert into schedule values(?,?,TO_DATE(?,'yyyy/mm/dd HH24:MI:ss'),?)");
			pstmt.setString(1, flightNo);
			pstmt.setString(2, destination);
			pstmt.setString(3, date);
			pstmt.setString(4, gate);
			result = new StringBuilder();
			if(pstmt.executeUpdate() != 0){
				result.append(" 기장 코드 = ");
				result.append(flightNo); 
				result.append(" 도착지 = ");
				result.append(destination);
				result.append(" 출발 날짜 = ");
				result.append(date);
				result.append(" 게이트 = ");
				result.append(gate);
			}else {
				throw new NotExistException("스케줄 추가에 실패하셨습니다.");
			}
		}finally {
			DBUtil.close(con, pstmt);
		}
		return result.toString();
	}
	//스케줄 전체출력   R
	public static ArrayList<ScheduleDTO> getScheduleList() throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<ScheduleDTO> data = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("select sd.flight_no, sd.destination, TO_CHAR(sd.time,'yyyy/mm/dd hh24:mm'),sd.gate from schedule sd");
			rset = pstmt.executeQuery();
			data = new ArrayList<ScheduleDTO>();
			while(rset.next()) {
				data.add(new ScheduleDTO(rset.getString(1), rset.getString(2), rset.getString(3), rset.getString(4)));
			}
		}finally {
			DBUtil.close(con, pstmt, rset);
		}
		return data;
	}
	//스케줄 검색출력   R
	public static ArrayList<ScheduleDTO> getSchedule(String destination) throws SQLException, NotExistException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<ScheduleDTO> data = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("select sd.flight_no, sd.destination, TO_CHAR(sd.time,'yyyy/mm/dd hh24:mm'),sd.gate from schedule sd where destination = ?");
			pstmt.setString(1, destination);
			rset = pstmt.executeQuery();
			data = new ArrayList<ScheduleDTO>();
			while(rset.next()) {
				data.add(new ScheduleDTO(rset.getString(1), rset.getString(2), rset.getString(3), rset.getString(4)));
			}
			if(data.size() == 0) {
				throw new NotExistException("검색하신 도착지로 가는 스케줄은 존재하지 않습니다.");
			}
		}finally {
			DBUtil.close(con, pstmt, rset);
		}
		return data;
	}
	//스케줄 수정       U
	public static void updateSchedule(String flightNo, String time) throws SQLException, NotExistException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("update schedule set time = TO_DATE(?,'yyyy/mm/dd HH24:MI:ss') where flight_no = ?");
			pstmt.setString(1, time);
			pstmt.setString(2, flightNo);
			if(pstmt.executeUpdate() != 0) {
				throw new NotExistException("일정이 변경되었습니다.");
			}else {
				throw new NotExistException("일정표에 존재하지않습니다.");
			}
		}finally {
			DBUtil.close(con, pstmt);
		}
	}
	//스케줄 삭제       D
	public static void deleteSchedule(String flightNo) throws SQLException, NotExistException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("delete from schedule where flight_no = ?");
			pstmt.setString(1, flightNo);
			if(pstmt.executeUpdate() != 0) {
				throw new NotExistException("일정표에서 삭제되었습니다.");
			}else {
				throw new NotExistException("일정표에 존재하지 않는 비행기입니다.");
			}
		}finally {
			DBUtil.close(con, pstmt);
		}
	}
}
