package controller;

public class HandlerMapping {
	private static HandlerMapping instance = new HandlerMapping();
	
	private HandlerMapping() {	}
	
	public static HandlerMapping getInstance() {
		if(instance == null)
			instance = new HandlerMapping();
		
		return instance;
	}
	
	public Controller createController(int no) {
		Controller controller = null;
		
		switch(no) {
		case 1:
			controller = new CarInfoPrintAll();
			break;
		case 2:
			controller = new CarEaPricePrint();
			break;
		case 3:
			controller = new CarAvgPrice();
			break;
		case 4:
			controller = new CarMostSell();
			break;
		case 5:
			controller = new CarMostPrice();
			break;
		}
		
		return controller;
	}
	
}
