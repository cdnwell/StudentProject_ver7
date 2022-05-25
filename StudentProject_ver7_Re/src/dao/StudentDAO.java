package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import config.DBManager;
import vo.StudentVO;

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
		
		String sql = "SELECT S.SNO, S.SNAME, S.MAJOR_NO, M.MAJOR_NAME, S.SCORE "
				+ "FROM STUDENT S, MAJOR M "
				+ "WHERE S.MAJOR_NO = M.MAJOR_NO(+) "
				+ "AND S.SNO LIKE ?";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = manager.getConn().prepareStatement(sql);
			pstmt.setString(1, sno);
			rs = pstmt.executeQuery();
			
			
			if (rs.next()) {
				String sname = rs.getString(2);
				int majorNo = rs.getInt(3);
				String majorName = rs.getString(4);
				double score = rs.getDouble(5);
				
				vo = new StudentVO(sno,sname,majorNo,majorName,score);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return vo;
	}

	public ArrayList<StudentVO> selectAllStudent() {
		ArrayList<StudentVO> list = new ArrayList<StudentVO>();
		
		String sql = "SELECT S.SNO, S.SNAME, S.MAJOR_NO, M.MAJOR_NAME, S.SCORE "
				+ "FROM STUDENT S, MAJOR M "
				+ "WHERE S.MAJOR_NO = M.MAJOR_NO(+)";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = manager.getConn().prepareStatement(sql);
			rs = pstmt.executeQuery();
		
			while(rs.next()) {
				String sno = rs.getString(1);
				String sname = rs.getString(2);
				int majorNo = rs.getInt(3);
				String majorName = rs.getString(4);
				double score = rs.getDouble(5);
				list.add(new StudentVO(sno,sname,majorNo,majorName,score));
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		} finally {
			manager.close(pstmt, rs);
		}
		
		return list;
	}

	public ArrayList<StudentVO> selectRankOne() {
		ArrayList<StudentVO> list = new ArrayList<>();
		String sql = "SELECT S.SNO, S.SNAME, S.MAJOR_NO, M.MAJOR_NAME, S.SCORE "
				+ "FROM STUDENT S, MAJOR M "
				+ "WHERE S.MAJOR_NO = M.MAJOR_NO(+) "
				+ "AND S.SCORE = (SELECT MAX(SCORE) FROM STUDENT)";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = manager.getConn().prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String sno = rs.getString(1);
				String sname = rs.getString(2);
				int majorNo = rs.getInt(3);
				String majorName = rs.getString(4);
				double score = rs.getDouble(5);
				
				list.add(new StudentVO(sno,sname,majorNo,majorName,score));
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			manager.close(pstmt, rs);
		}
		
		return list;
	}
	
	
	
}
