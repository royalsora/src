package com.dark.rs2.content.dialogue.impl;

import com.dark.rs2.content.dialogue.Dialogue;
import com.dark.rs2.content.dialogue.DialogueConstants;
import com.dark.rs2.content.dialogue.DialogueManager;
import com.dark.rs2.content.minigames.raid.MainHandler;
import com.dark.rs2.content.minigames.raid.RaidBoss.Boss;
import com.dark.rs2.entity.player.Player;
import com.dark.rs2.entity.player.net.out.impl.SendMessage;
import com.dark.rs2.entity.player.net.out.impl.SendRemoveInterfaces;

public class RaidCreateDialogue extends Dialogue {
	public RaidCreateDialogue(Player player) {
	this.player = player;
	}

	@Override
	public boolean clickButton(int id) {
	switch (id) {
	case DialogueConstants.OPTIONS_2_1:
		if (player.getInventory().hasItemAmount(995, 6_000_000)) {
			MainHandler.createRaid(player, Boss.BANDOS);
			player.send(new SendRemoveInterfaces());
			player.getInventory().remove(995, 6_000_000);
			player.send(new SendMessage("You created a bandos raid. You must wait for 3 more participants to start."));
		} else {
			player.send(new SendMessage("You need 6m in either your inventory or bank to create this raid."));
			player.send(new SendRemoveInterfaces());
		}
		break;

	case DialogueConstants.OPTIONS_2_2:
		player.send(new SendRemoveInterfaces());
		break;

	}

	return false;
	}

	@Override
	public void execute() {
	if (player.getRaid() != null || (player.isInRaidParty() && player.getRaid() == null)) {
		player.send(new SendMessage("You cannot create another raid untill your previous one is finished."));
		player.send(new SendRemoveInterfaces());
		next = 2;
		return;
	}
	switch (next) {
	case 0:

		DialogueManager.sendStatement(player, "Depending on what raid you want to create", "you will be charged a certain amount of gp.", "Please select a boss type.");
		next++;
		break;
	case 1:
		DialogueManager.sendOption(player, "Bandos", "More Raids Comming Soon.");
		break;
	}
	}

}
