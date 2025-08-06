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


public class basketDAO {
	Connection con = null;
	ResultSet rs = null;
	// ResultSet : 실행한 쿼리문의 값을 받는 객체
	Statement st = null;
	// Statement : 그냥 가져오는것
	PreparedStatement ps = null;
	// PreparedStatement : ?넣어서 집어넣는것

	public basketDAO() {
		con = YJCafe.getConnection();
	}

	
	
	public String readm_count(int k_number) {

		  String m_count ="";
		 
//		  try {
//				
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}

		  try {
			  
		    st = con.createStatement();
		    String sql = "SELECT COUNT(*) FROM 메뉴 WHERE k_number = " + k_number;
		    rs = st.executeQuery(sql);

		    while(rs.next()) {
		      m_count = rs.getString("COUNT(*)");
		    }

		  } catch (SQLException e) {
		    e.printStackTrace();
		  }finally {
			  dbClose();
		  }

		  return m_count;

		}
	
	public ArrayList<MenuSubVO> readM_Data(int k_number) {

		  ArrayList<MenuSubVO> arr = new ArrayList<>();

		  try {

		    st = con.createStatement();

		    String sql = "SELECT m.m_name, m.m_description, m.m_price,m_total_price, m.m_number " +  
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
