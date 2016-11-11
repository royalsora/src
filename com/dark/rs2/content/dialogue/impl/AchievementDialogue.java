package com.dark.rs2.content.dialogue.impl;

import com.dark.rs2.content.achievements.AchievementList;
import com.dark.rs2.content.dialogue.Dialogue;
import com.dark.rs2.content.dialogue.DialogueConstants;
import com.dark.rs2.content.dialogue.DialogueManager;
import com.dark.rs2.content.dialogue.Emotion;
import com.dark.rs2.entity.player.Player;
import com.dark.rs2.entity.player.PlayerConstants;
import com.dark.rs2.entity.player.net.out.impl.SendMessage;
import com.dark.rs2.entity.player.net.out.impl.SendRemoveInterfaces;

/**
 * Dialogue for Achievement
 * @author Daniel
 *
 */
public class AchievementDialogue extends Dialogue {
	
	public AchievementDialogue(Player player) {
		this.player = player;
	}

	@Override
	public boolean clickButton(int id) {
		switch(id) {
		case DialogueConstants.OPTIONS_3_1:
			
			boolean completed = true;
			for (AchievementList achievement : player.getPlayerAchievements().keySet()) {
				if (achievement != null && player.getPlayerAchievements().get(achievement) != achievement.getCompleteAmount()) {
					completed = false;
					break;
				}
			}
			
			if (completed) {
				setNext(6);
				execute();
			}
	 else {
			player.send(new SendMessage("You have not completed all the achievements!"));				
		}
			player.send(new SendRemoveInterfaces());
			break;
			
		case DialogueConstants.OPTIONS_2_1: 
			if (player.getInventory().hasItemAmount(995, 99000)) {
			player.getInventory().remove(995, 99000);
			player.getInventory().add(13069, 1);
			player.getInventory().add(13070, 1);
			DialogueManager.sendItem1(player, "Twiggy sells you a cape and hood.", 13069);
			}
			 else {
				player.send(new SendRemoveInterfaces());
				player.send(new SendMessage("You do not have enough coins to purchase this cape."));
			} 
			end();
			break;
		case DialogueConstants.OPTIONS_2_2:
			player.send(new SendRemoveInterfaces());
			break;
			
		case DialogueConstants.OPTIONS_3_2:
			if (PlayerConstants.isDeveloper(player)) {
				player.getShopping().open(89);
			} else {
				DialogueManager.sendStatement(player, "Coming soon!");
			}
			break;
		case DialogueConstants.OPTIONS_3_3:
			player.send(new SendRemoveInterfaces());
			break;
			
		}
		return false;
	}

	@Override
	public void execute() {
		switch(next) {
		case 0:
			DialogueManager.sendNpcChat(player, 5527, Emotion.HAPPY, "Hello "+player.getUsername()+".", "How may I help you?");
			next++;
			break;
		case 1:
			DialogueManager.sendPlayerChat(player, Emotion.CALM, "What are you doing here?");
			next++;
			break;
		case 2:
			DialogueManager.sendNpcChat(player, 5527, Emotion.HAPPY, "I'm looking for the very best of course!");
			next++;			
			break;
		case 3:
			DialogueManager.sendPlayerChat(player, Emotion.CALM, "The very best?");
			next++;
			break;
		case 4:
			DialogueManager.sendNpcChat(player, 5527, Emotion.HAPPY, "Yes!", "Players that have completed all the achievements,", "will be rewarded with my cape.");
			next++;			
			break;
		case 5:
			DialogueManager.sendOption(player, "Obtain cape", "Trade", "Nothing.");
			break;
		case 6:
			DialogueManager.sendNpcChat(player, 5527, Emotion.HAPPY, "I see you've completed all of the Achievements,", "well done! Now, I'll need 99,000 coins to cover my costs.");
			next++;
			break;
		case 7:
			DialogueManager.sendOption(player, "Okay, here's 99,000 coins.", "Actually, forget it.");
			break;

		}
	}


}
