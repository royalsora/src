package com.dark.rs2.content.dialogue.impl;

import com.dark.core.util.GameDefinitionLoader;
import com.dark.rs2.content.dialogue.Dialogue;
import com.dark.rs2.content.dialogue.DialogueConstants;
import com.dark.rs2.content.dialogue.DialogueManager;
import com.dark.rs2.content.dialogue.Emotion;
import com.dark.rs2.entity.player.Player;
import com.dark.rs2.entity.player.net.out.impl.SendRemoveInterfaces;

/**
 * Handles the Odd Old Man D
 * @author Jamian
 *
 */
public class OddOldMan extends Dialogue {
	
	public OddOldMan(Player player) {
		this.player = player;
	}
	
	public void ElitePlatebody() {
		if (!player.getInventory().hasItemAmount(7817, 1)) {
			DialogueManager.sendNpcChat(player, 1259, Emotion.DEFAULT, "You don't have any Bear ribs.");
			return;
		}
		if (!player.getInventory().hasItemAmount(7826, 1)) {
			DialogueManager.sendNpcChat(player, 1259, Emotion.DEFAULT, "You don't have a Giant Mole bone.");
			return;
		}
		if (!player.getInventory().hasItemAmount(7829, 1)) {
			DialogueManager.sendNpcChat(player, 1259, Emotion.DEFAULT, "You don't have a Skeletal Wyvern bone.");
			return;
		}
		if (!player.getInventory().hasItemAmount(7841, 1)) {
			DialogueManager.sendNpcChat(player, 1259, Emotion.DEFAULT, "You don't have a Large Dragon bone.");
			return;
		}
		if (!player.getInventory().hasItemAmount(7856, 1)) {
			DialogueManager.sendNpcChat(player, 1259, Emotion.DEFAULT, "You don't have a Monkey's Paw.");
			return;
		}
		if (!player.getInventory().hasItemAmount(7859, 1)) {
			DialogueManager.sendNpcChat(player, 1259, Emotion.DEFAULT, "You don't have any Dagannoth ribs.");
			return;
		}
		if (!player.getInventory().hasItemAmount(7862, 1)) {
			DialogueManager.sendNpcChat(player, 1259, Emotion.DEFAULT, "You don't have a Snakes spine.");
			return;
		}
		if (!player.getInventory().hasItemAmount(7880, 1)) {
			DialogueManager.sendNpcChat(player, 1259, Emotion.DEFAULT, "You don't have a Large Aviansie bone");
			return;
		}
		if (!player.getInventory().hasItemAmount(7892, 1)) {
			DialogueManager.sendNpcChat(player, 1259, Emotion.DEFAULT, "You don't have any Large Cow ribs");
			return;
		}
		if (!player.getInventory().hasItemAmount(7916, 1)) {
			DialogueManager.sendNpcChat(player, 1259, Emotion.DEFAULT, "You don't have a Jackle bone.");
			return;
		}
		player.getInventory().remove(7817, 1);
		player.getInventory().remove(7826, 1);
		player.getInventory().remove(7829, 1);
		player.getInventory().remove(7841, 1);
		player.getInventory().remove(7856, 1);
		player.getInventory().remove(7859, 1);
		player.getInventory().remove(7862, 1);
		player.getInventory().remove(7880, 1);
		player.getInventory().remove(7892, 1);
		player.getInventory().remove(7916, 1);
		player.getInventory().add(13107, 1);
		DialogueManager.sendNpcChat(player, 1259, Emotion.DEFAULT, "Don't worry.. i'm not going to do anything wierd with these bones... mauhahuauha");
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
			DialogueManager.sendNpcChat(player, 1259, Emotion.DEFAULT, "Who are you?", "What do you want?");
			next ++;
			break;
		case 1:
			DialogueManager.sendOption(player, "Uhh, do you have any use for these bones?", "Nothing.");
			break;
		case 2:
			DialogueManager.sendNpcChat(player, 1259, Emotion.DEFAULT, "Interesting...", "I suppose i could give you this platebody", "Its quite powerful");
			next ++;
			break;
		case 3:
			ElitePlatebody();
			break;
			
		}
	}

}
