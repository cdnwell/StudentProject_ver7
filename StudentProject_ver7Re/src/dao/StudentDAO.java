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
		if (instance == null)
			instance = new StudentDAO();

		return instance;
	}

	public StudentVO selectStudent(String sno) {
		StudentVO vo = null;
		String sql = "select s.sno, s.sname, s.major_no, m.major_name, s.score "
				+ "from student s, major m where s.major_no = m.major_no "
				+ "and sno like ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = manager.getConn().prepareStatement(sql);
			pstmt.setString(1, sno);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String sname = rs.getString(2);
				int major_no = rs.getInt(3);
				String major_name = rs.getString(4);
				double score = rs.getDouble(5);
				vo = new StudentVO(sno, sname, major_no, major_name, score);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return vo;
	}
	
	public ArrayList<StudentVO> selectAllStudent() {
		ArrayList<StudentVO> list = new ArrayList<StudentVO>();
		String sql = "select s.sno, s.sname, s.major_no, m.major_name, s.score "
				+ "from student s, major m where s.major_no = m.major_no";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = manager.getConn().prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				String sno = rs.getString(1);
				String sname = rs.getString(2);
				int major_no = rs.getInt(3);
				String major_name = rs.getString(4);
				Double score = rs.getDouble(5);

				list.add(new StudentVO(sno, sname, major_no, major_name, score));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			manager.close(pstmt, rs);
		}

		return list;
	}

	public int updateStudent(StudentVO vo) {
		
		int count = 0;
		
		String sql = "update student set sname = ?, major_no = ?, score = ? "
				+ "where sno like ?";
		PreparedStatement pstmt = null;
		
		try {
			pstmt = manager.getConn().prepareStatement(sql);
			pstmt.setString(1, vo.getSname());
			pstmt.setInt(2, vo.getMajorNo());
			pstmt.setDouble(3, vo.getScore());
			pstmt.setString(4, vo.getSno());
			count = pstmt.executeUpdate();
			manager.getConn().commit();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			manager.close(pstmt, null);
		}
		
		return count;
		
	}//updateStudent
	
	public ArrayList<StudentVO> selectRankOne(){
		ArrayList<StudentVO> list = new ArrayList<StudentVO>();
		String sql = "SELECT * FROM(SELECT S.SNO, S.SNAME, S.MAJOR_NO, M.MAJOR_NAME, S.SCORE, RANK() OVER(ORDER BY S.SCORE DESC) AS RK FROM STUDENT S, MAJOR M WHERE S.MAJOR_NO = M.MAJOR_NO) WHERE RK = 1";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = manager.getConn().prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				String sno = rs.getString(1);
				String sname = rs.getString(2);
				int majorNo = rs.getInt(3);
				String majorName = rs.getString(4);
				double score = rs.getDouble(5);
				list.add(new StudentVO(sno,sname,majorNo,majorName,score));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			manager.close(pstmt, rs);
		}
		
		return list;
	}

	public int deleteStudent(String sno) {
		int count = 0;
		
		PreparedStatement pstmt = null;
		String sql = "delete from student where sno like ?";
		
		try {
			pstmt = manager.getConn().prepareStatement(sql);
			pstmt.setString(1, sno);
			count = pstmt.executeUpdate();
//			manager.getConn().commit();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			manager.close(pstmt, null);
		}
		
		return count;
	}

}
