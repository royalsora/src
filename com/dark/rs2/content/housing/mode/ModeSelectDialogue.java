package com.dark.rs2.content.housing.mode;

import com.dark.rs2.content.dialogue.Dialogue;
import com.dark.rs2.content.dialogue.DialogueConstants;
import com.dark.rs2.content.dialogue.DialogueManager;
import com.dark.rs2.entity.player.Player;
import com.dark.rs2.entity.player.net.out.impl.SendMessage;
import com.dark.rs2.entity.player.net.out.impl.SendRemoveInterfaces;

public class ModeSelectDialogue extends Dialogue {

	public ModeSelectDialogue(Player player) {
	this.player = player;
	}

	@Override
	public boolean clickButton(int id) {
	if (player.getDelay().elapsed() < 3000) {
		player.send(new SendRemoveInterfaces());
		return false;
	}
	switch (id) {
	case DialogueConstants.OPTIONS_3_1:
		player.setHouseMode("Private");
		break;
	case DialogueConstants.OPTIONS_3_2:
		player.setHouseMode("Public");
		break;
	case DialogueConstants.OPTIONS_3_3:
		for (int i = 0; i < player.VISITORS.size(); i++) {
			player.getHouseManager().resetHouse(player.VISITORS.get(i));
		}

		player.setHouseMode("Build");
		break;
	}
	player.send(new SendMessage("You have switched to " + player.getHouseMode() + " mode."));
	player.getHouseManager().loadHouseText(player);
	player.send(new SendRemoveInterfaces());
	player.getDelay().reset();
	return false;

	}

	@Override
	public void execute() {
	switch (next) {
	case 0:
		DialogueManager.sendOption(player, "Private mode", "Public mode", "Build mode");
		break;
	}

	}
}
