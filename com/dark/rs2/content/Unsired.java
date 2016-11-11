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
public class Unsired {
	/**
	 * Mystery box Identification
	 */
	private final static Item UNSIRED = new Item(13273);
	
	/**
	 * All possible loots from unsired
	 */
	public static Chance<Item> LOOTS = new Chance<Item>(Arrays.asList(
			//Common Items
			new WeightedChance<Item>(WeightedChance.COMMON, new Item(7979, 1)), //Abyssal Head
			new WeightedChance<Item>(WeightedChance.COMMON, new Item(6199, 1)), //Mystery box
			new WeightedChance<Item>(WeightedChance.COMMON, new Item(13277, 1)), //Jar of Miasma
			new WeightedChance<Item>(WeightedChance.COMMON, new Item(4151, 1)), //Abyssal whip
			//uncommon
			new WeightedChance<Item>(WeightedChance.UNCOMMON, new Item(13265, 1)), //Abyssal Dagger
			new WeightedChance<Item>(WeightedChance.UNCOMMON, new Item(13274, 1)), //Bludgeon spine
			new WeightedChance<Item>(WeightedChance.UNCOMMON, new Item(13275, 1)), //Bludgeon claw
			new WeightedChance<Item>(WeightedChance.UNCOMMON, new Item(13276, 1)), //Bludgeon axon
			//Very Rare Items
			new WeightedChance<Item>(WeightedChance.VERY_RARE, new Item(4149, 1)) //Abyssal demon pet
	));
	
	/**
	 * Handles opening the Mystery Box
	 * @param player
	 */
	public static void open(Player player) {
		Item reward = LOOTS.nextObject().get(); 
		String name = reward.getDefinition().getName();
		String formatted_name = Utility.getAOrAn(name) + " " + name;
		player.getInventory().remove(UNSIRED);
		player.getInventory().addOrCreateGroundItem(reward);
		player.send(new SendMessage("You have inspected the unsired and were rewarded with " + formatted_name + " ."));
		if (reward.getDefinition().getGeneralPrice() >= 9_900_000) {
			World.sendGlobalMessage("@mag@" + player.determineIcon(player) + " " + player.getUsername() + " has recieved " + formatted_name + " from an unsired!");
		}
	}

}
