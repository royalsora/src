package com.dark.rs2.content.dialogue.impl;

import com.dark.rs2.content.dialogue.Dialogue;
import com.dark.rs2.content.dialogue.DialogueManager;
import com.dark.rs2.content.dialogue.Emotion;

public class StartRaidDialogue extends Dialogue {

	@Override
	public boolean clickButton(int id) {
	// TODO Auto-generated method stub
	return false;
	}

	@Override
	public void execute() {
	switch (next) {
	case 0:
		DialogueManager.sendNpcChat(player, 725, Emotion.CALM_CONTINUED, "You currently have " + player.getRaid().partySize + " party members.", "You area allowed a maxium of 10 party members.", "Would you like to continue?");
		next++;
		break;
	case 1:
		DialogueManager.sendOption(player, "Yes", "No, wait for more members.");
		break;
	}

	}

}
