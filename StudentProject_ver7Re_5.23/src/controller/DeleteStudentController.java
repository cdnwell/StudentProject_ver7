package controller;

import java.util.Scanner;

import service.StudentService;

public class DeleteStudentController implements Controller {

	@Override
	public void execute(Scanner sc) {
		System.out.print("삭제할 학생의 학번 : ");
		String sno = sc.nextLine();
		int result;
		
		result=StudentService.getInstance().deleteStudent(sno);
		
		if(result != 0) {
			System.out.println("성공적으로 삭제되었습니다.");
		}else {
			System.out.println("삭제에 실패했습니다.");
		}
		
	}

}
