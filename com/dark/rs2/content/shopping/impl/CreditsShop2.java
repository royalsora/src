package com.dark.rs2.content.shopping.impl;

import com.dark.rs2.content.interfaces.InterfaceHandler;
import com.dark.rs2.content.interfaces.impl.QuestTab;
import com.dark.rs2.content.shopping.Shop;
import com.dark.rs2.entity.item.Item;
import com.dark.rs2.entity.player.Player;
import com.dark.rs2.entity.player.net.out.impl.SendMessage;

/**
 * Shop for pest credits
 * 
 * @author Goten
 */
public class CreditsShop2 extends Shop {

	/**
	 * Id of shop
	 */
	public static final int SHOP_ID = 90;

	/**
	 * Prices of item in shop
	 * 
	 * @param id
	 * @return
	 */
	public static final int getPrice(int id) {
		switch (id) {
		case 1038:
		case 1040:
		case 1042:
		case 1044:
		case 1046:
		case 1048:
			return 4000;
			

		case 11863:
		case 11862:
		case 12399:
			return 5000;
		
		case 1053:
		case 1055:
		case 1057:
			return 1500;

		case 11847:
			return 2000;

		case 1050:
			return 2000;

		case 13343:
			return 2500;

		case 13068:
			return 1000;

		case 6199:
			return 100;
		case 7959:
			return 600;
		case 10872:
			return 700;
		case 1499:
			return 700;
		case 1580:
			return 700;
		case 292:
			return 100;
			
		case 12789:
			return 250;
			
		case 989:
			return 50;

		case 10952:
			return 200;

		case 290:
			return 1000;

		case 11739:
			return 150;

		case 12955:
			return 600;

		case 11738:
			return 400;

		case 11996:
			return 300;

		case 3062:
			return 400;

		case 8038:
			return 250;

		case 12897:
			return 600;

		case 7142:
			return 2000;

		case 748:
			return 2000;

		case 6831:
			return 250;

		case 2944:
			return 300;

		}

		return 1000;
	}

	/**
	 * Items in shop
	 */
	public CreditsShop2() {
		super(SHOP_ID, new Item[] { 
				new Item(1038),
				new Item(1040),
				new Item(1042),
				new Item(1044),
				new Item(1046),
				new Item(1048),
				new Item(11863),
				new Item(11862),
				new Item(12399),
				new Item(1053),
				new Item(1055),
				new Item(1057),
				new Item(11847),
				new Item(1050),
				new Item(13343),	
				new Item(13068),
				new Item(7959),
				new Item(6199),
				new Item(12789),
				new Item(12897),		
				new Item(290),	
				new Item(11739),
				new Item(12955),
				new Item(11738),	
				new Item(11996),
				new Item(3062),
				new Item(8038),
				new Item(989),
				new Item(2944),	
				new Item(7142),

		}, false, "Donator Shop");
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

		if (player.getCredits() < amount * getPrice(id)) {
			player.getClient().queueOutgoingPacket(new SendMessage("You do not have enough Credits to buy that."));
			return;
		}

		player.setCredits(player.getCredits() - (amount * getPrice(id)));

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
		return "Credits";
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
