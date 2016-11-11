package com.dark.rs2.content.dialogue.impl;

import com.dark.rs2.content.PlayerTitle;
import com.dark.rs2.content.achievements.AchievementList;
import com.dark.rs2.content.dialogue.Dialogue;
import com.dark.rs2.content.dialogue.DialogueConstants;
import com.dark.rs2.content.dialogue.DialogueManager;
import com.dark.rs2.content.dialogue.Emotion;
import com.dark.rs2.content.skill.Skills;
import com.dark.rs2.entity.player.Player;
import com.dark.rs2.entity.player.net.out.impl.SendMessage;
import com.dark.rs2.entity.player.net.out.impl.SendRemoveInterfaces;

public class HardModeDialouge extends Dialogue {

	public HardModeDialouge(Player player) {
	this.player = player;
	}

	@Override
	public boolean clickButton(int id) {
	switch (id) {
	case DialogueConstants.OPTIONS_2_1:
		if(player.isIron()) {
			player.send(new SendMessage("Ironman cannot convert to hard mode."));
			return false;
		}
		for (int i = 0; i < player.getEquipment().getItems().length; i++) {
			if (player.getEquipment().getItems()[i] != null) {
				player.send(new SendMessage("Take of your equipment before trying this."));
				player.send(new SendRemoveInterfaces());
				return false;
			}
		}
		player.setHardMode(true);
		for (int i = 0; i < Skills.SKILL_COUNT; i++)
			player.getSkill().reset(i);
		player.getSkill().update();
		player.setPlayerTitle(PlayerTitle.create("Hardcore", 0x9F2863, false));
		player.setAppearanceUpdateRequired(true);
		player.send(new SendMessage("You have successfully converted into hard mode. Xp rate is x20, Good luck!"));
		player.send(new SendRemoveInterfaces());
		break;
	}
	return false;
	}

	@Override
	public void execute() {
	switch (next) {
	case 0:
		DialogueManager.sendNpcChat(player, 365, Emotion.CALM, "Hello " + player.getUsername(), "Converting to hard mode will be, x5 slower", "then the regular xp rate.");
		next++;
		break;
	case 1:
		DialogueManager.sendNpcChat(player, 365, Emotion.DISTRESSED, "By convertin to hard mode", "You will loose all of your stats.");
		next++;
		break;
	case 2:
		DialogueManager.sendNpcChat(player, 365, Emotion.DISTRESSED, "Are you sure you want to continue?");
		next++;
		break;
	case 3:
		DialogueManager.sendOption(player, "Yes", "No");
		break;
	}

	}

}
