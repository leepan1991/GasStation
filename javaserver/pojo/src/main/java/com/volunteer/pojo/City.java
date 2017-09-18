package com.volunteer.pojo;

public class City extends AbstractEntity { 
	/** 省份id **/
	private int provinceId;

	/** 城市名 **/
	private String name;

	public int getProvinceId(){
		return this.provinceId;
	}
	public void setProvinceId(int provinceId){
		this.provinceId = provinceId;
	}
	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name = name;
	}
}