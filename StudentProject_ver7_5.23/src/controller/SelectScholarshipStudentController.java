package controller;

import java.util.ArrayList;
import java.util.Scanner;

import service.StudentService;
import vo.StudentVO;

public class SelectScholarshipStudentController implements Controller {

	@Override
	public void execute(Scanner sc) {
		ArrayList<StudentVO> list = new ArrayList<StudentVO>();
		System.out.println("장학금을 받는 학생 명단....");
		
//		list = StudentService.getInstance().selectSchoolScholarship();
		
		for(StudentVO vo : list) {
			String sno = vo.getSno();
			String sname = vo.getSname();
			int money = vo.getMajorNo();
			String majorName = vo.getMajorName();
			System.out.println(sno + " " +sname + " "+majorName+ " "+money);
		}
		
	}

}
