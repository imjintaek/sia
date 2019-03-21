package schedule.model.dto;

import java.sql.Date;

public class ScheduleDTO {
	//비행 스케줄
	private String airplan_no;
	private String destination;
	private String time;
	private String gate;
	
	public ScheduleDTO() {}
	
	public ScheduleDTO(String airplan_no, String destination, String time, String gate) {
		this.airplan_no = airplan_no;
		this.destination = destination;
		this.time = time;
		this.gate = gate;
	}

	public String getAirplan_no() {
		return airplan_no;
	}

	public void setAirplan_no(String airplan_no) {
		this.airplan_no = airplan_no;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getGate() {
		return gate;
	}

	public void setGate(String gate) {
		this.gate = gate;
	}
	@Override
	public String toString() {
		return "비행기 기종 = " + airplan_no + ", 도착지 = " + destination + ", 출발시간 = " + time + ", 게이트 = "
				+ gate;
	}

}
