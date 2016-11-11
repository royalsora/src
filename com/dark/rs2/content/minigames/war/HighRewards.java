package com.dark.rs2.content.minigames.war;

import java.util.Arrays;

import com.dark.core.util.chance.Chance;
import com.dark.core.util.chance.WeightedChance;
import com.dark.rs2.entity.item.Item;

public class HighRewards {
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
	
}
