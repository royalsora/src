package com.dark.rs2.content.shopping.impl;

import com.dark.rs2.content.shopping.Shop;
import com.dark.rs2.entity.item.Item;
import com.dark.rs2.entity.player.Player;
import com.dark.rs2.entity.player.net.out.impl.SendMessage;

/**
 * Shop for tokkul currency
 * 
 * @author Daniel
 */
public class TokkulShop extends Shop {
	
	/**
	 * Item id of tokkul
	 */
	public static final int TOKKUL = 6529;
	
	/**
	 * Id of tokkul store
	 */
	public static final int SHOP_ID = 4;

	/**
	 * Prices of items in store
	 * @param id
	 * @return
	 */
	public static final int getPrice(int id) {
		switch (id) {
		case 6571:
			return 200000;
		case 6568:
			return 35000;
		case 6524:
		case 6528:
		case 608:
			return 80000;
		case 6522:
			return 350;
		case 13103:
			return 500000;
		}

		return 2147483647;
	}

	/**
	 * Items in store
	 */
	public TokkulShop() {
		super(SHOP_ID, new Item[] { 
				new Item(6522, 1), 
				new Item(6568, 1), 				
				new Item(6524, 1), 
				new Item(608, 1), 
				new Item(6528, 1), 				
				new Item(6571, 1), 
				new Item(13103, 1), 				
			}, false, "Tokkul Shop");
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

		if (player.getInventory().getItemAmount(6529) < amount * getPrice(id)) {
			player.getClient().queueOutgoingPacket(new SendMessage("You do not have enough Tokkul to buy that."));
			return;
		}

		player.getInventory().remove(6529, amount * getPrice(id));

		player.getInventory().add(buying);
		update();
	}

	@Override
	public int getBuyPrice(int id) {
		return 0;
	}

	@Override
	public String getCurrencyName() {
		return "Tokkul";
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
