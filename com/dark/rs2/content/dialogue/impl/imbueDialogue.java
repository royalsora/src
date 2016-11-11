package com.dark.rs2.content.dialogue.impl;

import com.dark.core.util.GameDefinitionLoader;
import com.dark.rs2.content.dialogue.Dialogue;
import com.dark.rs2.content.dialogue.DialogueConstants;
import com.dark.rs2.content.dialogue.DialogueManager;
import com.dark.rs2.content.dialogue.Emotion;
import com.dark.rs2.entity.player.Player;
import com.dark.rs2.entity.player.net.out.impl.SendRemoveInterfaces;

/**
 * Handles the Oziach dialogue
 * @author Daniel
 *
 */
public class imbueDialogue extends Dialogue {
	
	public imbueDialogue(Player player) {
		this.player = player;
	}
	//Cape
	public void ObsidianCape() {
	if (!player.getInventory().hasItemAmount(6568, 1)) {
		DialogueManager.sendNpcChat(player, 2183, Emotion.DEFAULT, "You don't have an Obsidian Cape to trade.");
		end();
		return;
	}
		player.getInventory().remove(6568, 1);
		player.getInventory().add(6529, 3500);
		DialogueManager.sendNpcChat(player, 2183, Emotion.DEFAULT, "You recieve 3,500 Tokkul.");
		player.send(new SendRemoveInterfaces());
	} 
	//Archers ring
	public void ObsidianShield() {
	if (!player.getInventory().hasItemAmount(6524, 1)) {
		DialogueManager.sendNpcChat(player, 2183, Emotion.DEFAULT, "You don't have an Obsidian Shield to trade.");
		end();
		return;
	}
		player.getInventory().remove(6524, 1);
		player.getInventory().add(6529, 8000);
		DialogueManager.sendNpcChat(player, 2183, Emotion.DEFAULT, "You recieve 8,000 tokkul.");
		player.send(new SendRemoveInterfaces());
	} 
	
	//seers ring
	public void ObsidianMaul() {
	if (!player.getInventory().hasItemAmount(6528, 1)) {
		DialogueManager.sendNpcChat(player, 2183, Emotion.DEFAULT, "You don't have an Obsidian Maul to trade.");
		end();
		return;
	}
		player.getInventory().remove(6528, 1);
		player.getInventory().add(6529, 8000);
		DialogueManager.sendNpcChat(player, 2183, Emotion.DEFAULT, "You recieve 8,000 tokkul.");
		player.send(new SendRemoveInterfaces());
	} 
	
	public void UncutOnyx() {
	if (!player.getInventory().hasItemAmount(6571, 1)) {
		DialogueManager.sendNpcChat(player, 2183, Emotion.DEFAULT, "You don't have an Uncut Onyx to trade.");
		end();
		return;
	}
		player.getInventory().remove(6571, 1);
		player.getInventory().add(6529, 20000);
		DialogueManager.sendNpcChat(player, 2183, Emotion.DEFAULT, "You recieve 20,000 tokkul.");
		player.send(new SendRemoveInterfaces());
	} 

	
	

	@Override
	public boolean clickButton(int id) {
		switch (id) {
		
		case DialogueConstants.OPTIONS_4_1:
			setNext(3);
			execute();
			break;
		case DialogueConstants.OPTIONS_4_2:
			setNext(4);
			execute();
			break;
		case DialogueConstants.OPTIONS_4_3:
			setNext(5);
			execute();
			break;
			
		case DialogueConstants.OPTIONS_4_4:
			setNext(6);
			execute();
			break;
		
		}
		return false;
	}

	@Override
	public void execute() {
		switch (next) {
		
		case 0:
			DialogueManager.sendNpcChat(player, 2183, Emotion.DEFAULT, "Hello there!", "How may I help you?");
			next ++;
			break;
		case 1:
			DialogueManager.sendOption(player, "Exchange Obsidian Cape.", "Exchange Obsidian Shield.", "Exchange Obsidian Maul.", "Exchange Uncut Onyx.");
			break;
		case 2:
			DialogueManager.sendNpcChat(player, 2183, Emotion.DEFAULT, "Yes of course!", "I'll give you 10% of the shop price.", "just bring them to me.");
			next ++;
			break;
		case 3:
			ObsidianCape();
			break;
		case 4:
			ObsidianShield();
			break;
		case 5:
			ObsidianMaul();
			break;
		case 6:
			UncutOnyx();
			break;
			
		}
	}

}
