package com.dark.rs2.content.dialogue.impl;

import com.dark.core.util.GameDefinitionLoader;
import com.dark.rs2.content.dialogue.Dialogue;
import com.dark.rs2.content.dialogue.DialogueConstants;
import com.dark.rs2.content.dialogue.DialogueManager;
import com.dark.rs2.content.dialogue.Emotion;
import com.dark.rs2.entity.player.Player;
import com.dark.rs2.entity.player.net.out.impl.SendRemoveInterfaces;

/**
 * Handles the Oziach dialogue
 * @author Daniel
 *
 */
public class OziachDialogue extends Dialogue {
	
	public OziachDialogue(Player player) {
		this.player = player;
	}
	
	public void makeShield() {
		if (!player.getInventory().hasItemAmount(995, 2_000_000)) {
			DialogueManager.sendNpcChat(player, 822, Emotion.DEFAULT, "You need 2 Million coins to do this!", "You have " + player.getInventory().getItemAmount(995) + ".");
			return;
		}
		if (!player.getInventory().hasItemAmount(11286, 1)) {
			DialogueManager.sendNpcChat(player, 822, Emotion.DEFAULT, "You need a " + GameDefinitionLoader.getItemDef(11286).getName() + ".");
			return;
		}
		player.getInventory().remove(995,2_000_000);
		player.getInventory().remove(11286, 1);
		player.getInventory().add(11283, 1);
		DialogueManager.sendNpcChat(player, 822, Emotion.DEFAULT, "Good luck.");
		player.send(new SendRemoveInterfaces());
	}

	@Override
	public boolean clickButton(int id) {
		switch (id) {
		
		case DialogueConstants.OPTIONS_2_1:
			setNext(2);
			execute();
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
			DialogueManager.sendNpcChat(player, 822, Emotion.DEFAULT, "Hello there!", "How may I help you?");
			next ++;
			break;
		case 1:
			DialogueManager.sendOption(player, "Can make me a dragonfire shield?", "Nothing.");
			break;
		case 2:
			DialogueManager.sendNpcChat(player, 822, Emotion.DEFAULT, "Yes of course!", "It will cost 5 million coins.", "You also need a Dragonic visage.");
			next ++;
			break;
		case 3:
			makeShield();
			break;
			
		}
	}

}
