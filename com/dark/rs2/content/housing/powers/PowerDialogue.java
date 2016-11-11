package com.dark.rs2.content.housing.powers;

import com.dark.rs2.content.dialogue.Dialogue;
import com.dark.rs2.content.dialogue.DialogueConstants;
import com.dark.rs2.content.dialogue.DialogueManager;
import com.dark.rs2.content.housing.mode.CatagoryManager;
import com.dark.rs2.entity.player.Player;
import com.dark.rs2.entity.player.net.out.impl.SendEnterString;
import com.dark.rs2.entity.player.net.out.impl.SendInterface;

public class PowerDialogue extends Dialogue {
	public PowerDialogue(Player player) {
	this.player = player;
	}

	@Override
	public boolean clickButton(int id) {

	switch (id) {
	case DialogueConstants.OPTIONS_2_1:
		// Load interface
		player.send(new SendInterface(14000));
		player.getBuildMode().loadList(CatagoryManager.CATAGORY.BANK, player);
		break;
	case DialogueConstants.OPTIONS_2_2:
		// show the object spawnign interface
		// load only my inventory
		break;

	case DialogueConstants.OPTIONS_4_1:
		player.setEnterXInterfaceId(55790);// ban
		player.getClient().queueOutgoingPacket(new SendEnterString());
		break;
	case DialogueConstants.OPTIONS_4_2:
		player.setEnterXInterfaceId(55791);// unban
		player.getClient().queueOutgoingPacket(new SendEnterString());
		break;
	case DialogueConstants.OPTIONS_4_3:
		player.setEnterXInterfaceId(55792);// kick
		player.getClient().queueOutgoingPacket(new SendEnterString());
		break;
	case DialogueConstants.OPTIONS_4_4:
		// lock houselockHouse
		
		player.getPowerHandler().lockHouse(player);
		break;
	}
	// TODO Auto-generated method stub

	return false;
	}

	@Override
	public void execute() {
	switch (next) {
	case 0:
		if (player.getHouseMode().equalsIgnoreCase("build")) {
			DialogueManager.sendOption(player, "Buyables", "My Inventory");
		} else {
			DialogueManager.sendOption(player, "Ban player", "Unban player", "Kick player", player.isLocked() ? "Unlock House" : "Lock House");
		}
		break;
	}
	}

}
