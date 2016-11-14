package com.dark.rs2.content.interfaces.impl;

import com.dark.Constants;
import com.dark.Server;
import com.dark.core.util.Utility;
import com.dark.rs2.content.interfaces.InterfaceHandler;
import com.dark.rs2.entity.World;
import com.dark.rs2.entity.player.Player;
import com.dark.rs2.entity.player.controllers.WildernessController;
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
			"@red@Game Information",
			"@or1@ Online Players: @whi@" + World.getActivePlayers(),	
			"@or1@ Online Staff: @whi@" + World.getStaff(),
			"@or1@ Time: @whi@"+ Utility.getCurrentServerTime(),
			"@or1@ Online Player Record: @whi@" + Constants.MOST_ONLINE,	
			" [View]@or1@Drop table",
			"",
			"@red@Player Information",	
			"@or1@ Username: @whi@" + Utility.capitalizeFirstLetter(player.getUsername()),
			"@or1@ Rank: " + player.determineIcon(player) + player.determineRank(player) ,
			"@or1@ Credits: @whi@" + Utility.format(player.getCredits()),
			"@or1@ Amount donated: @whi@$" + Utility.format(player.getMoneySpent()),
			"@or1@ Kills/Deaths/KDR: @whi@" + Utility.format(player.getKills()) + "/" + Utility.format(player.getDeaths()) + "/" + Utility.format((long) WildernessController.getKDR(player)),
			"@or1@ Slayer task: @whi@"+ ((player.getSlayer().getAmount() == 0 ? "" : player.getSlayer().getAmount() + " ") + (player.getSlayer().getTask() == null ? "None" : player.getSlayer().getTask())),
			" [View]@or1@Log Panel",
			" [View]@or1@Point Statistics",
			"@red@ [Points]",
			"@or1@   -Vote: @whi@" + Utility.format(player.getVotePoints()),	
                        "@or1@   -Slayer: @whi@" + Utility.format(player.getSlayerPoints()),	
			"@or1@   -Trivia: @whi@" + Utility.format(player.getTriviaPoints()),
			"@or1@   -Bounty: @whi@" + Utility.format(player.getBountyPoints()),
			"@or1@   -Abyssal: @whi@" + Utility.format(player.getAbyssalPoints()),	
			"@or1@   -Pest control: @whi@" + Utility.format(player.getPestPoints()),
                        ""
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

