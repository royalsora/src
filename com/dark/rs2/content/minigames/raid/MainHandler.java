package com.dark.rs2.content.minigames.raid;

import java.util.ArrayList;

import com.dark.Server;
import com.dark.core.util.Utility;
import com.dark.rs2.content.minigames.raid.RaidBoss.Boss;
import com.dark.rs2.content.minigames.raid.rounds.RoundHandler;
import com.dark.rs2.entity.Location;
import com.dark.rs2.entity.World;
import com.dark.rs2.entity.item.Item;
import com.dark.rs2.entity.mob.Mob;
import com.dark.rs2.entity.player.Player;
import com.dark.rs2.entity.player.net.out.impl.SendMessage;
import com.dark.rs2.entity.player.net.out.impl.SendRemoveInterfaces;

public class MainHandler {

	/** Keep track of all player created raids. **/
	public static ArrayList<String> raidList = new ArrayList<String>();

	/**
	 * Creator starts raid remove name raid off of list
	 */
	public static void startRaid(Player creator) {
	if (raidList.contains(creator.getUsername())) {
		raidList.remove(creator.getUsername());
	}
	if (creator.getRaid().playersInRaid.size() > 8) {
		creator.getRaid().doubleNpcs = true;
	}
	for (String player : creator.getRaid().playersInRaid) {
		World.getPlayerByName(player).send(new SendMessage("Raid round is going to start in 15 seconds."));
		World.getPlayerByName(player).setInRaid(true);
		World.getPlayerByName(player).setRaidOwner(creator);
		World.getPlayerByName(player).teleport(new Location(2273 - Utility.random(3), 4695 + Utility.random(3), creator.getRaid().playersInRaid.contains(player) ? creator.getIndex() << 2 : creator.getIndex() << 2));
		World.getPlayerByName(player).send(new SendMessage("You must do above " + Rewards.getPercentage(World.getPlayerByName(player)) + "% of the damage to get a rare."));
	}
	Rewards.tempPartySize = creator.getRaid().playersInRaid.size();
	creator.getRaid().currentRound = 0;
	RoundHandler.startRound(creator, creator.getRaid().bossType);
	}

	/**
	 * Create a raid Add to the current raidList
	 **/
	public static void createRaid(Player creator, Boss type) {
	if (creator.getRaid() != null) {
		creator.send(new SendMessage("@red@You already own a raid, please finish that before joining somebody else's."));
	}

	creator.setRaid(new Raid());
	raidList.add(creator.getUsername());
	creator.getRaid().playersInRaid.add(creator.getUsername());
	creator.getRaid().partySize++;
	creator.getRaid().startedRaid = false;
	creator.getRaid().doubleNpcs = false;
	creator.getRaid().bossType = type;
	}

	/**
	 * When players pick from the raidList add that player to the creators party
	 * size Increase party count + 1
	 **/
	public static void addPlayerToRaidParty(Player creator, Player player) {
	if (creator.getRaid().playersInRaid.size() > 15) {
		player.send(new SendMessage("@red@Raid is full"));
		return;
	}
	if (player.isInRaidParty()) {
		player.send(new SendMessage("@red@You are already in another raid party."));
		return;
	}
	if (creator.isInRaid()) {
		player.send(new SendMessage("@red@The raid hoster, has already started the raid. You were a bit late."));
		return;
	}
	if (creator.getRaid().playersInRaid.contains(player.getUsername())) {
		player.send(new SendMessage("@red@You are already in this party."));
		player.send(new SendRemoveInterfaces());
		return;
	}

	if (player.getInventory().hasItemAmount(995, 4000000)) {
		creator.getRaid().partySize++;
		creator.getRaid().playersInRaid.add(player.getUsername());
		//creator.send(new SendMessage("@or2@" + player.getUsername() + " @bla@has paid you 4m to join your party for the @or2@" + Mob.getDefinition(creator.getRaid().bossType.getId()).getName() + " @bla@raid."));
		creator.send(new SendMessage("@red@You now have a party size of @cya@" + creator.getRaid().partySize));
		player.setInRaidParty(true);
		player.getInventory().remove(995, 4000000);
		player.send(new SendMessage("@red@You have paid 4m to join " + creator.getUsername() + "'s raid."));
		player.setRaidOwner(creator);
	} else {
		player.send(new SendMessage("@red@You need to have 4m so you can pay the hoster before, being able to join the party"));
	}
	}
}
