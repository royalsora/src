package com.dark.rs2.content;

import com.dark.core.util.ItemNames;
import com.dark.core.util.Utility;
import com.dark.rs2.content.achievements.AchievementHandler;
import com.dark.rs2.content.achievements.AchievementList;
import com.dark.rs2.content.dialogue.DialogueManager;
import com.dark.rs2.entity.Animation;
import com.dark.rs2.entity.Graphic;
import com.dark.rs2.entity.item.Item;
import com.dark.rs2.entity.player.Player;
import com.dark.rs2.entity.player.net.out.impl.SendMessage;

/**
 * Handles crystal chest
 * 
 * @author Daniel
 *
 */
public class CrystalChest {

	/**
	 * Ids of key halves
	 */
	public static final Item[] KEY_HALVES = { new Item(985), new Item(987) };

	/**
	 * Crystal key Id
	 */
	public static final Item KEY = new Item(989);

	/**
	 * Creates the key
	 * 
	 * @param player
	 */
	public static void createKey(final Player player) {
		if (player.getInventory().contains(KEY_HALVES)) {
			player.getInventory().remove(KEY_HALVES[0]);
			player.getInventory().remove(KEY_HALVES[1]);
			player.getInventory().add(KEY);
			DialogueManager.sendItem1(player, "You have combined the two parts to form a key.", KEY.getId());
		}
	}

	public static final Item[] UNCOMMON_CHEST_REWARDS = { new Item(ItemNames.SHIELD_LEFT_HALF), new Item(ItemNames.SHIELD_RIGHT_HALF), new Item(ItemNames.CASH_CASKET), new Item(ItemNames.TEACHER_WAND), new Item(ItemNames.CLUE_SCROLL__MEDIUM_1), new Item(ItemNames.DRAGONSTONE_AMMY_2), new Item(ItemNames.MYSTERIOUS_EMBLEM_2), new Item(ItemNames.GRANITE_SHIELD), new Item(ItemNames.MYSTERY_BOX), new Item(ItemNames.GLOVES_13), new Item(ItemNames.RUNE_CLAWS), new Item(ItemNames.HELM_OF_NEITIZNOT), new Item(ItemNames.WARRIOR_HELM), new Item(ItemNames.ARCHER_HELM), new Item(ItemNames.FARSEER_HELM), new Item(ItemNames.SLAYER_BELL), new Item(ItemNames.DOCTORS_GOWN), };

	public static final Item[] RARE_CHEST_REWARDS = { new Item(ItemNames.MASTER_WAND), new Item(ItemNames.INFINITY_TOP), new Item(ItemNames.INFINITY_HAT), new Item(ItemNames.INFINITY_BOOTS), new Item(ItemNames.INFINITY_GLOVES), new Item(ItemNames.MAGES_BOOK), new Item(ItemNames.CLUE_SCROLL__HARD), new Item(ItemNames.BOND_5), new Item(ItemNames.MYSTERIOUS_EMBLEM_3), new Item(ItemNames.MYSTERY_BOX), new Item(ItemNames.ECUMENICAL_KEY), new Item(ItemNames.GOLDEN_KEY), new Item(ItemNames.WOLF_WHISTLE), new Item(ItemNames.UNCUT_ONYX), new Item(ItemNames.INFINITY_BOTTOMS) };

	/**
	 * Chest rewards
	 */
	public static final Item[] COMMON_CHEST_REWARDS = {

			/* Melee */
			new Item(ItemNames.RUNE_FULL_HELM), new Item(ItemNames.RUNE_PLATEBODY), new Item(ItemNames.RUNE_KITESHIELD), new Item(ItemNames.RUNE_PLATELEGS), new Item(ItemNames.RUNE_PLATESKIRT), new Item(ItemNames.RUNE_BOOTS), new Item(ItemNames.RUNE_CHAINBODY), new Item(ItemNames.RUNE_CROSSBOW), new Item(ItemNames.RING_OF_RECOIL),

			/* Ranged */
			new Item(ItemNames.SNAKESKIN_BODY), new Item(ItemNames.SNAKESKIN_CHAPS), new Item(ItemNames.SNAKESKIN_BANDANA), new Item(ItemNames.SNAKESKIN_BOOTS), new Item(ItemNames.SNAKESKIN_VAMBRACES), new Item(ItemNames.MAGIC_LONGBOW), new Item(ItemNames.AMULET_OF_POWER), new Item(ItemNames.AMULET_OF_ACCURACY), new Item(ItemNames.AVAS_ACCUMULATOR), new Item(ItemNames.GREEN_DHIDE_BODY), new Item(ItemNames.GREEN_DHIDE_CHAPS), new Item(ItemNames.GREEN_DHIDE_BODY), new Item(ItemNames.GREEN_DHIDE_CHAPS),

			/* Mage */
			new Item(ItemNames.SPLITBARK_HELM), new Item(ItemNames.SPLITBARK_BODY), new Item(ItemNames.SPLITBARK_LEGS), new Item(ItemNames.SPLITBARK_GAUNTLETS), new Item(ItemNames.SPLITBARK_BOOTS), new Item(ItemNames.DAMAGED_BOOK), new Item(ItemNames.DAMAGED_BOOK_1), new Item(ItemNames.DAMAGED_BOOK_2), new Item(ItemNames.ROCK_CLIMBING_BOOTS), new Item(ItemNames.GLOVES_11), new Item(ItemNames.ELEMENTAL_SHIELD), new Item(ItemNames.BEGINNER_WAND),

			/* Other */
			new Item(ItemNames.BOOK_OF_DARKNESS), new Item(ItemNames.BOOK_OF_LAW), new Item(ItemNames.BOOK_OF_WAR), new Item(ItemNames.MYSTERIOUS_EMBLEM_1), new Item(ItemNames.DRAGON_SCIMITAR), new Item(ItemNames.CLUE_SCROLL__EASY_5), new Item(ItemNames.BLACK_SKIRT_G), new Item(ItemNames.BLACK_SKIRT_T), new Item(ItemNames.BLACK_WIZARD_ROBE_G), new Item(ItemNames.BLACK_WIZARD_ROBE_T), new Item(ItemNames.BLACK_WIZARD_HAT_G), new Item(ItemNames.BLACK_WIZARD_HAT_T), new Item(ItemNames.BLACK_CANE), };

	/**
	 * Searches the chest
	 * 
	 * @param player
	 * @param x
	 * @param y
	 */

	public static void searchChest(final Player player, final int x, final int y) {
		if (player.getInventory().contains(KEY)) {
			player.send(new SendMessage("You unlock the chest with your key."));
			player.getInventory().remove(KEY);
			AchievementHandler.activateAchievement(player, AchievementList.OPEN_50_CRYSTAL_CHESTS, 1);
			player.getUpdateFlags().sendAnimation(new Animation(881));
			player.getUpdateFlags().sendGraphic(new Graphic(402, true));
			player.getInventory().add(new Item(995, Utility.random(3200)));
			Item itemReceived;
			switch (Utility.random(30)) {
			case 0:
			case 1:
			case 2:
			case 3:
			case 4:
			case 5:
			case 6:
			case 7:
			case 8:
			case 9:
			case 10:
				itemReceived = Utility.randomElement(UNCOMMON_CHEST_REWARDS);
				break;
			case 25:
				itemReceived = Utility.randomElement(RARE_CHEST_REWARDS);
				break;
			default:
				itemReceived = Utility.randomElement(COMMON_CHEST_REWARDS);
			}

			player.getInventory().addOrCreateGroundItem(itemReceived.getId(), itemReceived.getAmount(), true);
			player.send(new SendMessage("You find " + Utility.determineIndefiniteArticle(itemReceived.getDefinition().getName()) + " " + itemReceived.getDefinition().getName() + " in the chest."));
			if (itemReceived.getDefinition().getGeneralPrice() < 100_000) {
				switch (Utility.random(50)) {
				case 0:
				case 1:
				case 2:
				case 3:
				case 4:
				case 5:
				case 6:
				case 7:
				case 8:
				case 9:
				case 10:
					itemReceived = Utility.randomElement(UNCOMMON_CHEST_REWARDS);
					break;
				case 25:
					itemReceived = Utility.randomElement(RARE_CHEST_REWARDS);
					break;
				default:
					itemReceived = Utility.randomElement(COMMON_CHEST_REWARDS);
				}
				player.getInventory().addOrCreateGroundItem(itemReceived.getId(), itemReceived.getAmount(), true);
				player.send(new SendMessage("You find " + Utility.determineIndefiniteArticle(itemReceived.getDefinition().getName()) + " " + itemReceived.getDefinition().getName() + " in the chest."));
			}
		} else {
			player.send(new SendMessage("You need a key to open this chest."));
		}
	}

}
