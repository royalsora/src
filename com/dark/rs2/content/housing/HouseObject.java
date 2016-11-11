package com.dark.rs2.content.housing;

public class HouseObject {

	private int objectId;
	private int x;
	private int y;
	private int z;
	private int face;

	public int getObjectId() {
	return objectId;
	}

	public int getX() {
	return x;
	}

	public int getY() {
	return y;
	}

	public int getZ() {
	return z;
	}

	public void setObjectId(int objectId) {
	this.objectId = objectId;
	}

	public void setX(int x) {
	this.x = x;
	}

	public void setY(int y) {
	this.y = y;
	}

	public void setZ(int z) {
	this.z = z;
	}

	public HouseObject(int objectId, int x, int y, int z, int face) {
	this.objectId = objectId;
	this.x = x;
	this.y = y;
	this.z = z;
	this.face = face;
	}

	public HouseObject(int objectId, int x, int y, int z, int type, int face) {
	this.objectId = objectId;
	this.x = x;
	this.y = y;
	this.z = z;
	}

	public int getFace() {
	return face;
	}

	public void setFace(int face) {
	this.face = face;
	}

}
