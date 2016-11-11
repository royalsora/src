package com.dark.rs2.content.dialogue.impl;

import com.dark.core.util.GameDefinitionLoader;
import com.dark.core.util.Utility;
import com.dark.rs2.content.dialogue.Dialogue;
import com.dark.rs2.content.dialogue.DialogueConstants;
import com.dark.rs2.content.dialogue.DialogueManager;
import com.dark.rs2.content.dialogue.Emotion;
import com.dark.rs2.content.pets.BossPets;
import com.dark.rs2.content.pets.BossPets.PetData;
import com.dark.rs2.entity.item.Item;
import com.dark.rs2.entity.item.impl.GroundItemHandler;
import com.dark.rs2.entity.player.Player;
import com.dark.rs2.entity.player.net.out.impl.SendMessage;
import com.dark.rs2.entity.player.net.out.impl.SendRemoveInterfaces;

/**
 * Tzhaar Mej Kah Dialogue
 * @author Daniel
 *
 */
public class TzhaarMejKahDialogue extends Dialogue {
	
	public TzhaarMejKahDialogue(Player player) {
		this.player = player;
	}
	
	public void handlePet() {
		PetData petDrop = PetData.forItem(13225);
	
		if (petDrop != null) {
			if (player.getBossPet() == null) {
				BossPets.spawnPet(player, petDrop.getItem(), true);
				player.send(new SendMessage("You feel a pressence following you; " + Utility.formatPlayerName(GameDefinitionLoader.getNpcDefinition(petDrop.getNPC()).getName()) + " starts to follow you."));
			} else {
				player.getBank().depositFromNoting(petDrop.getItem(), 1, 0, false);
				player.send(new SendMessage("You feel a pressence added to your bank."));
			}
		} else {
			GroundItemHandler.add(new Item(13225, 1), player.getLocation(), player, player.ironPlayer() ? player : null);
		}
	}

	@Override
	public boolean clickButton(int id) {
		switch (id) {
	
		case DialogueConstants.OPTIONS_2_1:
			if (!player.getInventory().hasItemId(6570)) {
				DialogueManager.sendItem1(player, "You don't have a Firecape to do this!", 6570);
				setNext(2);
				return false;
			}
			player.getInventory().remove(6570, 1);
			if (Utility.random(100) == 1) {
				handlePet();
			} else {
				player.send(new SendMessage("@red@You have sacrificed a Fire cape... Nothing happens."));
			}
			player.send(new SendRemoveInterfaces());
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
			DialogueManager.sendNpcChat(player, 2181, Emotion.HAPPY, "Hello there, " + player.getUsername() + ".", "Want to sacrifice a Firecape for a chance to", "Obtain the Jad pet?");
			next++;
			break;
		case 1:
			DialogueManager.sendOption(player, "Yes", "No");
			break;
		case 2:
			player.send(new SendRemoveInterfaces());
			break;
		
		}
	}

}
