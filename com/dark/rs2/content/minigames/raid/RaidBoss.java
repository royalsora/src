package com.dark.rs2.content.minigames.raid;

public class RaidBoss {

	public enum Boss {
		BANDOS(2215, 6);

		Boss(int npcId, int rounds) {
		this.npcId = npcId;
		this.maxRounds = rounds;
		}

		public int getId() {
		return npcId;
		}

		private int maxRounds;

		private int npcId;

		public int getMaxRounds() {
		return maxRounds;
		}

		public void setMaxRounds(int rounds) {
		this.maxRounds = rounds;
		}

	}
}
