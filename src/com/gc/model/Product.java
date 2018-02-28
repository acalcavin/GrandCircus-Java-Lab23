package com.gc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity // marks the class as a hibernate entity (mapped class)
@Table(name = "items") // maps the class to the DB table specified by the name modifier

public class Product {

	private String name;
	private String description;
	private int quantity; // new
	private double price;

	public Product() {

	}

	public Product(String name, String description, int quantity, double price) {

		this.name = name;
		this.description = description;
		this.quantity = quantity;
		this.price = price;
	}

	@Id // maps the primary key
	@Column(name = "name") // name is optional if your column names match your variable names
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Column
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String toString() {
		return "Primary key assigned as 0 is assigning the the value of null to the productID: " + name + "," + description + ", Quantity: " + quantity + ", Price: $" + price;
	}

}
