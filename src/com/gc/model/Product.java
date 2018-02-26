package com.gc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity // marks the class as a hibernate entity (mapped class)
@Table(name = "product") // maps the class to the DB table specified by the name modifier

public class Product {
	
	private int productID;
	private String code;
	private String description;
	private double listPrice;
	
	
	public Product() {

	}


	public Product(int productID, String code, String description, double listPrice) {
	
		this.productID = productID;
		this.code = code;
		this.description = description;
		this.listPrice = listPrice;
	}


	@Id // maps the primary key
	@GeneratedValue // instructs DB to generate a value for the field automatically
	@Column(name = "productID") // name is optional if your column names match your variable names
	public int getProductID() {
		return productID;
	}


	public void setProductID(int productID) {
		this.productID = productID;
	}

	@Column 
	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}

	@Column
	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}

	@Column
	public double getListPrice() {
		return listPrice;
	}


	public void setListPrice(double listPrice) {
		this.listPrice = listPrice;
	}
	
	
	

}
