package ecommerceSystem;

public class CartItem {
	private Product product;
	private int quantity;
	
	public CartItem(Product p, int q) {
		this.product = p;
		this.quantity = q;
	}
	
	public Product getProduct() {
		return product;
	}
	public int getQuantity() {
		return quantity;
	}
	public void addQuantity(int quantity) {
		this.quantity += quantity;
	}
}
