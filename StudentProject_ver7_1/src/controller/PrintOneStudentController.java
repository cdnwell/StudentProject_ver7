package controller;

import java.util.ArrayList;
import java.util.Scanner;

import exception.StudentException;
import service.StudentService;
import vo.StudentVO;

public class PrintOneStudentController implements Controller {

	@Override
	public void execute(Scanner sc) {
		try {
			System.out.print("조회할 학번 : ");
			String selectNo = sc.nextLine();
			
			ArrayList<StudentVO> list = StudentService.getInstance().selectAllStudent();
			
			for(int i=0;i<list.size();i++) {
				if(list.get(i).getSno().equals(selectNo)) {
					System.out.println(list.get(i));
				}
			}
			
		} catch (StudentException e) {
			System.out.println(e.getMessage());
		}
		
	}

}
