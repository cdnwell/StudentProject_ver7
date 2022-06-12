package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import config.DBManager;
import vo.CarVO;

public class CarDAO {
	private static CarDAO instance = new CarDAO();
	private DBManager manager;
	
	private CarDAO() {
		manager = DBManager.getInstance();
	}
	
	public static CarDAO getInstance() {
		if(instance == null)
			instance = new CarDAO();
		
		return instance;
	}

	public ArrayList<CarVO> selectAllCar(){
		ArrayList<CarVO> list = new ArrayList<>();
		
		String sql = "SELECT M.MAKER_NAME, C.CAR_NAME, C.PRICE "
				+ "FROM CAR C, CAR_MAKER M "
				+ "WHERE C.MAKER_NO = M.MAKER_NO";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
		pstmt = manager.getConn().prepareStatement(sql);
		rs = pstmt.executeQuery();
		
		while(rs.next()) {
			String makerName = rs.getString(1);
			String carMaker = rs.getString(2);
			int price = rs.getInt(3);
			list.add(new CarVO(makerName,carMaker,price));
		}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			
			manager.close(pstmt, rs);
		}
		
		return list;
	}

	public HashMap<String, Integer> selectCarRevenue() {
		HashMap<String,Integer> map = new HashMap<>();
		
		String sql ="SELECT C.CAR_NAME, SUM(C.PRICE) "
				+ "FROM CAR C, CAR_SELL_LIST L "
				+ "WHERE C.CAR_NO = L.CAR_NO "
				+ "GROUP BY C.CAR_NAME";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			pstmt=manager.getConn().prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				map.put(rs.getString(1),rs.getInt(2));
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			manager.close(pstmt, rs);
		}
		
		
		return map;
	}

	public HashMap<String, Integer> selectCarAvgPrice() {
		HashMap<String, Integer> map = new HashMap<>();
	
		String sql ="SELECT C.CAR_NAME ,FLOOR(AVG(C.PRICE)) "
				+ "FROM CAR C, CAR_SELL_LIST L "
				+ "WHERE C.CAR_NO = L.CAR_NO "
				+ "GROUP BY C.CAR_NAME";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt=manager.getConn().prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				String carName = rs.getString(1);
				int avgPrice = rs.getInt(2);
				map.put(carName, avgPrice);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			manager.close(pstmt, rs);
		}
		
		
		return map;
	}

	public HashMap<String, Integer> selectMostSell() {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		
		String sql = "SELECT C.CAR_NAME, COUNT(*) "
				+ "FROM CAR C, CAR_SELL_LIST L "
				+ "WHERE C.CAR_NO = L.CAR_NO "
				+ "GROUP BY C.CAR_NAME "
				+ "HAVING COUNT(*) = "
				+ "(SELECT MAX(CNT) FROM "
				+ "(SELECT COUNT(*) AS CNT FROM CAR C, CAR_SELL_LIST L "
				+ "WHERE C.CAR_NO = L.CAR_NO "
				+ "GROUP BY C.CAR_NO))";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = manager.getConn().prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String carName = rs.getString(1);
				int count = rs.getInt(2);
				
				map.put(carName, count);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			manager.close(pstmt, rs);
		}
		
		return map;
	}

	public String[] selectMostPrice() {
		String[] carInfo = new String[4];
		
		String sql ="SELECT C.CAR_NO, C.CAR_NAME, M.MAKER_NO, C.PRICE "
				+ "FROM CAR C, CAR_MAKER M "
				+ "WHERE C.MAKER_NO = M.MAKER_NO "
				+ "AND C.PRICE = (SELECT MAX(PRICE) "
				+ "FROM CAR)";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuilder sb = new StringBuilder();
		
		try {
			pstmt = manager.getConn().prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			rs.next();
			String carNo = rs.getString(1);
			String carName = rs.getString(2);
			String makerName = rs.getString(3);
			String price = rs.getString(4);
//			sb.append(rs.getInt(4));
			
			carInfo[0] = carNo;
			carInfo[1] = carName;
			carInfo[2] = makerName;
//			carInfo[3] = sb.toString();
			carInfo[3] = price;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			manager.close(pstmt, rs);
		}
		
		return carInfo;
	}
	
	
	
}
