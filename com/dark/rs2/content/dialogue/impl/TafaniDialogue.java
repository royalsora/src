package com.dark.rs2.content.dialogue.impl;

import com.dark.core.task.Task;
import com.dark.core.task.TaskQueue;
import com.dark.rs2.content.dialogue.Dialogue;
import com.dark.rs2.content.dialogue.DialogueConstants;
import com.dark.rs2.content.dialogue.DialogueManager;
import com.dark.rs2.content.dialogue.Emotion;
import com.dark.rs2.content.skill.Skills;
import com.dark.rs2.content.skill.magic.MagicSkill.TeleportTypes;
import com.dark.rs2.entity.Animation;
import com.dark.rs2.entity.Graphic;
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
public class TafaniDialogue extends Dialogue {
	
	/**
	 * Otto Godblessed
	 * @param player
	 */
	public TafaniDialogue(Player player) {
		this.player = player;
	}
	

	@Override
	public boolean clickButton(int id) {
		switch (id) {
		
		//Yes Option
		case DialogueConstants.OPTIONS_2_1:
			int hitpointsLevel = player.getMaxLevels()[3];
		//do shit here
			if (player.getRights() > 0) {
				player.getSpecialAttack().setSpecialAmount(100);
				player.getSpecialAttack().update();
				player.getSkill().setLevel(3, hitpointsLevel);
				player.getSkill().update(3);
				player.getUpdateFlags().sendGraphic(new Graphic(436));
				DialogueManager.sendNpcChat(player, 3343, Emotion.HAPPY_TALK, "And you're healed! Psst, I've thrown in my", "special attack serum in there too.", "Your special attack is also restored. Have fun, Warrior!");
				setNext(-1);
				break;
			}
			
			player.getSkill().setLevel(3, hitpointsLevel);
			player.getSkill().update(3);
			player.getUpdateFlags().sendGraphic(new Graphic(436));
			DialogueManager.sendNpcChat(player, 3343, Emotion.HAPPY_TALK, "And you're healed! Have fun, Warrior!");
			setNext(-1);
			//for donator
			
			break;
			
			//No option
		case DialogueConstants.OPTIONS_2_2:
			
				
				setNext(-1);
			
			break;
			
		
	
		}
		return false;
	}

	@Override
	public void execute() {
		switch (next) {
		case 0:
			DialogueManager.sendNpcChat(player, 3343, Emotion.DISTRESSED_CONTINUED, "Hello Warrior.", "I can heal your wounds for you.", "Would you like to be healed?");
			next++;
			break;
		case 1:
			DialogueManager.sendOption(player, "Yes.", "No.");
			break;
		
		}
	}

}
