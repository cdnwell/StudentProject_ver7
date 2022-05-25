package controller;

import java.util.Scanner;

import exception.StudentException;
import service.StudentService;
import vo.StudentVO;

public class SearchStudent implements Controller {

	@Override
	public void execute(Scanner sc) {
		//1.조회할 학번 입력 
		//2.try, catch문으로 감싸기, catch로 메세지 출력
		
		System.out.print("검색할 학번 입력:");
		String sno = sc.nextLine();
		
		try {
			StudentVO vo = StudentService.getInstance().selectStudent(sno);
			System.out.println(vo.toString());
		}catch(StudentException e) {
			System.out.println(e.getMessage());
		}
		
	}

}
