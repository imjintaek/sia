package schedule.view;


import schedule.service.PlainScheduleController;

public class RunningStartView {
	public static void main(String[] args) {
		//		C
		System.out.println("***** 비행기 데이터 등록 ********");
		PlainScheduleController.insertAirPlane("AB3905","보잉600");
		PlainScheduleController.insertAirPlane("CX2311","보잉450");
		System.out.println("***** 기장 데이터 등록 *********");
		PlainScheduleController.insertFlight_captain("JM0001","최재민","CX2311");
		System.out.println("***** 스케줄 등록 *********");
		PlainScheduleController.insertSchedule("AB3905","오사카","2019/03/21 04:10","A-08");
		//		R
		System.out.println("***** 모든 비행기 데이터 출력 ********");
		PlainScheduleController.getAirPlaneList();
		System.out.println("***** 모든 기장 데이터 출력 ********");
		PlainScheduleController.getFlight_captainList();
		System.out.println("***** 모든 스케줄 데이터 출력 ********");
		PlainScheduleController.getScheduleList();
		System.out.println("***** 비행기 코드 검색 출력 ********");
		PlainScheduleController.getAirplane("QF3474");		//존재하는 데이터값
		PlainScheduleController.getAirplane("test3");    	//존재하지 않는 데이터 값
		System.out.println("***** 기장 코드 검색 출력 ********");
		PlainScheduleController.getFlight_captain("JH0001");	//존재하는 데이터값
		PlainScheduleController.getFlight_captain("AM0001");	//존재하지 않는 데이터 값
		System.out.println("***** 스케줄 도착지 검색 출력 ********");
		PlainScheduleController.getSchedule("도쿄");
		PlainScheduleController.getSchedule("베를린");
		
		//U
		System.out.println("***** 비행기 데이터 수정 ********");
		PlainScheduleController.updateAirPlane("AB3905","보잉660");
		System.out.println("***** 기장 비행기 수정 ********");
		PlainScheduleController.updateFlight_captine("JM0001","AB3905");
		System.out.println("***** 스케줄 출발시간 수정 ******");
		PlainScheduleController.updateSchedule("AB3905","2019/03/21 08:20");
		System.out.println("***** 모든 스케줄 데이터 출력 ********");
		PlainScheduleController.getScheduleList();
		//D
		System.out.println("***** 스케줄 삭제 ******");
		PlainScheduleController.deleteSchedule("AB3905");
		System.out.println("***** 모든 스케줄 데이터 출력 ********");
		PlainScheduleController.getScheduleList();
		System.out.println("***** 모든 기장 데이터 출력 ********");
		PlainScheduleController.getFlight_captainList();
		System.out.println("***** 비행기 데이터 삭제 ******");
		PlainScheduleController.deleteAirPlane("CX2311");
		System.out.println("***** 기장 데이터 삭제 ******");
		PlainScheduleController.deleteFlight_captine("JM0001");
		PlainScheduleController.deleteFlight_captine("MJ0001");
		System.out.println("***** 모든 스케줄 데이터 출력 ********");
		PlainScheduleController.getScheduleList();
	}
}
