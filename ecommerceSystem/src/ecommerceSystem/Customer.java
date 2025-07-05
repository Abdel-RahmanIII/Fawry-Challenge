package ecommerceSystem;

import java.util.ArrayList;
import java.util.List;

public class Customer {
	double balance;
	private List<CartItem> cart;

	public Customer(double balance) {
		this.balance = balance;
		this.cart = new ArrayList<CartItem>();
	}
	
	public void addToCart(Product p, int quantity) {
		if (quantity == 0) {
			throw new IllegalStateException("Purchase quantity can't be 0");
		}
		if (quantity > p.getQuantity()) {
			throw new IllegalStateException("Insufficient stock of: " + p.getName());
		}
		cart.add(new CartItem(p,quantity));
	}

	public void setBalance(double balance) {		//Could replace by deposit/withdraw to ensure correct calculations
		if (balance < 0) {
			throw new IllegalStateException("Balance can't be negative");
		}
		this.balance = balance;
	}
	
	public double getBalance() {
		return balance;
	}
	
	public List<CartItem> getCart() {
		return cart;
	}
}
