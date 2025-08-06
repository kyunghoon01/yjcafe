package yjcafe.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import yjcafe.YJCafe;

public class CoffeeInfoDAO {

	Connection con = null;
	ResultSet rs = null;
	// ResultSet : 실행한 쿼리문의 값을 받는 객체
	Statement st = null;
	// Statement : 그냥 가져오는것
	PreparedStatement ps = null;
	// PreparedStatement : ?넣어서 집어넣는것

	public CoffeeInfoDAO() {
		con = YJCafe.getConnection();
	}

	public String readu_name(int u_number) {

		String u_name = new String();
		try {

			st = con.createStatement();

			String sql = "select * from 회원 where u_number = " + u_number;

			rs = st.executeQuery(sql);
			while (rs.next()) {
				u_name = rs.getString("u_name");
			}

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			dbClose();
		}
		return u_name;
	}

	public String readu_cash(int u_number) {

		String u_cash = new String();
		try {

			st = con.createStatement();

			String sql = "select * from 회원 where u_number = " + u_number;

			rs = st.executeQuery(sql);
			while (rs.next()) {
				u_cash = rs.getString("u_cash");
			}

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			dbClose();
		}
		return u_cash;
	}

	public String readk_name(int k_number) {

		  String k_name = new String();

		  try {

		    st = con.createStatement();

		    String sql = "SELECT * FROM 카페 WHERE k_number = " + k_number;

		    rs = st.executeQuery(sql);

		    while(rs.next()) {
		      k_name = rs.getString("k_name");
		    }

		  } catch (SQLException e) {
		    e.printStackTrace();
		  } finally {
		    dbClose();
		  }

		  return k_name;

		}
	public String readk_location(int k_number) {

		  String k_location = new String();

		  try {

		    st = con.createStatement();

		    String sql = "SELECT * FROM 카페 WHERE k_number = " + k_number;

		    rs = st.executeQuery(sql);

		    while(rs.next()) {
		      k_location = rs.getString("k_address");
		    }

		  } catch (SQLException e) {
		    e.printStackTrace();
		  } finally {
		    dbClose();
		  }

		  return k_location;

		}

	public String readk_open_time(int k_number) {

		  String k_open_time = new String();

		  try {

		    st = con.createStatement();

		    String sql = "SELECT * FROM 카페 WHERE k_number = " + k_number;

		    rs = st.executeQuery(sql);

		    while(rs.next()) {
		      k_open_time = rs.getString("k_open_time");
		    }

		  } catch (SQLException e) {
		    e.printStackTrace();
		  } finally {
		    dbClose();
		  }

		  return k_open_time;

		}

		public String readk_close_time(int k_number) {

		  String k_close_time = new String();

		  try {

		    st = con.createStatement();

		    String sql = "SELECT * FROM 카페 WHERE k_number = " + k_number;

		    rs = st.executeQuery(sql);

		    while(rs.next()) {
		      k_close_time = rs.getString("k_close_time");
		    }

		  } catch (SQLException e) {
		    e.printStackTrace();
		  } finally {
		    dbClose();
		  }

		  return k_close_time;

		}

		public int readm_price(int k_number) {

			  String m_price = new String();

			  try {

			    st = con.createStatement();

			    String sql = "SELECT * FROM 메뉴 WHERE k_number = " + k_number;

			    rs = st.executeQuery(sql);

			    while(rs.next()) {
			      m_price = rs.getString("m_price");
			    }

			  } catch (SQLException e) {
			    e.printStackTrace();
			  } finally {
			    dbClose();
			  }

			  return Integer.parseInt(m_price);

			}

			public String readm_name(int m_number) {

			  String m_name = new String();

			  try {

			    st = con.createStatement();

			    String sql = "SELECT * FROM 메뉴 WHERE m_number = " + m_number;

			    rs = st.executeQuery(sql);

			    while(rs.next()) {
			      m_name = rs.getString("m_name");
			    }

			  } catch (SQLException e) {
			    e.printStackTrace();
			  } finally {
			    dbClose();
			  }

			  return m_name;

			}
			public String readm_description(int m_number) {

				  String m_description = new String();

				  try {

				    st = con.createStatement();

				    String sql = "SELECT * FROM 메뉴 WHERE m_number = " + m_number;

				    rs = st.executeQuery(sql);

				    while(rs.next()) {
				      m_description = rs.getString("m_description");
				    }

				  } catch (SQLException e) {
				    e.printStackTrace();
				  } finally {
				    dbClose();
				  }

				  return m_description;

				}


	public String readc_name(int k_number) {

		String c_name = new String();
		try {

			st = con.createStatement();

			String sql = "select * from 메뉴 where k_number = " + k_number;

			rs = st.executeQuery(sql);
			while (rs.next()) {
				c_name = rs.getString("c_name");
			}

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			dbClose();
		}
		return c_name;
	}

	public int readc_rate(int c_number) {
		
		 int c_rate = 0;
		try {

			st = con.createStatement();
			
			String sql = "select * from 쿠폰 where c_number = " + c_number;
			
			rs = st.executeQuery(sql);
			while(rs.next()) {
				c_rate = rs.getInt("c_rate");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("쿼리 적용 실패");
		} finally {
			dbClose();
		}
		return c_rate;
	}

	public String readc_name(String value) {

		String c_name = new String();
		try {

			st = con.createStatement();

			String sql = "select * from 쿠폰 where c_name = " + value;

			rs = st.executeQuery(sql);
			while (rs.next()) {
				c_name = rs.getString("c_name");
			}

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			dbClose();
		}
		return c_name;
	}

	public ArrayList<MenuSubVO> readM_Data(int k_number) {

		  ArrayList<MenuSubVO> arr = new ArrayList<>();

		  try {

		    st = con.createStatement();

		    String sql = "SELECT m.m_name, m.m_description, m.m_price, m.m_total_price,m.m_number " +  
		                 "FROM 메뉴 m " +
		                 "WHERE m.k_number = " + k_number;

		    rs = st.executeQuery(sql);

		    while(rs.next()) {
		      arr.add(new MenuSubVO(rs.getString("m_name"),
		                            rs.getString("m_description"),
		                            rs.getInt("m_price"),
		                            rs.getInt("m_total_price"),
		                            rs.getInt("m_number")));
		    }

		  } catch (SQLException e) {
		    e.printStackTrace();
		  } finally {
		    dbClose();
		  }

		  return arr;

		}
	
	public ArrayList<MenuSubVO> readMM_Data(int m_number) {

		  ArrayList<MenuSubVO> arr = new ArrayList<>();

		  try {

		    st = con.createStatement();

		    String sql = "SELECT m.m_name, m.m_description, m.m_price, m.m_number " +  
		                 "FROM 메뉴 m " +
		                 "WHERE m.m_number = " + m_number;

		    rs = st.executeQuery(sql);

		    while(rs.next()) {
		      arr.add(new MenuSubVO(rs.getString("m_name"),
		                            rs.getString("m_description"),
		                            rs.getInt("m_price"),
		                            rs.getInt("m_total_price"),
		                            rs.getInt("m_number")));
		    }

		  } catch (SQLException e) {
		    e.printStackTrace();
		  } finally {
		    dbClose();
		  }

		  return arr;

		}
	public ArrayList<CouponSubVO> readC_Data(int u_number) {
	    ArrayList<CouponSubVO> arr = new ArrayList<CouponSubVO>();

	    try {
	        st = con.createStatement();

	        String sql = "SELECT c.c_number, c.c_name, c.c_rate " +
	                     "FROM 쿠폰 c " +
	                     "JOIN 쿠폰_보유상태 cs ON c.c_number = cs.c_number " +
	                     "WHERE c.c_min_date <= CURDATE() " +
	                     "AND c.c_max_date >= CURDATE() " +
	                     "AND cs.c_status = '보유중' " +
	                     "AND cs.u_number = " + u_number;

	        rs = st.executeQuery(sql);

	        while (rs.next()) {
	            arr.add(new CouponSubVO(rs.getInt("c_number"), rs.getString("c_name"), rs.getInt("c_rate")));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        dbClose();
	    }
	    return arr;
	}

	public ArrayList<MenuImageVO> readm_imagelist(int k_number) {

		  ArrayList<MenuImageVO> arr = new ArrayList<>();

		  try {

		    st = con.createStatement();

		    String sql = "SELECT 메뉴_이미지.m_image FROM 메뉴 JOIN 메뉴_이미지 ON 메뉴.m_number = 메뉴_이미지.m_number AND k_number = " + k_number;

		    rs = st.executeQuery(sql);

		    while (rs.next()) {
		      arr.add(new MenuImageVO(rs.getString("m_image")));
		    }

		  } catch (SQLException e) {
		    e.printStackTrace();
		  } finally {
		    dbClose();
		  }

		  return arr;
		}
	
	public ArrayList<MenuImageVO> readm_Imagelist(int m_number) {

		  ArrayList<MenuImageVO> arr = new ArrayList<>();

		  try {

		    st = con.createStatement();

		    String sql = "SELECT 메뉴_이미지.m_image FROM 메뉴 "
	                   + "JOIN 메뉴_이미지 ON 메뉴.m_number = 메뉴_이미지.m_number "
	                   + "WHERE 메뉴.m_number = " + m_number;
		    rs = st.executeQuery(sql);

		    while (rs.next()) {
		      arr.add(new MenuImageVO(rs.getString("m_image")));
		    }

		  } catch (SQLException e) {
		    e.printStackTrace();
		  } finally {
		    dbClose();
		  }

		  return arr;
		}

	public String readm_image(int m_number) {

		  String m_image = new String();

		  try {

		    st = con.createStatement();

		    String sql = "SELECT * FROM 메뉴_이미지 WHERE m_number = " + m_number;

		    rs = st.executeQuery(sql);

		    while(rs.next()) {
		      m_image = rs.getString("m_image");
		    }

		  } catch (SQLException e) {
		    e.printStackTrace();
		  } finally {
		    dbClose();
		  }

		  return m_image;

		}


	public String readm_count(int k_number) {

		String m_count = new String();
		try {

			st = con.createStatement();

			String sql = "select count(*) from 메뉴 where k_number = " + k_number;

			rs = st.executeQuery(sql);
			while (rs.next()) {
				m_count = rs.getString("count(*)");
			}

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			dbClose();
		}
		return m_count;
	}

		
		public int readmm_count(int k_number) {
		    int m_count = 0;

		    String sql = "SELECT COUNT(*) AS count FROM 메뉴 WHERE k_number = ?";

		    try (PreparedStatement pstmt = con.prepareStatement(sql)) {
		        pstmt.setInt(1, k_number);
		        try (ResultSet rs = pstmt.executeQuery()) {
		            if (rs.next()) {
		                m_count = rs.getInt("count");
		            }
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		    return m_count;
		}

		
		public String readM_count(int m_number) {

			  String m_count = new String();

			  try {

			    st = con.createStatement();

			    String sql = "SELECT COUNT(*) FROM 메뉴 WHERE m_number = " + m_number;

			    rs = st.executeQuery(sql);

			    while(rs.next()) {
			      m_count = rs.getString("COUNT(*)");
			    }

			  } catch (SQLException e) {
			    e.printStackTrace();
			  } finally {
			    dbClose();
			  }

			  return m_count;

			}
	
	public int readc_count(int u_number) {
	    int r_count = 0;
	    try {
	        st = con.createStatement();

	        String sql = "SELECT COUNT(*) AS count " +
	                     "FROM 쿠폰 c " +
	                     "JOIN 쿠폰_보유상태 cs ON c.c_number = cs.c_number " +
	                     "WHERE c.c_min_date <= CURDATE() " +
	                     "AND c.c_max_date >= CURDATE() " +
	                     "AND cs.c_status = '보유중' " +
	                     "AND cs.u_number = " + u_number;

	        rs = st.executeQuery(sql);

	        if (rs.next()) {
	            r_count = rs.getInt("count");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        System.out.println("쿼리 적용 실패");
	    } finally {
	        dbClose();
	    }
	    return r_count;
	}

	
	public boolean insert_reservedb(int b_guest_quantity, int total_cost,int u_number, int k_number, int m_number) {

		  String sql = "INSERT INTO 예약 (b_guest_quantity, b_payment_method, b_payment_cost, b_payment_date,b_status,u_number, k_number, m_number) " +
		               "VALUES (" + b_guest_quantity + ", '보유 포인트', " + total_cost + ", NOW(), '예약중', "+ u_number + ", " + k_number + ", " + m_number + ")";
		  
		  int count = 0;

		  try {
		    st = con.createStatement();
		    count = st.executeUpdate(sql);

		  } catch (SQLException e) {
		    e.printStackTrace();
		  } finally {
		    dbClose();
		  }

		  return count > 0;

		}
	
	public boolean insert_reservedb2(Date date, int hour,int minute) {

		  String sql = "INSERT INTO 예약_일자 (b_date,b_hour,b_minute, b_number) " +
				  "VALUES ('" + date + "', " + hour + ", " + minute + ", (SELECT MAX(b_number) FROM 예약))";
		  
		  int count = 0;

		  try {
		    st = con.createStatement();
		    count = st.executeUpdate(sql);

		  } catch (SQLException e) {
		    e.printStackTrace();
		  } finally {
		    dbClose();
		  }

		  return count > 0 ? true : false ;

		}
	public boolean update_u_cash(int totalcost , int u_number) {

		String sql = " UPDATE 회원 SET u_cash = u_cash - " + totalcost + " WHERE u_number = " + u_number;
		int count = 0;
		System.out.println("sql = " + sql);
		try {

			ps = con.prepareStatement(sql);

			count = ps.executeUpdate(sql);
			

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			dbClose();
		}
		
		return  count > 0 ? true : false ;
	}
	
	public boolean update_c_status(int couponnum , int u_number) {

		String sql = " UPDATE 쿠폰_보유상태 SET c_status = \"사용완료\" WHERE u_number = " + u_number + " and c_number = " + couponnum;
		int count = 0;
		try {

			ps = con.prepareStatement(sql);

			count = ps.executeUpdate(sql);
			

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			dbClose();
		}
		
		return  count > 0 ? true : false ;
	}

	public void dbClose() {
		try {
			
			if (st != null)
				st.close();
			if (ps != null)
				ps.close();
			if (rs != null)
				rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
