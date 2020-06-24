package rental.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RentalDAO {
	private RentalDAO() {
	}

	private static RentalDAO instance = new RentalDAO();

	public static RentalDAO getInstance() {
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

	public void makeReservation(RentalDTO rental) {
		int num = 0;
		// db 연동
		conn = getConnection();
		try {

			String numsql = "select max(rental_num) from rental";
			pstmt = conn.prepareStatement(numsql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				num = rs.getInt(1) + 1;
			}
			String sql = "INSERT INTO rental "+
			"(rental_num,rental_usebluetooth,rental_usegps,rental_startdate,rental_reserveddays,"+
			"rental_extradays,rental_planneddate,rental_returndate,rental_estimatedprice,rental_extracharge ,car_num ,user_num) "+
			"VALUES(?,?,?,?,?,0,?,null,?,0,?,?)";
			
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, num);
			pstmt.setBoolean(2, rental.isRental_usebluetooth());
			pstmt.setBoolean(3, rental.isRental_usegps());
			pstmt.setDate(4,rental.getRental_startdate() );
			pstmt.setInt(5, rental.getRental_reserveddays());
			pstmt.setDate(6, rental.getRental_planneddate());
			pstmt.setLong(7, rental.getRental_estimatedprice());
			pstmt.setInt(8, rental.getCar_num());
			pstmt.setInt(9, rental.getUser_num());
			pstmt.executeUpdate();
			System.out.println(rental.getRental_startdate());
			System.out.println(rental.getRental_planneddate());
			
			

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
	public ArrayList<RentalDTO> getReservationList(int user_num){
		ArrayList<RentalDTO> List= new ArrayList<>();
		// db 연동
				conn = getConnection();
				try {
					String sql = "select * from rental where user_num=?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, user_num);
					rs = pstmt.executeQuery();
					while (rs.next()) {
						RentalDTO temp = new RentalDTO();
						temp.setCar_num(rs.getInt("car_num"));
						temp.setRental_num(rs.getInt("rental_num"));
						temp.setRental_startdate(rs.getDate("rental_startdate"));
						temp.setRental_planneddate(rs.getDate("rental_planneddate"));
						temp.setRental_reserveddays(rs.getInt("rental_reserveddays"));
						temp.setRental_usebluetooth(rs.getBoolean("rental_usebluetooth"));
						temp.setRental_usegps(rs.getBoolean("rental_usegps"));
						temp.setRental_estimatedprice(rs.getLong("rental_estimatedprice"));
						temp.setRental_extradays(rs.getInt("rental_extradays"));
						temp.setRental_returndate(rs.getDate("rental_returndate"));
						temp.setRental_extracharge(rs.getLong("rental_extracharge"));
						temp.setUser_num(user_num);
						List.add(temp);
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

		return List;
	}
	public RentalDTO getOneReservation(int rental_num){
		RentalDTO rental= new RentalDTO();
		// db 연동
				conn = getConnection();
				try {
					String sql = "select * from rental where rental_num=?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, rental_num);
					rs = pstmt.executeQuery();
					if (rs.next()) {
						
						rental.setCar_num(rs.getInt("car_num"));
						rental.setRental_num(rs.getInt("rental_num"));
						rental.setRental_startdate(rs.getDate("rental_startdate"));
						rental.setRental_planneddate(rs.getDate("rental_planneddate"));
						rental.setRental_reserveddays(rs.getInt("rental_reserveddays"));
						rental.setRental_usebluetooth(rs.getBoolean("rental_usebluetooth"));
						rental.setRental_usegps(rs.getBoolean("rental_usegps"));
						rental.setRental_estimatedprice(rs.getLong("rental_estimatedprice"));
						rental.setRental_extradays(rs.getInt("rental_extradays"));
						rental.setRental_returndate(rs.getDate("rental_returndate"));
						rental.setRental_extracharge(rs.getLong("rental_extracharge"));
						rental.setUser_num(rs.getInt("user_num"));
	
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

		return rental;
	}
	public void cancelReservation(int rental_num) {
		// db 연동
		conn = getConnection();
		try {
			String sql = "delete from rental where rental_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rental_num);
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
