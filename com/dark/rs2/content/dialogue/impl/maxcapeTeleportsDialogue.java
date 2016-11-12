package com.dark.rs2.content.dialogue.impl;

import com.dark.rs2.content.dialogue.Dialogue;
import com.dark.rs2.content.dialogue.DialogueConstants;
import com.dark.rs2.content.dialogue.DialogueManager;
import com.dark.rs2.content.skill.magic.MagicSkill.TeleportTypes;
import com.dark.rs2.entity.player.Player;
import com.dark.rs2.entity.player.net.out.impl.SendMessage;

/**
 * Handles Max cape teleports.
 * 
 * @author goten
 *
 */
public class maxcapeTeleportsDialogue extends Dialogue {

	public maxcapeTeleportsDialogue(Player player) {
		this.player = player;
	}
	
	//Finish this when home - max cape teleports.

	@Override
	public boolean clickButton(int id) {

		switch (id) {

		case DialogueConstants.OPTIONS_4_1:
			if (player.getWildernessLevel() > 20 && player.inWilderness()) {
				player.send(new SendMessage("You cannot teleport above 20 wilderness!"));
				return true;
			}
			player.getMagic().teleport(2442, 9805, 0, TeleportTypes.SPELL_BOOK);
			 player.send(new SendMessage("You are teleported to the Slayer dungeon."));
             return true;

		case DialogueConstants.OPTIONS_4_2:
			if (player.getWildernessLevel() > 20 && player.inWilderness()) {
				player.send(new SendMessage("You cannot teleport above 20 wilderness!"));
				return true;
			}
			player.getMagic().teleport(2854, 3431, 0, TeleportTypes.SPELL_BOOK);
			 player.send(new SendMessage("You are teleported to the fishing area for free."));
			break;

		case DialogueConstants.OPTIONS_4_3:
			if (player.getWildernessLevel() > 20 && player.inWilderness()) {
				player.send(new SendMessage("You cannot teleport above 20 wilderness!"));
				return true;
			}
			player.getMagic().teleport(2923, 4819, 0, TeleportTypes.SPELL_BOOK);
			 player.send(new SendMessage("You are teleported to the rune essence mine."));
			break;

		case DialogueConstants.OPTIONS_4_4:
			if (player.getWildernessLevel() > 20 && player.inWilderness()) {
				player.send(new SendMessage("You cannot teleport above 20 wilderness!"));
				return true;
			}
			player.getMagic().teleport(3429, 2891, 0, TeleportTypes.SPELL_BOOK);
			break;

			/* player.getInventory().remove(12938, 1);
			player.getMagic().teleport(2268, 3070, player.getIndex() << 2, TeleportTypes.SPELL_BOOK);
			TaskQueue.queue(new Task(5) {
				@Override
				public void execute() {
					Zulrah mob = new Zulrah(player, new Location(2266, 3073, player.getIndex() << 2));
					mob.face(player);
					mob.getUpdateFlags().sendAnimation(new Animation(5071));
					player.face(mob);
					player.send(new SendMessage("Welcome to Zulrah's shrine."));
					DialogueManager.sendStatement(player, "Welcome to Zulrah's shrine.");
					stop();
				}

				@Override
				public void onStop() {
				}
			});
			break; */

		case DialogueConstants.OPTIONS_5_5:
			break;

		}

		return false;
	}

	@Override
	public void execute() {

		switch (next) {

		case 0:
			DialogueManager.sendOption(player, "Slayer Dungeon", "Fishing Area", "Essence Mine", "Nardah");
			next++;
			break;

		}

	}

}