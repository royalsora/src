package com.dark.rs2.entity.player;

import com.dark.core.util.Utility;
import com.dark.rs2.content.io.PlayerSaveUtil;
import com.dark.rs2.entity.Location;

/**
 * Handles player constants
 * 
 * @author Daniel
 *
 */
@SuppressWarnings("unused")
public final class PlayerConstants {

	/**
	 * Array of owner usernames
	 */
	public static final String[] OWNER_USERNAME = { "affe", "prickachu", "", "", ""};
	/**
	 * Side bar interface IDs
	 */
	public static final int[] SIDEBAR_INTERFACE_IDS = { 2423, 3917, 29400, 3213, 1644, 5608, 0, -1, 5065, 5715, 18128, 904, 147, -1, 2449 };

	/**
	 * Max item count
	 */
	public static final int MAX_ITEM_COUNT = 21411;

	/**
	 * Lumbridge teleport
	 */
	public static Location LUMBRIDGE = new Location(3222, 3216, 0);
	
	/**
	 * Falador teleport
	 */
	public static Location FALADOR = new Location(2965, 3378, 0);
	
	/**
	 *  teleport
	 */
	public static Location CAMELOT = new Location(2757, 3477, 0);
	
	/**
	 *  teleport
	 */
	public static Location ARDOUGNE = new Location(2662, 3305, 0);
	
	/**
	 *  teleport
	 */
	public static Location WATCH_TOWER = new Location(2553, 3114, 0);
	
	/**
	 *  teleport
	 */
	public static Location TROLLHEIM = new Location(2888, 3674, 0);
	
	/**
	 *  teleport
	 */
	public static Location APE_ATOLL = new Location(2755, 2784, 0);

	/**
	 * Home teleport
	 */
	public static Location HOME = new Location(3088, 3500, 0);

	/**
	 * Edgeville teleport
	 */
	public static Location EDGEVILLE = new Location(3094, 3447, 0);
	
	// varrock
	
	public static Location VARROCK = new Location (3213, 3424, 0);
	
	/**
	 * Jailed area
	 */
	public static Location JAILED_AREA = new Location(2774, 2794, 0);
	
	/**
	 * Staff area
	 */
	public static Location STAFF_AREA = new Location(2758, 3507, 2);
	
	/**
	 * Member zone
	 */
	public static Location MEMEBER_AREA = new Location(2516, 3860, 0);

	/**
	 * Handles starters
	 * 
	 * @param player
	 */
	public static void doStarter(Player player) {
		player.setAppearanceUpdateRequired(true);
		player.getEquipment().onLogin();
		PlayerSaveUtil.setReceivedStarter(player);
		player.getRunEnergy().setRunning(true);
		player.setProfilePrivacy(false);
	}

	/**
	 * Overrides object existance
	 * 
	 * @param p
	 * @param objectId
	 * @param x
	 * @param y
	 * @param z
	 * @return
	 */
	public static boolean isOverrideObjectExistance(Player p, int objectId, int x, int y, int z) {
		if ((x == 2851) && (y == 5333)) {
			return true;
		}

		if (objectId == 26342 && p.getX() >= 2916 && p.getY() >= 3744 && p.getX() <= 2921 && p.getY() <= 3749) {
			return true;
		}
		
		if (objectId == 2072) {
			return true;
		}

		return false;
	}
	
	public static boolean isHighClass(Player player) {
		final int[] ranks = { 2, 3, 4 };
		for (int i = 0; i < ranks.length; i++) {
			if (player.getRights() == ranks[i]) {
				return true;
			}
		}	
		return false;
	}
	
	public static boolean isPlayer(Player player) {
		if (player.getRights() == 0) {
			return true;
		}
		if (player.getRights() == 11 && !player.isMember()) {
			return true;
		}
		if (player.getRights() == 12 && !player.isMember()) {
			return true;
		}
		return false;
	}

	public static boolean isStaff(Player player) {
		if (player.getRights() == 1 || player.getRights() == 2 || player.getRights() == 3 || player.getRights() == 4) {
			return true;
		}
	
		return false;
	}
	
	public static boolean isModerator(Player player) {
		if (player.getRights() == 1) {
			return true;
		}
		return false;
	}
	
	public static boolean isAdministrator(Player player) {
		if (player.getRights() == 2) {
			return true;
		}
		return false;
	}
	
	public static boolean isIron(Player player) {
		if (player.getRights() == 11) {
			return true;
		}
		return false;
	}
	
	public static boolean isMember(Player player) {
		if (player.isMember()) {
			return true;
		}
		return false;
	}
	
	
	/**
	 * Checks if player is Owner
	 * 
	 * @param p
	 * @return
	 */
	public static boolean isOwner(Player p) {
		return  p.getUsername().equalsIgnoreCase("prickachu") || p.getUsername().equalsIgnoreCase("affe") || p.getUsername().equalsIgnoreCase("") || p.getUsername().equalsIgnoreCase("");
	}

	/**
	 * Checks if player is Developer
	 * 
	 * @param p
	 * @return
	 */
	public static boolean isDeveloper(Player p) {
	return p.getUsername().equalsIgnoreCase("prickachu") || p.getUsername().equalsIgnoreCase("affe") || p.getUsername().equalsIgnoreCase("") || p.getUsername().equalsIgnoreCase("");
	}

	/**
	 * Checks if player is setting appearance
	 * 
	 * @param player
	 * @return
	 */
	public static final boolean isSettingAppearance(Player player) {
		return player.getAttributes().get("setapp") != null;
	}

}
