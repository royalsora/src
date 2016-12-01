package com.dark.rs2.content.shopping.impl;

import com.dark.rs2.content.interfaces.InterfaceHandler;
import com.dark.rs2.content.interfaces.impl.QuestTab;
import com.dark.rs2.content.shopping.Shop;
import com.dark.rs2.entity.item.Item;
import com.dark.rs2.entity.player.Player;
import com.dark.rs2.entity.player.net.out.impl.SendMessage;

/**
 * Shop for trivia, formally known as magearena
 * 
 * @author Daniel
 */
public class TriviaShop extends Shop {

	/**
	 * Id of shop
	 */
	public static final int SHOP_ID = 95;

	/**
	 * Prices of item in shop
	 * 
	 * @param id
	 * @return
	 */
	public static final int getPrice(int id) {
		switch (id) {

		case 6199: 
			return 6;

		case 989:  
			return 2;

		case 405: 
			return 2;

		case 12938: 
			return 1;

		case 3835: 
		case 3836: 
		case 3837: 
		case 3838: 
		case 3827: 
		case 3828: 
		case 3829: 
		case 3830: 
		case 3831: 
		case 3832: 
		case 3833: 
		case 3834: 
			return 3;

		case 12428: 
		case 12361: 
		case 12355: 
			return 15;

		case 12337: 
		case 7803:
		case 6856:
		case 6857:
		case 6858:
		case 6859:
		case 6860:
		case 6861:
		case 6862:
		case 6863: 	
			return 10;

		case 12351: 
		case 12249: 
		case 12245: 
		case 1506:
		case 12359:
			return 15;

		case 7451: 
			return 8;

		case 6831: 
			return 12;

		case 2944: 
			return 8;

		case 7462: 
			return 12;



		}

		return 2147483647;
	}

	/**
	 * Items in shop
	 */
	public TriviaShop() {
		super(SHOP_ID, new Item[] { 
			new Item(6199), 
			new Item(989), 
			new Item(405), 
			new Item(12938), 
			new Item(2944), 
			new Item(3827), 
			new Item(3828), 
			new Item(3829), 
			new Item(3830), 
			new Item(12428), 
			new Item(12361), 
			new Item(12355), 
			new Item(12337), 
			new Item(7803), 
			new Item(3831), 
			new Item(3832), 
			new Item(3833), 
			new Item(3834), 
			new Item(12351), 
			new Item(12249), 
			new Item(12245), 
			new Item(7462), 
			new Item(7451), 
			new Item(3835), 
			new Item(3836), 
			new Item(3837), 
			new Item(3838),
			new Item(6856),
			new Item(6857),
			new Item(6858),
			new Item(6859),
			new Item(6860),
			new Item(6861),
			new Item(6862),
			new Item(6863),
			new Item(12359),


	 
		}, false, "Trivia Store");
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

		if (player.getTriviaPoints() < amount * getPrice(id)) {
			player.getClient().queueOutgoingPacket(new SendMessage("You do not have enough Trivia points to buy that."));
			return;
		}

		player.setTriviaPoints(player.getTriviaPoints() - (amount * getPrice(id)));

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
		return "Trivia points";
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
