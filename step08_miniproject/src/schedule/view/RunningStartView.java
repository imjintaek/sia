package schedule.view;


import schedule.service.PlainScheduleController;

public class RunningStartView {
	public static void main(String[] args) {
		//		C
		System.out.println("***** ����� ������ ��� ********");
		PlainScheduleController.insertAirPlane("AB3905","����600");
		PlainScheduleController.insertAirPlane("CX2311","����450");
		System.out.println("***** ���� ������ ��� *********");
		PlainScheduleController.insertFlight_captain("JM0001","�����","CX2311");
		System.out.println("***** ������ ��� *********");
		PlainScheduleController.insertSchedule("AB3905","����ī","2019/03/21 04:10","A-08");
		//		R
		System.out.println("***** ��� ����� ������ ��� ********");
		PlainScheduleController.getAirPlaneList();
		System.out.println("***** ��� ���� ������ ��� ********");
		PlainScheduleController.getFlight_captainList();
		System.out.println("***** ��� ������ ������ ��� ********");
		PlainScheduleController.getScheduleList();
		System.out.println("***** ����� �ڵ� �˻� ��� ********");
		PlainScheduleController.getAirplane("QF3474");		//�����ϴ� �����Ͱ�
		PlainScheduleController.getAirplane("test3");    	//�������� �ʴ� ������ ��
		System.out.println("***** ���� �ڵ� �˻� ��� ********");
		PlainScheduleController.getFlight_captain("JH0001");	//�����ϴ� �����Ͱ�
		PlainScheduleController.getFlight_captain("AM0001");	//�������� �ʴ� ������ ��
		System.out.println("***** ������ ������ �˻� ��� ********");
		PlainScheduleController.getSchedule("����");
		PlainScheduleController.getSchedule("������");
		
		//U
		System.out.println("***** ����� ������ ���� ********");
		PlainScheduleController.updateAirPlane("AB3905","����660");
		System.out.println("***** ���� ����� ���� ********");
		PlainScheduleController.updateFlight_captine("JM0001","AB3905");
		System.out.println("***** ������ ��߽ð� ���� ******");
		PlainScheduleController.updateSchedule("AB3905","2019/03/21 08:20");
		System.out.println("***** ��� ������ ������ ��� ********");
		PlainScheduleController.getScheduleList();
		//D
		System.out.println("***** ������ ���� ******");
		PlainScheduleController.deleteSchedule("AB3905");
		System.out.println("***** ��� ������ ������ ��� ********");
		PlainScheduleController.getScheduleList();
		System.out.println("***** ��� ���� ������ ��� ********");
		PlainScheduleController.getFlight_captainList();
		System.out.println("***** ����� ������ ���� ******");
		PlainScheduleController.deleteAirPlane("CX2311");
		System.out.println("***** ���� ������ ���� ******");
		PlainScheduleController.deleteFlight_captine("JM0001");
		PlainScheduleController.deleteFlight_captine("MJ0001");
		System.out.println("***** ��� ������ ������ ��� ********");
		PlainScheduleController.getScheduleList();
	}
}
