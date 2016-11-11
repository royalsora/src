package com.dark.rs2.content.dialogue.impl;

import com.dark.rs2.content.dialogue.Dialogue;
import com.dark.rs2.content.dialogue.DialogueConstants;
import com.dark.rs2.content.dialogue.DialogueManager;
import com.dark.rs2.content.dialogue.Emotion;
import com.dark.rs2.content.minigames.raid.MainHandler;
import com.dark.rs2.entity.player.Player;
import com.dark.rs2.entity.player.net.out.impl.SendRemoveInterfaces;

public class RaidStartDialouge extends Dialogue {
	public RaidStartDialouge(Player player) {
	this.player = player;
	}

	@Override
	public boolean clickButton(int id) {
	switch (id) {
	case DialogueConstants.OPTIONS_2_1:
		player.send(new SendRemoveInterfaces());
		MainHandler.startRaid(player);
		break;
	case DialogueConstants.OPTIONS_2_2:
		player.send(new SendRemoveInterfaces());
		break;
	}
	return false;
	}

	public void execute() {
	switch (next) {
	case 0:
		DialogueManager.sendNpcChat(player, 765, Emotion.CALM_CONTINUED, "You have a party size of " + player.getRaid().partySize + ".", "Would you like to start now or ", "continue waiting for more party members.");
		next++;
		break;
	case 1:
		DialogueManager.sendOption(player, "Yes start now", "No continue waiting");
		break;
	}
	}

}
