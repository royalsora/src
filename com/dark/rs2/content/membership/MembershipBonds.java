package com.dark.rs2.content.membership;

import java.util.HashMap;

import com.dark.core.util.Utility;
import com.dark.rs2.content.interfaces.InterfaceHandler;
import com.dark.rs2.content.interfaces.impl.QuestTab;
import com.dark.rs2.entity.World;
import com.dark.rs2.entity.player.Player;
import com.dark.rs2.entity.player.net.out.impl.SendMessage;

/**
 * Handles Membership Bonds
 * @author Daniel
 *
 */
public class MembershipBonds {
	
	/**
	 * Bond Data
	 * @author Daniel
	 *
	 */
	public enum BondData {
		
		ONE("$5 Bond", 13190, 5, 500, 0),
		TWO("$10 Bond", 13191, 10, 1000, 50),
		THREE("$25 Bond", 13192, 25, 2500, 200),
		FOUR("$50 Bond", 13193, 50, 5000, 500),
		FIVE("$100 Bond", 13195, 100, 10000, 1250),
		SIX("200 credit", 13194, 20, 200, 25),
		SEVEN("500 credit", 13196, 50, 500, 50),
		EIGHT("1,000 credit", 13197, 100, 1000, 150),
		//TEN("20 Credit", 608, 0, 20, 0),
		NINE("2,000 credit", 13198, 200, 2000, 500);
		
		private final String name;
		private final int item;
		private final int moneySpent;
		private final int credits;
		private final int complimentary;
		
		private BondData(String name, int item, int moneySpent, int credits, int complimentary) {
			this.name = name;
			this.item = item;
			this.moneySpent = moneySpent;
			this.credits = credits;
			this.complimentary = complimentary;
		}
		
		public String getName() {
			return name;
		}
		
		public int getItem() {
			return item;
		}
		
		public int getSpent() {
			return moneySpent;
		}
		
		public int getCredits() {
			return credits;
		}
		
		public int getComplimentary() {
			return complimentary;
		}
		
		private static HashMap<Integer, BondData> bonds = new HashMap<Integer, BondData>();

		static {
			for (final BondData item : BondData.values()) {
				BondData.bonds.put(item.item, item);
			}
		}
	}

	/**
	 * Handles opening bond
	 * @param player
	 * @param itemId
	 * @return
	 */
	public static boolean handle(Player player, int itemId) {
		
		BondData data = BondData.bonds.get(itemId);

		if (data == null) {
			return false;
		}
		
		if (player.getInventory().getFreeSlots() == 0) {
			player.send(new SendMessage("Please clear up some inventory spaces before doing this!"));
			return false;
		}
		
		player.setMember(true);
		player.getInventory().remove(data.getItem(), 1);
		player.setCredits(player.getCredits() + data.getCredits());
		player.setMoneySpent(player.getMoneySpent() + data.getSpent());
		player.send(new SendMessage("@dre@Thank you for your purchase!"));
		RankHandler.upgrade(player);		
		if (data.getComplimentary() != 0) {
			player.setCredits(player.getCredits() + data.getComplimentary());
			player.send(new SendMessage("@dre@You have been complimentated " + Utility.format(data.getComplimentary()) + " credits!"));
		}
		World.sendGlobalMessage("</col>[ @dre@Horizon-OS </col>] @dre@" + player.determineIcon(player) + " " + Utility.formatPlayerName(player.getUsername()) + "</col> has just reedemed a @dre@$" + Utility.format(data.getSpent()) + "</col> Bond!");
		InterfaceHandler.writeText(new QuestTab(player));
		return true;
	}

}
