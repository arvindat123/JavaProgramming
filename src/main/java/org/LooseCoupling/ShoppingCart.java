package org.LooseCoupling;

public class ShoppingCart {
	public ShoppingCartEntry[] items;
	
	public float getTotalPrice() {
		float totalPrice = 0;
		for(ShoppingCartEntry item : items) {
			totalPrice += item.getTotalPrice();
		}
		return totalPrice;
	}
 }
