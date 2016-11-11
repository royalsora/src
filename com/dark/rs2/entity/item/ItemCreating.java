package com.dark.rs2.entity.item;

import java.util.ArrayList;

import com.dark.core.util.GameDefinitionLoader;
import com.dark.core.util.Utility;
import com.dark.rs2.content.Prestige;
import com.dark.rs2.content.dialogue.DialogueManager;
import com.dark.rs2.content.skill.Skills;
import com.dark.rs2.entity.player.Player;
import com.dark.rs2.entity.player.net.out.impl.SendMessage;

/**
 * Handles creating items
 * @author Daniel
 *
 */
public enum ItemCreating {
	
	//ITEM("", new int[] {  }, new int[] {  }, new int[][] { }),
	
	CAP_AND_GOGGLES("Cap and goggles", new int[] { 9946 }, new int[] { 9945, 9472 }, new int[][] { }),
	TOXIC_STAFF_OF_THE_DEAD("Toxic staff of the dead", new int[] { 12904 }, new int[] { 11791, 12932 }, new int[][] { { Skills.CRAFTING, 59 } }),
	TRIDENT_OF_THE_SWAMP("Trident of the swamp", new int[] { 12899 }, new int[] { 12932, 11907 }, new int[][] { { Skills.CRAFTING, 59 } }),
	AMULET_OF_TORTURE("Amulet of Torture", new int[] { 19553 }, new int[] { 19529, 6585 }, new int[][] { { Skills.CRAFTING, 98 } }),
	RING_OF_SUFFERING("Ring of Suffering", new int[] { 19550 }, new int[] { 19529, 6583 }, new int[][] { { Skills.CRAFTING, 89 } }),
	NECKLACE_OF_ANGUISH("Necklace of Anguish", new int[] { 19547 }, new int[] { 19529, 11128 }, new int[][] { { Skills.CRAFTING, 92 } }),
	TORMENTED_BRACELET("Tormented Bracelet", new int[] { 19544 }, new int[] { 19529, 11133 }, new int[][] { { Skills.CRAFTING, 95 } }),
	DRAGON_PICKAXE("Dragon pickaxe", new int[] { 12797 }, new int[] { 11920, 12800 }, new int[][] { }),
	HEAVY_BALLISTA("Heavy Ballista", new int[] { 19481 }, new int[] { 19607, 19610 }, new int[][] { }),
	ODIUM_WARD("Odium ward", new int[] { 11926 }, new int[] { 11928, 11929, 11930 }, new int[][] { }),
	MALEDICTION_WARD("Malediction ward", new int[] { 11924 }, new int[] { 11931, 11932, 11933 }, new int[][] { }),	
	ODIUM_WARD_OR("Odium ward (or)", new int[] { 12807 }, new int[] { 11926, 12802 }, new int[][] { }),	
	MALEDICTION_WARD_OR("Malediction ward (or)", new int[] { 12806 }, new int[] { 11924, 12802 }, new int[][] { }),	
	MAGIC_SHORTBOW_INFUSED("Magic shortbow (i)", new int[] { 12788 }, new int[] { 12786, 861 }, new int[][] { }),
	FURY_AMULET_KIT("Amulet of fury (or)", new int[] { 12436 }, new int[] { 6585, 12526 }, new int[][] { }),
	ABYSSAL_TENTACLE("Abyssal tentacle", new int[] { 12006 }, new int[] { 12004, 4151 }, new int[][] { }),
	VOLCANIC_ABYSSAL_WHIP("Volcanic abyssal whip", new int[] { 12773 }, new int[] { 12771, 4151 }, new int[][] { }),
	FROZEN_ABYSSAL_WHIP("Frozen abyssal whip", new int[] { 12774 }, new int[] { 12769, 4151 }, new int[][] { }),
	FIRE_MAX_CAPE("Fire max cape", new int[] { 13329, 13330, }, new int[] { 6570, }, new int[][] { }),
	ARMADYL_GODSWORD("Armadyl godsword", new int[] { 11802 }, new int[] { 11798, 11810 }, new int[][] { }),
	BANDOS_GODSWORD("Bandos godsword", new int[] { 11804 }, new int[] { 11798, 11812 }, new int[][] { }),
	BLOWPIPE("Toxic blowpipe", new int[] { 12924 }, new int[] { 12922, 1755 }, new int[][] { }),	
	BLUDGEON("Abyssal Bludgeon", new int[] { 13263 }, new int[] { 13274, 13275, 13276 }, new int[][] { }),
	PRIMORDIAL_BOOTS("Primordial boots", new int[] { 13239 }, new int[] { 11840, 13231 }, new int[][] { }),	
	PURPLE_GRACEFUL("Purple Graceful Set", new int[] { 13579, 13581, 13583, 13585, 13587, 13589 }, new int[] { 1773, 11850, 11852, 11854, 11856, 11858, 11860 }, new int[][] { }),
	BLUE_GRACEFUL("Blue Graceful Set", new int[] { 13591, 13593, 13595, 13597, 13599, 13601 }, new int[] { 1767, 11850, 11852, 11854, 11856, 11858, 11860 }, new int[][] { }),	
	YELLOW_GRACEFUL("Yellow Graceful Set", new int[] { 13603, 13605, 13607, 13609, 13611, 13613 }, new int[] { 1765, 11850, 11852, 11854, 11856, 11858, 11860 }, new int[][] { }),
	RED_GRACEFUL("Red Graceful Set", new int[] { 13615, 13617, 13619, 13621, 13623, 13625 }, new int[] { 1763, 11850, 11852, 11854, 11856, 11858, 11860 }, new int[][] { }),
	GREEN_GRACEFUL("Green Graceful Set", new int[] { 13627, 13629, 13631, 13633, 13635, 13637 }, new int[] { 1771, 11850, 11852, 11854, 11856, 11858, 11860 }, new int[][] { }),
	SLAYER_HELMET("Slayer helmet", new int[] { 11864 }, new int[] { 4166, 8921,  4164, 4168, 4551, 4155}, new int[][] { }),	
	HOLY_BOOK("Holy book", new int[] { 3840 }, new int[] { 3827, 3828, 3829, 3830, 3839}, new int[][] { }),
	UNHOLY_BOOK("Unholy book", new int[] { 3842 }, new int[] { 3831, 3832, 3833, 3834, 3841}, new int[][] { }),	
	BOOK_OF_BALANCE("Book of balance", new int[] { 3844 }, new int[] { 3835, 3836, 3837, 3838, 3843}, new int[][] { }),
	PEGASIAN_BOOTS("Pegasian boots", new int[] { 13237 }, new int[] { 2577, 13229 }, new int[][] { }),
	ETERNAL_BOOTS("Eternal boots", new int[] { 13235 }, new int[] { 6920, 13227 }, new int[][] { }),	
	DARK_INFINITY("Dark infinity set", new int[] { 12458, 12457, 12459 }, new int[] { 12528, 6916, 6918, 6924 }, new int[][] { }),	
	GODSWORD_BLADE("Godsword blade", new int[] { 11798 }, new int[] { 11818, 11820, 11822 }, new int[][] { }),
	LIGHT_INFINITY("Light infinity set", new int[] { 12420, 12419, 12421 }, new int[] { 12530, 6916, 6918, 6924 }, new int[][] { }),
	MYSTIC_STEAM_BATTLESTAFF("Mystic steam battlestaff", new int[] { 12796 }, new int[] { 11789, 12798 }, new int[][] { }),
	STEAM_BATTLESTAFF("Steam battlestaff", new int[] { 12795 }, new int[] { 11787, 12798 }, new int[][] { }),
	BLACK_SLAYER_HELMET("Black slayer helemet", new int[] { 19641 }, new int[] { 7980, 11865 }, new int[][] { }),
	GREEN_SLAYER_HELMET("Green slayer helemet", new int[] { 19645 }, new int[] { 7981, 11865 }, new int[][] { }),
	RED_SLAYER_HELMET("Red slayer helemet", new int[] { 19649 }, new int[] { 7979, 11865 }, new int[][] { }),
	CRYSTAL_BOW("Crystal Bow", new int[] { 4214 }, new int[] { 4207, 9705 }, new int[][] { }),
	CRYSTAL_HALBERD("Crystal Halberd", new int[] { 13092 }, new int[] { 4207, 1413 }, new int[][] { }),
	CRYSTAL_SHIELD("Crystal Shield", new int[] { 4225 }, new int[] { 4207, 9704 }, new int[][] { }),
	SARADOMIN_BLESSED_SWORD("Saradomin blessed sword", new int[] { 12809 }, new int[] { 12804, 11838 }, new int[][] { }),
	SARADOMIN_GODSWORD("Saradomin godsword", new int[] { 11806 }, new int[] { 11798, 11814 }, new int[][] { }),
	ZAMORAK_GODSWORD("Zamorak godsword", new int[] { 11808 }, new int[] { 11798, 11816 }, new int[][] {{ Skills.SLAYER, 90 } }),
	SERPENTINE_VISAGE("Serpentine visage", new int[] { 12929 }, new int[] { 12927, 1755 }, new int[][] { { Skills.CRAFTING, 52 } }),
	DRAGON_SQ_SHIELD("Dragon sq shield", new int[] { 1187 }, new int[] { 2366, 2368 }, new int[][] { { Skills.SMITHING, 60 } }),
	DRAGONFIRE_SHIELD("Dragonfire shield", new int[] { 11283 }, new int[] { 1540, 11286 }, new int[][] { { Skills.SMITHING, 90 } }),	
	DRAGON_SHIELD_KIT("Dragon sq shield (g)", new int[] { 12418 }, new int[] { 12532, 1187 }, new int[][] { }),	
	DRAGON_CHAINBODY_KIT("Dragon chainbody (g)", new int[] { 12414 }, new int[] { 12534, 2513 }, new int[][] { }),		
	DRAGON_CHAINBODY_KIT_2("Dragon chainbody (g)", new int[] { 12414 }, new int[] { 12534, 3140 }, new int[][] { }),	
	DRAGON_HELM_KIT("Dragon full helm (g)", new int[] { 12417 }, new int[] { 12538, 11335 }, new int[][] { }),
	DRAGON_PLATELEGS_KIT("Dragon platelegs (g)", new int[] { 12415 }, new int[] { 12536, 4087 }, new int[][] { }),
	BLESSED_SS("Blessed Spirit Shield", new int[] { 12831 }, new int[] { 12833, 12829 }, new int[][] { }),
	ARCANE("Arcane Spirit Shield", new int[] { 12825 }, new int[] { 12831, 12827 }, new int[][] { }),
	SPECTRAL("Spectral Spirit Shield", new int[] { 12821 }, new int[] { 12831, 12823 }, new int[][] { }),
	ELYSIAN("Elysian Spirit Shield", new int[] { 12817 }, new int[] { 12831, 12819 }, new int[][] { }),
	DRAGON_PLATESKIRT_KIT("Dragon plateskirt (g)", new int[] { 12416 }, new int[] { 12536, 4585 }, new int[][] { });
	
	
	private final String name;
	private final int[] product;
	private final int[] items;
	private final int[][] skills;
	private ItemCreating(String name, int[] product, int[] items, int[][] skills) {
		this.name = name;
		this.product = product;
		this.items = items;
		this.skills = skills;
	}
	
	public String getName() {
		return name;
	}
	
	public int[] getProduct() {
		return product;
	}
	
	public int[] getItems() {
		return items;
	}
	
	public int[][] getSkills() {
		return skills;
	}
	
	/**
	 * Gets the items 
	 * @param item1
	 * @param item2
	 * @return
	 */
	public static ItemCreating getDataForItems(int item1, int item2) {
		for(ItemCreating data : ItemCreating.values()) {
			int found = 0;
			for (int it : data.items) {
				if (it == item1 || it == item2) {
					found++;
				}
			}
			if (found >= 2) {
				return data;				
			}
		}
		return null;
	}
	
	/**
	 * Handles creating the items
	 * @param player
	 * @param item1
	 * @param item2
	 * @return
	 */
	public static boolean handle(Player player, int item1, int item2) {
		if (item1 == item2) {
			return false;			
		}
		
		ItemCreating data = ItemCreating.getDataForItems(item1, item2);
		
		if (data == null || !player.getInventory().hasItemId(item1) || !player.getInventory().hasItemId(item2)) {
			return false;			
		}
		
		for (int i = 0; i < data.getSkills().length; i++) {
			if (player.getSkill().getLevels()[data.getSkills()[i][0]] < data.getSkills()[i][1]) {
				DialogueManager.sendItem1(player, "You need a " + Prestige.getSkillName(data.getSkills()[i][0]) + " level of " + data.getSkills()[i][1] + " to do this!", data.getProduct()[0]);
				return true;
			}
		}
		
		ArrayList<String> required = new ArrayList<String>();
		
		for (int reqItem : data.items) {
			if (!player.getInventory().hasItemId(reqItem)) {
				player.send(new SendMessage("You do not have all the needed items to do this!"));
				required.add(GameDefinitionLoader.getItemDef(reqItem).getName());
				continue;
			}
		}
		
		if (!required.isEmpty()) {
			player.send(new SendMessage("@red@To make " + data.getName() + " you need: " + required + "."));
			required.clear();
			return false;
		}
		
		if (player.getInventory().getFreeSlots() < data.product.length) {
			player.send(new SendMessage("You need at least " + data.product.length + " free inventory space(s) to do this!"));
			return false;
		}
		
		for (int reqItem : data.items) {
			if (reqItem == 1755) {//) || reqItem == 1595) {
				continue;				
			}
			player.getInventory().remove(reqItem);
		}
	
		for (int product : data.product) {
			player.getInventory().add(product, 1);
		}
		
		DialogueManager.sendItem1(player, "@dre@You have created " + Utility.getAOrAn(data.getName()) + " " + data.getName() + ".", data.getProduct()[0]);		
		return true;
	}

}
