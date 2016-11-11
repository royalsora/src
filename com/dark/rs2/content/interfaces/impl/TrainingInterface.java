package com.dark.rs2.content.interfaces.impl;

import com.dark.rs2.content.interfaces.InterfaceHandler;
import com.dark.rs2.entity.player.Player;

/**
 * Handles the training teleport interface
 * @author Daniel
 *
 */
public class TrainingInterface extends InterfaceHandler {
	
	public TrainingInterface(Player player) {
		super(player);
	}

	private final String[] text = {
			"Rock Crabs",
			"Hill Giants",
			"Skeletal Wyverns",
			"Cows",
			"Yaks",
			"Brimhaven Dung",
			"Taverly Dung",
			"Slayer Tower",
			"Lava Dragons",
			"Mithril Dragons",
	};

	@Override
	protected String[] text() {
		return text;
	}

	@Override
	protected int startingLine() {
		return 61051;
	}

}

