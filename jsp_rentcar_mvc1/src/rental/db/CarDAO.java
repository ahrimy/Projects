package rental.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CarDAO {
	private CarDAO() {
	}

	private static CarDAO instance = new CarDAO();

	public static CarDAO getInstance() {
		return instance;
	}
	// db연동 클래스 선언
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	// db연동 메서드
	public Connection getConnection() {
		String url = "jdbc:mysql://localhost:3306/rentalDB?serverTimezone=UTC";
		String dbId = "root";
		String dbPw = "root";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, dbId, dbPw);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public ArrayList<CarDTO> getCarList(String car_category){
		ArrayList<CarDTO> carList= new ArrayList<>();
		// db 연동
				conn = getConnection();
				try {
					String sql = "select * from car where car_category=?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, car_category);
					rs = pstmt.executeQuery();
					while (rs.next()) {
						CarDTO temp = new CarDTO();
						temp.setCar_category(car_category);
						temp.setCar_image(rs.getString("car_image"));
						temp.setCar_isreserved(rs.getBoolean("car_isreserved"));
						temp.setCar_isused(rs.getBoolean("car_isused"));
						temp.setCar_name(rs.getString("car_name"));
						temp.setCar_num(rs.getInt("car_num"));
						temp.setCar_price(rs.getInt("car_price"));
						temp.setCar_regdate(rs.getTimestamp("car_regdate"));
						carList.add(temp);
					}

				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (conn != null) {
						try {
							conn.close();
						} catch (SQLException e) {
						}
					}
					if (pstmt != null) {
						try {
							pstmt.close();
						} catch (SQLException e) {
						}
					}
					// if(rs!=null) {try {rs.close();} catch (SQLException e) {}}
				}

		return carList;
	}
	public CarDTO getOneCar(int car_num){
		CarDTO car= new CarDTO();
		// db 연동
				conn = getConnection();
				try {
					String sql = "select * from car where car_num=?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, car_num);
					rs = pstmt.executeQuery();
					if (rs.next()) {

						car.setCar_category(rs.getString("car_category"));
						car.setCar_image(rs.getString("car_image"));
						car.setCar_isreserved(rs.getBoolean("car_isreserved"));
						car.setCar_isused(rs.getBoolean("car_isused"));
						car.setCar_name(rs.getString("car_name"));
						car.setCar_num(rs.getInt("car_num"));
						car.setCar_price(rs.getInt("car_price"));
						car.setCar_regdate(rs.getTimestamp("car_regdate"));

					}

				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (conn != null) {
						try {
							conn.close();
						} catch (SQLException e) {
						}
					}
					if (pstmt != null) {
						try {
							pstmt.close();
						} catch (SQLException e) {
						}
					}
					// if(rs!=null) {try {rs.close();} catch (SQLException e) {}}
				}

		return car;
	}
	public void rentCar(int car_num) {
		// db 연동
				conn = getConnection();
				try {
					String sql = "update car set car_isreserved = true where car_num=?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, car_num);
					pstmt.executeUpdate();
		

				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (conn != null) {
						try {
							conn.close();
						} catch (SQLException e) {
						}
					}
					if (pstmt != null) {
						try {
							pstmt.close();
						} catch (SQLException e) {
						}
					}
					// if(rs!=null) {try {rs.close();} catch (SQLException e) {}}
				}

	}
	public void finishRental(int car_num) {
		// db 연동
		conn = getConnection();
		try {
			String sql = "update car set car_isreserved = false where car_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, car_num);
			pstmt.executeUpdate();


		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			}
			// if(rs!=null) {try {rs.close();} catch (SQLException e) {}}
		}
	}
	
}
