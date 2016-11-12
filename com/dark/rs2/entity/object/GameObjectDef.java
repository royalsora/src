package com.dark.rs2.entity.object;

import com.dark.rs2.entity.Location;


public class GameObjectDef {

	private int id, type, face;
	private Location position;

	public GameObjectDef(int id, int type, int face, Location position) {
		this.id = id;
		this.type = type;
		this.face = face;
		this.position = position;
	}

	public int getId() {
		return id;
	}

	public int getType() {
		return type;
	}

	public int getFace() {
		return face; 
	}

	public Location getPosition() {
		return position;
	}
}
