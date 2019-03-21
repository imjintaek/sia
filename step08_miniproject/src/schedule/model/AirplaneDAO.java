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
	
	//����� �߰�		C	����ó�� �߰��ʿ�
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
				result.append(" ����� �ڵ� = ");
				result.append(airplaneId); 
				result.append(name);
				result.append(" ����� ������ �����ϼ̽��ϴ�.");
			}
		}finally{
			DBUtil.close(con, pstmt);
		}
		return result.toString();
	}
	
	//����� ��ü ����Ʈ ���	R	����ó�� �߰��ʿ�
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
	//����� �Ѱ� ���	���� �߰� ó�� �ʿ�
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
				throw new NotExistException("��ϵǾ��ִ� �����ID�� �ƴմϴ�.");
			}
		}finally{
			DBUtil.close(con, pstmt);
		}
		return data;
	}
	
	// ����� �̸� ���� U ����ó�� �߰��ʿ� 
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
				msg.append(" ����� �ڵ� = ");
				msg.append(airplaneId);
				msg.append(" ����� �������� ");
				msg.append(name);
				msg.append("���� �����մϴ�.");
			}
		} finally {
			DBUtil.close(con, pstmt);
		}
		return msg.toString();
	}

	// ����� ���� D ����ó�� �߰��ʿ�  1.����Ű�ϴ����̶� ���� �Ұ��� ���� 2.flight_no �������� ����
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
				msg.append(" ����� �ڵ� = ");
				msg.append(airplaneId);
				msg.append("�� �����ϴµ� �����Ͽ����ϴ�. ");
			}
		} finally {
			DBUtil.close(con, pstmt);
		}
		return msg.toString();
	}
	
}