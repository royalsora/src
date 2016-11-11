package com.dark.rs2.content;

import com.dark.core.util.Utility;
import com.dark.rs2.entity.Animation;
import com.dark.rs2.entity.Graphic;
import com.dark.rs2.entity.World;
import com.dark.rs2.entity.item.Item;
import com.dark.rs2.entity.player.Player;
import com.dark.rs2.entity.player.net.out.impl.SendMessage;

/**
 * Handles RareChest
 * 
 * @author jamian
 *
 */
public class RareChest {
	
	public static int getLength() {
	return CHEST_REWARDS.length;
}



	/**
	 * Rare key Id
	 */
	public static final Item KEY = new Item(2944);

	

	/**
	 * Rare Chest rewards
	 */
	private static final int[] CHEST_REWARDS = { 2682, 2803, 2722, 4087, 4585, 1187, 1149, 4587, 4214, 4225, 12612, 12610, 12608, 12954, 10551, 7462, 6570, 10548, 3481, 3483, 3485, 3486, 3488, 608, 7783, 7798, 7795, 7792, 7786, 7780, 7789 }; 
	
	public static final int[] RARE_REWARDS = { 11840, 6920, 2577, 2579, 12598, 12800, 12802, 12751, 6571, 11286, 11928, 11929, 11930, 11931, 11932, 11933, 12389, 12391 }; 
	
	public static final int[] MEGA_RARE_REWARDS = { 11847, 4151, 12596, 2581, 962, 12526, 11335, 13190  };
	
	/**
	 * Searches the Rare chest
	 * 
	 * @param player
	 * @param x
	 * @param y
	 */

	public static void searchChest(final Player player, final int x, final int y) {
		if (player.getInventory().contains(KEY)) {
			player.send(new SendMessage("You unlock the chest with your key."));
			player.getInventory().remove(KEY);
			//AchievementHandler.activateAchievement(player, AchievementList.OPEN_THE_RARE_CHEST_5_TIMES, 1);
			player.getUpdateFlags().sendAnimation(new Animation(881));
			player.getUpdateFlags().sendGraphic(new Graphic(1100, true));
			//Item itemReceived;
			switch (Utility.random(1)) {
			default:
			player.getInventory().add(2722, 1);
			//	player.getInventory().addOrCreateGroundItem(itemReceived.getId(), itemReceived.getAmount(), true);
				player.getInventory().add(CHEST_REWARDS[Utility.random(CHEST_REWARDS.length - 1)], 1); //adds the "CHEST_REWARDS" into invent.
				
			}
			if (Utility.random(5) == 1) { // 1 in 20 rate
				player.getInventory().add(RARE_REWARDS[Utility.random(RARE_REWARDS.length - 1)], 1);
			}
			if (Utility.random(25) == 1) { // 1/150 rate
				player.getInventory().add(MEGA_RARE_REWARDS[Utility.random(getLength() - 1)], 1); //adds the "MEGA_RARE_REWARDS" into invent.
				World.sendGlobalMessage("<shad=0>@yel@" + player.determineIcon(player) + " " + player.getUsername() + " has recieved a rare item from a Golden Key");
			}

		}
	}
	

}
