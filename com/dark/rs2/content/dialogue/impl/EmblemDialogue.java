package com.dark.rs2.content.dialogue.impl;

import com.dark.core.util.Utility;
import com.dark.rs2.content.dialogue.Dialogue;
import com.dark.rs2.content.dialogue.DialogueConstants;
import com.dark.rs2.content.dialogue.DialogueManager;
import com.dark.rs2.content.dialogue.Emotion;
import com.dark.rs2.content.shopping.impl.BountyShop;
import com.dark.rs2.entity.player.Player;
import com.dark.rs2.entity.player.net.out.impl.SendRemoveInterfaces;

/**
 * Dialogue that handles Emblem Trader
 * 
 * @author Daniel
 *
 */
public class EmblemDialogue extends Dialogue {

	/**
	 * Emblem Trader
	 * 
	 * @param player
	 */
	public EmblemDialogue(Player player) {
		this.player = player;
	}

	/**
	 * The bounty total
	 */
	int bountyTotal;

	/**
	 * The tier data
	 */
	public int[][] TIER_DATA = { { 12746, 50_000 }, { 12748, 100_000 }, { 12749, 200_000 }, { 12750, 400_000 }, { 12751, 750_000 }, { 12752, 1_200_000 }, { 12753, 1_750_000 }, { 12754, 2_500_000 }, { 12755, 3_500_000 }, { 12756, 5_000_000 } };

	/**
	 * Calculation of tier
	 */
	public void calculateTotal() {
		bountyTotal = 0;
		for (int[] emblem : TIER_DATA) {
			bountyTotal += player.getInventory().getItemAmount(emblem[0]) * emblem[1];
		}
	}

	/**
	 * Clicking button
	 * 
	 * @param id
	 */
	@Override
	public boolean clickButton(int id) {
		switch (id) {
// Trading tiers
        case DialogueConstants.OPTIONS_5_1:
            int countT = 0;
            bountyTotal = 0;
            int a=0;
            for (int[] emblem : TIER_DATA) {
                countT = player.getInventory().getItemAmount(emblem[0]);
                if(countT >= 1){
                    if(a == 0) a=1;
                bountyTotal += countT * emblem[1];
                player.getInventory().remove(emblem[0],countT);
            }
            }
                if (a==1) {
                    player.send(new SendRemoveInterfaces());
                    player.setBountyPoints(player.getBountyPoints() + bountyTotal);
                    DialogueManager.sendStatement(player, "You traded your emblems for " + Utility.format(bountyTotal) + " points.");
                    break;
                } else {
                    DialogueManager.sendNpcChat(player, 315, Emotion.ANGRY_1, "You do not have any tiers on you!");
                }
           
            break;

		// Statistics
		case DialogueConstants.OPTIONS_5_2:
			double kdr = ((double) player.getKills()) / ((double) player.getDeaths());
			DialogueManager.sendInformationBox(player, "PvP Statistics:", "Points: @red@" + player.getBountyPoints(), "Kills: @red@" + player.getKills(), "Deaths: @red@" + player.getDeaths(), "KDR: @red@" + kdr);
			break;

		// Trading
		case DialogueConstants.OPTIONS_5_3:
			player.getShopping().open(BountyShop.SHOP_ID);
			break;

		// Skulling
		case DialogueConstants.OPTIONS_5_4:
			if (player.getSkulling().isSkulled()) {
				DialogueManager.sendNpcChat(player, 315, Emotion.DEFAULT, "You already have a wilderness skull!");
				return false;
			}
			player.getSkulling().skull(player, player);
			DialogueManager.sendNpcChat(player, 315, Emotion.DEFAULT, "You have been skulled.");
			break;

		// Close dialogue
		case DialogueConstants.OPTIONS_5_5:
			if (player.getCredits() < 3) {
				DialogueManager.sendStatement(player, "You need 3 Gold points to do this!");
				return false;
			}
			player.setCredits(player.getCredits() - 3);
			player.setDeaths(0);
			DialogueManager.sendStatement(player, "Your deaths have been reset!");
			break;
		}
		return false;
	}

	/**
	 * Execute dialogue
	 */
	@Override
	public void execute() {
		switch (next) {
		case 0:
			DialogueManager.sendNpcChat(player, 315, Emotion.CALM, "Hello adventurer.", "How may I help you today?");
			next++;
			break;
		case 1:
			DialogueManager.sendOption(player, "Sell tiers", "Show me my PvP statistics", "I would like to trade", "Give me a wilderness skull", "Reset KDR");
			break;
		}
	}

}
