package com.dark.rs2.content.minigames.war;

import com.dark.core.util.Utility;
import com.dark.rs2.entity.World;
import com.dark.rs2.entity.item.Item;
import com.dark.rs2.entity.player.Player;

public class WarRewardHandler {

	public static void giveReward(Player player, double tokenAmount) {

	if (tokenAmount > 4000) {
		Item reward = HighRewards.HIGER_TIER.nextObject().get();
		String name = reward.getDefinition().getName();
		String formatted_name = Utility.getAOrAn(name) + " " + name;
		player.getInventory().addOrCreateGroundItem(reward);
		if (reward.getDefinition().getGeneralPrice() >= 500_000) {
			World.sendGlobalMessage("@mbl@" + player.determineIcon(player) + " " + player.getUsername() + " has recieved " + formatted_name + " from the war exchange!");

		}
	} else if (tokenAmount > 200) {
		Item reward = LowRewards.LOWER_TIER.nextObject().get();
		String name = reward.getDefinition().getName();
		String formatted_name = Utility.getAOrAn(name) + " " + name;
		player.getInventory().addOrCreateGroundItem(reward);
		if (reward.getDefinition().getGeneralPrice() >= 500_000) {
			World.sendGlobalMessage("@mbl@" + player.determineIcon(player) + " " + player.getUsername() + " has recieved " + formatted_name + " from a war exchange!");

		}
	} else {
		Item reward = LowRewards.SHIT_TIER.nextObject().get();
		String name = reward.getDefinition().getName();
		String formatted_name = Utility.getAOrAn(name) + " " + name;
		player.getInventory().addOrCreateGroundItem(reward);
		if (reward.getDefinition().getGeneralPrice() >= 500_000) {
			World.sendGlobalMessage("@mbl@" + player.determineIcon(player) + " " + player.getUsername() + " has recieved " + formatted_name + " from a war exchange!");

		}
	}
	}
}
