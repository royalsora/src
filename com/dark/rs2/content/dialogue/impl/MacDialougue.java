package com.dark.rs2.content.dialogue.impl;

import com.dark.rs2.content.dialogue.Dialogue;
import com.dark.rs2.content.dialogue.DialogueConstants;
import com.dark.rs2.content.dialogue.DialogueManager;
import com.dark.rs2.content.dialogue.Emotion;
import com.dark.rs2.entity.player.Player;
import com.dark.rs2.entity.player.net.out.impl.SendRemoveInterfaces;

/**
 * 
 * @author goten
 *
 */
public class MacDialougue extends Dialogue {

	public MacDialougue(Player player) {
	this.player = player;
	}

	public final int MAX_CAPE_ID = 13280;
	public final int MAX_HOOD_ID = 13281;

	@Override
	public boolean clickButton(int id) {
	switch (id) {
	case DialogueConstants.OPTIONS_4_1:
		player.send(new SendRemoveInterfaces());
		setNext(3);
		execute();
		break;
	case DialogueConstants.OPTIONS_4_2:
		setNext(6);
		execute();
		break;
	case DialogueConstants.OPTIONS_4_3:
		setNext(16);
		execute();
		break;

	case DialogueConstants.OPTIONS_4_4:
		player.send(new SendRemoveInterfaces());
		break;

	case DialogueConstants.OPTIONS_2_1:
		if (!player.getInventory().hasItemAmount(995, 2_277_000)) {
			DialogueManager.sendNpcChat(player, 6481, Emotion.DEFAULT, "You do not have enough money to buy the cape");
			end();
			return false;
		}
		int requirements = 99;
		for (int i =0; i < 20; i++) { 
			if (player.getSkill().getLevels()[i] < requirements)	{ 
			DialogueManager.sendNpcChat(player, 6481, Emotion.ANGRY_4, "You're not worthy enough to have my cape.");
			end();
			return false;
			}
		}
		
		if (!player.getInventory().hasItemAmount(995, 2277000)) { 
			DialogueManager.sendNpcChat(player, 6481, Emotion.DEFAULT, "You need 2,277,000 to purchase this.");
			end();
			return false;
		}
		player.getInventory().remove(995, 2_277_000);
		player.getInventory().add(13280, 1);
		player.getInventory().add(13281, 1);
		DialogueManager.sendItem2(player, "Mac grunts and hands over his cape,"
				, "pocketing your money swiftly.", 13281, 13280);
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
	switch (next) {
	case 0:
		DialogueManager.sendPlayerChat(player, Emotion.CALM, "Hello");
		next++;
		break;
	case 1:
		DialogueManager.sendStatement(player, new String[] { "The man glances at you and grunts something unintelligent" });
		next++;
		break;
	case 2:
		DialogueManager.sendOption(player, "Who are you?", "What do you have in your sack?", "Why are you do dirty?", "Bye.");
		break;
	case 3:
		DialogueManager.sendPlayerChat(player, Emotion.CALM, "Who are you?");
		next++;
		break;
	case 4:
		DialogueManager.sendNpcChat(player, 6481, Emotion.ANGRY_1, "Mac. What's it you you?");
		next++;
		break;
	case 5:
		DialogueManager.sendPlayerChat(player, Emotion.CALM, "Only trying to be freindly.");
		setNext(2);
		break;
	case 6:	
		DialogueManager.sendPlayerChat(player, Emotion.CALM, "What do you have in your sack?");
		next++;
		break;
	case 7:
		DialogueManager.sendNpcChat(player, 6481, Emotion.CALM, "S'me cape.");
		next++;
		break;
	case 8:
		DialogueManager.sendPlayerChat(player, Emotion.CALM, "Your cape?");
		next++;
		break;
	case 9:
		DialogueManager.sendPlayerChat(player, Emotion.CALM, "Can I have it?");
		next++;
		break;
	case 10:
		DialogueManager.sendPlayerChat(player, Emotion.HAPPY_TALK, "Can I have it?");
		next++;
		break;
	case 11:
		DialogueManager.sendNpcChat(player, 6481, Emotion.CALM, "Mebe");
		next++;
		break;
	case 12:
		DialogueManager.sendPlayerChat(player, Emotion.HAPPY, "I'm sure I could make it worth your while.");
		next++;
		break;
	case 13:
		DialogueManager.sendNpcChat(player, 6481, Emotion.HAPPY, "How much?");
		next++;
		break;
	case 14:
		DialogueManager.sendPlayerChat(player, Emotion.HAPPY, "How about 2277000 gold?");
		next++;
		break;
	case 15:
		DialogueManager.sendOption(player, "Yes, pay the man.", "No");
		break;
	case 16:
		DialogueManager.sendPlayerChat(player, Emotion.CALM, "Why are you so dirty?");
		next++;
		break;
	case 17:
		DialogueManager.sendNpcChat(player, 6481, Emotion.CALM, "Bath XP waste.");
		setNext(2);
		break;

	}

	}

}