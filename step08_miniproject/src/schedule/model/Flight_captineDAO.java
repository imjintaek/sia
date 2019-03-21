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
	
	//���� ������ �߰�		C	����ó��: ��ϵ������� ����� ����Ҷ� 
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
				result.append(" ���� �ڵ� = ");
				result.append(flightId); 
				result.append(" ���� �̸� = ");
				result.append(name);
				result.append(" ������ ��� ����� �ڵ� = ");
				result.append(airplaneNo);
			}
		}finally {
			DBUtil.close(con, pstmt);
		}
		return result.toString();
	}
	
	//���� ����Ʈ �˻�		R	
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
	//���� ���� �˻�		R	����ó��: ������ �������� ����
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
				throw new NotExistException("�׷��� ���̵� �������ִ� ������ �������� �ʽ��ϴ�");
			}
		}finally {
			DBUtil.close(con, pstmt, rset);
		}
		return data;
	}
	//������ ��� ����� ����		U ����ó��: airplane �θ� ���̺� ����� ���� ��ϾȵȰ� �ٲٷ����ϸ� ����,�׷��� id�̸��� �������������
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
				result.append(" ���� �ڵ� = ");
				result.append(captineId); 
				result.append(" ������ ��� ����� �ڵ� = ");
				result.append(airplane);
			}else {
				throw new NotExistException(captineId + "��� ���̵� ���� ������� �������� �ʽ��ϴ�.");
			}
		}finally {
			DBUtil.close(con, pstmt);
		}
		return result.toString();
	}
	
	//���� ������ ���� 				D ���� �����ٿ� �������� �ʾƾ� �����͸� ������ ������ 
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
				throw new NotExistException("���� ���̵� �����Ͽ����ϴ�.");
			}else {
				throw new NotExistException("������ ���� �������� �����־� ������ �Ұ����մϴ�.");
			}
		}finally {
			DBUtil.close(con, pstmt);
		}
	}
	
}
