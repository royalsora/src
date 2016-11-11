package com.dark.rs2.content.housing.powers;

import java.io.IOException;

import com.dark.rs2.content.housing.ParseLoad;
import com.dark.rs2.entity.player.Player;
import com.dark.rs2.entity.player.net.out.impl.SendMessage;
import com.dark.rs2.entity.player.net.out.impl.SendRemoveInterfaces;

public class PowerHandler {

	// Ban players
	public void banPlayer(Player owner, Player playerToBan) {
	if (ParseLoad.HOUSES.get(owner.getUsername()).getBAN_LIST().contains(playerToBan)) {
		owner.send(new SendMessage(playerToBan.getUsername() + " is already on your ban list."));
		owner.send(new SendRemoveInterfaces());
		return;
	}
	if (owner.VISITORS.contains(playerToBan)) {
		owner.getHouseManager().resetHouse(playerToBan);
	}
	ParseLoad.HOUSES.get(owner.getUsername()).addToBan(playerToBan.getUsername());
	playerToBan.send(new SendMessage("You have been permantly banned from " + owner.getUsername() + "'s house."));
	for (Player players : owner.VISITORS) {
		players.send(new SendMessage(playerToBan.getUsername() + " has been banned from the house."));
	}
	owner.send(new SendMessage("You have sucuesfully banned " + playerToBan.getUsername() + " from your house."));
	try {
		ParseLoad.saveHouse(owner);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	owner.send(new SendRemoveInterfaces());
	}

	// Unban player
	public void unbanPlayer(Player owner, Player playerToUnban) {
	if (!ParseLoad.HOUSES.get(owner.getUsername()).getBAN_LIST().contains(playerToUnban.getUsername())) {
		owner.send(new SendMessage(playerToUnban.getUsername() + " is not on your ban list."));
		owner.send(new SendRemoveInterfaces());
		return;
	}
	ParseLoad.HOUSES.get(owner.getUsername()).getBAN_LIST().remove(playerToUnban.getUsername());
	owner.send(new SendMessage("You have unbanned " + playerToUnban.getUsername() + "."));
	playerToUnban.send(new SendMessage("You have been unbanned from " + owner.getUsername() + "'s house."));
	try {
		ParseLoad.saveHouse(owner);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	owner.send(new SendRemoveInterfaces());
	}

	// Kick players
	public void kickPlayer(Player owner, Player playerToKick) {
	owner.getHouseManager().resetHouse(playerToKick);
	playerToKick.send(new SendMessage("You have been kicked from " + owner.getUsername() + "'s house."));
	for (Player players : owner.VISITORS) {
		owner.VISITORS.remove(players);
		owner.getHouseManager().loadHouseText(owner);
		players.send(new SendMessage(playerToKick.getUsername() + " has been kicked from the house."));
	}
	owner.send(new SendMessage("You have sucuesfully kicked " + playerToKick.getUsername() + " from your house."));
	owner.send(new SendRemoveInterfaces());
	}

	// Lock
	public void lockHouse(Player player) {
	if (player.isLocked()) {
		player.send(new SendMessage("You have unlocked your house."));
		player.send(new SendRemoveInterfaces());
		player.setLocked(false);
		return;
	}
	player.setLocked(true);
	player.send(new SendMessage("You have succesfully locked your house."));
	player.send(new SendRemoveInterfaces());
	}
}
