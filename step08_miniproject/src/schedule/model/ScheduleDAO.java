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
	//������ �߰�		C		����ó��
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
				result.append(" ���� �ڵ� = ");
				result.append(flightNo); 
				result.append(" ������ = ");
				result.append(destination);
				result.append(" ��� ��¥ = ");
				result.append(date);
				result.append(" ����Ʈ = ");
				result.append(gate);
			}else {
				throw new NotExistException("������ �߰��� �����ϼ̽��ϴ�.");
			}
		}finally {
			DBUtil.close(con, pstmt);
		}
		return result.toString();
	}
	//������ ��ü���   R
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
	//������ �˻����   R
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
				throw new NotExistException("�˻��Ͻ� �������� ���� �������� �������� �ʽ��ϴ�.");
			}
		}finally {
			DBUtil.close(con, pstmt, rset);
		}
		return data;
	}
	//������ ����       U
	public static void updateSchedule(String flightNo, String time) throws SQLException, NotExistException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("update schedule set time = TO_DATE(?,'yyyy/mm/dd HH24:MI:ss') where flight_no = ?");
			pstmt.setString(1, time);
			pstmt.setString(2, flightNo);
			if(pstmt.executeUpdate() != 0) {
				throw new NotExistException("������ ����Ǿ����ϴ�.");
			}else {
				throw new NotExistException("����ǥ�� ���������ʽ��ϴ�.");
			}
		}finally {
			DBUtil.close(con, pstmt);
		}
	}
	//������ ����       D
	public static void deleteSchedule(String flightNo) throws SQLException, NotExistException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("delete from schedule where flight_no = ?");
			pstmt.setString(1, flightNo);
			if(pstmt.executeUpdate() != 0) {
				throw new NotExistException("����ǥ���� �����Ǿ����ϴ�.");
			}else {
				throw new NotExistException("����ǥ�� �������� �ʴ� ������Դϴ�.");
			}
		}finally {
			DBUtil.close(con, pstmt);
		}
	}
}
