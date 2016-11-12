package com.dark.rs2.content.minigames.war;

import java.util.ArrayList;

import com.dark.rs2.entity.player.Player;

public class War {

	private ArrayList<Player> playersInWar = new ArrayList<Player>();
	private int height;
	private boolean warStarted;

	public War() {
	playersInWar.clear();
	setHeight(4);
	warStarted = true;
	}

	public int getHeight() {
	return height;
	}

	public void setHeight(int height) {
	this.height = height;
	}

	public ArrayList<Player> warPlayer() {
	return playersInWar;
	}

	public void addPlayer(Player player) {
	playersInWar.add(player);
	}

	public boolean isWarStarted() {
	return warStarted;
	}

	public void setWarStarted(boolean warStarted) {
	this.warStarted = warStarted;
	}
}
