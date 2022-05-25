package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import dao.StudentDAO;
import exception.StudentException;
import vo.MajorVO;
import vo.StudentVO;

public class StudentService {
	private static StudentService instance = new StudentService();
	private StudentDAO dao;

	private StudentService() {
		dao = StudentDAO.getInstance();
	}

	public static StudentService getInstance() {
		if (instance == null)
			instance = new StudentService();
		return instance;
	}
	
	public ArrayList<StudentVO> selectAllStudent() throws StudentException{
		ArrayList<StudentVO> list = dao.selectAllStudent();
		
		if(list.isEmpty()) {
			//throw해서 exception 걸라고 함
			throw new StudentException("학생 데이터가 하나도 없습니다.");
		}
		
		return list;
	}
	
	public StudentVO selectStudent(String sno) throws StudentException {
		StudentVO vo = dao.selectStudent(sno);
		
		if (vo == null) throw new StudentException("검색 결과 해당 학생 정보가 없습니다.");
		
		return vo;
		
	}

	public int insertStudent(StudentVO vo) {
		
		return dao.insertStudent(vo);
	}
	
	public int updateStudent(StudentVO vo) {
		
		return dao.updateStudent(vo);
	}

	public int deleteStudent(String sno) {
		
		return dao.deleteStudent(sno);
	}
	
	public ArrayList<StudentVO> selectRankOne() throws StudentException{
		ArrayList<StudentVO> list = dao.selectRankOne();
		
		if(list.isEmpty()) {
			//throw해서 exception 걸라고 함
			throw new StudentException("학생 데이터가 하나도 없습니다.");
		}
		
		return list;
	}

	public HashMap<String, Double> selectMajorAvgScore() {

		return dao.selectMajorAvgScore();
	}

//	public ArrayList<StudentVO> selectScholarshipStudent() {
//		
//		return dao.selectScholarshipStudent();
//		
//	}

	public ArrayList<HashMap<String, Object>> selectSchoolScholarship() {
		return dao.selectSchoolScholarship();
	}

//	public Map<StudentVO,MajorVO> selectMajorAvgScore() throws StudentException{
//		Map<StudentVO,MajorVO> list = dao.selectMajorAvgScore();
//		
//		if(list.isEmpty()) {
//			throw new StudentException("학생 데이터가 하나도 없습니다.");
//		}
//		
//		return list;
//	}
	
//	public StudentVO selectStudentUpdate(String sno) throws StudentException {
//		StudentVO vo = dao.selectStudent(sno);
//		
//		if (vo != null) throw new StudentException("검색 결과 해당 학생 정보가 없습니다.");
//		
//		return vo;
//		
//	}
	
}





