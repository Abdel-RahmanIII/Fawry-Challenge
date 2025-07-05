package ecommerceSystem;

public class defaultShippable implements Shippable{
	private String name;
	private double weight;
	
	public defaultShippable(String name, double weight) {
		if (weight <= 0) {
			throw new IllegalStateException("Weight must be greater that 0");
		}
		this.name = name;
		this.weight = weight;
	}
	
	@Override
	public String getName() {
		return name;
	}
	@Override
	public double getWeight() {
		return weight;
	}

}
