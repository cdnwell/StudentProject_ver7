package controller;

import java.util.ArrayList;
import java.util.Scanner;

import exception.StudentException;
import service.StudentService;
import vo.StudentVO;

public class PrintMajorAvgScore implements Controller {

	@Override
	public void execute(Scanner sc) {
		ArrayList<StudentVO> list = new ArrayList<>();
		System.out.println("학과별 평점의 평균을 조회합니다......");
		
//		try {
//			list = StudentService.getInstance().selectMajorAvgScore();
//		
//			
//		
//		} catch (StudentException e) {
//			System.out.println(e.getMessage());
//		}
		
	}

}
