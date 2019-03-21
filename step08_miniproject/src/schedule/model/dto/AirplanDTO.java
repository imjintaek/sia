package schedule.model.dto;

public class AirplanDTO {
	//비행기 기종
	private String Airplan_Id;
	private String name;
	
	public AirplanDTO() {}
	
	public AirplanDTO(String airplan_Id, String name) {
		Airplan_Id = airplan_Id;
		this.name = name;
	}

	public String getAirplan_Id() {
		return Airplan_Id;
	}
	public void setAirplan_Id(String airplan_Id) {
		Airplan_Id = airplan_Id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "비행기 코드 = " + Airplan_Id + ", 비행기 기종 = " + name;
	}
}
