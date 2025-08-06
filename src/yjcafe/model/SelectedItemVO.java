package yjcafe.model;

public class SelectedItemVO {
    private String name;
    private int quantity;
    private int price;
    private int totalCost; // price * quantity

    public SelectedItemVO(String name, int quantity, int price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.totalCost = quantity * price; // Calculate the total cost
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        this.totalCost = this.price * quantity; // Update the total cost when quantity changes
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
        this.totalCost = price * this.quantity; // Update the total cost when price changes
    }

    public int getTotalCost() {
        return totalCost;
    }

    // Note: The total cost is derived from quantity and price, so we do not provide a setter for it.
}
