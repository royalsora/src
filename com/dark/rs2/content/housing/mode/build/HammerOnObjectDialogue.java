package com.dark.rs2.content.housing.mode.build;

import com.dark.rs2.content.dialogue.Dialogue;
import com.dark.rs2.content.dialogue.DialogueConstants;
import com.dark.rs2.content.dialogue.DialogueManager;
import com.dark.rs2.content.dialogue.Emotion;
import com.dark.rs2.entity.player.net.out.impl.SendEnterString;
import com.dark.rs2.entity.player.net.out.impl.SendRemoveInterfaces;

public class HammerOnObjectDialogue extends Dialogue {

	@Override
	public boolean clickButton(int id) {
	switch (id) {
	case DialogueConstants.OPTIONS_3_1:
		this.setNext(3);
		switch (next) {
		case 3:
			DialogueManager.sendNpcChat(player, 765, Emotion.CALM, "Do you want to move it", "Up, Down, Left, or Right?");
			next++;
			break;
		}
		return true;
	case DialogueConstants.OPTIONS_3_2:
		DialogueManager.sendNpcChat(player, 765, Emotion.CALM, "Which way do you want it to face?", "North, South, East, or West?");
		setNext(10);
		return true;
	case DialogueConstants.OPTIONS_3_3:
		next++;
		switch (next) {
		case 1:
			DialogueManager.sendNpcChat(player, 765, Emotion.EVIL, "You will not be reinbursed for your money", "Are you sure you want to delete", "this object?");
			next++;
			break;
		}
		return true;
	case DialogueConstants.OPTIONS_2_1:
		player.getHouseObjectHandler().delete(player, player.getObjectBeingEdited());
		player.send(new SendRemoveInterfaces());
		break;
	case DialogueConstants.OPTIONS_2_2:
		player.send(new SendRemoveInterfaces());
		break;
	case DialogueConstants.OPTIONS_4_1:
		this.setNext(5);
		switch (next) {
		case 5:
			DialogueManager.sendStatement(player, "How many squares up do you want to move it?");
			next++;
			setNext(6);
			break;

		}
		break;
	case DialogueConstants.OPTIONS_4_2:
		this.setNext(5);
		switch (next) {
		case 5:
			DialogueManager.sendStatement(player, "How many squares down do you want to move it?");
			setNext(7);
			break;
		}
		break;
	case DialogueConstants.OPTIONS_4_3:
		this.setNext(5);
		switch (next) {
		case 5:
			DialogueManager.sendStatement(player, "How many squares left do you want to move it?");
			next++;
			setNext(8);
			break;

		}
		break;
	case DialogueConstants.OPTIONS_4_4:
		this.setNext(5);
		switch (next) {
		case 5:
			DialogueManager.sendStatement(player, "How many squares right do you want to move it?");
			next++;
			setNext(9);
			break;
		}
		break;
	}
	return false;
	}

	@Override
	public void execute() {
	switch (next) {
	case 0:
		DialogueManager.sendOption(player, "Move Object", "Rotate Object", "Delete Object");
		break;
	case 2:
		DialogueManager.sendOption(player, "Yes, delete this object.", "I would rather keep it.");
		break;
	case 4:
		DialogueManager.sendOption(player, "Up", "Down", "Left", "Right");
		break;
	case 6:
		player.send(new SendRemoveInterfaces());
		player.setEnterXInterfaceId(55794);// move up
		player.getClient().queueOutgoingPacket(new SendEnterString());
		break;
	case 7:
		player.send(new SendRemoveInterfaces());
		player.setEnterXInterfaceId(55795);// move down
		player.getClient().queueOutgoingPacket(new SendEnterString());
		break;
	case 8:
		player.send(new SendRemoveInterfaces());
		player.setEnterXInterfaceId(55796);// move left
		player.getClient().queueOutgoingPacket(new SendEnterString());
		break;
	case 9:
		player.send(new SendRemoveInterfaces());
		player.setEnterXInterfaceId(55797);// move right
		player.getClient().queueOutgoingPacket(new SendEnterString());
		break;
	case 10:
		player.start(new RotateDialouge(player));
		break;

	}
	}

}
