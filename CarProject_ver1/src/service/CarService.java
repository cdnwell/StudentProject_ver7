package service;

import java.util.ArrayList;
import java.util.HashMap;

import dao.CarDAO;
import exception.CarException;
import vo.CarVO;

public class CarService {
	private static CarService instance = new CarService();
	private CarDAO dao;
	
	private CarService() {
		dao = CarDAO.getInstance();
	}
	
	public static CarService getInstance() {
		if(instance == null)
			instance = new CarService();
		
		return instance;
	}
	
	public ArrayList<CarVO> selectAllCar() throws CarException {
		ArrayList <CarVO> list = dao.selectAllCar();
		
		if(list.isEmpty()) throw new CarException("차 정보가 없습니다.");
		
		return list;
	}

	public HashMap<String, Integer> selectCarRevenue() throws CarException{
		HashMap<String,Integer> map = dao.selectCarRevenue();
		
		if(map.isEmpty()) throw new CarException("데이터가 존재하지 않습니다.");
		
		return map;
	
	}

	public HashMap<String, Integer> selectCarAvgPrice() throws CarException{
		HashMap<String,Integer> map = dao.selectCarAvgPrice();
		
		if(map.isEmpty()) throw new CarException("차량 정보가 존재하지 않습니다.");
		
		return map;
	}

	public HashMap<String, Integer> selectMostSell() throws CarException{
		HashMap<String, Integer> map = dao.selectMostSell();
		
		if(map.isEmpty()) throw new CarException("차량 정보가 없습니다.");
		
		return map;
	}

	public String[] selectMostPrice() throws CarException{
		String []carInfo = dao.selectMostPrice(); 
		
		if(carInfo == null) {
			throw new CarException("차량 정보가 존재하지 않습니다.");
		}
		
		return carInfo;
	}
	
}
