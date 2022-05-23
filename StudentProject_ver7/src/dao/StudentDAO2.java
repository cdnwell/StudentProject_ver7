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
public class StudentDAO2 {
	private static StudentDAO2 instance = new StudentDAO2();
	private DBManager manager;
	private StudentDAO2() {
		manager =  DBManager.getInstance();
	}
	
	public static StudentDAO2 getInstance() {
		if(instance ==  null)
			instance = new StudentDAO2();
		
		return instance;
	}
	
	//학생정보를 서비스에게 그걸 컨트롤러에게 줌
	
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
	
	
	
	
	
//	public ArrayList<StudentVO> selectAllStudent(){
//		ArrayList<StudentVO> list = new ArrayList<StudentVO>();
//		//connection, prepared ... 전체 학생정보 list에 가져오기
//		String sql = "select s.sno, s.sname, m.major_name, s.score "
//				+ "from student s, major m where s.major_no = m.major_no";
//		
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try {
//			pstmt = manager.getConn().prepareStatement(sql);
//			rs = pstmt.executeQuery();
//			
//			while(rs.next()) {
//				String sno = rs.getString(1);
//				String sname = rs.getString(2);
//				String majorName = rs.getString(3);
//				double score = rs.getDouble(4);
//				list.add(new StudentVO(sno,sname,0,majorName,score));
//			}
//		} catch( SQLException e) {
//			e.printStackTrace();
//		} finally {
//			manager.close(pstmt, rs);
//		}
//		
////		try {
////		manager.getInstance().getConn();
////				
////		//sql문을 실행해서 데이터를 전부 읽어옴
////		
////		PreparedStatement stmt = conn.prepareStatement(sql);
//////		stmt.setString(1, name);
////		ResultSet rs = stmt.executeQuery();
////		
////		while(rs.next()) {
////			System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getInt(3) + " " + rs.getDouble(4) );
////		} 
////		
////		rs.close();
////		stmt.close();
////		conn.close();
////		
////		}catch (SQLException e) {
////			e.printStackTrace();
////		}
//		
//		return list;
//	}
	
	
	
}
