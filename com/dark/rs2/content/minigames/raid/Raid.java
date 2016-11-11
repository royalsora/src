package com.dark.rs2.content.minigames.raid;

import java.util.ArrayList;
import com.dark.rs2.content.minigames.raid.RaidBoss.Boss;
import com.dark.rs2.content.minigames.raid.rounds.RoundHandler;
import com.dark.rs2.entity.World;
import com.dark.rs2.entity.mob.Mob;
import com.dark.rs2.entity.mob.VirtualMobRegion;
import com.dark.rs2.entity.player.Player;
import com.dark.rs2.entity.player.PlayerConstants;

public class Raid {

	// Type of boss the raid is hosting
	public Boss bossType;

	// Creator of raid lobby
	public Player raidOwner;

	// Party size of raid. Minimum 3
	public int partySize;

	public int currentRound = 0;

	// Default is false
	public boolean startedRaid;

	// Total raid damage dealt so far
	public int totalRaidDamage = 0;

	// private raid region
	public final VirtualMobRegion region = new VirtualMobRegion();

	// List of the damage leaders
	public int leaderSize;
	public String names[] = new String[leaderSize];

	// List of all the players currently in raid
	public ArrayList<String> playersInRaid = new ArrayList<String>();

	// List of all the npcs, that spawn in the rounds.
	public ArrayList<Mob> listOfRoundMobs = new ArrayList<Mob>();

	public RoundHandler handleRound = new RoundHandler();

	public boolean doubleNpcs;
	
	public int getTotalRaidDamage(Player creator) {
	for (String names : creator.getRaid().playersInRaid) {
		creator.getRaid().totalRaidDamage += World.getPlayerByName(names).getRaidContribution();
	}
	return creator.getRaid().totalRaidDamage;
	}

	public void resetRaid(Player creator) {
	if (listOfRoundMobs.size() > 0) {
		for (Mob m : listOfRoundMobs) {
			m.remove();
		}
	}
	for (String name : creator.getRaid().playersInRaid) {
		World.getPlayerByName(name).setInRaidParty(false);
		World.getPlayerByName(name).setInRaid(false);
		World.getPlayerByName(name).teleport(PlayerConstants.HOME);
		World.getPlayerByName(name).setRaidContribution(0);
	}
	creator.getRaid().playersInRaid.clear();
	creator.getRaid().listOfRoundMobs.clear();
	creator.getRaid().startedRaid = false;
	creator.getRaid().bossType = null;
	creator.getRaid().currentRound = 0;
	creator.getRaid().totalRaidDamage = 0;
	creator.setRaid(null);

	}

	public Raid() {
	}
}
