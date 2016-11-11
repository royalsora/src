package com.dark.rs2.content.minigames.raid.rounds;

import com.dark.rs2.entity.player.Player;

public abstract class RaidRound {

	// Start the round of which the raid owner chose, conntinue rounds if not
	// finished
	public abstract void startRound(Player creator, int currentRound, int maxRound);

	// Ends the raid
	// Checks if creator is done with raid
	// if not than
	// outprint the damage leaders
	// will get total damage dealt altogether currently.

	public abstract void endRaid(Player creato);

}
