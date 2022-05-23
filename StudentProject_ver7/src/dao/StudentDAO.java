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
		if (instance == null)
			instance = new StudentDAO();
		return instance;
	}

	public StudentVO selectStudent(String sno) {
		StudentVO vo = null;
		String sql = "select s.sno, s.sname, m.major_name, s.score "
				+ "from STUDENT s, MAJOR m where s.major_no = m.major_no(+) and s.sno like ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = manager.getConn().prepareStatement(sql);
			pstmt.setString(1, sno);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				String sname = rs.getString(2);
				String majorName = rs.getString(3);
				double score = rs.getDouble(4);
				vo = new StudentVO(sno, sname, 0, majorName, score);
			} // 하나라서 if문만 써도 된다. while써도 문제 될건 없음

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return vo;
	}

	public ArrayList<StudentVO> selectAllStudent() {
		ArrayList<StudentVO> list = new ArrayList<StudentVO>();
		String sql = "select s.sno, s.sname, m.major_name, s.score "
				+ "from STUDENT s, MAJOR m where s.major_no = m.major_no(+)";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = manager.getConn().prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				String sno = rs.getString(1);
				String sname = rs.getString(2);
				String majorName = rs.getString(3);
				double score = rs.getDouble(4);
				list.add(new StudentVO(sno, sname, 0, majorName, score));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			manager.close(pstmt, rs);
		}

		return list;
	}

	public int insertStudent(StudentVO vo) {
		int count = 0;

		PreparedStatement pstmt = null;
		String sql = "insert into student values(?,?,?,?)";

		try {
			pstmt = manager.getConn().prepareStatement(sql);
			pstmt.setString(1, vo.getSno());
			pstmt.setString(2, vo.getSname());
			pstmt.setInt(3, vo.getMajorNo());
			pstmt.setDouble(4, vo.getScore());
			count = pstmt.executeUpdate();
			manager.getConn().commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			manager.close(pstmt, null);
		}

		return count;

	}

	public int updateStudent(StudentVO vo) {
		int count = 0;

		PreparedStatement pstmt = null;
		String sql = "update student set sname=?,major_no=?,score=? where sno like ?";

		try {
			pstmt = manager.getConn().prepareStatement(sql);
			pstmt.setString(4, vo.getSno());
			pstmt.setString(1, vo.getSname());
			pstmt.setInt(2, vo.getMajorNo());
			pstmt.setDouble(3, vo.getScore());
			count = pstmt.executeUpdate();
			manager.getConn().commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			manager.close(pstmt, null);
		}

		return count;
	}

	public int deleteStudent(String sno) {
		int count = 0;

		PreparedStatement pstmt = null;
		String sql = "delete from student where sno like ?";
		//공백들을 날려야된다. 중요
		try {
			pstmt = manager.getConn().prepareStatement(sql);
			pstmt.setString(1, sno);
			count = pstmt.executeUpdate();
//			manager.getCon/n().commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			manager.close(pstmt, null);
		}

		return count;
		
	}
	
	public ArrayList<StudentVO> selectRankOne(){
		ArrayList<StudentVO> list = new ArrayList<StudentVO>();
		String sql = "SELECT * FROM(SELECT S.SNO, S.SNAME, M.MAJOR_NAME, S.SCORE, RANK() OVER(ORDER BY S.SCORE DESC) AS RK FROM STUDENT S, MAJOR M WHERE S.MAJOR_NO = M.MAJOR_NO ) WHERE RK = 1";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = manager.getConn().prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				String sno = rs.getString(1);
				String sname = rs.getString(2);
				String majorName = rs.getString(3);
				double score = rs.getDouble(4);
				list.add(new StudentVO(sno, sname, 0, majorName, score));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			manager.close(pstmt, rs);
		}

		return list;
	}
	
//	public int updateStudent(StudentVO vo) {
//
////		int count = 0;
////
////		PreparedStatement pstmt = null;
////		String sql = "update student set sname = ?, major_no = ?, score = ?";
////
////		try {
////			pstmt = manager.getConn().prepareStatement(sql);
////			pstmt.setString(1, vo.getSname());
////			pstmt.setInt(2, vo.getMajorNo());
////			pstmt.setDouble(3, vo.getScore());
////			count = pstmt.executeUpdate();
////			manager.getConn().commit();
////		} catch (SQLException e) {
////			e.printStackTrace();
////		} finally {
////			manager.close(pstmt, null);
////		}
////
////		return count;
//		
//		int count = 0;
//
//		PreparedStatement pstmt = null;
//		String sql = "update student set sname =?, major_no = ?, score =?, where sno like ?";
//
//		try {
//			pstmt = manager.getConn().prepareStatement(sql);
//			pstmt.setString(4, vo.getSno());
//			pstmt.setString(1, vo.getSname());
//			pstmt.setInt(2, vo.getMajorNo());
//			pstmt.setDouble(3, vo.getScore());
//			count = pstmt.executeUpdate();
//			manager.getConn().commit();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			manager.close(pstmt, null);
//		}
//
//		return count;
//		
//	}

}
