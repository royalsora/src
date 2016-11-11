package com.dark.rs2.content.dialogue.impl;

import com.dark.rs2.content.dialogue.Dialogue;
import com.dark.rs2.content.dialogue.DialogueConstants;
import com.dark.rs2.content.dialogue.DialogueManager;
import com.dark.rs2.entity.World;
import com.dark.rs2.entity.player.Player;
import com.dark.rs2.entity.player.net.out.impl.SendRemoveInterfaces;

/**
 * Dialogue for Achievement
 * @author goten
 *
 */
public class dicerScrollDialogue extends Dialogue {
	
	public dicerScrollDialogue(Player player) {
		this.player = player;
	}

	@Override
	public boolean clickButton(int id) {
		switch(id) {
		case DialogueConstants.OPTIONS_2_1:
			player.getInventory().remove(11740, 1);
			player.getInventory().add(12020, 1);
			DialogueManager.sendItem1(player, "You redeem your dice bag and the scroll is deleted.", 12020);
			World.sendGlobalMessage("<img=4>@red@"+ player.getUsername()+" has just redeemed their dice bag, and can now host dice games.<img=4>");
			player.isDicer = true;
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
			DialogueManager.sendStatement(player, "Are you sure you want to redeem your dice bag?", 
					"There will be no refunds given for this action!");
			next++;			
			break;
		case 1:
			DialogueManager.sendOption(player, "Yes", "No");
			break;

		}
	}


}
