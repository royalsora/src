package com.dark.rs2.content.dialogue.impl;

import com.dark.rs2.content.dialogue.Dialogue;
import com.dark.rs2.content.dialogue.DialogueConstants;
import com.dark.rs2.content.dialogue.DialogueManager;
import com.dark.rs2.content.minigames.raid.MainHandler;
import com.dark.rs2.entity.World;
import com.dark.rs2.entity.mob.Mob;
import com.dark.rs2.entity.player.Player;
import com.dark.rs2.entity.player.net.out.impl.SendMessage;
import com.dark.rs2.entity.player.net.out.impl.SendRemoveInterfaces;

public class RaidJoinDialouge extends Dialogue {

	public RaidJoinDialouge(Player player) {
	this.player = player;
	}

	@Override
	public boolean clickButton(int id) {
	switch (id) {
	case DialogueConstants.OPTIONS_2_1:
		MainHandler.addPlayerToRaidParty(World.getPlayerByName(MainHandler.raidList.get(0)), player);
		player.send(new SendRemoveInterfaces());
		break;
	case DialogueConstants.OPTIONS_2_2:
		MainHandler.addPlayerToRaidParty(World.getPlayerByName(MainHandler.raidList.get(1)), player);
		player.send(new SendRemoveInterfaces());
		break;
	case DialogueConstants.OPTIONS_3_1:
		MainHandler.addPlayerToRaidParty(World.getPlayerByName(MainHandler.raidList.get(0)), player);
		player.send(new SendRemoveInterfaces());
		break;
	case DialogueConstants.OPTIONS_3_2:
		MainHandler.addPlayerToRaidParty(World.getPlayerByName(MainHandler.raidList.get(1)), player);
		player.send(new SendRemoveInterfaces());
		break;
	case DialogueConstants.OPTIONS_3_3:
		MainHandler.addPlayerToRaidParty(World.getPlayerByName(MainHandler.raidList.get(2)), player);
		player.send(new SendRemoveInterfaces());
		break;
	}
	return false;
	}

	@Override
	public void execute() {

	switch (next) {
	case 0:
		if (MainHandler.raidList.isEmpty()) {
			DialogueManager.sendStatement(player, "There are no raids currently active.");
			next = 2;
			return;
		}
		switch (MainHandler.raidList.size()) {
		case 2:
			DialogueManager.sendOption(player, "Host: " + MainHandler.raidList.get(0) + ", Raid: " + Mob.getDefinition(World.getPlayerByName(MainHandler.raidList.get(0)).getRaid().bossType.getId()).getName(), "Host: " + MainHandler.raidList.get(1) + ", Raid: " + Mob.getDefinition(World.getPlayerByName(MainHandler.raidList.get(0)).getRaid().bossType.getId()).getName());
			break;
		case 3:
			DialogueManager.sendOption(player, "Host: " + MainHandler.raidList.get(0) + ", Raid: " + Mob.getDefinition(World.getPlayerByName(MainHandler.raidList.get(0)).getRaid().bossType.getId()).getName(), "Host: " + MainHandler.raidList.get(1) + ", Raid: " + Mob.getDefinition(World.getPlayerByName(MainHandler.raidList.get(1)).getRaid().bossType.getId()).getName(), "Host: " + MainHandler.raidList.get(2) + ", Raid: " + Mob.getDefinition(World.getPlayerByName(MainHandler.raidList.get(2)).getRaid().bossType.getId()).getName());
			break;
		default:
			if(player.getUsername().equalsIgnoreCase(MainHandler.raidList.get(0)) && player.getRaid().partySize > 1) {
				DialogueManager.sendInformationBox(player, "Party size", "@bla@You have a party size of @red@" + player.getRaid().partySize, "", "", "");
				next = 2;
				return;
			} else
			if(player.getUsername().equalsIgnoreCase(MainHandler.raidList.get(0))) {
				DialogueManager.sendStatement(player, "You cannot join another person's raid while hosting your own.");
				next = 2;
				return;
			}
			if(World.getPlayerByName(MainHandler.raidList.get(0)).getRaid().playersInRaid.contains(player.getUsername())) {
				DialogueManager.sendStatement(player, "You are already in " + (MainHandler.raidList.get(0)) + "'s party.");
				next = 2;
				return;
			} 
			DialogueManager.sendStatement(player, "Continue if you wish to join " + MainHandler.raidList.get(0) + "s' lobby for a ", "@or2@" +  Mob.getDefinition(World.getPlayerByName(MainHandler.raidList.get(0)).getRaid().bossType.getId()).getName() + " @bla@raid.");
			next++;
			break;
		}
		break;
	case 1:
		if (World.getPlayerByName(MainHandler.raidList.get(0)).getRaid().playersInRaid.contains(player.getUsername())) {
			player.send(new SendMessage("You are already in this party."));
			player.send(new SendRemoveInterfaces());
		} else {
			player.send(new SendRemoveInterfaces());
			MainHandler.addPlayerToRaidParty(World.getPlayerByName(MainHandler.raidList.get(0)), player);
		}
		break;
	case 2:
		player.send(new SendRemoveInterfaces());
		break;
	}
	}
}
