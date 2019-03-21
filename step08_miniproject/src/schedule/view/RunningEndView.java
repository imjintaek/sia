package schedule.view;

import java.util.ArrayList;

public class RunningEndView {
	//모든 리스트 출력
	public static void dataListView(ArrayList allList){
		int length = allList.size();
		if( length != 0 ){
			for(int index = 0; index < length; index++){			
				System.out.println("검색정보 " + (index+1) + " - " + allList.get(index));
			}
		}
	}
		
	//한개 검색 데이터 출력 
	public static void dataView(Object data){
		System.out.println(data.toString());		
	}
		
	//예외 상황 출력
	public static void showError(String message){
		System.out.println(message);		
	}

	//성공 메세지 출력
	public static void showMessage(String message) {
		System.out.println(message);
	}
}
