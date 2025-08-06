package yjcafe.model;

public class MenuSubVO {

	  public int getM_number() {
		return m_number;
	}

	public void setM_number(int m_number) {
		this.m_number = m_number;
	}

	public String getM_name() {
		return m_name;
	}

	public void setM_name(String m_name) {
		this.m_name = m_name;
	}

	public String getM_description() {
		return m_description;
	}

	public void setM_description(String m_description) {
		this.m_description = m_description;
	}

	public int getM_price() {
		return m_price;
	}

	public void setM_price(int m_price) {
		this.m_price = m_price;
	}

	public int getK_number() {
		return k_number;
	}

	public void setK_number(int k_number) {
		this.k_number = k_number;
	}

	  private int m_number;
	  private String m_name;  
	  private String m_description;
	  private int m_price;
	  private int k_number;
	  private int m_total_price;

	  public int getM_total_price() {
		return m_total_price;
	}

	public void setM_total_price(int m_total_price) {
		this.m_total_price = m_total_price;
	}

	public MenuSubVO(String m_name, String m_description, int m_price, int m_total_price,int m_number) {
	    this.m_name = m_name;
	    this.m_description = m_description;
	    this.m_price = m_price;
	    this.m_number = m_number;
	    this.m_total_price=m_total_price;
	  }


}