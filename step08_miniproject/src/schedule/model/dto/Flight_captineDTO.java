package schedule.model.dto;

public class Flight_captineDTO {
	//���� 
	private String captine_Id;
	private String name;
	private String airplan_no;
	
	
	public Flight_captineDTO() {}
	public Flight_captineDTO(String captine_Id, String name, String airplan_no) {
		this.captine_Id = captine_Id;
		this.name = name;
		this.airplan_no = airplan_no;
	}
	public String getCaptine_Id() {
		return captine_Id;
	}
	public void setCaptine_Id(String captine_Id) {
		this.captine_Id = captine_Id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAirplan_no() {
		return airplan_no;
	}
	public void setAirplan_no(String airplan_no) {
		this.airplan_no = airplan_no;
	}
	@Override
	public String toString() {
		return "���� �ڵ� = " + captine_Id + ", ���� �̸� = " + name + ", ���� ������ڵ� = " + airplan_no;
	}
}