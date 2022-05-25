package controller;

import java.util.Scanner;

import exception.StudentException;
import service.StudentService;
import vo.StudentVO;

public class UpdateStudentController implements Controller {

	@Override
	public void execute(Scanner sc) {

		System.out.println("학생정보 수정을 시작합니다...........");

		System.out.print("학번 입력 : ");
		String sno = sc.nextLine();
		try {
			StudentVO vo = StudentService.getInstance().selectStudent(sno);
			System.out.print("이름 입력 : ");
			vo.setSname(sc.nextLine());
			System.out.print("학과번호 입력 : ");
			vo.setMajorNo(sc.nextInt());
			sc.nextLine();
			System.out.print("평점 입력 : ");
			vo.setScore(sc.nextDouble());
			sc.nextLine();

			StudentService.getInstance().updateStudent(vo);
		} catch (StudentException e) {
			System.out.println(e.getMessage());
		}

//		System.out.println("학생정보 수정을 시작합니다......");
//		
//		System.out.print("학번 입력 : ");
//		String sno = sc.nextLine();
//		
//		try {
//			StudentVO vo = StudentService.getInstance().selectStudent(sno);
//			System.out.print("이름 입력 : ");
//			vo.setSname(sc.nextLine());
//			System.out.print("학과번호 입력 : ");
//			vo.setMajorNo(sc.nextInt());
//			sc.nextLine();
//			System.out.print("평균 입력 : ");
//			vo.setScore(sc.nextDouble());
//			sc.nextLine();
//			
//			StudentService.getInstance().updateStudent(vo);
//		} catch (StudentException e) {
//			System.out.println(e.getMessage());
//		}

//		System.out.println("학생정보 수정을 시작합니다......");
//		StudentVO vo = null;
//		
//		System.out.print("수정 할 학번을 입력 : ");
//		String sno = sc.nextLine();
//		
//		try {
//			vo = StudentService.getInstance().selectStudentUpdate(sno);
//			while (vo == null) {
//				System.out.println("학번이 존재 하지 않습니다. 다시 입력해주세요.");
//				System.out.print("학번 입력 : ");
//				sno = sc.nextLine();
//				vo = StudentService.getInstance().selectStudentUpdate(sno);
//			}
//		} catch (StudentException e) {
//			System.out.println("학번이 존재 하지 않습니다.");
//		}
//		
//		System.out.print("이름 입력 : ");
//		String sname = sc.nextLine();
//		System.out.print("학과번호 입력 : ");
//		int majorNo = sc.nextInt();
//		sc.nextLine();
//		System.out.print("평점 입력 : ");
//		double score = sc.nextDouble();
//		sc.nextLine();
//		
//		StudentService.getInstance()
//		.updateStudent(new StudentVO(sno, sname, majorNo, null, score));

	}

}
