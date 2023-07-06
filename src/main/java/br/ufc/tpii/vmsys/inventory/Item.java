package br.ufc.tpii.vmsys.inventory;

public class Item {
	private String name;
	private double price = 0.0;
	private int count = 0;

	public Item(String name, double price, int count) {
		this.name = name;
		if(price>=0) {
			this.price = price;
		}
		else {
			throw new IllegalArgumentException();
		}
		if(count >=0) {
			this.count = count;
		}
		else {
			throw new IllegalArgumentException();
		}
	}

	public String getName() {
		return this.name;
	}

	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) throws IllegalArgumentException{
		if(price >= 0) {
			this.price = price;
		}
		else {
			throw new IllegalArgumentException();
		}
		
	}

	public int getCount() {
		return this.count;
	}

	public void decCount() {
		if(this.count > 0) {
			this.count--;
		}
	}

	public void incCount() {
		this.count++;
	}
}