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
import com.dark.rs2.entity.mob.Mob;
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
public class KrakenDialogue extends Dialogue {
	
	/**
	 * Otto Godblessed
	 * @param player
	 */
	public KrakenDialogue(Player player) {
		this.player = player;
	}
	

	@Override
	public boolean clickButton(int id) {
		switch (id) {
		
		//Creates hasta
		case DialogueConstants.OPTIONS_2_1:
			if (player.canTeleport == false || player.inKraken()) {
				return false;
			}
			if (!player.getInventory().hasItemAmount(995, 25000)) {
				DialogueManager.sendStatement(player, "You need 25,000 coins to enter the lair.");
				setNext(-1);
				return false;
			}
			player.getInventory().remove(995, 25000);
			 player.getMagic().teleport(3696, 5807, player.getIndex() << 2, TeleportTypes.CRAWL);
             TaskQueue.queue(new Task(5) {
                 @Override
                 public void execute() {
                     int[][] DATA = {{493, 3691, 5810}, {493, 3691, 5814}, {493, 3700, 5810}, {493, 3700, 5814}, {496, 3694, 5811}};
                     for (int i = 0; i < DATA.length; i++) {
                         Mob mob = new Mob(player, DATA[i][0], false, false, false, new Location(DATA[i][1], DATA[i][2], player.getZ()));
                         mob.setCanAttack(false);
                         player.face(mob);
                     }
                     stop();
                 }

                 @Override
                 public void onStop() {
                     player.whirlpoolsHit = 0;
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
			DialogueManager.sendStatement(player, "Do you wish to enter the Kraken Lair?", "This will cost a one time fee of 25k.");
			next++;
			break;
		case 1:
			DialogueManager.sendOption(player, "Yes.", "No.");
			break;
		
		}
	}

}
