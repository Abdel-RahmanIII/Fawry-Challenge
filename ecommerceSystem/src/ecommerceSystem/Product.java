package ecommerceSystem;

import java.time.LocalDate;

public class Product {
	private String name;
	private int price;
	private int quantity;
	
	Shippable shippable;
	Expirable expirable;
	
	public Product(String name, int price, int quantity) {
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}
	
	public void consume(int quantity) {
		if (quantity <= 0)
			throw new  IllegalStateException("consumption can't be less that 1");
		if (this.quantity < quantity)
			throw new  IllegalStateException("Insufficient stock of: " + name);

		this.quantity -= quantity;
	}
	
	public void setShippable(double weight) {
		this.shippable = new defaultShippable(name, weight);
	}
	
	public void setExpirable(LocalDate expireDate) {
		this.expirable = new defaultExpirable(expireDate);
	}
	
	public boolean isShippable() {
		return shippable != null;
	}
	
	public boolean isExpirable() {
		return expirable != null;
	}

	public String getName() {
		return name;
	}
	public int getPrice() {
		return price;
	}
	public int getQuantity() {
		return quantity;
	}
	
	
}
