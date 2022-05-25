package service;

import java.util.ArrayList;

import dao.StudentDAO;
import exception.StudentException;
import vo.StudentVO;

public class StudentService {
	private static StudentService instance = new StudentService();
	private StudentDAO dao;
	
	private StudentService() {
		//dao의 인스턴스를 받아온다.
		dao = StudentDAO.getInstance();
	}
	
	public static StudentService getInstance() {
		if(instance == null)
			instance = new StudentService();
		
		return instance;
	}

	public StudentVO selectStudent(String sno) throws StudentException{
		StudentVO vo = dao.selectStudent(sno);
		
		if(vo == null) throw new StudentException("해당 학번의 학생 정보가 존재하지 않습니다.");
		
		return vo;
	}

	public ArrayList<StudentVO> selectAllStudent() throws StudentException{
		ArrayList<StudentVO> list = dao.selectAllStudent();
		
		if(list.isEmpty()) throw new StudentException("학생 정보가 존재하지 않습니다.");
		
		return list;
	}

	public ArrayList<StudentVO> selectRankOne() throws StudentException{
		ArrayList<StudentVO> list = dao.selectRankOne();
		
		if(list.isEmpty()) throw new StudentException("학생 정보가 존재하지 않습니다.");
		
		return list;
	}
	
	
	
}
