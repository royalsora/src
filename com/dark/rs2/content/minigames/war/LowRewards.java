package com.dark.rs2.content.minigames.war;

import java.util.Arrays;

import com.dark.core.util.chance.Chance;
import com.dark.core.util.chance.WeightedChance;
import com.dark.rs2.entity.item.Item;

public class LowRewards {
	public static Chance<Item> LOWER_TIER = new Chance<Item>(Arrays.asList(new WeightedChance<Item>(WeightedChance.VERY_RARE, new Item(11840, 1)), // Dragon
			// boots
			new WeightedChance<Item>(WeightedChance.VERY_RARE, new Item(12821, 1)), // Spectral
			new WeightedChance<Item>(WeightedChance.VERY_RARE, new Item(11806, 1)), // Saradomin
			// godsword
			new WeightedChance<Item>(WeightedChance.VERY_RARE, new Item(11808, 1)), // Zamorak
			// godsword
			new WeightedChance<Item>(WeightedChance.RARE, new Item(4152, 2)), // x2
			// whips
			new WeightedChance<Item>(WeightedChance.RARE, new Item(11838, 1)), // sara
			// sword
			new WeightedChance<Item>(WeightedChance.VERY_RARE, new Item(995, 40000000)), // 40m
			// coins
			new WeightedChance<Item>(WeightedChance.VERY_RARE, new Item(12927, 1)), // Serpentine
			// visage
			new WeightedChance<Item>(WeightedChance.VERY_RARE, new Item(11286, 1)), // Draconic
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
			new WeightedChance<Item>(WeightedChance.VERY_RARE, new Item(12825, 1)), // Arcane
			// Common Items
			new WeightedChance<Item>(WeightedChance.RARE, new Item(2362, 200)), // Addy
																				// bar
			new WeightedChance<Item>(WeightedChance.RARE, new Item(2364, 200)), // Runite
																				// bar
			new WeightedChance<Item>(WeightedChance.UNCOMMON, new Item(8850, 1)), // Rune
																					// defender
			new WeightedChance<Item>(WeightedChance.RARE, new Item(995, 500000)), // cash
			new WeightedChance<Item>(WeightedChance.UNCOMMON, new Item(6199, 1)), // Mystery
																					// box
			new WeightedChance<Item>(WeightedChance.RARE, new Item(995, 500000)), // cash
			new WeightedChance<Item>(WeightedChance.RARE, new Item(12789, 1)), // Elite
																				// Box
			new WeightedChance<Item>(WeightedChance.UNCOMMON, new Item(6570, 1)), // Fire
																					// Cape
			new WeightedChance<Item>(WeightedChance.UNCOMMON, new Item(6585, 1)), // Amulet
																					// of
																					// fury
			new WeightedChance<Item>(WeightedChance.RARE, new Item(995, 5000000)), // cash
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
			new WeightedChance<Item>(WeightedChance.VERY_RARE, new Item(990, 20)), // Crystal
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
	public static Chance<Item> SHIT_TIER = new Chance<Item>(Arrays.asList(
			// Common Items
			new WeightedChance<Item>(WeightedChance.COMMON, new Item(2362, 200)), // Addy
																					// bar
			new WeightedChance<Item>(WeightedChance.COMMON, new Item(2364, 200)), // Runite
																					// bar
			new WeightedChance<Item>(WeightedChance.RARE, new Item(6199, 1)), // Mystery
																				// box
			new WeightedChance<Item>(WeightedChance.RARE, new Item(12789, 1)), // Elite
																					// Box
			new WeightedChance<Item>(WeightedChance.RARE, new Item(6570, 1)), // Fire
																					// Cape
			new WeightedChance<Item>(WeightedChance.RARE, new Item(6585, 1)), // Amulet
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
			
			new WeightedChance<Item>(WeightedChance.VERY_RARE, new Item(990, 20)), // Crystal
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

}
