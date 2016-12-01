package com.dark.rs2.content.shopping.impl;

import com.dark.rs2.content.shopping.Shop;
import com.dark.rs2.entity.item.Item;
import com.dark.rs2.entity.player.Player;
import com.dark.rs2.entity.player.net.out.impl.SendMessage;

/**
 * Shop for graceful currency
 * 
 * @author Daniel
 */
public class GracefulShop extends Shop {
	
	/**
	 * Item id of graceful
	 */
	public static final int GRACE_MARKS = 11849;
	
	/**
	 * Id of graceful store
	 */
	public static final int SHOP_ID = 3;

	/**
	 * Prices of items in store
	 * @param id
	 * @return
	 */
	public static final int getPrice(int id) {
		switch (id) {
		case 11850:
			return 15;

		case 11854:
			return 25;

		case 11856:
			return 30;
		case 11858:

			return 15;

		case 11860:
		case 11852:
			return 20;
	
		case 7783:
			return 30;

		case 1767:
		case 1765:
		case 1763:
		case 1771:
		case 1773:
			return 175;
				
		case 13132;
			return 300;
				
		}

		return 2147483647;
	}

	/**
	 * Items in store
	 */
	public GracefulShop() {
		super(SHOP_ID, new Item[] { 
				new Item(11850, 1), 
				new Item(11852, 1), 
				new Item(11854, 1), 
				new Item(11856, 1), 
				new Item(11858, 1), 
				new Item(11860, 1), 
				new Item(7783, 1),
				new Item(1767, 1), 
				new Item(1765, 1), 				
				new Item(1763, 1), 
				new Item(1771, 1), 
				new Item(1773, 1),
				new Item(13132, 1)
			}, false, "Graceful Store");
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

		if (player.getInventory().getItemAmount(GRACE_MARKS) < amount * getPrice(id)) {
			player.getClient().queueOutgoingPacket(new SendMessage("You do not have enough grace marks to buy that."));
			return;
		}

		player.getInventory().remove(GRACE_MARKS, amount * getPrice(id));

		player.getInventory().add(buying);
		update();
	}

	@Override
	public int getBuyPrice(int id) {
		return 0;
	}

	@Override
	public String getCurrencyName() {
		return "Marks Of grace";
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
