package com.dark.rs2.content.shopping.impl;

import com.dark.rs2.content.interfaces.InterfaceHandler;
import com.dark.rs2.content.interfaces.impl.QuestTab;
import com.dark.rs2.content.shopping.Shop;
import com.dark.rs2.entity.item.Item;
import com.dark.rs2.entity.player.Player;
import com.dark.rs2.entity.player.net.out.impl.SendMessage;

/**
 * Shop for Achievements
 * 
 * @author Goten
 */
public class AchievementShop extends Shop {

	/**
	 * Id of shop
	 */
	public static final int SHOP_ID = 89;

	/**
	 * Prices of item in shop
	 * 
	 * @param id
	 * @return
	 */
	public static final int getPrice(int id) {
		switch (id) {

		case 6199:
			return 10;

		case 989:
			return 4;

		case 2944:
			return 15;

		case 405:
			return 4;

		case 2682:
			return 10;

		case 2803:
			return 20;

		case 2722:
			return 30;

		case 10952:
			return 25;

		case 12938:
			return 1;

		case 9470:
		case 9472:
			return 10;

		case 9944:
		case 9945:
			return 10;

		case 9005:
		case 12430:
			return 10;

		case 6547:
		case 6548:
			return 10;

		case 11990:
			return 10;

		case 12956:
		case 12957:
		case 12958:
		case 12959:
			return 15;


		case 12393:
		case 12395:
		case 12397:
		case 12439:
			return 20;


		case 12432:
		case 12600:
		case 12514:
		case 12357:
		case 12335:
			return 50;

		case 1419:
		case 12637:
		case 12638:
		case 12639:
			return 100;

		case 13190:
			return 100;

		}

		return 120;
	}

	/**
	 * Items in shop
	 */
	public AchievementShop() {
		super(SHOP_ID, new Item[] { 
			new Item(6199), 
			new Item(989), 
			new Item(2944), 
			new Item(405), 
			new Item(2682), 
			new Item(2803), 
			new Item(2722), 
			new Item(10952), 
			new Item(12938), 

			new Item(9470), 
			new Item(9472), 
			new Item(9944), 
			new Item(9945),
			new Item(9005), 
			new Item(12430),
			new Item(6547), 
			new Item(6548),
			new Item(11990), 

			new Item(12956), 
			new Item(12957), 
			new Item(12958), 
			new Item(12959),
			new Item(1419), 
			new Item(12393), 
			new Item(12395),
			new Item(12397), 
			new Item(12439),

			new Item(12432), 
			new Item(12600), 
			new Item(12514), 
			new Item(12357), 
			new Item(12335), 
			new Item(12637), 
			new Item(12638), 
			new Item(12639),
			new Item(13190), 

			
		}, false, "Achievement Store");
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
                if(player.getPA().achievementCompleted() > 10) {
			player.getClient().queueOutgoingPacket(new SendMessage("You need to complete at least 10 achievements before buying an item."));
                        return;
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

		if (player.getAchievementsPoints() < amount * getPrice(id)) {
			player.getClient().queueOutgoingPacket(new SendMessage("You do not have enough Achievements points to buy that."));
			return;
		}

		player.addAchievementPoints(player.getAchievementsPoints() - (amount * getPrice(id)));

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
		return "Achievements points";
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
