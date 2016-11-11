package com.dark.rs2.content.housing;

import java.io.IOException;

import com.dark.rs2.entity.Location;
import com.dark.rs2.entity.player.Player;
import com.dark.rs2.entity.player.PlayerConstants;
import com.dark.rs2.entity.player.net.out.impl.SendInterface;
import com.dark.rs2.entity.player.net.out.impl.SendMessage;
import com.dark.rs2.entity.player.net.out.impl.SendRemoveInterfaces;
import com.dark.rs2.entity.player.net.out.impl.SendSidebarInterface;
import com.dark.rs2.entity.player.net.out.impl.SendString;

public class HouseManager {

	/* Purchase a house */
	public void buyHouse(Player player) throws IOException {
	if (player.getHouse() != null) {
		player.send(new SendMessage("You already own a house."));
		return;
	}
	if (player.getInventory().hasItemAmount(995, 300_000_000)) {
		player.setHouse(new House());
		player.getInventory().remove(995, 300_000_000);
		ParseLoad.HOUSES.put(player.getUsername(), new House());
		System.out.println(ParseLoad.HOUSES.get(player.getUsername()));
		ParseLoad.HOUSES.get(player.getUsername()).setName(player.getUsername());
		enterHouse(player);
		ParseLoad.saveHouse(player);
	} else {
		player.send(new SendMessage("You need 300m to purchase a house."));
		player.send(new SendRemoveInterfaces());
		return;
	}
	}

	/* House owner enters house */
	public void enterHouse(Player player) {
	try {
		ParseLoad.saveHouse(player);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	for (int i = 0; i < PlayerConstants.SIDEBAR_INTERFACE_IDS.length; i++)
		player.send(new SendSidebarInterface(i, i != 3 && i != 2 && i != 7 && i != 0 && i != 6 && i != 8 && i != 9 && i != 4 ? -1 : i == 7 ? 15000 : i == 6 ? player.getMagic().getMagicBook() : PlayerConstants.SIDEBAR_INTERFACE_IDS[i]));
	// loadExistingObjects
	if (player.getHouse().getObject() != null) {
		for (HouseObject obj : ParseLoad.HOUSES.get(player.getUsername()).getObject()) {
			player.setHouseObjectHandler(new ObjectHandler(player, obj.getX(), obj.getY(), obj.getObjectId(), obj.getFace(), false));
		}
	} else {
		System.out.println("no obj");
	}
	player.setInHouse(true);
	player.teleport(new Location(3180, 9758, player.getZ() * 4));
	player.setHouseMode("Private");
	loadHouseText(player);
	player.send(new SendMessage("Welcome to your home."));
	}

	public void sendInvite(Player owner, Player guest) {
	if (ParseLoad.HOUSES.get(owner.getUsername()).getBAN_LIST().contains(guest.getUsername())) {
		owner.send(new SendMessage(guest.getUsername() + " is on your ban list. Unban him before inviting him."));
		return;
	}
	if (owner.isLocked()) {
		owner.send(new SendMessage("You cannot invite someone while your house is locked."));
		return;
	}
	if (guest.isInHouse()) {
		owner.send(new SendMessage("You cannot invite someone who is already in a house."));
		return;
	}
	if (owner == guest) {
		owner.send(new SendMessage("You cannot invite yourself to your own house."));
		return;
	}
	if (owner.VISITORS.contains(guest)) {
		owner.send(new SendMessage(guest.getUsername() + " is already in your house."));
		return;
	}
	if (guest.getCombat().inCombat()) {
		owner.send(new SendMessage(guest.getUsername() + " is currently in combat."));
		return;
	}
	if (guest.inWilderness() && guest.getWildernessLevel() > 10) {
		owner.send(new SendMessage(guest.getUsername() + " is currently to deep in the wilderness."));
		return;
	}
	guest.send(new SendInterface(55500));
	guest.send(new SendString("Continue with " + owner.getUsername() + "'s house invite?", 55510));
	guest.setHouseOwnersRequest(owner);
	}

	// Adds guest to owners house.
	public void acceptInvite(Player owner, Player guest) {
	if (owner.isLocked()) {
		guest.send(new SendMessage(owner.getUsername() + " has locked the house."));
		return;
	}
	joinHouse(owner, guest);
	loadHouseText(owner);
	}

	public void declineInvite(Player owner, Player guest) {
	owner.send(new SendMessage(guest.getUsername() + " has denied your house invite."));
	guest.setHouseOwnersRequest(null);
	guest.send(new SendRemoveInterfaces());
	return;
	}

	// Join owners house.
	public void joinHouse(Player owner, Player guest) {
	if (ParseLoad.HOUSES.get(owner.getUsername()).getBAN_LIST().contains(guest.getUsername())) {
		guest.send(new SendMessage("You are banned from this house."));
		return;
	}
	if (owner.isLocked()) {
		guest.send(new SendMessage(owner.getUsername() + " has locked the house."));
		return;
	}
	owner.VISITORS.add(guest);
	owner.send(new SendMessage(guest.getUsername() + " has joined your house"));
	guest.teleport(new Location(3180, 9758, owner.getZ()));
	guest.send(new SendMessage("You have joined " + owner.getUsername() + "'s house."));
	guest.setInHouse(true);
	for (int i = 0; i < PlayerConstants.SIDEBAR_INTERFACE_IDS.length; i++)
		guest.send(new SendSidebarInterface(i, i != 3 && i != 2 && i != 7 && i != 0 && i != 6 && i != 7 && i != 8 && i != 9 && i != 4 ? -1
				/* : i == 7 ? 15000 */ : i == 6 ? guest.getMagic().getMagicBook() : PlayerConstants.SIDEBAR_INTERFACE_IDS[i]));
	}

	// Loads players in house, house mode, and visitor count.
	private int counter = 0;

	public void loadHouseText(Player owner) {
	for (int i = 0; i < 25; i++) {
		owner.send(new SendString("", 35021 + i));
	}
	if (owner.VISITORS.size() > 0) {
		owner.VISITORS.forEach(players -> {
			owner.send(new SendString(players.getUsername(), 35021 + counter));
			counter++;
		});
		counter = 0;
	}
	owner.send(new SendString("House Mode: " + owner.getHouseMode() + " Mode", 14993));
	owner.send(new SendString("Visior Count: " + owner.VISITORS.size(), 14992));
	}

	public void resetHouse(Player player) {
	System.out.println(ParseLoad.HOUSES.size());
	if (ParseLoad.HOUSES.containsKey(player.getUsername())) {
		System.out.println(player.getUsername() + " has done a house owner reset.");
		if (!ParseLoad.HOUSES.get(player.getUsername()).getObject().isEmpty()) {
			for (HouseObject obj : ParseLoad.HOUSES.get(player.getUsername()).getObject()) {
				player.setHouseObjectHandler(new ObjectHandler(player, obj.getX(), obj.getY(), obj.getObjectId(), obj.getFace(), true));
			}
		}
		for (Player players : player.VISITORS) {
			for (int i = 0; i < PlayerConstants.SIDEBAR_INTERFACE_IDS.length; i++)
				players.send(new SendSidebarInterface(i, i == 6 ? player.getMagic().getMagicBook() : PlayerConstants.SIDEBAR_INTERFACE_IDS[i]));
			players.send(new SendMessage("Owner has left the house and has retured the visitors back home."));
			players.setInHouse(false);
			players.setHouseOwnersRequest(null);
			players.teleport(PlayerConstants.HOME);
		}

		for (int i = 0; i < PlayerConstants.SIDEBAR_INTERFACE_IDS.length; i++)
			player.send(new SendSidebarInterface(i, i == 6 ? player.getMagic().getMagicBook() : PlayerConstants.SIDEBAR_INTERFACE_IDS[i]));
		player.VISITORS.clear();
		player.setInHouse(false);
		player.teleport(PlayerConstants.HOME);
		ParseLoad.HOUSES.remove(player.getUsername(), ParseLoad.HOUSES.get(player.getUsername()));
		return;
	} else {
		for (int i = 0; i < PlayerConstants.SIDEBAR_INTERFACE_IDS.length; i++)
			player.send(new SendSidebarInterface(i, i == 6 ? player.getMagic().getMagicBook() : PlayerConstants.SIDEBAR_INTERFACE_IDS[i]));
		if (!player.VISITORS.isEmpty()) {
			player.getHouseOwnersRequest().send(new SendMessage(player.getUsername() + " has left your home."));
			player.getHouseOwnersRequest().VISITORS.remove(player);
			loadHouseText(player.getHouseOwnersRequest());
		}
		player.teleport(PlayerConstants.HOME);
		player.setInHouse(false);
		player.setHouseOwnersRequest(null);
	}
	}

}
