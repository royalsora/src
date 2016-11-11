package com.dark.rs2.content.dialogue.impl;

import com.dark.core.util.Utility;
import com.dark.rs2.content.achievements.AchievementHandler;
import com.dark.rs2.content.achievements.AchievementList;
import com.dark.rs2.content.dialogue.Dialogue;
import com.dark.rs2.content.dialogue.DialogueConstants;
import com.dark.rs2.content.dialogue.DialogueManager;
import com.dark.rs2.content.dialogue.Emotion;
import com.dark.rs2.entity.item.Item;
import com.dark.rs2.entity.player.Player;
import com.dark.rs2.entity.player.net.out.impl.SendRemoveInterfaces;

/**
 * Handles the wilderness resource trivia
 * @author Daniel
 *
 */
public class SelipDialogue extends Dialogue {

	/**
	 * Piles dialogue
	 * @param player
	 */
	public SelipDialogue(Player player) {
		this.player = player;
	}

	/**
	 * The items that may be converted in the arena
	 */
	public int ITEMS[][] = { { 451, 452 }, { 11934, 11935 }, { 440, 441 }, { 453, 454 }, { 444, 445 }, { 447, 448 }, { 449, 450 }, { 1515, 1516 }, { 1513, 1514 }, { 249, 250 }, { 251, 252 }, { 253, 254 }, { 255, 256 }, { 257, 258 }, { 259, 260 }, { 261, 262 }, { 263, 264 }, { 265, 266 }, { 267, 268 }, { 269, 270 }, { 2481, 2482 }, { 3000, 3001 }, { 2998, 2999 }, { 7936, 7937 }, { 1436, 1437 }, { 989, 990 }, { 227, 228 }, { 1777, 1778 }, { 1617, 1618 }, { 1619, 1620 }, { 1620, 1621 }, { 1622, 1623 }, { 1631, 1632 }, { 6571, 6572 }, { 1601, 1602 }, { 1603, 1604 }, { 1605, 1606 }, { 1607, 1608 }, { 1615, 1616 }, { 2444, 2445 }, { 2434, 2435 }, { 12695, 12696 }, { 3024, 3025 }, { 6685, 6686 }, { 2436, 2437 }, { 2440, 2441 }, { 2442, 2443 }, { 11260, 11261 }, { 1517, 1519 }, { 1521, 1522 }, { 383, 384 }, { 377, 378 }, { 7944, 7945 } };

	@Override
	public boolean clickButton(int id) {
		switch (id) {

		case DialogueConstants.OPTIONS_2_1:
			player.send(new SendRemoveInterfaces());

			for (int i = 0; i < ITEMS.length; i++) {
				if (player.getInventory().hasItemId(new Item(ITEMS[i][1]))) {
					int amount = player.getInventory().getItemAmount(ITEMS[i][1]);
					int payment = player.getInventory().getItemAmount(ITEMS[i][0]) * 5;
					
					if (!player.getInventory().hasItemId(new Item(995, payment))) {
						DialogueManager.sendStatement(player, Utility.format(payment) + " coins is required to do this; which you do not have!"); 
						break;
					}
					player.getInventory().remove(new Item(ITEMS[i][1], 1));
					player.getInventory().add(new Item(ITEMS[i][0], 1));
					player.getInventory().remove(995, payment);
				
					AchievementHandler.activateAchievement(player, AchievementList.EXCHANGE_500_ITEMS_PILES, amount);		
					break;
				} else {
					DialogueManager.sendStatement(player, "You do not contain any items that are allowed to be noted!");
				}
			}

			break;

		case DialogueConstants.OPTIONS_2_2:
			player.send(new SendRemoveInterfaces());
			break;

		}
		return false;
	}

	@Override
	public void execute() {
		switch (next) {

		case 0:
			DialogueManager.sendNpcChat(player, 1615, Emotion.HAPPY, "I can note any items obtained from the resource", "trivia for 50 coins an item.");
			next++;
			break;
		case 1:
			DialogueManager.sendOption(player, "Note items", "Nevermind");
			break;

		}
	}

}
