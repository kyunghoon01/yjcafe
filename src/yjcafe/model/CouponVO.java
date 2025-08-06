package yjcafe.model;

import java.sql.Date;
import java.time.LocalDate;

public class CouponVO {
	// 가공되지않은 DB 필드값
	public int coupon_id;
	public String serial_number; // char16
	public String name; // varchar30
	public String display;
	public double rate;
	public Date min_date;
	public Date max_date;
	public int min_age;
	public int max_age;
	public int min_cost;
	public Date min_enroll_date;
	public Date max_enroll_date;

	// DB 필드값을 가공해서 리턴하는 부분
	// 기간
	public String getPeriod() {
		if (min_date == null || max_date == null)
			return "날짜를 불러올 수 없습니다.";
		return min_date.toString() + " ~ " + max_date.toString();
	}

	// 툴팁
	 public String getTooltip() {
	        StringBuilder tooltip = new StringBuilder();

	        if (min_age != 0) {
	            tooltip.append(",").append(min_age).append("세 이상");
	        }
	        if (max_age != 0) {
	            tooltip.append(",").append(max_age).append("세 이하");
	        }
	        if (min_cost != 0) {
	            tooltip.append(",").append(min_cost).append("원 이상");
	        }
	        if (min_enroll_date != null) {
	            tooltip.append(",가입일 ").append(min_enroll_date.toString()).append(" 이상");
	        }
	        if (max_enroll_date != null) {
	            tooltip.append(",가입일 ").append(max_enroll_date.toString()).append(" 이하");
	        }

	        if (tooltip.length() > 0) {
	            tooltip.append(" 사용가능");
	            return tooltip.substring(1);
	        }

	        return "조건없음";
	    }
}