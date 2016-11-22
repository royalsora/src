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
public class OmegaEgg {
	/**
	 * Mystery box Identification
	 */
	private final static Item OMEGA = new Item(10537);
	
	/**
	 * All possible loots from egg
	 */
	public static Chance<Item> LOOTS = new Chance<Item>(Arrays.asList(
			//Common Items
			new WeightedChance<Item>(WeightedChance.COMMON, new Item(10547, 1)), //Healer Hat
			new WeightedChance<Item>(WeightedChance.COMMON, new Item(10548, 1)), //Fighter Hat
			new WeightedChance<Item>(WeightedChance.COMMON, new Item(10549, 1)), //Runner Hat
			new WeightedChance<Item>(WeightedChance.COMMON, new Item(10551, 1)), //Fighter Torso
			new WeightedChance<Item>(WeightedChance.COMMON, new Item(10552, 1)), //Runner Boots
			new WeightedChance<Item>(WeightedChance.COMMON, new Item(10553, 1)), //Penance Gloves
			new WeightedChance<Item>(WeightedChance.COMMON, new Item(10555, 1)), //Penance Skirt
			new WeightedChance<Item>(WeightedChance.UNCOMMON, new Item(13124, 1)), //Ardougne Cloak
			//Very Rare Items
			new WeightedChance<Item>(WeightedChance.VERY_RARE, new Item(12703, 1)) //Penance Queen Pet
	));
	
	/**
	 * Handles opening the Mystery Box
	 * @param player
	 */
	public static void open(Player player) {
		Item reward = LOOTS.nextObject().get(); 
		String name = reward.getDefinition().getName();
		String formatted_name = Utility.getAOrAn(name) + " " + name;
		player.getInventory().remove(OMEGA);
		player.getInventory().addOrCreateGroundItem(reward);
		player.send(new SendMessage("You have investigated the Omega egg and were rewarded with " + formatted_name + " ."));
		if (reward.getDefinition().getGeneralPrice() >= 999_999) {
			World.sendGlobalMessage("@or2@" + player.determineIcon(player) + " " + player.getUsername() + " has recieved " + formatted_name + " from an Omega Egg!");
		}
	}

}
