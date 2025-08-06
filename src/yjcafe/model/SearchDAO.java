package yjcafe.model;

import java.awt.Image;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import yjcafe.YJCafe;

public class SearchDAO {
	private Connection conn;
	// 문장 전송 => SQL
	private PreparedStatement ps;
	// 연결 => 주소
	// 드라이버 등록
	ImageIcon imageIcon;
	Image image;

	public SearchDAO() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	// 연결
	public void getConnection() {
		try {
			conn = YJCafe.getConnection();
		} catch (Exception ex) {
		}
	}

	// 닫기
	public void disConnection() {
		try {
			if (ps != null)
				ps.close();
			// exit
		} catch (Exception ex) {
		}
	}

	public ArrayList<SearchVO> people(int r_people, int r_people1) { // 최소 인원, 최대 인원
		ArrayList<SearchVO> people = new ArrayList<SearchVO>();
		try {
			// 연결
			getConnection();

			String sql = "SELECT k_number, max(r_max_personnel), min(r_max_personnel) from 메뉴 group by k_number";

			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();// 실행
			while (rs.next()) {
				SearchVO vo = new SearchVO();
				vo.setR_people(rs.getInt(2));
				vo.setR_people1(rs.getInt(3));
				people.add(vo);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			disConnection();
		}
		return people;
	}
	
	

	public ArrayList<SearchVO> cost(int r_cost, int r_cost1) { // 최소 가격, 최대 가격
		ArrayList<SearchVO> cost = new ArrayList<SearchVO>();
		try {
			// 연결
			getConnection();

			String sql = "SELECT k_number, max(m_price), min(m_price) from 메뉴 group by k_number";

			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();// 실행
			while (rs.next()) {
				SearchVO vo = new SearchVO();
				vo.setR_cost(rs.getInt(2));
				vo.setR_cost1(rs.getInt(3));
				cost.add(vo);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			disConnection();
		}
		return cost;
	}
	
	public ArrayList<SearchVO> getCafeDesc(String k_description) {

		  ArrayList<SearchVO> list = new ArrayList<>();

		  try {

		    // 연결
		    getConnection();

		    String sql = "SELECT k_number, k_description FROM 카페 WHERE k_description LIKE ?";
		    
		    ps = conn.prepareStatement(sql);
		    ps.setString(1, "%" + k_description + "%");

		    ResultSet rs = ps.executeQuery();

		    while(rs.next()) {

		      SearchVO vo = new SearchVO();
		      vo.setK_number(rs.getInt(1));
		      vo.setK_description(rs.getString(2));
		      
		      list.add(vo);

		    }

		  } catch (Exception e) {
		    e.printStackTrace();
		  } finally {
		    disConnection();
		  }

		  return list;

		}
	
	public ArrayList<SearchVO> getCafeLocation(String k_location) {

		  ArrayList<SearchVO> list2 = new ArrayList<>();

		  try {

		    // 연결
		    getConnection();

		    String sql = "SELECT k_number, k_location FROM 카페 WHERE k_location LIKE ?";
		    
		    ps = conn.prepareStatement(sql);
		    ps.setString(1, "%" + k_location + "%");

		    ResultSet rs = ps.executeQuery();

		    while(rs.next()) {

		      SearchVO vo = new SearchVO();
		      vo.setK_number(rs.getInt(1));
		      vo.setK_location(rs.getString(2));
		      
		      list2.add(vo);

		    }

		  } catch (Exception e) {
		    e.printStackTrace();
		  } finally {
		    disConnection();
		  }

		  return list2;

		}
	public ArrayList<SearchVO> name(String h_name) { // 숙소 이름
		ArrayList<SearchVO> name = new ArrayList<SearchVO>();
		try {
			// 연결
			getConnection();

			String sql = "select k_name, k_image, k_number from 카페 group by k_name";

			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();// 실행
			while (rs.next()) {
				SearchVO vo = new SearchVO();
				vo.setK_name(rs.getString(1));
				vo.setK_image(rs.getString(2));
				vo.setK_number(rs.getInt(3));
				name.add(vo);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			disConnection();
		}
		return name;
	}

	
	public ArrayList<SearchVO> s_name(String k_name, int m_price) {
	    ArrayList<SearchVO> s_name = new ArrayList<SearchVO>();
	    String sql = "SELECT DISTINCT s.k_number, s.k_name, c.m_price, s.k_image, s.k_description, s.k_location "
	               + "FROM 카페 As s INNER JOIN 메뉴 AS c ON (c.k_number = s.k_number) "
	               + "WHERE s.k_name LIKE CONCAT('%', ?, '%') AND c.m_price <= ?";
	    try {
	        ps = conn.prepareStatement(sql);
	        ps.setString(1, k_name);
	        ps.setInt(2, m_price);
	        ResultSet rs = ps.executeQuery();
	        while(rs.next()) {
	            SearchVO vo = new SearchVO();
	            vo.setK_number(rs.getInt("k_number"));
	            vo.setK_name(rs.getString("k_name"));
	            vo.setM_price(rs.getInt("m_price"));
	            vo.setK_image(rs.getString("k_image"));
	            vo.setK_description(rs.getString("k_description"));
	            vo.setK_location(rs.getString("k_location"));
	            s_name.add(vo);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return s_name;
	}
	
	
	


}
