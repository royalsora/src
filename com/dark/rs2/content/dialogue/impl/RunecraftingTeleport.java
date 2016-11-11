package com.dark.rs2.content.dialogue.impl;

import com.dark.core.util.Utility;
import com.dark.rs2.content.dialogue.Dialogue;
import com.dark.rs2.content.dialogue.DialogueManager;
import com.dark.rs2.content.dialogue.Emotion;
import com.dark.rs2.entity.Location;
import com.dark.rs2.entity.mob.Mob;
import com.dark.rs2.entity.player.Player;
import com.dark.rs2.entity.player.net.out.impl.SendRemoveInterfaces;

/**
 * Dialogue for Runecrafting training teleport
 * @author Daniel
 *
 */
public class RunecraftingTeleport extends Dialogue {
	
	final Mob mob;

	public RunecraftingTeleport(Player player, Mob mob) {
		this.player = player;
		this.mob = mob;
	}

	@Override
	public boolean clickButton(int id) {
		if (id == 9157) {
			player.getClient().queueOutgoingPacket(new SendRemoveInterfaces());
			player.teleport(new Location(3039, 4834, 0));
			//TaskQueue.queue(new TeleOtherTask(mob, player, new Location(3039, 4834)));
			
		}
		if (id == 9158) {
			player.getClient().queueOutgoingPacket(new SendRemoveInterfaces());
			player.teleport(new Location(2923, 4819, 0));
			
		}
		return false;
	}

	@Override
	public void execute() {
		switch (next) {
		case 0:
			DialogueManager.sendNpcChat(player, mob.getId(), Emotion.DEFAULT, "Hello "+Utility.formatPlayerName(player.getUsername())+".", "I can teleport you to a Runecrafting training area." , "Where would you like to go?");
			next++;
			break;
		case 1:
			DialogueManager.sendOption(player, new String[] { "Abyss", "Essence mine" });	
			break;
		}
	}
}
