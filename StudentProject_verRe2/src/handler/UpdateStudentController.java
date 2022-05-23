package handler;

import java.util.Scanner;

import controller.Controller;
import exception.StudentException;
import service.StudentService;
import vo.StudentVO;

public class UpdateStudentController implements Controller {

	@Override
	public void execute(Scanner sc) {
		int result = 0 ;
		System.out.println("학생 정보를 업데이트합니다.......");
		System.out.print("학번을 적어주세요 : ");
		String sno = sc.nextLine();
		
		try {
			StudentVO vo = StudentService.getInstance().selectStudent(sno);
			System.out.print("이름 입력 : ");
			String sname = sc.nextLine();
			vo.setSname(sname);
			System.out.print("학과번호 입력 : ");
			int majorNo = sc.nextInt();
			sc.nextLine();
			vo.setMajorNo(majorNo);
			System.out.print("평점 입력 : ");
			double score = sc.nextDouble();
			sc.nextLine();
			vo.setScore(score);
			
			result = StudentService.getInstance().updateStudent(vo);
		}catch (StudentException e) {
			System.out.println(e.getMessage());
		}
		
		if(result == 0) {
			System.out.println("존재하지 않는 학번입니다.");
		}else {
			System.out.println("업데이트가 완료되었습니다.");
		}
		
	}

}
