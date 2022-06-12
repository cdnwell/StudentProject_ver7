package vo;

public class CarVO {
	private String makerName;
	private String carName;
	private int price;
	
	public CarVO(String makerName, String carName, int price) {
		this.makerName = makerName;
		this.carName = carName;
		this.price = price;
	}
	
	public String getMakerName() {
		return makerName;
	}
	public void setMakerName(String makerName) {
		this.makerName = makerName;
	}
	public String getCarName() {
		return carName;
	}
	public void setCarName(String carName) {
		this.carName = carName;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return makerName + ", " + carName + ", " + price;
	}
	
	
}
