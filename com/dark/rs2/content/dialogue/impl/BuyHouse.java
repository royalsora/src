package com.dark.rs2.content.dialogue.impl;

import java.io.IOException;

import com.dark.rs2.content.dialogue.Dialogue;
import com.dark.rs2.content.dialogue.DialogueConstants;
import com.dark.rs2.content.dialogue.DialogueManager;
import com.dark.rs2.content.dialogue.Emotion;
import com.dark.rs2.entity.player.Player;
import com.dark.rs2.entity.player.net.out.impl.SendRemoveInterfaces;

public class BuyHouse extends Dialogue {

	public BuyHouse(Player player) {
	this.player = player;
	}

	@Override
	public boolean clickButton(int id) {
	switch (id) {
	case DialogueConstants.OPTIONS_2_1:
		try {
			player.getHouseManager().buyHouse(player);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	switch (next) {
	case 0:
		DialogueManager.sendNpcChat(player, 1353, Emotion.HAPPY, "Hello " + player.getUsername(), "I am the property seller.", "Would you like to buy a house?");
		next++;
		break;
	case 1:
		DialogueManager.sendNpcChat(player, 1353, Emotion.HAPPY, "Before you decide on purchasing a house", "Note that cost of a house is 300m.", "Are you sure you want it?");
		next++;
		break;
	case 2:
		DialogueManager.sendOption(player, "Yes", "Too rich for my blood.");
		break;
	}
	}

}
