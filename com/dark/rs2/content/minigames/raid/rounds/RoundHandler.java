package com.dark.rs2.content.minigames.raid.rounds;

import java.util.ArrayList;
import java.util.HashMap;

import com.dark.core.task.Task;
import com.dark.core.task.TaskQueue;
import com.dark.rs2.content.minigames.raid.RaidBoss.Boss;
import com.dark.rs2.content.minigames.raid.Rewards;
import com.dark.rs2.entity.World;
import com.dark.rs2.entity.player.Player;
import com.dark.rs2.entity.player.net.out.impl.SendMessage;

public class RoundHandler {
	public static RaidRound mainRound;

	public static HashMap<Integer, RaidRound> roundType = new HashMap<Integer, RaidRound>();

	public Bandos BANDOS = new Bandos();

	public RoundHandler() {
	roundType.put(0, BANDOS);
	}

	public static void startRound(Player creator, Boss raidBoss) {
	if (creator.getRaid().currentRound > 0) {
		for (String name : creator.getRaid().playersInRaid) {
			World.getPlayerByName(name).send(new SendMessage("Startin the next round in 15 seconds."));
		}
	}
	TaskQueue.queue(new Task(15) {
		@Override
		public void execute() {
		if (creator.getRaid() != null) {
			switch (raidBoss) {
			case BANDOS:
				roundType.get(0).startRound(creator, creator.getRaid().currentRound, creator.getRaid().bossType.getMaxRounds());
				break;
			}
		}
		stop();
		}

		@Override
		public void onStop() {
		}
	});

	}

	public static void getRoundDamage(Player creator) {
	int totalDamage = 0;
	for (String name : creator.getRaid().playersInRaid) {
		totalDamage += World.getPlayerByName(name).getRaidContribution();
	}
	System.out.println("Raid max damage = " + totalDamage);
	getPercentage(creator, totalDamage);
	}

	public static ArrayList<Player> nonContributors = new ArrayList<Player>();

	public static void getPercentage(Player creator, int totalDamage) {
	for (String name : creator.getRaid().playersInRaid) {
		int percentage = (int) ((World.getPlayerByName(name).getRaidContribution() * 100.0f) / totalDamage);
		World.getPlayerByName(name).send(new SendMessage("You have done " + percentage + "% of the damage so far."));
		if (nonContributors.contains(World.getPlayerByName(name)) && Rewards.getPercentage(World.getPlayerByName(name)) > 5) {
			nonContributors.remove(World.getPlayerByName(name));
		}
		if (Rewards.getPercentage(World.getPlayerByName(name)) < 6 && !nonContributors.contains(World.getPlayerByName(name))) {
			nonContributors.add(World.getPlayerByName(name));
		}
	}
	creator.getRaid().totalRaidDamage = totalDamage;
	}

	public static void endRaid(Player creator) {
	for (String name : creator.getRaid().playersInRaid) {
		System.out.println(creator.getRaid().totalRaidDamage);
		if ((int) ((World.getPlayerByName(name).getRaidContribution() * 100.0f) / creator.getRaid().totalRaidDamage) > 10) {
			Rewards.giveReward(World.getPlayerByName(name), World.getPlayerByName(name).getRaidContribution(), creator.getRaid().totalRaidDamage);
		}
	}
	}
}
