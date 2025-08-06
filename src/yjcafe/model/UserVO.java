package yjcafe.model;

import java.sql.Date;

public class UserVO {

	
	  public int getU_number() {
		return u_number;
	}
	public void setU_number(int i) {
		this.u_number = i;
	}
	public String getU_name() {
		return u_name;
	}
	public void setU_name(String u_name) {
		this.u_name = u_name;
	}
	public String getU_id() {
		return u_id;
	}
	public void setU_id(String u_id) {
		this.u_id = u_id;
	}
	public String getU_password() {
		return u_password;
	}
	public void setU_password(String u_password) {
		this.u_password = u_password;
	}
	public String getU_phone_number() {
		return u_phone_number;
	}
	public void setU_phone_number(String u_phone_number) {
		this.u_phone_number = u_phone_number;
	}
	public Date getU_birthday() {
		return u_birthday;
	}
	public void setU_birthday(Date u_birthday) {
		this.u_birthday = u_birthday;
	}
	public String getU_email() {
		return u_email;
	}
	public void setU_email(String u_email) {
		this.u_email = u_email;
	}
	public int getU_cash() {
		return u_cash;
	}
	public void setU_cash(int u_cash) {
		this.u_cash = u_cash;
	}
	public Date getU_enroll_date() {
		return u_enroll_date;
	}
	public void setU_enroll_date(Date u_enroll_date) {
		this.u_enroll_date = u_enroll_date;
	}
	public String getU_profile_image() {
		return u_profile_image;
	}
	public void setU_profile_image(String u_profile_image) {
		this.u_profile_image = u_profile_image;
	}
	public String getU_department() {
		return u_department;
	}
	public void setU_department(String u_department) {
		this.u_department = u_department;
	}
	  private int u_number;
	private String u_name;
	  private String u_id;
	  private String u_password;
	  private String u_phone_number;
	  private Date u_birthday;
	  private String u_email;
	  private int u_cash;
	  private Date u_enroll_date;
	  private String u_profile_image;
	  private String u_department;
}