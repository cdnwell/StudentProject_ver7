package controller;

import java.util.Scanner;

import exception.StudentException;
import service.StudentService;
import vo.StudentVO;

public class UpdateStudentController implements Controller {

	@Override
	public void execute(Scanner sc) {
		System.out.println("학생 정보 업데이트를 시작합니다.");
		System.out.print("정보를 업데이트할 학번:");
		String sno = sc.nextLine();
		StudentVO vo = null;
		int result = 0;
		try {
			vo = StudentService.getInstance().selectStudent(sno);
			System.out.print("이름:");
			vo.setSname(sc.nextLine());
			System.out.print("학과번호:");
			vo.setMajorNo(sc.nextInt());
			sc.nextLine();
			System.out.print("평점:");
			vo.setScore(sc.nextDouble());
			sc.nextLine();

			result = StudentService.getInstance().updateStudent(vo);
		} catch (StudentException e) {
			System.out.println(e.getMessage());
		}

		if (result != 0) System.out.println("학생 정보 업데이트에 성공하였습니다.");
	}

}
