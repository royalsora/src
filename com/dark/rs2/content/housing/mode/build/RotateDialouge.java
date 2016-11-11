package com.dark.rs2.content.housing.mode.build;

import com.dark.rs2.content.dialogue.Dialogue;
import com.dark.rs2.content.dialogue.DialogueConstants;
import com.dark.rs2.content.dialogue.DialogueManager;
import com.dark.rs2.entity.player.Player;

public class RotateDialouge extends Dialogue {

	public RotateDialouge(Player player) {
	this.player = player;
	}

	@Override
	public boolean clickButton(int id) {
	switch (id) {
	case DialogueConstants.OPTIONS_4_1:
		player.getMoveObject().rotateFace(player, "North");
		break;
	case DialogueConstants.OPTIONS_4_2:
		player.getMoveObject().rotateFace(player, "South");
		break;
	case DialogueConstants.OPTIONS_4_3:
		player.getMoveObject().rotateFace(player, "East");
		break;
	case DialogueConstants.OPTIONS_4_4:
		player.getMoveObject().rotateFace(player, "West");
		break;
	}
	return false;
	}

	@Override
	public void execute() {
	switch (next) {
	case 0:
		DialogueManager.sendOption(player, "North", "South", "East", "West");
		break;
	}
	}

}
