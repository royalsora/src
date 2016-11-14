package com.dark.rs2.content.dialogue.impl;

import com.dark.core.task.Task;
import com.dark.core.task.TaskQueue;
import com.dark.rs2.content.dialogue.Dialogue;
import com.dark.rs2.content.dialogue.DialogueConstants;
import com.dark.rs2.content.dialogue.DialogueManager;
import com.dark.rs2.content.dialogue.Emotion;
import com.dark.rs2.content.skill.magic.MagicSkill.TeleportTypes;
import com.dark.rs2.entity.Animation;
import com.dark.rs2.entity.Location;
import com.dark.rs2.entity.mob.impl.Zulrah;
import com.dark.rs2.entity.player.Player;
import com.dark.rs2.entity.player.PlayerConstants;
import com.dark.rs2.entity.player.net.out.impl.SendMessage;
import com.dark.rs2.entity.player.net.out.impl.SendRemoveInterfaces;

/**
 * Otto Godblessed dialogue (creates & reverts Zamorakian hasta)
 * @author Daniel
 *
 */
public class ZulrahDialogue extends Dialogue {
	
	/**
	 * Otto Godblessed
	 * @param player
	 */
	public ZulrahDialogue(Player player) {
		this.player = player;
	}
	

	@Override
	public boolean clickButton(int id) {
		switch (id) {
		
		//Creates hasta
		case DialogueConstants.OPTIONS_2_1:
			if (player.canTeleport == false || player.inZulrah()) {
				return false;
			}
			
			player.getMagic().teleport(2268, 3070, player.getIndex() << 2, TeleportTypes.SPELL_BOOK);
			 DialogueManager.sendStatement(player, "The priestess rows you to Zulrah's shrine,", "then hurridly paddles away.");
			 player.canTeleport = false;
            TaskQueue.queue(new Task(5) {
                @Override
                public void execute() {
                Zulrah mob = new Zulrah(player, new Location(2266, 3073, player.getIndex() << 2));
                mob.face(player);
                mob.getUpdateFlags().sendAnimation(new Animation(5071));
                player.face(mob);
                player.send(new SendMessage("Welcome to Zulrah's shrine."));
                stop();
                //setNext(-1);
                }

                @Override
                public void onStop() {
                player.canTeleport = true;
                }
            });
			
		//Reverts hasta
		case DialogueConstants.OPTIONS_2_2:
			
				//DialogueManager.sendNpcChat(player, 2914, Emotion.ANNOYED, "You need a Zamorakian hasta to do this!");
				setNext(-1);
			
			break;
			
		//Nothing
		case DialogueConstants.OPTIONS_3_3:
			player.send(new SendRemoveInterfaces());
			break;
	
		}
		return false;
	}

	@Override
	public void execute() {
		switch (next) {
		case 0:
			DialogueManager.sendNpcChat(player, 2033, Emotion.DISTRESSED_CONTINUED, "Hello sacrifice.", "I can use that boat to row you to Zulrah's Shrine.", "Would you like to go?");
			next++;
			break;
		case 1:
			DialogueManager.sendOption(player, "Yes.", "No.");
			break;
		
		}
	}

}
