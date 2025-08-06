package yjcafe.model;

import java.sql.Date;

public class basketVO {
    public int getBasketItemId() {
		return basketItemId;
	}

	public void setBasketItemId(int basketItemId) {
		this.basketItemId = basketItemId;
	}

	public int getU_number() {
		return u_number;
	}

	public void setU_number(int u_number) {
		this.u_number = u_number;
	}

	public int getM_number() {
		return m_number;
	}

	public void setM_number(int m_number) {
		this.m_number = m_number;
	}

	public int getBasket_quantity() {
		return basket_quantity;
	}

	public void setBasket_quantity(int basket_quantity) {
		this.basket_quantity = basket_quantity;
	}

	private int basketItemId; // Corresponds to basket_item_id in the table
    private int u_number;      // Corresponds to u_number
    private int m_number;      // Corresponds to m_number
    private int basket_quantity;     // Corresponds to quantity
  // Corresponds to selected_date

    // Constructor
    public basketVO(int basketItemId, int uNumber, int mNumber, int quantity) {
        this.basketItemId = basketItemId;
        this.u_number = u_number;
        this.m_number = m_number;
        this.basket_quantity = basket_quantity;
       
    }
}