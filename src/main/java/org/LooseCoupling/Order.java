package org.LooseCoupling;

public class Order {
	private ShoppingCart cart;
	private float salesTax;
	
	public Order(ShoppingCart cart, float salesTax) {
		this.cart = cart;
		this.salesTax = salesTax;
	}
	
	public float orderTotalPrice() {
		return cart.getTotalPrice()*(1.0f + salesTax);
	}
}
