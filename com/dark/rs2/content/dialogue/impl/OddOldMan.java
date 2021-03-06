package com.dark.rs2.content.dialogue.impl;

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
			DialogueManager.sendNpcChat(player, 1259, Emotion.DEFAULT, "Please bring me some Bear ribs.");
			return;
		}
		if (!player.getInventory().hasItemAmount(7826, 1)) {
			DialogueManager.sendNpcChat(player, 1259, Emotion.DEFAULT, "Please bring me a Giant Mole bone.");
			return;
		}
		if (!player.getInventory().hasItemAmount(7829, 1)) {
			DialogueManager.sendNpcChat(player, 1259, Emotion.DEFAULT, "Please bring me a Skeletal Wyvern bone.");
			return;
		}
		if (!player.getInventory().hasItemAmount(7841, 1)) {
			DialogueManager.sendNpcChat(player, 1259, Emotion.DEFAULT, "Please bring me a Large Dragon bone.");
			return;
		}
		if (!player.getInventory().hasItemAmount(7856, 1)) {
			DialogueManager.sendNpcChat(player, 1259, Emotion.DEFAULT, "Please bring me a Monkey's Paw.");
			return;
		}
		if (!player.getInventory().hasItemAmount(7859, 1)) {
			DialogueManager.sendNpcChat(player, 1259, Emotion.DEFAULT, "Please bring me some Dagannoth ribs.");
			return;
		}
		if (!player.getInventory().hasItemAmount(7862, 1)) {
			DialogueManager.sendNpcChat(player, 1259, Emotion.DEFAULT, "Please bring me a Snake's spine.");
			return;
		}
		if (!player.getInventory().hasItemAmount(7880, 1)) {
			DialogueManager.sendNpcChat(player, 1259, Emotion.DEFAULT, "Please bring me a Large Aviansie bone");
			return;
		}
		if (!player.getInventory().hasItemAmount(7916, 1)) {
			DialogueManager.sendNpcChat(player, 1259, Emotion.DEFAULT, "Please bring me a Jackle bone.");
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
		DialogueManager.sendNpcChat(player, 1259, Emotion.DEFAULT, "Don't worry.. I'm not going to do anything wierd with these bones... mauhahuauha");
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
			DialogueManager.sendNpcChat(player, 1259, Emotion.DEFAULT, "Hi, er, lad. Ca-can you do me a favor?", "I.. am in search of some bones.");
			next ++;
			break;
		case 1:
			DialogueManager.sendOption(player, "Uhh, why? Do you have any use for these 'bones'?", "Uh.. No thanks.");
			break;
		case 2:
			DialogueManager.sendNpcChat(player, 1259, Emotion.DEFAULT, "Heheh..", "Nothing you'd be interested in. If you bring me these bones", "I'll be sure to make it worth your while.");
			next ++;
			break;
		case 3:
			ElitePlatebody();
			break;
			
		}
	}

}
