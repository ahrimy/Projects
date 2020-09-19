package rental.db;

import java.sql.Date;

public class RentalDTO {
	private int rental_num ;
    private boolean rental_usebluetooth;
    private boolean rental_usegps;
    private Date rental_startdate ;
    private int rental_reserveddays ;
    private int rental_extradays;
    private Date rental_planneddate ;
    private Date rental_returndate ;
    private long rental_estimatedprice ;
    private long rental_extracharge ;
    private int car_num ;
    private int user_num ;
	public int getRental_num() {
		return rental_num;
	}
	public void setRental_num(int rental_num) {
		this.rental_num = rental_num;
	}
	public boolean isRental_usebluetooth() {
		return rental_usebluetooth;
	}
	public void setRental_usebluetooth(boolean rental_usebluetooth) {
		this.rental_usebluetooth = rental_usebluetooth;
	}
	public boolean isRental_usegps() {
		return rental_usegps;
	}
	public void setRental_usegps(boolean rental_usegps) {
		this.rental_usegps = rental_usegps;
	}
	public Date getRental_startdate() {
		return rental_startdate;
	}
	public void setRental_startdate(Date rental_startdate) {
		this.rental_startdate = rental_startdate;
	}
	public int getRental_reserveddays() {
		return rental_reserveddays;
	}
	public void setRental_reserveddays(int rental_reserveddays) {
		this.rental_reserveddays = rental_reserveddays;
	}
	public int getRental_extradays() {
		return rental_extradays;
	}
	public void setRental_extradays(int rental_extradays) {
		this.rental_extradays = rental_extradays;
	}
	public Date getRental_planneddate() {
		return rental_planneddate;
	}
	public void setRental_planneddate(Date rental_planneddate) {
		this.rental_planneddate = rental_planneddate;
	}
	public Date getRental_returndate() {
		return rental_returndate;
	}
	public void setRental_returndate(Date rental_returndate) {
		this.rental_returndate = rental_returndate;
	}
	public long getRental_estimatedprice() {
		return rental_estimatedprice;
	}
	public void setRental_estimatedprice(long rental_estimatedprice) {
		this.rental_estimatedprice = rental_estimatedprice;
	}
	public long getRental_extracharge() {
		return rental_extracharge;
	}
	public void setRental_extracharge(long rental_extracharge) {
		this.rental_extracharge = rental_extracharge;
	}
	public int getCar_num() {
		return car_num;
	}
	public void setCar_num(int car_num) {
		this.car_num = car_num;
	}
	public int getUser_num() {
		return user_num;
	}
	public void setUser_num(int user_num) {
		this.user_num = user_num;
	}
    
    
}
