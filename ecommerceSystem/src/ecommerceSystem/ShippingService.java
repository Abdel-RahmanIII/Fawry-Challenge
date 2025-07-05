package ecommerceSystem;

import java.util.List;

public class ShippingService {
	
	public void shipItems(List<Shippable> items) {
		for(Shippable item : items) {
			System.out.println("Shipped item " + item.getName() + " weighing " + item.getWeight() + "g successfully");

		}
	}
}
