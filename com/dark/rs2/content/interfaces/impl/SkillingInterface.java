package com.dark.rs2.content.interfaces.impl;

import com.dark.rs2.content.interfaces.InterfaceHandler;
import com.dark.rs2.entity.player.Player;

/**
 * Handles the skilling teleport interface
 * @author Daniel
 *
 */
public class SkillingInterface extends InterfaceHandler {
	
	public SkillingInterface(Player player) {
		super(player);
	}

	private final String[] text = {
			"Wilderness Resource",
			"Skilling Zone",
			"Thieving",
			"Crafting",
			"Agility",
			"Mining",
			"Smithing",
			"Fishing",
			"Woodcutting",
			"Farming",
			"Skilling Zone",
			"",
			"",
			"",
			
	};

	@Override
	protected String[] text() {
		return text;
	}

	@Override
	protected int startingLine() {
		return 62051;
	}

}

