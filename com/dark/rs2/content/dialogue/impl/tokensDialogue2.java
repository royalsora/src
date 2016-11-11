package com.dark.rs2.content.dialogue.impl;

import com.dark.rs2.content.dialogue.Dialogue;
import com.dark.rs2.content.dialogue.DialogueConstants;
import com.dark.rs2.content.dialogue.DialogueManager;
import com.dark.rs2.entity.player.Player;
import com.dark.rs2.entity.player.net.out.impl.SendRemoveInterfaces;

/**
 * Dialogue for Achievement
 * @author Daniel
 *
 */
public class tokensDialogue2 extends Dialogue {
	
	public tokensDialogue2(Player player) {
		this.player = player;
	}

	@Override
	public boolean clickButton(int id) {
		switch(id) {
		case DialogueConstants.OPTIONS_2_1:
			int amount = player.getInventory().getItemAmount(13204);
			player.getInventory().remove(13204, amount);
			player.getInventory().add(995, amount * 1000);
			DialogueManager.sendItem2(player, "The bank exchanges your Platinum tokens for Coins.", "", 13204, 995);
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
			DialogueManager.sendStatement(player, "Are you sure you want to swap your platinum tokens for coins?");
			next++;			
			break;
		case 1:
			DialogueManager.sendOption(player, "Yes", "No");
			break;

		}
	}


}
