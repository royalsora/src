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
public class StrangeBox {
	/**
	 * Mystery box Identification
	 */
	private final static Item STRANGE_BOX = new Item(12789);
	
	/**
	 * All possible loots from Mystery Box
	 */
	public static Chance<Item> LOOTS = new Chance<Item>(Arrays.asList(
			//Common Items
			new WeightedChance<Item>(WeightedChance.COMMON, new Item(11840, 1)), //Dragon boots
			new WeightedChance<Item>(WeightedChance.COMMON, new Item(7789, 1)), //Slayer Tome
			new WeightedChance<Item>(WeightedChance.COMMON, new Item(7783, 1)), //Agility Tome
			new WeightedChance<Item>(WeightedChance.COMMON, new Item(7798, 1)), //Woodcutting Tome
			new WeightedChance<Item>(WeightedChance.COMMON, new Item(7795, 1)), //Firemaking Tome
			new WeightedChance<Item>(WeightedChance.COMMON, new Item(7792, 1)), //Mining Tome
			new WeightedChance<Item>(WeightedChance.COMMON, new Item(7786, 1)), //Thieving Tome
			new WeightedChance<Item>(WeightedChance.COMMON, new Item(7780, 1)), //Fishing Tome
			new WeightedChance<Item>(WeightedChance.COMMON, new Item(11335, 1)), //Dragon full helm
			new WeightedChance<Item>(WeightedChance.COMMON, new Item(3140, 1)), //Dragon chainbody
			new WeightedChance<Item>(WeightedChance.COMMON, new Item(12829, 1)), //Spirit shield
			/*
			 * Very rare items
			 */
			new WeightedChance<Item>(WeightedChance.UNCOMMON, new Item(6731, 1)), //Seers ring
			new WeightedChance<Item>(WeightedChance.UNCOMMON, new Item(6733, 1)), //Archers ring
			new WeightedChance<Item>(WeightedChance.UNCOMMON, new Item(6735, 1)), //Warrior ring
			new WeightedChance<Item>(WeightedChance.UNCOMMON, new Item(6737, 1)), //Berserker ring
			new WeightedChance<Item>(WeightedChance.UNCOMMON, new Item(6570, 1)), //Fire cape
			new WeightedChance<Item>(WeightedChance.UNCOMMON, new Item(12795, 1)), //Steam battlestaff
			
			
			new WeightedChance<Item>(WeightedChance.VERY_RARE, new Item(962, 1)), //Christmas Cracker
			new WeightedChance<Item>(WeightedChance.VERY_RARE, new Item(1050, 1)), //Santa Hat
			new WeightedChance<Item>(WeightedChance.VERY_RARE, new Item(13343, 1)), //Black Santa Hat
			new WeightedChance<Item>(WeightedChance.RARE, new Item(13274, 1)), //Bludgeon Spine
			new WeightedChance<Item>(WeightedChance.RARE, new Item(13275, 1)), //Bludgeon Claw
			new WeightedChance<Item>(WeightedChance.RARE, new Item(13276, 1)), //Bludgeon Axon
			new WeightedChance<Item>(WeightedChance.RARE, new Item(13222, 1)), //Music cape
			new WeightedChance<Item>(WeightedChance.RARE, new Item(13182, 1)), //Bunny feet
			new WeightedChance<Item>(WeightedChance.VERY_RARE, new Item(1037, 1)), //Bunny ears
			new WeightedChance<Item>(WeightedChance.RARE, new Item(11900, 1)), //Decorative Legs
			new WeightedChance<Item>(WeightedChance.RARE, new Item(11899, 1)), //Decorative Top
			new WeightedChance<Item>(WeightedChance.RARE, new Item(11901, 1)), //Decorative Quiver
			new WeightedChance<Item>(WeightedChance.RARE, new Item(19610, 1)), //Monkey Tail
			new WeightedChance<Item>(WeightedChance.RARE, new Item(19607, 1)), //Unstrung Heavy Ballista
			new WeightedChance<Item>(WeightedChance.RARE, new Item(12873, 1)), //Guthans Set
			new WeightedChance<Item>(WeightedChance.RARE, new Item(12875, 1)), //Veracs set
			new WeightedChance<Item>(WeightedChance.RARE, new Item(12877, 1)), //Dharoks Set
			new WeightedChance<Item>(WeightedChance.RARE, new Item(12879, 1)), //Torags set
			new WeightedChance<Item>(WeightedChance.RARE, new Item(12881, 1)), //Ahrims Set
			new WeightedChance<Item>(WeightedChance.RARE, new Item(12883, 1)), //Karils set
			new WeightedChance<Item>(WeightedChance.RARE, new Item(8942, 1)) //Monkey Pet

	));
	
	/**
	 * Handles opening the Mystery Box
	 * @param player
	 */
	public static void open(Player player) {
		Item reward = LOOTS.nextObject().get(); 
		String name = reward.getDefinition().getName();
		String formatted_name = Utility.getAOrAn(name) + " " + name;
		player.getInventory().remove(STRANGE_BOX);
		player.getInventory().addOrCreateGroundItem(reward);
		player.send(new SendMessage("You have opened the and were rewarded with " + formatted_name + " ."));
		if (reward.getDefinition().getGeneralPrice() >= 999_000) {
			World.sendGlobalMessage("@mbl@" + player.determineIcon(player) + " " + player.getUsername() + " has recieved " + formatted_name + " from a Elite Box!");
		}
	}

}
