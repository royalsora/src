package com.dark.rs2.content;

import java.util.Arrays;

import com.dark.core.util.Utility;
import com.dark.core.util.chance.Chance;
import com.dark.core.util.chance.WeightedChance;
import com.dark.rs2.entity.World;
import com.dark.rs2.entity.item.Item;
import com.dark.rs2.entity.player.Player;
import com.dark.rs2.entity.player.net.out.impl.SendMessage;

/**
 * Handles Mystery Box rewards
 * @author Daniel
 *
 */
public class ChristmasCracker {
	/**
	 * Mystery box Identification
	 */
	private final static Item CRACKER = new Item(962);
	
	/**
	 * All possible loots from Christmas Cracker
	 */
	public static Chance<Item> LOOTS = new Chance<Item>(Arrays.asList(
			new WeightedChance<Item>(WeightedChance.COMMON, new Item(1038, 1)), //Red Partyhat
			new WeightedChance<Item>(WeightedChance.COMMON, new Item(1040, 1)), //Yellow Partyhat
			new WeightedChance<Item>(WeightedChance.COMMON, new Item(1042, 1)), //Blue partyhat
			new WeightedChance<Item>(WeightedChance.COMMON, new Item(1044, 1)), //Green platebody
			new WeightedChance<Item>(WeightedChance.COMMON, new Item(1046, 1)), //Purple Partyhat
			new WeightedChance<Item>(WeightedChance.COMMON, new Item(1048, 1)), //White Partyhat
			new WeightedChance<Item>(WeightedChance.VERY_RARE, new Item(11862, 1)), //Black Partyhat
			new WeightedChance<Item>(WeightedChance.VERY_RARE, new Item(11863, 1)), //Rainbow Partyhat
			new WeightedChance<Item>(WeightedChance.VERY_RARE, new Item(12399, 1)) //Partyhat & Specs

	));
	
	/**
	 * Handles opening the Mystery Box
	 * @param player
	 */
	public static void open(Player player) {
		Item reward = LOOTS.nextObject().get(); 
		String name = reward.getDefinition().getName();
		String formatted_name = Utility.getAOrAn(name) + " " + name;
		player.getInventory().remove(CRACKER);
		player.getInventory().addOrCreateGroundItem(reward);
		player.send(new SendMessage("You have opened the Cracker and were rewarded with " + formatted_name + " ."));
		if (reward.getDefinition().getGeneralPrice() >= 100_500_000) {
			World.sendGlobalMessage("@mbl@" + player.determineIcon(player) + " " + player.getUsername() + " has recieved " + formatted_name + " from a CRACKER!");
		}
	}

}
