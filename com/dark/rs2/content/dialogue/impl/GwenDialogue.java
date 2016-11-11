package com.dark.rs2.content.dialogue.impl;

import com.dark.core.util.GameDefinitionLoader;
import com.dark.rs2.content.dialogue.Dialogue;
import com.dark.rs2.content.dialogue.DialogueConstants;
import com.dark.rs2.content.dialogue.DialogueManager;
import com.dark.rs2.content.dialogue.Emotion;
import com.dark.rs2.entity.player.Player;
import com.dark.rs2.entity.player.net.out.impl.SendRemoveInterfaces;

/**
 * Handles the Princess dialogue
 * @author Jamian
 *
 */
public class GwenDialogue extends Dialogue {
	
	public GwenDialogue(Player player) {
		this.player = player;
	}
	
	public void makeTotem() {	
		if (!player.getInventory().hasItemAmount(12934, 50_000)) {
			DialogueManager.sendNpcChat(player, 1617, Emotion.DEFAULT, "You need 50,00 Zulrah Scales to do this!", "You have " + player.getInventory().getItemAmount(12934) + ".");
			return;
		}
		if (!player.getInventory().hasItemAmount(1857, 1)) {
			DialogueManager.sendNpcChat(player, 1617, Emotion.DEFAULT, "You need a " + GameDefinitionLoader.getItemDef(1857).getName() + ".");
			return;
		}
		player.getInventory().remove(12934, 50_000);
		player.getInventory().remove(1857, 1);
		player.getInventory().add(749, 1);
		DialogueManager.sendNpcChat(player, 1617, Emotion.DEFAULT, "Good luck.");
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
			DialogueManager.sendNpcChat(player, 1617, Emotion.DEFAULT, "Hello there!", "How may I help you?");
			next ++;
			break;
		case 1:
			DialogueManager.sendOption(player, "Will you make me a Zul-Andra Totem?", "Nothing.");
			break;
		case 2:
			DialogueManager.sendNpcChat(player, 1617, Emotion.DEFAULT, "Yes of course!", "I need 50,00 Zulrah Scales", "and a blank totem.");
			next ++;
			break;
		case 3:
			makeTotem();
			break;
			
		}
	}

}
