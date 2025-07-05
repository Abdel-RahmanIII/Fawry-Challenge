package ecommerceSystem;

import java.time.LocalDate;

public class defaultExpirable implements Expirable{
	private LocalDate expireDate;
	
	public defaultExpirable(LocalDate expireDate) {
		// Removed to be able to test expire checkout
//		if (expireDate.isBefore(LocalDate.now())) {		// could have a minimum remaining valid days
//			throw new IllegalStateException("The product is expired");
//		}
		this.expireDate = expireDate;
	}

	@Override
	public LocalDate getExpireDate() {
		return expireDate;
	}
}
