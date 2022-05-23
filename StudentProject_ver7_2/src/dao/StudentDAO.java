package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import config.DBManager;
import vo.StudentVO;

/*
 * db작업 여기서
 * 학생관리 dao, 학과 관리 dao... 이후에는 여러개 dao가 생성된다.
 */
public class StudentDAO {
	private static StudentDAO instance = new StudentDAO();
	private DBManager manager;
	private StudentDAO() {
		manager = DBManager.getInstance();
	}

	public static StudentDAO getInstance() {
		if(instance == null)
			instance = new StudentDAO();
		return instance;
	}
	
	public StudentVO selectStudent(String sno) {
		StudentVO vo = null;
		String sql = "select s.sno, s.sname, m.major_name, s.score "
				+ "from STUDENT s, MAJOR m where s.major_no = m.major_no and s.sno like ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = manager.getConn().prepareStatement(sql);
			pstmt.setString(1, sno);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String sname = rs.getString(2);
				String majorName = rs.getString(3);
				double score = rs.getDouble(4);
				vo = new StudentVO(sno, sname, 0, majorName, score);	
			}//하나라서 if문만 써도 된다. while써도 문제 될건 없음
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return vo;
	}
	
	public ArrayList<StudentVO> selectAllStudent(){
		ArrayList<StudentVO> list = new ArrayList<StudentVO>();
		String sql = "select s.sno, s.sname, m.major_name, s.score "
				+ "from STUDENT s, MAJOR m where s.major_no = m.major_no";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = manager.getConn().prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String sno = rs.getString(1);
				String sname = rs.getString(2);
				String majorName = rs.getString(3);
				double score = rs.getDouble(4);
				list.add(new StudentVO(sno, sname, 0, majorName, score));				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			manager.close(pstmt, rs);
		}	
		
		return list;
	}
	
	public void insertStudent() {
		
		
	}
	
	
}
