package rental.db;

import java.sql.Timestamp;

public class CarDTO {
	private int car_num;
    private String car_category;
    private String car_name;
    private int car_price;
    private Timestamp car_regdate;
    private boolean car_isreserved;
    private boolean car_isused;
    private String car_image;
    
	public String getCar_image() {
		return car_image;
	}
	public void setCar_image(String car_image) {
		this.car_image = car_image;
	}
	public int getCar_num() {
		return car_num;
	}
	public void setCar_num(int car_num) {
		this.car_num = car_num;
	}
	public String getCar_category() {
		return car_category;
	}
	public void setCar_category(String car_category) {
		this.car_category = car_category;
	}
	public String getCar_name() {
		return car_name;
	}
	public void setCar_name(String car_name) {
		this.car_name = car_name;
	}
	public int getCar_price() {
		return car_price;
	}
	public void setCar_price(int car_price) {
		this.car_price = car_price;
	}
	public Timestamp getCar_regdate() {
		return car_regdate;
	}
	public void setCar_regdate(Timestamp car_regdate) {
		this.car_regdate = car_regdate;
	}
	public boolean isCar_isreserved() {
		return car_isreserved;
	}
	public void setCar_isreserved(boolean car_isreserved) {
		this.car_isreserved = car_isreserved;
	}
	public boolean isCar_isused() {
		return car_isused;
	}
	public void setCar_isused(boolean car_isused) {
		this.car_isused = car_isused;
	}
    

}
