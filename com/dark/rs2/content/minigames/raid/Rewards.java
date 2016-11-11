package com.dark.rs2.content.minigames.raid;

import java.util.Arrays;

import com.dark.core.util.Utility;
import com.dark.core.util.chance.Chance;
import com.dark.core.util.chance.WeightedChance;
import com.dark.rs2.content.minigames.raid.rounds.RoundHandler;
import com.dark.rs2.entity.World;
import com.dark.rs2.entity.item.Item;
import com.dark.rs2.entity.player.Player;
import com.dark.rs2.entity.player.net.out.impl.SendMessage;

public class Rewards {

	public static Chance<Item> HIGER_TIER = new Chance<Item>(Arrays.asList(
			// Common Items
			new WeightedChance<Item>(WeightedChance.COMMON, new Item(11840, 1)), // Dragon
																					// boots
			new WeightedChance<Item>(WeightedChance.COMMON, new Item(12821, 1)), // Spectral
			new WeightedChance<Item>(WeightedChance.COMMON, new Item(11806, 1)), // Saradomin
																					// godsword
			new WeightedChance<Item>(WeightedChance.COMMON, new Item(11808, 1)), // Zamorak
																					// godsword
			new WeightedChance<Item>(WeightedChance.COMMON, new Item(4152, 2)), // x2
																				// whips
			new WeightedChance<Item>(WeightedChance.COMMON, new Item(11838, 1)), // sara
																					// sword
			new WeightedChance<Item>(WeightedChance.COMMON, new Item(995, 40000000)), // 40m
																						// coins
			new WeightedChance<Item>(WeightedChance.RARE, new Item(12927, 1)), // Serpentine
																				// visage
			new WeightedChance<Item>(WeightedChance.RARE, new Item(11286, 1)), // Draconic
																				// visage
			new WeightedChance<Item>(WeightedChance.VERY_RARE, new Item(10334, 1)), // 3a
																					// range
																					// coif
			new WeightedChance<Item>(WeightedChance.VERY_RARE, new Item(10336, 1)), // 3rd
																					// age
																					// vambraces
			new WeightedChance<Item>(WeightedChance.VERY_RARE, new Item(11802, 1)), // Armadyl
																					// godsword
			new WeightedChance<Item>(WeightedChance.VERY_RARE, new Item(11804, 1)), // Bandos
																					// godsword
			new WeightedChance<Item>(WeightedChance.VERY_RARE, new Item(10340, 1)), // 3rd
																					// age
																					// robe
			new WeightedChance<Item>(WeightedChance.VERY_RARE, new Item(10342, 1)), // 3rd
																					// age
																					// mage
																					// hat
			new WeightedChance<Item>(WeightedChance.VERY_RARE, new Item(13188, 1)), // Dragon
																					// Claws
			new WeightedChance<Item>(WeightedChance.VERY_RARE, new Item(10330, 1)), // 3a
																					// range
																					// top
			new WeightedChance<Item>(WeightedChance.VERY_RARE, new Item(10332, 1)), // 3a
																					// range
																					// bottom
			new WeightedChance<Item>(WeightedChance.VERY_RARE, new Item(10346, 1)), // 3a
																					// range
																					// bottom
			new WeightedChance<Item>(WeightedChance.VERY_RARE, new Item(12426, 1)), // 3rd
																					// age
																					// longsword
			new WeightedChance<Item>(WeightedChance.VERY_RARE, new Item(10338, 1)), // 3rd
																					// age
																					// robe
																					// top
			new WeightedChance<Item>(WeightedChance.VERY_RARE, new Item(10334, 1)), // 3rd
																					// age
																					// platelegs
			new WeightedChance<Item>(WeightedChance.VERY_RARE, new Item(10348, 1)), // 3rd
																					// age
																					// platebody
			new WeightedChance<Item>(WeightedChance.VERY_RARE, new Item(10350, 1)), // 3rd
																					// age
																					// full
																					// helmet
			new WeightedChance<Item>(WeightedChance.VERY_RARE, new Item(10352, 1)), // 3rd
																					// age
																					// kiteshield
			new WeightedChance<Item>(WeightedChance.VERY_RARE, new Item(12422, 1)), // 3rd
																					// age
																					// wand
			new WeightedChance<Item>(WeightedChance.VERY_RARE, new Item(12424, 1)), // 3rd
																					// age
																					// bow
			new WeightedChance<Item>(WeightedChance.VERY_RARE, new Item(10344, 1)), // 3rd
																					// age
																					// amulet
			new WeightedChance<Item>(WeightedChance.VERY_RARE, new Item(12817, 1)), // Elysian
			new WeightedChance<Item>(WeightedChance.VERY_RARE, new Item(12825, 1)) // Arcane

	));
	public static Chance<Item> LOWER_TIER = new Chance<Item>(Arrays.asList(
			// Common Items
			new WeightedChance<Item>(WeightedChance.COMMON, new Item(2362, 200)), // Addy
																					// bar
			new WeightedChance<Item>(WeightedChance.COMMON, new Item(2364, 200)), // Runite
																					// bar
			new WeightedChance<Item>(WeightedChance.COMMON, new Item(8850, 1)), // Rune
																				// defender
			new WeightedChance<Item>(WeightedChance.COMMON, new Item(995, 500000)), // cash
			new WeightedChance<Item>(WeightedChance.COMMON, new Item(6199, 1)), // Mystery
																				// box
			new WeightedChance<Item>(WeightedChance.COMMON, new Item(995, 500000)), // cash
			new WeightedChance<Item>(WeightedChance.COMMON, new Item(12789, 1)), // Elite
																					// Box
			new WeightedChance<Item>(WeightedChance.UNCOMMON, new Item(6570, 1)), // Fire
																					// Cape
			new WeightedChance<Item>(WeightedChance.UNCOMMON, new Item(6585, 1)), // Amulet
																					// of
																					// fury
			new WeightedChance<Item>(WeightedChance.UNCOMMON, new Item(995, 5000000)), // cash
			new WeightedChance<Item>(WeightedChance.RARE, new Item(995, 7000000)), // cash
			new WeightedChance<Item>(WeightedChance.RARE, new Item(10548, 1)), // Fighter
																				// hat
			new WeightedChance<Item>(WeightedChance.RARE, new Item(10551, 1)), // Fighter
																				// torso
			new WeightedChance<Item>(WeightedChance.VERY_RARE, new Item(11840, 1)), // Dragon
																					// Boots
			new WeightedChance<Item>(WeightedChance.VERY_RARE, new Item(12954, 1)), // Dragon
																					// defender
			new WeightedChance<Item>(WeightedChance.VERY_RARE, new Item(12436, 1)), // Amulet
																					// of
																					// fury
																					// (or)
			new WeightedChance<Item>(WeightedChance.RARE, new Item(990, 20)), // Crystal
																				// key
			new WeightedChance<Item>(WeightedChance.VERY_RARE, new Item(12873, 1)), // Guthan's
																					// armour
																					// set
			new WeightedChance<Item>(WeightedChance.VERY_RARE, new Item(12875, 1)), // Verac's
																					// armour
																					// set
			new WeightedChance<Item>(WeightedChance.VERY_RARE, new Item(12877, 1)), // Dharok's
																					// armour
																					// set
			new WeightedChance<Item>(WeightedChance.VERY_RARE, new Item(12879, 1)), // Torag's
																					// armour
																					// set
			new WeightedChance<Item>(WeightedChance.VERY_RARE, new Item(12881, 1)), // Ahrim's
																					// armour
																					// set
			new WeightedChance<Item>(WeightedChance.VERY_RARE, new Item(12883, 1)) // Karil's
																					// armour
																					// set

	));

	/**
	 * Determine type of reward.
	 */
	public static void giveReward(Player player, int damageDealt, int totalDamage) {
	if (((int) (damageDealt * 100.0f) / totalDamage) >= getPercentage(player)) {
		Item reward = HIGER_TIER.nextObject().get();
		String name = reward.getDefinition().getName();
		String formatted_name = Utility.getAOrAn(name) + " " + name;
		player.getInventory().addOrCreateGroundItem(reward);
		player.send(new SendMessage("For doing more than" + getPercentage(player) + "% of the damage, you have been given, " + formatted_name + " ."));
		if (reward.getDefinition().getGeneralPrice() >= 500_000) {
			World.sendGlobalMessage("@mbl@" + player.determineIcon(player) + " " + player.getUsername() + " has recieved " + formatted_name + " from a Raid!");

		}
	} else {
		System.out.println("running lower tier");
		Item reward = LOWER_TIER.nextObject().get();
		String name = reward.getDefinition().getName();
		String formatted_name = Utility.getAOrAn(name) + " " + name;
		player.getInventory().addOrCreateGroundItem(reward);
		player.send(new SendMessage("For doing more than a quareter of the damage, you have been given, @or2@" + formatted_name + " ."));
		if (reward.getDefinition().getGeneralPrice() >= 500_000) {
			World.sendGlobalMessage("@mbl@" + player.determineIcon(player) + " " + player.getUsername() + " has recieved " + formatted_name + " from a Raid!");
		}
	}
	player.getRaidOwner().getRaid().resetRaid(player.getRaidOwner());
	}

	public static int tempPartySize = 0;
	public static int getPercentage(Player player) {
	int required = 0;

	for (@SuppressWarnings("unused") Player nonContributor : RoundHandler.nonContributors) {
		tempPartySize--;
	}
	
	System.out.println("Your temp party is " + tempPartySize);
	
	if (tempPartySize == 4) {
		required = 37;
	} else if (tempPartySize > 4) {
		required = 31;
	} else if (tempPartySize > 9) {
		required = 23;
	} else if (tempPartySize == 15) {
		required = 14;
	}
	
	return required;
	}

}
