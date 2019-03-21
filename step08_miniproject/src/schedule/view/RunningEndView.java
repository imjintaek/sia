package schedule.view;

import java.util.ArrayList;

public class RunningEndView {
	//��� ����Ʈ ���
	public static void dataListView(ArrayList allList){
		int length = allList.size();
		if( length != 0 ){
			for(int index = 0; index < length; index++){			
				System.out.println("�˻����� " + (index+1) + " - " + allList.get(index));
			}
		}
	}
		
	//�Ѱ� �˻� ������ ��� 
	public static void dataView(Object data){
		System.out.println(data.toString());		
	}
		
	//���� ��Ȳ ���
	public static void showError(String message){
		System.out.println(message);		
	}

	//���� �޼��� ���
	public static void showMessage(String message) {
		System.out.println(message);
	}
}
