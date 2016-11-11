package com.dark.rs2.content.interfaces.impl;

import com.dark.core.util.Utility;
import com.dark.rs2.content.interfaces.InterfaceHandler;
import com.dark.rs2.entity.player.Player;


public class PointsInterface extends InterfaceHandler {
	
	public PointsInterface(Player player) {
		super(player);
	}

	private final String[] text = {
		//"Your " + Mob.1466() + " kill count is: @red@" + Utility.format(player.getProperties().getPropertyValue("MOB_" + mob.getDefinition().getName())) + "</col>.",
		//"player.getPropertiesValue(1466, 1)()),
		"@dre@Vote: @blu@" + Utility.format(player.getVotePoints()),
		"@dre@Bounty: @blu@" + Utility.format(player.getBountyPoints()),
		"@dre@Slayer: @blu@" + Utility.format(player.getSlayerPoints()),
		"@dre@Prestige: @blu@" + Utility.format(player.getPrestigePoints()),
		"@dre@Pest Control: @blu@" + Utility.format(player.getPestPoints()),
		"@dre@Trivia: @blu@" + Utility.format(player.getTriviaPoints()),
		"@dre@Weapon Game: @blu@" + Utility.format(player.getWeaponPoints()),
		"",
		"",
		"",
		"",
		"",
		"",
		"",
		"",
		"",
		"",
		"",
		"",
		"",
		"",
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
		return 8145;
	}

}

