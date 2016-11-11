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
public class PrizeBox {
	/**
	 * Mystery box Identification
	 */
	private final static Item FREEDOM = new Item(6831);
	
	/**
	 * All possible loots from unsired
	 */
	public static Chance<Item> LOOTS = new Chance<Item>(Arrays.asList(
			//Common Items7806
			new WeightedChance<Item>(WeightedChance.COMMON, new Item(1618, 100)), //uncut diamond
			new WeightedChance<Item>(WeightedChance.COMMON, new Item(1620, 100)), //uncut ruby
			new WeightedChance<Item>(WeightedChance.COMMON, new Item(1624, 100)), //uncut sapphire
			new WeightedChance<Item>(WeightedChance.COMMON, new Item(2645, 1)), //Red Headband
			new WeightedChance<Item>(WeightedChance.COMMON, new Item(12299, 1)), //White Headband						new WeightedChance<Item>(WeightedChance.COMMON, new Item(12301, 1)), //Blue Headband
			new WeightedChance<Item>(WeightedChance.COMMON, new Item(12247, 1)), //Red Beret
			new WeightedChance<Item>(WeightedChance.COMMON, new Item(2637, 1)), //White Beret
			new WeightedChance<Item>(WeightedChance.COMMON, new Item(2633, 1)), //Blue Beret
			new WeightedChance<Item>(WeightedChance.COMMON, new Item(10386, 1)), //Sara d'hide body
			new WeightedChance<Item>(WeightedChance.COMMON, new Item(10388, 1)), //Sara d'hide chaps
			new WeightedChance<Item>(WeightedChance.COMMON, new Item(10370, 1)), //Zamorak d'hide body
			new WeightedChance<Item>(WeightedChance.COMMON, new Item(10372, 1)), //Zamorak d'hide chaps
			new WeightedChance<Item>(WeightedChance.COMMON, new Item(12508, 1)), //Arma d'hide body
			new WeightedChance<Item>(WeightedChance.COMMON, new Item(12510, 1)), //Arma d'hide chaps

			//Uncommon Items
			new WeightedChance<Item>(WeightedChance.UNCOMMON, new Item(11840, 1)), //Dragon Boots
			new WeightedChance<Item>(WeightedChance.UNCOMMON, new Item(6920, 1)), //Infinity Boots
			new WeightedChance<Item>(WeightedChance.UNCOMMON, new Item(6619, 1)), //White Boots
			new WeightedChance<Item>(WeightedChance.UNCOMMON, new Item(537, 100)), //Dragon Bones
			new WeightedChance<Item>(WeightedChance.UNCOMMON, new Item(2508, 100)), //Red Dragonhide
			new WeightedChance<Item>(WeightedChance.UNCOMMON, new Item(9381, 500)), //Runite bolts
			new WeightedChance<Item>(WeightedChance.UNCOMMON, new Item(10394, 1)), //Flared trousers
			new WeightedChance<Item>(WeightedChance.UNCOMMON, new Item(5046, 1)), //Blue Shorts
			new WeightedChance<Item>(WeightedChance.UNCOMMON, new Item(10879, 1)), //Red Satchel
			
			//Rare Items
			new WeightedChance<Item>(WeightedChance.RARE, new Item(6739, 1)), //Dragon hatchet
			new WeightedChance<Item>(WeightedChance.RARE, new Item(1052, 1)), //Cape of Legends
			new WeightedChance<Item>(WeightedChance.RARE, new Item(12637, 1)), //Sara Halo
			new WeightedChance<Item>(WeightedChance.RARE, new Item(12638, 1)), //Zammy Halo
			new WeightedChance<Item>(WeightedChance.RARE, new Item(9470, 1)), //Gnome scarf
			new WeightedChance<Item>(WeightedChance.RARE, new Item(4214, 1)), //Crystal bow
			new WeightedChance<Item>(WeightedChance.RARE, new Item(10466, 1)), //Saradomin Cloak
			new WeightedChance<Item>(WeightedChance.RARE, new Item(10450, 1)), //Zamorak Cloak
			new WeightedChance<Item>(WeightedChance.RARE, new Item(6818, 1)), //Bowsword
			
			//Very Rare Items
			new WeightedChance<Item>(WeightedChance.VERY_RARE, new Item(6858, 1)), //Jester Scarf
			new WeightedChance<Item>(WeightedChance.VERY_RARE, new Item(7807, 1)), //Anger Baxe
			new WeightedChance<Item>(WeightedChance.VERY_RARE, new Item(7806, 1)), //Anger sword
			new WeightedChance<Item>(WeightedChance.VERY_RARE, new Item(7808, 1)), //Anger mace
			new WeightedChance<Item>(WeightedChance.VERY_RARE, new Item(2994, 1)), //Blue Chompy
			new WeightedChance<Item>(WeightedChance.VERY_RARE, new Item(2992, 1)), //Red Chompy
			new WeightedChance<Item>(WeightedChance.VERY_RARE, new Item(2990, 1)) //White Chompy

	));
	
	/**
	 * Handles opening the Mystery Box
	 * @param player
	 */
	public static void open(Player player) {
		Item reward = LOOTS.nextObject().get(); 
		String name = reward.getDefinition().getName();
		String formatted_name = Utility.getAOrAn(name) + " " + name;
		player.getInventory().remove(FREEDOM);
		player.getInventory().addOrCreateGroundItem(reward);
		player.send(new SendMessage("You have opened the box and were rewarded with " + formatted_name + " ."));
		if (reward.getDefinition().getGeneralPrice() >= 9_900_000) {
			World.sendGlobalMessage("@blu@" + player.determineIcon(player) + " " + player.getUsername() + " has recieved " + formatted_name + " from a Freedom Box!");
		}
	}

}
