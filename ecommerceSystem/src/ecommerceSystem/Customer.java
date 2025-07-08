package ecommerceSystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Customer {
	double balance;
	private HashMap<String, CartItem> cart;

	public Customer(double balance) {
		this.balance = balance;
		this.cart = new HashMap<String, CartItem>();
	}
	
	public void addToCart(Product p, int quantity) {
		if (quantity == 0) {
			throw new IllegalStateException("Purchase quantity can't be 0");
		}
		if (quantity > p.getQuantity()) {
			throw new IllegalStateException("Insufficient stock of: " + p.getName());
		}
		
		String name = p.getName();
		if (cart.containsKey(name)) {
			CartItem item = cart.get(name);
			item.addQuantity(quantity);
			cart.put(name, item);
		}else
			cart.put(name, new CartItem(p,quantity));
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
		List<CartItem> output = new ArrayList<CartItem>();
		
		for(CartItem item : cart.values()) {
			output.add(item);
		}
		return output;
	}
}
