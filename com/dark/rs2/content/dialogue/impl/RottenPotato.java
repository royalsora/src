package com.dark.rs2.content.dialogue.impl;

import com.dark.rs2.content.dialogue.Dialogue;
import com.dark.rs2.content.dialogue.DialogueConstants;
import com.dark.rs2.content.dialogue.DialogueManager;
import com.dark.rs2.content.skill.Skill;
import com.dark.rs2.entity.player.Player;
import com.dark.rs2.entity.player.net.out.impl.SendMessage;
import com.dark.rs2.entity.player.net.out.impl.SendRemoveInterfaces;

/**
 * Handles Flower Game dialogue
 * 
 * @author goten
 *
 */
public class RottenPotato extends Dialogue {

	public RottenPotato(Player player) {
		this.player = player;
	}

	@Override
	public boolean clickButton(int id) {

		switch (id) {

		case DialogueConstants.OPTIONS_4_1:
			 for (int i = 0; i < 25; i++) {
                 player.getLevels()[i] = 99;
                 player.getMaxLevels()[i] = 99;
                 player.getSkill().getExperience()[i] = Skill.EXP_FOR_LEVEL[98];
             }
             player.getSkill().update();

             player.setAppearanceUpdateRequired(true);
             return true;

		case DialogueConstants.OPTIONS_4_2:
			 for (int i = 0; i < 25; i++) {
                 player.getLevels()[i] = 1;
                 player.getMaxLevels()[i] = 1;
                 player.getSkill().getExperience()[i] = Skill.EXP_FOR_LEVEL[0];
             }
             player.getSkill().update();

             player.setAppearanceUpdateRequired(true);
             player.send(new SendMessage("You feel your stats drain themselves back to level 1."));
			break;

		case DialogueConstants.OPTIONS_4_3:
			player.getInventory().clear();
			player.send(new SendMessage("The rotten potato clears your inventory."));
			player.send(new SendRemoveInterfaces());
			break;

		case DialogueConstants.OPTIONS_4_4:
			player.getBank().openBank();
			break;

		case DialogueConstants.OPTIONS_5_5:
			//player.start(new GamblerDialogue(player));
			break;

		}

		return false;
	}

	@Override
	public void execute() {

		switch (next) {

		case 0:
			DialogueManager.sendOption(player, "Max Stats", "Reset Stats", "Wipe Inventory", "Open Bank");
			next++;
			break;

		}

	}

}