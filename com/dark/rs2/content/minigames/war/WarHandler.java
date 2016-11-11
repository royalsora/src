package com.dark.rs2.content.minigames.war;

import java.util.Calendar;
import java.util.Date;

import com.dark.core.task.Task;
import com.dark.core.task.TaskQueue;
import com.dark.core.util.Utility;
import com.dark.rs2.content.io.PlayerSave;
import com.dark.rs2.content.wilderness.PlayerKilling;
import com.dark.rs2.entity.Entity;
import com.dark.rs2.entity.Location;
import com.dark.rs2.entity.World;
import com.dark.rs2.entity.item.Item;
import com.dark.rs2.entity.player.Player;
import com.dark.rs2.entity.player.PlayerConstants;
import com.dark.rs2.entity.player.net.out.impl.SendMessage;

public class WarHandler {

	public static War CURRENT_WAR;

	// startWar
	public static void startWar() {
	TaskQueue.queue(new Task(1, false) {
		@Override
		public void execute() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		double hour = cal.get(Calendar.HOUR_OF_DAY);
		if (hour >= 12.00 && hour <= 12.99) {
			if (CURRENT_WAR == null)
				CURRENT_WAR = new War();
			checkforEnd();
			World.sendGlobalMessage("@blu@War has started! Do ::war to join. It will end at 6PM [SERVER TIME]. ");
			stop();
		}

		}

		@Override
		public void onStop() {
		}
	});

	}

	// endWar
	public static void checkforEnd() {
	TaskQueue.queue(new Task(1, false) {
		@Override
		public void execute() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		double hour = cal.get(Calendar.HOUR_OF_DAY);
		if (hour >= 18.00 && hour <= 18.99) {
			World.sendGlobalMessage("@blu@War has ended! Be ready again at 12PM [SERVER TIME]");
			endWar();
			stop();
		}

		}

		@Override
		public void onStop() {
		startWar();
		}
	});
	}

	// Player joins war and teleports to war
	public void joinWar(Player player) {
	if (CURRENT_WAR.warPlayer().contains(player)) {
		return;
	}
	CURRENT_WAR.addPlayer(player);
	teleportToWar(player);
	}

	/*
	 * If player dies, teleports, or logs out from war remove player fom the
	 * list Handle death task
	 */
	public void deathTask(Entity killer, Player player) {
	if (player == null || killer == null) {
		return;
	}
	if (CURRENT_WAR.warPlayer().contains(player)) {
		CURRENT_WAR.warPlayer().remove(player);
	}
	int pointsAwarded = Utility.random(50) + 1;
	if(killer.getPlayer().getLastPlayerKilld() != null && killer.getPlayer().getLastPlayerKilld().equalsIgnoreCase(player.getUsername())) {
		killer.getPlayer().send(new SendMessage("@blu@You dont get points for killing the same person."));
	} else {
	killer.getPlayer().warPoints += pointsAwarded;
	killer.getPlayer().send(new SendMessage("@blu@You have recieved " + pointsAwarded + " war points."));
	}
	killer.getPlayer().setLastPlayerKilld(player.getUsername());
	System.out.println(killer.getPlayer().getLastPlayerKilld());
	pointsAwarded = 0;
	player.setInWar(false);

	}

	public void teleportToWar(Player player) {
	player.teleport(new Location(Utility.random(20) < 10 ? 3238 + Utility.random(6) : 3238 - Utility.random(6), Utility.random(20) < 10 ? 2782 + Utility.random(10) : 2782 - Utility.random(10), CURRENT_WAR.getHeight()));
	player.setInWar(true);
	System.out.println("spawned in war zone");
	}

	public static void endWar() {
	CURRENT_WAR.warPlayer().forEach(player -> {
		pointsToTokens(player, player.getWarPoints());
		player.teleport(PlayerConstants.HOME);
		player.setWarPoints(0);
		player.setInWar(false);
	});
	WarHandler.CURRENT_WAR = null;
	}

	public static void pointsToTokens(Player player, int points) {
	int tokenAmount = points / 10;
	player.getInventory().addOrCreateGroundItem(4278, tokenAmount, true);
	player.send(new SendMessage("You have been given " + tokenAmount + " war-tokens in excahnge for " + points + " war points."));
	player.setWarPoints(0);
	}

	public void tokensToReward(Player player, String tokenAmount) {
	int temp = 0;
	try {
		temp = Integer.parseInt(tokenAmount.toLowerCase().replaceAll("k", "000").replaceAll("m", "000000").replaceAll("b", "000000000"));
	} catch (NumberFormatException e) {
		player.send(new SendMessage("Numbers only please."));
		return;
	}
	int value = (int) temp;
	if (value < 50) {
		player.send(new SendMessage("You must put a minimum of 50 tokens in."));
		return;
	}
	if (player.getInventory().hasItemAmount(new Item(4278, value))) {
		WarRewardHandler.giveReward(player, value);
		System.out.println(player.getUsername() + ": " + value);
		player.getInventory().remove(4278, value);
	} else {
		player.send(new SendMessage("You dont have enough tokens for this trade."));
		return;
	}
	}
}
