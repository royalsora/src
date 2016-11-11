package com.dark.rs2.content.dialogue.impl;


import com.dark.rs2.content.dialogue.Dialogue;
import com.dark.rs2.content.dialogue.DialogueConstants;
import com.dark.rs2.content.dialogue.DialogueManager;
import com.dark.rs2.content.dialogue.Emotion;
import com.dark.rs2.entity.player.Player;
import com.dark.rs2.entity.player.net.out.impl.SendRemoveInterfaces;
import com.dark.rs2.entity.player.net.out.impl.SendString;

/**
 * Handles the Membership dialogue
 * @author Daniel
 *
 */
public class MembershipDialogue extends Dialogue {
	
	public MembershipDialogue(Player player) {
		this.player = player;
	}

	@Override
	public boolean clickButton(int id) {
		switch (id) {
		
		case DialogueConstants.OPTIONS_5_1:
			setNext(2);
			execute();
			break;
		case DialogueConstants.OPTIONS_5_2:
			player.send(new SendString("http://www.Horizon-OS.org/forums", 12000));
			break;
		case DialogueConstants.OPTIONS_5_3:
			setNext(3);
			execute();
			break;
		case DialogueConstants.OPTIONS_5_4:
		//	RankHandler.upgrade(player);
			break;
		case DialogueConstants.OPTIONS_5_5:
			player.send(new SendRemoveInterfaces());
			break;
		
		}
		return false;
	}

	@Override
	public void execute() {
		switch (next) {
		
		case 0:
			DialogueManager.sendNpcChat(player, 3189, Emotion.DEFAULT, "Hello adventurer. How may I help you?");
			next ++;
			break;
		case 1:
			DialogueManager.sendOption(player, "What are Gold points?", "Benifits of membership", "I have purchased something", "Update rank", "Nevermind");
			break;
		case 2:
			DialogueManager.sendNpcChat(player, 3189, Emotion.DEFAULT, "Horizon-OS Gold points can be purchased on our online store.", "They can be used for buying items from my store ", "and many other features in game.", "Including purchasing Gold points in the Gold points tab.");
			setNext(1);
			break;
		
		}
		
	}

}
