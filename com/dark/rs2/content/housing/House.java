package com.dark.rs2.content.housing;

import java.util.ArrayList;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@JsonIgnoreProperties(ignoreUnknown = true)
public class House {

	private String houseName;

	@SerializedName("HouseObject")
	@Expose
	private ArrayList<HouseObject> objects = new ArrayList<HouseObject>();
	private ArrayList<String> BAN_LIST = new ArrayList<String>();

	/**
	 * 
	 * @return The name
	 */
	public String getName() {
	return houseName;
	}

	/**
	 * 
	 * @param name
	 *            The name
	 */
	public void setName(String name) {
	this.houseName = name;
	}

	/**
	 * 
	 * @return The id
	 */
	public ArrayList<HouseObject> getObject() {
	return objects;
	}

	/**
	 * 
	 * @param id
	 *            The id
	 */
	public void setObject(HouseObject obj) {
	objects.add(obj);
	}

	public ArrayList<String> getBAN_LIST() {
	return BAN_LIST;
	}

	public void addToBan(String name) {
	BAN_LIST.add(name);
	}

}
