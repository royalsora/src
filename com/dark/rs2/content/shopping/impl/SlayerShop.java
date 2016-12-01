package com.dark.rs2.content.shopping.impl;

import com.dark.rs2.content.interfaces.InterfaceHandler;
import com.dark.rs2.content.interfaces.impl.QuestTab;
import com.dark.rs2.content.shopping.Shop;
import com.dark.rs2.entity.item.Item;
import com.dark.rs2.entity.player.Player;
import com.dark.rs2.entity.player.net.out.impl.SendMessage;

/**
 * Slayer store
 * 
 * @author Daniel
 */
public class SlayerShop extends Shop {

	/**
	 * Id of slayer shop
	 */
	public static final int SHOP_ID = 6;

	/**
	 * Price of items in slayer store
	 * 
	 * @param id
	 * @return
	 */
	public static final int getPrice(int id) {
		switch (id) {
		case 6199:
			return 25;
		case 5841:
			return 5;
		case 989:
			return 15;

		case 7789:
			return 35;

		case 12938:
			return 10;

		case 2572:
			return 50;

		case 11866:
			return 50;

		case 11864:
		case 8650:
		case 8656:
		case 8662:
		case 8664:
		case 8666:
			return 150;

		case 11865:
			return 185;

		case 7462:
			return 40;

		case 10548:
			return 85;

		case 10551:
			return 125;

		case 10555:
			return 75;

		case 6831:
			return 60;

		case 2944:
			return 40;

		case 6949:
			return 45;

		case 4283:
		case 12785:
			return 200;

		case 6:
		case 8:
		case 10:
		case 12:
			return 250;
		
		case 290:
			return 1000;

		case 12897:
			return 600;

		case 13116:
			return 500;

		case 13128:
			return 2000;

		}
		return 2147483647;
	}

	/**
	 * All items in slayer store
	 */
	public SlayerShop() {
		super(SHOP_ID, new Item[] { 

new Item(6199),
new Item(5841),
new Item(989),
new Item(7789),
new Item(12938),
new Item(2572),
new Item(12785),
new Item(11864), 
new Item(11865),
new Item(11866), 
new Item(7462), 
new Item(10548), 
new Item(10551), 
new Item(10555),   
new Item(2944),
new Item(6949),
new Item(4283),
new Item(6),
new Item(8),
new Item(10),
new Item(12),
new Item(8650),
new Item(8656),
new Item(8662),
new Item(8664),
new Item(8666),
new Item(290),
new Item(12897),
new Item(13116),
new Item(13128),


		}, false, "Slayer Shop");
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

		if (player.getSlayerPoints() < amount * getPrice(id)) {
			player.getClient().queueOutgoingPacket(new SendMessage("You do not have enough Slayer points to buy that."));
			return;
		}

		player.setSlayerPoints(player.getSlayerPoints() - amount * getPrice(id));

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
		return "Slayer points";
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
