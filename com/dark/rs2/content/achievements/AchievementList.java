
package com.dark.rs2.content.achievements;

import com.dark.rs2.content.achievements.AchievementHandler.AchievementDifficulty;

/**
 * Holds all the achievements and their requirements to complete. When adding a
 * new achievement, add it to the end of the list to prevent bugs with
 * saving/loading achievements.

 * @author Daniel
 * @author Michael
 */
public enum AchievementList {
	
	/* Easy Achievements */
	CHANGE_APPEARANCE("Change your appearance once", 1, AchievementDifficulty.EASY),
	EAT_100_FOODS("Eat 100 of any food", 100, AchievementDifficulty.EASY),
	DRINK_100_POTIONS("Drink 100 of any potion", 100, AchievementDifficulty.EASY),
	KILL_75_COWS("Kill 75 cows", 75, AchievementDifficulty.EASY),
	SPEND_5000000_ON_SHOPS("Spend 5m coins on shops", 5000000, AchievementDifficulty.EASY),
	THIEVE_100_TIMES_FROM_STALLS("Thieve 100 stalls", 100, AchievementDifficulty.EASY),
	FAIL_15_TIMES_THIEVING_STALLS("Kill 50 Abyssal Spawns", 50, AchievementDifficulty.EASY),
	KILL_10_MITH("Kill 10 Mithril Dragons", 10, AchievementDifficulty.EASY),
	STRING_50_AMULETS("String 50 amulets", 50, AchievementDifficulty.EASY),
	VIEW_TABLE("View the Drop Table", 1, AchievementDifficulty.EASY),
	CHOP_DOWN_150_TREES("Chop down 150 trees", 150, AchievementDifficulty.EASY),
	COOK_250_FOODS("Cook 250 raw fish", 250, AchievementDifficulty.EASY),
	ANSWER_15_TRIVIABOTS_CORRECTLY("Answer 15 Trivia Questions", 15, AchievementDifficulty.EASY),
	KILL_1_PLAYER("Receive a clue from an impling", 1, AchievementDifficulty.EASY),
	DIE_1_TIME("Die 1 time", 1, AchievementDifficulty.EASY),
	COMPLETE_50_GNOME_COURSES("Complete 50 gnome courses", 50, AchievementDifficulty.EASY),
	CATCH_25_IMPLINGS("Catch 25 Implings", 25, AchievementDifficulty.EASY),
	KILL_1_GORILLA("Kill 1 Ancient Gorilla", 1, AchievementDifficulty.EASY),
	SHEAR_10_SHEEPS("Shear 10 Sheeps", 10, AchievementDifficulty.EASY),
	SETUP_A_BANK_PIN("Setup a bank pin", 1, AchievementDifficulty.EASY),
	VOTE_5_TIMES("Vote 5 Times", 5, AchievementDifficulty.EASY),
	DO_A_SKILLCAPE_EMOTE("Do a skillcape emote", 1, AchievementDifficulty.EASY),
	OPEN_5_MBOX("Open 5 Mystery Boxes", 5, AchievementDifficulty.EASY),
	USE_THE_SUPER_HEATING_SPELL_25_TIMES("Use superheat 25 times", 25, AchievementDifficulty.EASY),
	
	/* Medium Achievements */
	KILL_ROCK_CRABS("Kill 100 Rock crabs", 100, AchievementDifficulty.MEDIUM),
	KILL_100_CYCLOPS("Kill 100 Cyclops", 100, AchievementDifficulty.MEDIUM),
	EXCHANGE_500_ITEMS_PILES("Exchange 500 items to Piles", 500, AchievementDifficulty.MEDIUM),
	COMPLETE_10_SLAYER_TASKS("Complete 10 slayer tasks", 10, AchievementDifficulty.MEDIUM),
	OBTAIN_1_BOSS_PET("Obtain 1 skilling pet", 1, AchievementDifficulty.MEDIUM),
	HIGH_ALCH_250_ITEMS("High alch 250 items", 250, AchievementDifficulty.MEDIUM),
	OBTAIN_10_FIRECAPES("Obtain 10 firecapes", 10, AchievementDifficulty.MEDIUM),
	OPEN_10_CASKET("Open 10 Cash Caskets", 10, AchievementDifficulty.MEDIUM),
	WIN_30_PEST_CONTROL_GAMES("Win 30 Pest Control games", 30, AchievementDifficulty.MEDIUM),
	CHOP_DOWN_350_TREES("Chop down 350 trees", 350, AchievementDifficulty.MEDIUM),
	BURN_200_LOGS("Burn 200 logs", 200, AchievementDifficulty.MEDIUM),
	OPEN_15_ITEM_SETS("Open 15 item sets", 15, AchievementDifficulty.MEDIUM),
	RESET_1_STATISTICS("Reset 1 stat", 1, AchievementDifficulty.MEDIUM),
	KILL_15_PLAYER("Kill 100 Abyssal Scions", 100, AchievementDifficulty.MEDIUM),
	DIE_10_TIME("Die 10 times", 10, AchievementDifficulty.MEDIUM),
	COMPLETE_100_BARB_COURSES("Complete 100 barb courses", 100, AchievementDifficulty.MEDIUM),
	CUT_250_GEMS("Cut 250 gems", 250, AchievementDifficulty.MEDIUM),
	KILL_25_KRAKENS("Kill 25 Krakens", 25, AchievementDifficulty.MEDIUM),
	LOOT_1_BARROWS_CHESTS("Loot 1 Barrows chest", 1, AchievementDifficulty.MEDIUM),
	EARN_100_PEST_CONTROL_POINTS("Earn 100 Pest control points", 100, AchievementDifficulty.MEDIUM),
	CATCH_100_IMPLINGS("Catch 100 Implings", 100, AchievementDifficulty.MEDIUM),
	SHEAR_150_SHEEPS("Shear 150 Sheeps", 150, AchievementDifficulty.MEDIUM),
	ENCHANT_500_BOLTS("Kill 1 Demonic Gorilla", 1, AchievementDifficulty.MEDIUM),
	BURY_150_BONES("Bury or use on altar 150 bones", 150, AchievementDifficulty.MEDIUM),
	//USE_THE_SUPER_HEATING_SPELL_135_TIMES("Use superheat 135 times", 135, AchievementDifficulty.MEDIUM),

	
	/* Hard Achievements */
	KILL_KING_BLACK_DRAGON("Kill KBD 50 times", 50, AchievementDifficulty.HARD),
	KILL_250_SKELETAL_WYVERNS("Kill 250 Skeletal wyverns", 250, AchievementDifficulty.HARD),
	CRAFT_1000_NATURE_RUNES("Craft 1,000 Nature runes", 1000, AchievementDifficulty.HARD),
	COMPLETE_100_SLAYER_TASKS("Complete 100 slayer tasks", 100, AchievementDifficulty.HARD),
	OBTAIN_3_BOSS_PET("Use the Foutain of Rune 25 times", 25, AchievementDifficulty.HARD),
	SPEND_100000000_ON_SHOPS("Spend 100m coins on shops", 100000000, AchievementDifficulty.HARD),
	OBTAIN_25_FIRECAPES("Obtain 25 firecapes", 25, AchievementDifficulty.HARD),
	CAST_VENGEANCE_350_TIMES("Cast vengeance 350 times", 350, AchievementDifficulty.HARD),
	BURN_500_LOGS("Burn 500 logs", 500, AchievementDifficulty.HARD),
	COOK_500_FOODS("Cook 500 foods", 500, AchievementDifficulty.HARD),
	ANSWER_30_TRIVIABOTS_CORRECTLY("Answer 30 Trivia Questions", 30, AchievementDifficulty.HARD),
	OPEN_50_CRYSTAL_CHESTS("Open 50 Crystal chests", 50, AchievementDifficulty.HARD),
	KILL_50_PLAYER("Kill 250 Abyssal Monarchs", 250, AchievementDifficulty.HARD),
	EARN_500_PEST_CONTROL_POINTS("Earn 100 Pest control points", 500, AchievementDifficulty.HARD),
	DIE_50_TIME("Die 50 times", 50, AchievementDifficulty.HARD),
	OPEN_25_MBOX("Open 25 MBoxes", 25, AchievementDifficulty.HARD),
	MINE_500_ROCKS("Mine 500 rocks", 500, AchievementDifficulty.HARD),
	LOOT_25_BARROWS_CHESTS("Loot 25 Barrows chests", 25, AchievementDifficulty.HARD),
	KILL_100_ZULRAHS("Kill 100 Zulrahs", 100, AchievementDifficulty.HARD),
	KILL_150_KRAKENS("Kill 150 Krakens", 150, AchievementDifficulty.HARD),
	KILL_100_CALLISTO("Kill 100 Callisto", 100, AchievementDifficulty.HARD),
	OBTAIN_10_RARE_DROPS("Open a Zul-andra Totem", 1, AchievementDifficulty.HARD),
	BURY_500_BONES("Bury or use on altar 500 bones", 500, AchievementDifficulty.HARD),
	EQUIP_DRAGON_CLAWS("Kill an Enraged Cow", 1, AchievementDifficulty.HARD),
	VISIT_DZ("Visit the D Zone", 1, AchievementDifficulty.HARD),
	;

	private final String name;
	private final int completeAmount;
	private final AchievementDifficulty difficulty;

	private AchievementList(String name, int completeAmount, AchievementDifficulty difficulty) {
		this.name = name;
		this.completeAmount = completeAmount;
		this.difficulty = difficulty;
	}

	public String getName() {
		return name;
	}

	public int getCompleteAmount() {
		return completeAmount;
	}
	
	public int getReward() {
		switch (difficulty) {
		case MEDIUM:
			return 2;
		case HARD:
			return 3;
		case EASY:
		default:
			return 1;
		}
	}

	public AchievementDifficulty getDifficulty() {
		return difficulty;
	}
}