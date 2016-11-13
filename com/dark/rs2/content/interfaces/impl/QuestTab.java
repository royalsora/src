package com.dark.rs2.content.interfaces.impl;

import com.dark.core.util.Utility;
import com.dark.rs2.content.interfaces.InterfaceHandler;
import com.dark.rs2.entity.World;
import com.dark.rs2.entity.player.Player;
import com.dark.rs2.entity.player.net.out.impl.SendColor;

/**
 * Handles the quest tab text
 * @author Daniel
 *
 */
public class QuestTab extends InterfaceHandler {
	
	public QuestTab(Player player) {
		super(player);
		color(16, 0xC71C1C);
		color(17, 0xC71C1C);
	}
	
	public void color(int id, int color) {
		player.send(new SendColor(startingLine() + id, color));
	}
	
	private final String[] text = {
			"@red@Information",
			"@or1@Online Player(s): @whi@" + World.getActivePlayers(),		
			"@or1@Time: @whi@"+ Utility.getCurrentServerTime(),
			"[View drop table]",
			"@or1@Amount Donated: @gre@$" + Utility.format(player.getMoneySpent()),
			"@or1@Donator Credits: @gre@" + Utility.format(player.getCredits()),
			"@or1@Achievement: @gre@" + Utility.format(player.getAchievementsPoints()),
			"@or1@Vote: @gre@" + Utility.format(player.getVotePoints()),
			"@or1@Bounty: @gre@" + Utility.format(player.getBountyPoints()),
			"@or1@Slayer: @gre@" + Utility.format(player.getSlayerPoints()),
			"@or1@Pest Control: @gre@" + Utility.format(player.getPestPoints()),
			"@or1@Trivia: @gre@" + Utility.format(player.getTriviaPoints()),
			"@or1@Abyssal: @gre@" + Utility.format(player.getAbyssalPoints()),	
			"@or1@You have been tasked to kill: @gre@", player.getSlayer().getAmount() + " " + player.getSlayer().getTask(),			
			"",
			"",
	};

	@Override
	protected String[] text() {
		return text;
	}

	@Override
	protected int startingLine() {
		return 29501;
	}

}

