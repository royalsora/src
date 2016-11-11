package com.dark.rs2.content.dialogue.impl;

import com.dark.rs2.content.dialogue.Dialogue;
import com.dark.rs2.content.dialogue.DialogueManager;
import com.dark.rs2.content.dialogue.Emotion;
import com.dark.rs2.entity.player.Player;
import com.dark.rs2.entity.player.net.out.impl.SendRemoveInterfaces;

public class RaidTutorialDialouge extends Dialogue {
	
	public RaidTutorialDialouge(Player player) {
		this.player = player;
	}
	@Override
	public boolean clickButton(int id) {
	// TODO Auto-generated method stub
	return false;
	}

	@Override
	public void execute() {

	switch (next) {
	case 0:
		DialogueManager.sendNpcChat(player, 765, Emotion.DEFAULT, "A raid consists of 6 rounds and costs 6m to start.", "Each round has the same boss spawning once, and", "as you progress, it gets harder.");
		next++;
		break;
	case 1:
		DialogueManager.sendNpcChat(player, 765, Emotion.DEFAULT, "Rare rewards are given at the end of the round", "too the top two damage dealers.", "Everyone else gets a med/common reward");
		next++;
		break;
	case 2:
		DialogueManager.sendNpcChat(player, 765, Emotion.DEFAULT, "Note that, a raid is very difficult, but if you die", "you will only loose the 6m and not your items.", "Everyone else gets a med/common reward");
		next++;
		break;
	case 3:
		player.send(new SendRemoveInterfaces());
		break;
	}
	
	}

}
