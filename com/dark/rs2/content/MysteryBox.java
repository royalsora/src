package com.dark.rs2.content;

import java.util.Arrays;

import com.dark.core.util.Utility;
import com.dark.core.util.chance.Chance;
import com.dark.core.util.chance.WeightedChance;
import com.dark.rs2.content.achievements.AchievementHandler;
import com.dark.rs2.content.achievements.AchievementList;
import com.dark.rs2.entity.World;
import com.dark.rs2.entity.item.Item;
import com.dark.rs2.entity.player.Player;
import com.dark.rs2.entity.player.net.out.impl.SendMessage;

/**
 * Handles Mystery Box rewards
 * @author Goten
 *
 */
public class MysteryBox {
	/**
	 * Mystery box Identification
	 */
	private final static Item MYSTERY_BOX = new Item(6199);
	
	/**
	 * All possible loots from Mystery Box
	 */
	public static Chance<Item> LOOTS = new Chance<Item>(Arrays.asList(
			//Common Items
			new WeightedChance<Item>(WeightedChance.COMMON, new Item(7979, 1)), //Abyssal Demon head
			new WeightedChance<Item>(WeightedChance.COMMON, new Item(7980, 1)), //KBD Heads
			new WeightedChance<Item>(WeightedChance.COMMON, new Item(7981, 1)), //KQ Head
			new WeightedChance<Item>(WeightedChance.COMMON, new Item(2724, 1)), //Hard Clue Casket
			new WeightedChance<Item>(WeightedChance.COMMON, new Item(2714, 1)), //Easy Clue Casket
			new WeightedChance<Item>(WeightedChance.COMMON, new Item(2802, 1)), //Medium Clue Casket
			new WeightedChance<Item>(WeightedChance.COMMON, new Item(19484, 500)), //Dragon Javelin
			new WeightedChance<Item>(WeightedChance.COMMON, new Item(3140, 1)), //Dragon Chainbody
			new WeightedChance<Item>(WeightedChance.COMMON, new Item(1149, 1)), //Dragon Med Helm
			new WeightedChance<Item>(WeightedChance.COMMON, new Item(4585, 1)), //Dragon Plateskirt
			new WeightedChance<Item>(WeightedChance.COMMON, new Item(4087, 1)), //Dragon Platelegs
			new WeightedChance<Item>(WeightedChance.COMMON, new Item(2944, 1)), //Golden Key
			new WeightedChance<Item>(WeightedChance.COMMON, new Item(6914, 1)), //Master Wand
			new WeightedChance<Item>(WeightedChance.COMMON, new Item(6524, 1)), //Toktz-Xil
			new WeightedChance<Item>(WeightedChance.COMMON, new Item(7462, 1)), //Barrows Gloves
			new WeightedChance<Item>(WeightedChance.COMMON, new Item(10551, 1)), //Fighter Torso
			new WeightedChance<Item>(WeightedChance.COMMON, new Item(11230, 50)), //Dragon Darts
			new WeightedChance<Item>(WeightedChance.COMMON, new Item(452, 15)), //Runite Ore
			new WeightedChance<Item>(WeightedChance.COMMON, new Item(1514, 50)), //Magic Logs
			new WeightedChance<Item>(WeightedChance.COMMON, new Item(384, 50)), //Raw Sharks
			new WeightedChance<Item>(WeightedChance.COMMON, new Item(2435, 25)), //Prayer Potion
			new WeightedChance<Item>(WeightedChance.COMMON, new Item(208, 25)), //Grimy Ranarr
			new WeightedChance<Item>(WeightedChance.COMMON, new Item(5302, 10)), //Lantadyme Seed
			new WeightedChance<Item>(WeightedChance.COMMON, new Item(11944, 10)), //Lava Dragon Bones
			new WeightedChance<Item>(WeightedChance.COMMON, new Item(990, 2)), //Crystal Key
			new WeightedChance<Item>(WeightedChance.COMMON, new Item(398, 50)), //Sea Turtle
			new WeightedChance<Item>(WeightedChance.COMMON, new Item(11937, 25)), //Dark crab
			new WeightedChance<Item>(WeightedChance.COMMON, new Item(2441, 10)), //Super strength(4)
			new WeightedChance<Item>(WeightedChance.COMMON, new Item(2437, 10)), //Super attack(4)
			new WeightedChance<Item>(WeightedChance.COMMON, new Item(2443, 10)), //Super defence(4)
			new WeightedChance<Item>(WeightedChance.COMMON, new Item(3025, 10)), //Super restore(4)
			new WeightedChance<Item>(WeightedChance.COMMON, new Item(6686, 10)), //Saradomin brew(4)
			new WeightedChance<Item>(WeightedChance.COMMON, new Item(7789, 1)), //Slayer Tome
			new WeightedChance<Item>(WeightedChance.COMMON, new Item(7783, 1)), //Agility Tome
			new WeightedChance<Item>(WeightedChance.COMMON, new Item(7798, 1)), //Woodcutting Tome
			new WeightedChance<Item>(WeightedChance.COMMON, new Item(7795, 1)), //Firemaking Tome
			new WeightedChance<Item>(WeightedChance.COMMON, new Item(7792, 1)), //Mining Tome
			new WeightedChance<Item>(WeightedChance.COMMON, new Item(7786, 1)), //Thieving Tome
			new WeightedChance<Item>(WeightedChance.COMMON, new Item(7780, 1)), //Fishing Tome
			new WeightedChance<Item>(WeightedChance.COMMON, new Item(608, 2)), //Credit Scrolls
			
			//Uncommon Items
			new WeightedChance<Item>(WeightedChance.UNCOMMON, new Item(4708, 1)), //Ahrims Hood
			new WeightedChance<Item>(WeightedChance.UNCOMMON, new Item(4710, 1)), //Ahrims Staff
			new WeightedChance<Item>(WeightedChance.UNCOMMON, new Item(4712, 1)), //Ahrims Robetop
			new WeightedChance<Item>(WeightedChance.UNCOMMON, new Item(4714, 1)), //Ahrims Robeskirt
			new WeightedChance<Item>(WeightedChance.UNCOMMON, new Item(4716, 1)), //Dharoks Helm
			new WeightedChance<Item>(WeightedChance.UNCOMMON, new Item(4718, 1)), //Dharoks Greataxe
			new WeightedChance<Item>(WeightedChance.UNCOMMON, new Item(4720, 1)), //Dharoks Platebody
			new WeightedChance<Item>(WeightedChance.UNCOMMON, new Item(4722, 1)), //Dharoks Platelegs
			new WeightedChance<Item>(WeightedChance.UNCOMMON, new Item(4724, 1)), //Guthans Helm
			new WeightedChance<Item>(WeightedChance.UNCOMMON, new Item(4726, 1)), //Guthans Spear
			new WeightedChance<Item>(WeightedChance.UNCOMMON, new Item(4728, 1)), //Guthans Platebody
			new WeightedChance<Item>(WeightedChance.UNCOMMON, new Item(4730, 1)), //Guthans Platelegs
			new WeightedChance<Item>(WeightedChance.UNCOMMON, new Item(4732, 1)), //Karils Coif
			new WeightedChance<Item>(WeightedChance.UNCOMMON, new Item(4734, 1)), //Karls X-Bow
			new WeightedChance<Item>(WeightedChance.UNCOMMON, new Item(4736, 1)), //Karils Leathertop
			new WeightedChance<Item>(WeightedChance.UNCOMMON, new Item(4738, 1)), //Karils Leatherskirt
			new WeightedChance<Item>(WeightedChance.UNCOMMON, new Item(4746, 1)), //Torags Helm
			new WeightedChance<Item>(WeightedChance.UNCOMMON, new Item(4747, 1)), //Torags Hammers
			new WeightedChance<Item>(WeightedChance.UNCOMMON, new Item(4749, 1)), //Torags Platebody
			new WeightedChance<Item>(WeightedChance.UNCOMMON, new Item(4751, 1)), //Torags Platelegs
			new WeightedChance<Item>(WeightedChance.UNCOMMON, new Item(4753, 1)), //Veracs Helm
			new WeightedChance<Item>(WeightedChance.UNCOMMON, new Item(4755, 1)), //Veracs Flail
			new WeightedChance<Item>(WeightedChance.UNCOMMON, new Item(4757, 1)), //Veracs Platebody
			new WeightedChance<Item>(WeightedChance.UNCOMMON, new Item(4759, 1)), //Veracs Platelegs
			new WeightedChance<Item>(WeightedChance.UNCOMMON, new Item(13187, 1)), //Rune package
			new WeightedChance<Item>(WeightedChance.UNCOMMON, new Item(398, 1000)), //Turtles
			new WeightedChance<Item>(WeightedChance.UNCOMMON, new Item(12696, 100)), //Super Combat Potions
			
			//Rare Items
			new WeightedChance<Item>(WeightedChance.RARE, new Item(11928, 1)), //Odium shard 1
			new WeightedChance<Item>(WeightedChance.RARE, new Item(11929, 1)), //Odium shard 2
			new WeightedChance<Item>(WeightedChance.RARE, new Item(11930, 1)), //Odium shard 3
			new WeightedChance<Item>(WeightedChance.RARE, new Item(11931, 1)), //Malediction shard 1
			new WeightedChance<Item>(WeightedChance.RARE, new Item(11932, 1)), //Malediction shard 2
			new WeightedChance<Item>(WeightedChance.RARE, new Item(11933, 1)), //Malediction shard 3
			new WeightedChance<Item>(WeightedChance.RARE, new Item(13190, 1)), //$5 Bond
			new WeightedChance<Item>(WeightedChance.RARE, new Item(749, 1)), //Zul-Andra Totem
			new WeightedChance<Item>(WeightedChance.RARE, new Item(19607, 1)), //Unstrung Heavy Ballista
			new WeightedChance<Item>(WeightedChance.RARE, new Item(19610, 1)), //Monkey Tail
			new WeightedChance<Item>(WeightedChance.RARE, new Item(12528, 1)), //Light Infinity Kit
			new WeightedChance<Item>(WeightedChance.RARE, new Item(12530, 1)), //Dark Infinity kit
			new WeightedChance<Item>(WeightedChance.RARE, new Item(6731, 1)), //Seer's ring
			new WeightedChance<Item>(WeightedChance.RARE, new Item(6733, 1)), //Archer's ring
			new WeightedChance<Item>(WeightedChance.RARE, new Item(6735, 1)), //Warrior's ring
			new WeightedChance<Item>(WeightedChance.RARE, new Item(6737, 1)), //Berserker's ring
			new WeightedChance<Item>(WeightedChance.RARE, new Item(12938, 50)), //Zul-Andra Teleports
			new WeightedChance<Item>(WeightedChance.RARE, new Item(13092, 1)), //Crystal halberd
			new WeightedChance<Item>(WeightedChance.RARE, new Item(11898, 1)), //Decorative Hat
			new WeightedChance<Item>(WeightedChance.RARE, new Item(11896, 1)), //Decorative Robetop
			new WeightedChance<Item>(WeightedChance.RARE, new Item(11897, 1)), //Decorative Robelegs
			
			//Very Rare Items
			new WeightedChance<Item>(WeightedChance.VERY_RARE, new Item(19529, 1)), //Zenyte shard
			new WeightedChance<Item>(WeightedChance.VERY_RARE, new Item(13679, 1)), //Cabbage Cape
			new WeightedChance<Item>(WeightedChance.VERY_RARE, new Item(962, 1)), //Christmas Cracker
			new WeightedChance<Item>(WeightedChance.VERY_RARE, new Item(1050, 1)), //Santa Hat
			new WeightedChance<Item>(WeightedChance.VERY_RARE, new Item(13343, 1)), //Black Santa Hat
			new WeightedChance<Item>(WeightedChance.VERY_RARE, new Item(13274, 1)), //Bludgeon Spine
			new WeightedChance<Item>(WeightedChance.VERY_RARE, new Item(13275, 1)), //Bludgeon Claw
			new WeightedChance<Item>(WeightedChance.VERY_RARE, new Item(13276, 1)), //Bludgeon Axon
			new WeightedChance<Item>(WeightedChance.VERY_RARE, new Item(13222, 1)), //Music cape
			new WeightedChance<Item>(WeightedChance.VERY_RARE, new Item(13182, 1)), //Bunny feet
			new WeightedChance<Item>(WeightedChance.VERY_RARE, new Item(1037, 1)), //Bunny ears
			new WeightedChance<Item>(WeightedChance.VERY_RARE, new Item(11900, 1)), //Decorative Legs
			new WeightedChance<Item>(WeightedChance.VERY_RARE, new Item(11899, 1)), //Decorative Top
			new WeightedChance<Item>(WeightedChance.VERY_RARE, new Item(11901, 1)), //Decorative Quiver
			new WeightedChance<Item>(WeightedChance.VERY_RARE, new Item(12873, 1)), //Guthans Set
			new WeightedChance<Item>(WeightedChance.VERY_RARE, new Item(12875, 1)), //Veracs set
			new WeightedChance<Item>(WeightedChance.VERY_RARE, new Item(12877, 1)), //Dharoks Set
			new WeightedChance<Item>(WeightedChance.VERY_RARE, new Item(12879, 1)), //Torags set
			new WeightedChance<Item>(WeightedChance.VERY_RARE, new Item(12881, 1)), //Ahrims Set
			new WeightedChance<Item>(WeightedChance.VERY_RARE, new Item(12883, 1)), //Karils set
			new WeightedChance<Item>(WeightedChance.VERY_RARE, new Item(8942, 1)) //Monkey Pet
	));
	
	/**
	 * Handles opening the Mystery Box
	 * @param player
	 */
	public static void open(Player player) {
		Item reward = LOOTS.nextObject().get(); 
		String name = reward.getDefinition().getName();
		String formatted_name = Utility.getAOrAn(name) + " " + name;
		player.getInventory().remove(MYSTERY_BOX);
		AchievementHandler.activateAchievement(player, AchievementList.OPEN_5_MBOX, 1);
		AchievementHandler.activateAchievement(player, AchievementList.OPEN_25_MBOX, 1);
		player.getInventory().addOrCreateGroundItem(reward);
		player.send(new SendMessage("You have opened the and were rewarded with " + formatted_name + " ."));
		if (reward.getDefinition().getGeneralPrice() >= 9_999_999) {
			World.sendGlobalMessage("@mbl@" + player.determineIcon(player) + " " + player.getUsername() + " has recieved " + formatted_name + " from a Mystery box!");
		}
	}

}
