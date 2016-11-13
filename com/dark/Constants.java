package com.dark;

/**

 * 
 * @author 
 */
public class Constants {

	
	/**
	 * Saves the items and npc definitions to json
	 * 
	 * to true
	 */
	public static final boolean SAVE_DEFS_TO_JSON = false;
	
	/**
	 * The version of Vencillio
	 */
	public static final double VERSION = 2.4;
	
	/**
	 * Checks if in development mode
	 */
	public static boolean DEV_MODE = false;

	/**
	 * Walking check
	 */
	public static boolean WALK_CHECK = true;
	
	/**
	 * Checks if the world is staff only
	 */
	public static boolean STAFF_ONLY = false;
	
	/**
	 * Check to see if double experience is enabled
	 */
	public static boolean doubleExperience = true;

	/**
	 * Current amount of votes
	 */
	public static int CURRENT_VOTES = 0;

	/**
	 * The last voter
	 */
	public static String LAST_VOTER = "None";

	/**
	 * Holds the most players online at once
	 */ // test
	public static int MOST_ONLINE = 70;	

	/**
	 * Strings that are classified as bad
	 */
	public static final String[] BAD_STRINGS = { 
		"fag", "f4g", "faggot", "nigger", "fuck", "bitch", "whore", 
		"slut", "gay", "lesbian", "scape", ".net", ".com", ".org", 
		"vagina", "dick", "cock", "penis", "hoe", "soulsplit", "ikov", 
		"retard", "cunt", "mod", "Moderator", "admin", "administrator", "os-scape" , "os scape", 
	};
	
	/**
	 * Holds all usernames that can not be used
	 */
	public static final String[] BAD_USERNAMES = { 
		"mod", "admin", "moderator", "administrator", "owner", "m0d", "adm1n", "0wner", 
		"retard", "Nigga", "nigger", "n1gger", "n1gg3r", "nigg3r", "n1gga", "cock", 
		"faggot", "fag", "anus", "arse", "fuck", "bastard", "bitch", "cunt", "chode", 
		"damn", "dick", "faggit", "gay", "homo", "jizz", "lesbian", "negro", "pussy", "penis",
		"queef", "twat", "titty", "whore", "b1tch"
	};

	/**
	 * Strings that may not be used for yelltitles
	 */
	public static final String[] BAD_TITLES = { 
		"owner", "0wner", "own3r", "0wn3r", "admin", "administrator", "dev",
		"developer", "mod", "m0d", "moderator", "m0derator", 
	};
	
	/**
	 * All the staff members
	 */
	public final static String[] STAFF_MEMBERS = { 
		"affe", "prickachu", "Anan", ""
	};
	
	/**
	 * Login Messages for players
	 */
	public static final String[] LOGIN_MESSAGES = { 
		"There are currently@dre@ /s/ </col>players online.",
		"Be sure to vote every 12 hours for more players!",
	};
	
	/**
	 * Messages for identifying items in the DropTable interface
	 */
	public static final String[] ITEM_IDENTIFICATION_MESSAGES = { 
		"I would sell my left kidney for /s/!",
		"Who needs a girlfriend when you have /s/? Now only if I had one...",
		"God! I wish I owned /s/!",
		"Someone please give me /s/.",
		"My only dream in life is to obtain /s/!",
		"If I believe hard enough, Prickachu will give me /s/! JK.",
		"Please... Please god... Give me /s/!",
	};
	
	/**
	 * Item dismantling data
	 */
	public static final int[][] ITEM_DISMANTLE_DATA = {
		{ 12436, 6585, 12526 }, //Amulet of fury (or)
		{ 12807, 11926, 12802 }, //Odium ward (or)
		{ 12806, 11924, 12802 }, //Malediction ward (or)
		{ 12809, 12804, 11838 }, //Saradomin Blessed sword
		{ 12797, 11920, 12800 }, //Dragon pickaxe (or)
		{ 12924, 12922, -1 }, //Blowpipe
		{ 12929, 12927, -1 }, //Serpentine
		{ 12904, 11791, 12932} //Toxic staff of the dead
	};
	
	/**
	 * Holds all doors that can not be opened
	 */
	public static final int[] BLOCKED_DOORS = {
		26502, 26503, 26504, 26505,/* 24306, 24309,*/ 26760, 2104, 
		2102, 2100, 26461, 4799, 7129, 4133, 4132, 4108
	};
	
}
