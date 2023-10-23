package org.LooseCoupling;

public class ShoppingCartEntry {
	 float price;
	 int quantity;
	 
	 public float getTotalPrice() {
		 return price*quantity;
	 }
}
