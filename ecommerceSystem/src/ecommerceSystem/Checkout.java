package ecommerceSystem;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Checkout {
	private final int RECEIPT_WIDTH = 40;
    private final int QUANTITY_WIDTH = 4;
    private final int PRICE_WIDTH = 5;
    private final int SPACING = 2;
    private final int NAME_WIDTH = RECEIPT_WIDTH - QUANTITY_WIDTH - PRICE_WIDTH - SPACING;
    
	ShippingService shippingService = new ShippingService();
	
	public void checkout(Customer c) {
		List<CartItem> cart = c.getCart();
		
		if (cart.size() == 0) {
			throw new IllegalStateException("Cart is empty");
		}
		
		List<Shippable> items = new ArrayList<Shippable>();
		double subtotal = 0;
		double shipping = 0;
		double totalWeight = 0;
		double amount = 0;
		
		String checkoutReceipt = "** Checkout receipt **";
		int padding = (RECEIPT_WIDTH - checkoutReceipt.length()) / 2;
		checkoutReceipt = " ".repeat(Math.max(0, padding)) + checkoutReceipt + "\n";
		
		String shipmentNotice = "** Shipment notice **";
		padding = (RECEIPT_WIDTH - shipmentNotice.length()) / 2;
		shipmentNotice = " ".repeat(Math.max(0, padding)) + shipmentNotice + "\n";
		
		for(CartItem item : cart) {
			Product p = item.getProduct();
			int q = item.getQuantity();
			
			if (q > p.getQuantity()) {		// recheck availability
				throw new IllegalStateException("Insufficient stock of: " + p.getName());
			}
			if (p.isExpirable() && p.expirable.getExpireDate().isBefore(LocalDate.now())) {
				throw new IllegalStateException("Product " + p.getName() + " is expired");
			}
			// checkoutReceipt
            int total = p.getPrice() * q;
            subtotal += total;
			String name = formatName(p.getName(), NAME_WIDTH);
            
            checkoutReceipt += String.format("%-" + QUANTITY_WIDTH + "s %-" + NAME_WIDTH + "s %" + PRICE_WIDTH + "d\n",
            									q + "x", name, total);
            
            if (p.isShippable()) {
            	Shippable s = p.shippable;
            	items.add(s);
            	
            	shipping += s.getWeight() * q * 0.0005; // 0.5$ per kg
            	totalWeight += s.getWeight() * q;
            	
            	shipmentNotice += String.format("%-" + QUANTITY_WIDTH + "s %-" + NAME_WIDTH + "s %" + PRICE_WIDTH + "s\n",
                        q + "x", name, s.getWeight()+"g");
            }            
		}
		
		amount = subtotal + shipping;
		
		if (c.getBalance() < amount)
				throw new IllegalStateException("Insufficient balance");
		
		c.setBalance(c.getBalance() - amount);
        
        checkoutReceipt += "-".repeat(RECEIPT_WIDTH) + "\n";
        checkoutReceipt +=  String.format("%-" + (RECEIPT_WIDTH - PRICE_WIDTH) + "s %" + PRICE_WIDTH + ".2f\n", "Subtotal", subtotal) + 
        					String.format("%-" + (RECEIPT_WIDTH - PRICE_WIDTH) + "s %" + PRICE_WIDTH + ".2f\n", "Shipping", shipping) + 
        					String.format("%-" + (RECEIPT_WIDTH - PRICE_WIDTH) + "s %" + PRICE_WIDTH + ".2f\n", "Amount", amount);
        
        if (!items.isEmpty()) {
        	shipmentNotice += "Total package weight " + (totalWeight/1000.0) + "kg\n";
        	System.out.println(shipmentNotice);
        }
        System.out.println(checkoutReceipt);
        
        if (!items.isEmpty()) {
        	shippingService.shipItems(items);
        }
        
        for(CartItem item : cart) {
			Product p = item.getProduct();
			int q = item.getQuantity();
			
			p.consume(q);

        }
		
	}
	
	private static String formatName(String name, int maxLength) {
        if (name.length() <= maxLength) { 
        	return name;
        }
        return name.substring(0, maxLength - 3) + "...";
    }
	
	
		
	
}
