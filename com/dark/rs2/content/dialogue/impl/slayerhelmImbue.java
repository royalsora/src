package com.dark.rs2.content.dialogue.impl;

import com.dark.rs2.content.dialogue.Dialogue;
import com.dark.rs2.content.dialogue.DialogueConstants;
import com.dark.rs2.content.dialogue.DialogueManager;
import com.dark.rs2.entity.player.Player;
import com.dark.rs2.entity.player.net.out.impl.SendRemoveInterfaces;

/**
 * Dialogue for slayer helm imbuement
 * @author goten
 *
 */
public class slayerhelmImbue extends Dialogue {
	
	public slayerhelmImbue(Player player) {
		this.player = player;
	}

	@Override
	public boolean clickButton(int id) {
		switch(id) {
		case DialogueConstants.OPTIONS_2_1:
			player.getInventory().remove(7788, 1);
			player.getInventory().remove(11864, 1);
			player.getInventory().add(11865, 1);
			DialogueManager.sendItem1(player, "You imbue your slayer helmet, the tome has been deleted.", 11865);
			end();
			break;
			
		case DialogueConstants.OPTIONS_2_2:
			player.send(new SendRemoveInterfaces());
			break;
			
		}
		return false;
	}

	@Override
	public void execute() {
		switch(next) {
		case 0:
			DialogueManager.sendStatement(player, "Do you wish to imbue your Slayer helmet?", 
					"There is no going back from this action!");
			next++;			
			break;
		case 1:
			DialogueManager.sendOption(player, "Yes, Imbue my helmet.", "No I'd like to keep it how it is.");
			break;

		}
	}


}
