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
 * Handles the Oziach dialogue
 * @author Daniel
 *
 */
public class JarDialogue extends Dialogue {
	
	public JarDialogue(Player player) {
		this.player = player;
	}
	//miasma
	public void JarOfMiasma() {
	if (!player.getInventory().hasItemAmount(13277, 1)) {
		DialogueManager.sendNpcChat(player, 3772, Emotion.DEFAULT, "Bruv you ain't got no Miasma....");
		end();
		return;
	}
			int random = Utility.random(1) + Utility.random(9999);
		player.setAbyssalPoints(player.getAbyssalPoints() + random);
		player.getInventory().remove(13277, 1);
		player.send(new SendMessage("You recieve " + random + " Abyssal Points."));
		player.send(new SendRemoveInterfaces());
	} 

	//souls
	public void JarOfSouls() {
	if (!player.getInventory().hasItemAmount(13245, 1)) {
		DialogueManager.sendNpcChat(player, 3772, Emotion.DEFAULT, "You do not contain in your equipment pack the necessary jar");
		end();
		return;
	}
			int random = Utility.random(1) + Utility.random(9999999);
		player.setBountyPoints(player.getBountyPoints() + random);
		player.getInventory().remove(13245, 1);
		player.send(new SendMessage("You recieve " + random + " Bounty Hunter Points."));
		player.send(new SendRemoveInterfaces());
	} 
	
	//sawmp
	public void JarOfSwamp() {
	if (!player.getInventory().hasItemAmount(12936, 1)) {
		DialogueManager.sendNpcChat(player, 3772, Emotion.ANGRY_1, "The swamp is not with you.");
		end();
		return;
	}
			int random = Utility.random(1) + Utility.random(9999);
		player.getInventory().add(12934, random);
		player.getInventory().remove(12936, 1);
		player.send(new SendMessage("You recieve " + random + " Zulrah Scales."));
		player.send(new SendRemoveInterfaces());
	} 
	
	//sawmp
	public void JarOfSand() {
	if (!player.getInventory().hasItemAmount(12885, 1)) {
		DialogueManager.sendNpcChat(player, 3772, Emotion.ANGRY_4, "My good sir, i do believe you are missing one jar of sand");
		end();
		return;
	}
			int random = Utility.random(1) + Utility.random(29);
		player.setVotePoints(player.getVotePoints() + random);
		player.getInventory().remove(12885, 1);
		player.send(new SendMessage("You recieve " + random + " Vote Points."));
		player.send(new SendRemoveInterfaces());
	} 

	//dirt
	public void JarOfDirt() {
	if (!player.getInventory().hasItemAmount(12007, 1)) {
		DialogueManager.sendNpcChat(player, 3772, Emotion.ANGRY_1, "You DON'T got a jar of dirt! You DON'T got a jar of dirt!!");
		end();
		return;
	}
			int random = Utility.random(1) + Utility.random(499);
		player.setPestPoints(player.getPestPoints() + random);
		player.getInventory().remove(12007, 1);
		player.send(new SendMessage("You recieve " + random + " Pest Control Points."));
		player.send(new SendRemoveInterfaces());
	} 

	//dirt
	public void JarOfDarkness() {
	if (!player.getInventory().hasItemAmount(19701, 1)) {
		DialogueManager.sendNpcChat(player, 3772, Emotion.ANGRY_1, "You DON'T got a jar of dirt! You DON'T got a jar of dirt!!");
		end();
		return;
	}
			int random = Utility.random(1) + Utility.random(499);
		player.setSlayerPoints(player.getSlayerPoints() + random);
		player.getInventory().remove(19701, 1);
		player.send(new SendMessage("You recieve " + random + " Slayer Points."));
		player.send(new SendRemoveInterfaces());
	} 
	
	//Pet

	public void handlePet() {
		PetData petDrop = PetData.forItem(8305);
	
		if (petDrop != null) {
			if (player.getBossPet() == null) {
				BossPets.spawnPet(player, petDrop.getItem(), true);
				player.send(new SendMessage("You feel a pressence following you; " + Utility.formatPlayerName(GameDefinitionLoader.getNpcDefinition(petDrop.getNPC()).getName()) + " starts to follow you."));
			} else {
				player.getBank().depositFromNoting(petDrop.getItem(), 1, 0, false);
				player.send(new SendMessage("You feel a pressence added to your bank."));
			}
		} else {
			GroundItemHandler.add(new Item(8305, 1), player.getLocation(), player, player.ironPlayer() ? player : null);
		}
	}

	public void Rocnar() {
		if (!player.getInventory().hasItemAmount(13277, 1)) {
			DialogueManager.sendNpcChat(player, 3772, Emotion.ANGRY_1, "You are missing a Jar of Miasma");
			return;
		}
		if (!player.getInventory().hasItemAmount(13245, 1)) {
			DialogueManager.sendNpcChat(player, 3772, Emotion.ANGRY_1, "You are missing a Jar of Souls");
			return;
		}
		if (!player.getInventory().hasItemAmount(12936, 1)) {
			DialogueManager.sendNpcChat(player, 3772, Emotion.ANGRY_1, "You are missing a Jar of Swamp");
			return;
		}
		if (!player.getInventory().hasItemAmount(12885, 1)) {
			DialogueManager.sendNpcChat(player, 3772, Emotion.ANGRY_1, "You are missing a Jar of Sand");
			return;
		}
		if (!player.getInventory().hasItemAmount(12007, 1)) {
			DialogueManager.sendNpcChat(player, 3772, Emotion.ANGRY_1, "You are missing a Jar of Dirt");
			return;
		}
		if (!player.getInventory().hasItemAmount(19701, 1)) {
			DialogueManager.sendNpcChat(player, 3772, Emotion.ANGRY_1, "You are missing a Jar of Darkness");
			return;
		}
		player.getInventory().remove(13277, 1);
		player.getInventory().remove(13245, 1);
		player.getInventory().remove(12936, 1);
		player.getInventory().remove(12885, 1);
		player.getInventory().remove(12007, 1);
		player.getInventory().remove(19701, 1);
			if (Utility.random(3) == 1) {
				handlePet();
			} else {
				DialogueManager.sendNpcChat(player, 3772, Emotion.SAD, "I have nothing for you.....");
			}
		DialogueManager.sendNpcChat(player, 822, Emotion.DEFAULT, "Good luck.");
		player.send(new SendRemoveInterfaces());
	}

	@Override
	public boolean clickButton(int id) {
		switch (id) {
		
		case DialogueConstants.OPTIONS_5_1:
			setNext(3);
			execute();
			break;
		case DialogueConstants.OPTIONS_5_2:
			setNext(4);
			execute();
			break;
		case DialogueConstants.OPTIONS_5_3:
			setNext(5);
			execute();
			break;
			
		case DialogueConstants.OPTIONS_5_4:
			setNext(6);
			execute();
			break;

		case DialogueConstants.OPTIONS_5_5:
			setNext(2);
			execute();
			break;

		case DialogueConstants.OPTIONS_3_1:
			setNext(7);
			execute();
			break;

		case DialogueConstants.OPTIONS_3_2:
			setNext(8);
			execute();
			break;
			
		case DialogueConstants.OPTIONS_3_3:
			setNext(9);
			execute();
			break;
		
		}
		return false;
	}

	@Override
	public void execute() {
		switch (next) {
		
		case 0:
			DialogueManager.sendNpcChat(player, 3772, Emotion.SAD, "What? Who?", "Oh.... What?");
			next ++;
			break;
		case 1:
			DialogueManager.sendOption(player, "Exchange a Jar of Miasma for 1-10k Abyssal points.", "Exchange a Jar of Souls for 1-10M Bounty Hunter Points.", "Exchange a Jar of Swamp for 1-10k Zulrah Scales.", "Exchange a Jar of Sand for 1-30 Vote Points.", "More Options");
			next ++;
			break;
		case 2:
			DialogueManager.sendOption(player, "Exchange a Jar of Dirt for 1-500 Pest control Points", "Exchange a Jar of Darkness for 1-500 Slayer Points.", "Exchange all 6 for a chance at the pet");
			break;
		case 3:
			JarOfMiasma();
			break;
		case 4:
			JarOfSouls();
			break;
		case 5:
			JarOfSwamp();
			break;
		case 6:
			JarOfSand();
			break;
		case 7:
			JarOfDirt();
			break;
		case 8:
			JarOfDarkness();
			break;
		case 9:
			Rocnar();
			break;
			
		}
	}

}
