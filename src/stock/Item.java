package stock;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Item {

	private final String name;
	private final double code;
	private int quantity;
	private final String expDate;

	public Item(String name, double code, int quantity, String expDate) {
		this.name = name;
		this.code = code;
		this.quantity = quantity;
		this.expDate = expDate;
	}

	public Item forQuantity(int quantity){
		return new Item(name, code, quantity, expDate);
	}

	public String getName() {
		return name;
	}

	public double getCode() {
		return code;
	}
	
	public int getQuantity() {
		return quantity;
	}

    public void setQuantity(int quantity) {
    	this.quantity = quantity;
	
}
	public String getExpDate() {
		return expDate;
	}



//	@Override
//	public boolean equals(Object o) {
//		if (this == o) return true;
//		if (o == null || getClass() != o.getClass()) return false;
//		Item item = (Item) o;
//		return Double.compare(item.code, code) == 0 &&
//				quantity == item.quantity &&
//				Objects.equals(name, item.name) &&
//				Objects.equals(expDate, item.expDate);
//	}
//
//	@Override
//	public int hashCode() {
//		return Objects.hash(name, code, quantity, expDate);
//	}

	public String toString() {
	 return "Item name " + name + ", code " + code + ", quantity " + quantity + ", experiance date " + expDate + ".";
	}

 }

