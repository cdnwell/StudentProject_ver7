package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

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
				StudentVO vo = new StudentVO();
				vo.setMajorName(majorName);
				vo.setMajorNo(majorNo);
				vo.setSno(sno);
				vo.setSname(sname);
				vo.setScore(score);
				list.add(vo);
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

	public int updateStudent(StudentVO vo) {
		int result = 0;
		
		String sql = "UPDATE STUDENT SET SNAME=?, MAJOR_NO=?, SCORE=? "
				+ "WHERE SNO LIKE ?";
		PreparedStatement pstmt = null;
		
		try {
			pstmt = manager.getConn().prepareStatement(sql);
			pstmt.setString(1, vo.getSname());
			pstmt.setInt(2, vo.getMajorNo());
			pstmt.setDouble(3, vo.getScore());
			pstmt.setString(4, vo.getSno());
			result = pstmt.executeUpdate();
			manager.getConn().commit();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			manager.close(pstmt, null);
		}
		
		return result;
	}

	public int deleteStudent(String sno) {
		int result = 0;
		
		PreparedStatement pstmt = null;
		String sql = "delete from student where sno like ?";
		
		try {
			pstmt = manager.getConn().prepareStatement(sql);
			pstmt.setString(1, sno);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			manager.close(pstmt, null);
		}
		
		return result;
	}

	public HashMap<String, Double> selectMajorAvgScore() {
		HashMap<String,Double> map = new HashMap<String,Double>();
		
		PreparedStatement pstmt = null;
		String sql = "SELECT M.MAJOR_NAME, TRUNC(AVG(S.SCORE),2) "
				+ "FROM STUDENT S, MAJOR M "
				+ "WHERE S.MAJOR_NO = M.MAJOR_NO(+) "
				+ "GROUP BY M.MAJOR_NAME";
		ResultSet rs = null;
		
		try {
			pstmt=manager.getConn().prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String majorName = rs.getString(1);
				double score = rs.getDouble(2);
				map.put(majorName, score);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			manager.close(pstmt, rs);
		}
		return map;
	}

	public ArrayList<HashMap<String, Object>> selectSchoolScholarship() {
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		
		String sql = "SELECT S.SNO, S.SNAME, M.MAJOR_NAME, SS.MONEY "
				+ "FROM STUDENT S, MAJOR M, STUDENT_SCHOLARSHIP SS "
				+ "WHERE S.MAJOR_NO = M.MAJOR_NO(+) AND S.SNO = SS.STUDENT_NO";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = manager.getConn().prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				HashMap<String, Object> map = new HashMap<String, Object> ();
				map.put("sno", rs.getString(1));
				map.put("sname", rs.getString(2));
				map.put("major_name", rs.getString(3));
				map.put("price", rs.getInt(4));
				
				list.add(map);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			manager.close(pstmt, rs);
		}
		
		return list;
	}

	
	
	
}
