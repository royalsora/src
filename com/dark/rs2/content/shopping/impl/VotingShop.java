package com.dark.rs2.content.shopping.impl;

import com.dark.rs2.content.interfaces.InterfaceHandler;
import com.dark.rs2.content.interfaces.impl.QuestTab;
import com.dark.rs2.content.shopping.Shop;
import com.dark.rs2.entity.item.Item;
import com.dark.rs2.entity.player.Player;
import com.dark.rs2.entity.player.net.out.impl.SendMessage;

/**
 * Voting store
 * 
 * @author Goten
 */
public class VotingShop extends Shop {

	/**
	 * Id of Bounty shop
	 */
	public static final int SHOP_ID = 92;

	/**
	 * Price of items in Bounty store
	 * 
	 * @param id
	 * @return
	 */
	public static final int getPrice(int id) {
		switch (id) {
		case 6199:
			return 5;

		case 989:
			return 1;
		case 608:
			return 1;

		case 405:
			return 1;

		case 2682:
			return 5;

		case 2803:
			return 10;

		case 2722:
			return 15;

		case 10952:
			return 10;

		case 12938:
			return 1;


		case 12695:
			return 1;

		case 11090:
			return 1;

		case 2944:
			return 8;



		}
		return 2147483647;
	}

	/**
	 * All items in Bounty store
	 */
	public VotingShop() {
		super(SHOP_ID, new Item[] {
			new Item(6199), 
			new Item(608), 
			new Item(989),  
			new Item(405), 
			new Item(2682), 
			new Item(2803), 
			new Item(2722), 
			new Item(10952), 
			new Item(12938), 
			new Item(12695), 
			new Item(11090),
			new Item(2944),


		}, false, "Voting Store");
	}

	@Override
	public void buy(Player player, int slot, int id, int amount) {
		if (!hasItem(slot, id))
			return;
		if (get(slot).getAmount() == 0)
			return;
		if (amount > get(slot).getAmount()) {
			amount = get(slot).getAmount();
		}

		Item buying = new Item(id, amount);

		if (!player.getInventory().hasSpaceFor(buying)) {
			if (!buying.getDefinition().isStackable()) {
				int slots = player.getInventory().getFreeSlots();
				if (slots > 0) {
					buying.setAmount(slots);
					amount = slots;
				} else {
					player.getClient().queueOutgoingPacket(new SendMessage("You do not have enough inventory space to buy this item."));
				}
			} else {
				player.getClient().queueOutgoingPacket(new SendMessage("You do not have enough inventory space to buy this item."));
				return;
			}
		}

		if (player.getVotePoints() < amount * getPrice(id)) {
			player.getClient().queueOutgoingPacket(new SendMessage("You do not have enough Vote points to buy that."));
			return;
		}

		player.setVotePoints(player.getVotePoints() - amount * getPrice(id));

		InterfaceHandler.writeText(new QuestTab(player));

		player.getInventory().add(buying);
		update();
	}

	@Override
	public int getBuyPrice(int id) {
		return 0;
	}

	@Override
	public String getCurrencyName() {
		return "Vote points";
	}

	@Override
	public int getSellPrice(int id) {
		return getPrice(id);
	}

	@Override
	public boolean sell(Player player, int id, int amount) {
		player.getClient().queueOutgoingPacket(new SendMessage("You cannot sell items to this shop."));
		return false;
	}
}
