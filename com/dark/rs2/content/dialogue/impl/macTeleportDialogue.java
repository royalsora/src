package com.dark.rs2.content.dialogue.impl;

import com.dark.rs2.content.dialogue.Dialogue;
import com.dark.rs2.content.dialogue.DialogueConstants;
import com.dark.rs2.content.dialogue.DialogueManager;
import com.dark.rs2.content.dialogue.Emotion;
import com.dark.rs2.content.skill.magic.MagicSkill.TeleportTypes;
import com.dark.rs2.entity.player.Player;

public class macTeleportDialogue extends Dialogue {
	
	public macTeleportDialogue(Player player) {
		this.player = player;
	}

	@Override
	public boolean clickButton(int id) {
		switch (id) {
		case DialogueConstants.OPTIONS_2_1:
			
			
			int requirements = 99;
			for (int i =0; i < 20; i++) {
				
				if (player.getSkill().getLevels()[i] >= requirements)	{
					player.getMagic().teleport(2790, 3537, 0, TeleportTypes.SPELL_BOOK);	
				}
				else
				if (player.getSkill().getLevels()[i] < requirements)	{ 
				DialogueManager.sendNpcChat(player, 3855, Emotion.ANGRY_2, "You Liar!", " You don't have the required stats to teleport to Mac.");
				end();
				return false;
				}
			}
			
		}
				return false;
			}
			

	@Override
	public void execute() {
		switch (next) {
		case 0:
			DialogueManager.sendNpcChat(player, 3855, Emotion.HAPPY_TALK, "Hello adventurer!", "I believe you're here to find Mac right?", "I can take you there. "
					+ "But you'll need 99 in all stats!");
			next++;
			break;
		case 1:
			DialogueManager.sendPlayerChat(player, Emotion.CALM,( "I believe I have that?"));
			next++;
			break;
		case 2:
			DialogueManager.sendNpcChat(player, 3855, Emotion.NEAR_TEARS_2, "Oh I'm so sorry!", "I've had some lower levels come to me today...", 
					"What would you like to do?. ");
			next++;
			break;
		case 3:
			DialogueManager.sendOption(player, "Teleport to Mac", "Goodbye.");
			break;
		
		}
	}

}
