package com.test.entity;

import java.io.Serializable;

public class Goods implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private String content;
	private String pic;
	private Double price;
	
	
	public Goods(String id, String name, String content, String pic, Double price) {
		super();
		this.id = id;
		this.name = name;
		this.content = content;
		this.pic = pic;
		this.price = price;
	}
	public Goods() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Goods [id=" + id + ", name=" + name + ", content=" + content + ", pic=" + pic + ", price=" + price
				+ "]";
	}
	
	
}
