package yjcafe.model;

public class MenuImageVO {

  public String getM_image() {
		return m_image;
	}

	public void setM_image(String m_image) {
		this.m_image = m_image;
	}

	public int getM_number() {
		return m_number;
	}

	public void setM_number(int m_number) {
		this.m_number = m_number;
	}

private String m_image;
  private int m_number;



public MenuImageVO(String m_image) {
    this.m_image = m_image;
  }
  
  // getter, setter

}