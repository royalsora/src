package com.dark.core.network.mysql;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.dark.core.util.Utility;
import com.dark.rs2.content.skill.Skills;
import com.dark.rs2.entity.player.Player;

 
public final class HiscoreUpdater {

	private static ExternalDatabase database = null;

	private static ExecutorService executorService = null;

	private static boolean prepared = false;

	private static long getTotalExperience(Player player) {
		return player.getSkill().getTotalExperience();
	}

	@SuppressWarnings("unused")
	private static int getTotalLevel(Player player) {
		return player.getSkill().getTotalLevel();
	}

	public static void prepare() {
		database = new ExternalDatabase("darkasyl_lucky", "~fLLyWM}KI3Q", "Horizon-OS.org/darkasyl_hs", 3);
		database.initialise();
		executorService = Executors.newSingleThreadExecutor();
		prepared = true;
	}

	public static void shutdown() {
		database.shutdown();
		executorService.shutdown();
	}
	
	public static void delete(Player player) {
	if (!prepared) {
		throw new IllegalStateException("unprepared");
	}
	
	if (database.getPooledConnection() == null) {
		return;
	}

	executorService.submit(() -> {
		StringBuilder bldr = new StringBuilder(1256);
		bldr.append("DELETE FROM hs_users (");
		bldr.append("`username`");
		bldr.append(") VALUES (");
		bldr.append("'" + Utility.formatPlayerName(player.getUsername().toLowerCase()) + "'");

	});
}

	public static void update(Player player) {
		if (!prepared) {
			throw new IllegalStateException("unprepared");
		}
		
		if (database.getPooledConnection() == null) {
			return;
		}
		
		if (player.getRights() == 3) {
			return;
		}
		
		delete(player);
		
		executorService.submit(() -> {
			final long totalExp = getTotalExperience(player);
			for (int prestige : player.getSkillPrestiges()) {
				if (prestige > 0) {
				}
			}
			
			StringBuilder bldr = new StringBuilder(1256);

			bldr.append("INSERT INTO hs_users (");
			bldr.append("`username`");
			bldr.append(",`rights`");
			bldr.append(",`overall_xp`");

			for (int skill = 0; skill < Skills.SKILL_COUNT; skill++) {
				if (skill == Skills.CONSTRUCTION || skill == Skills.DUNGEONEERING || skill == Skills.SUMMONING) {
					continue;
				}
				String skillName = Skills.SKILL_NAMES[skill];
				bldr.append(",`" + skillName + "_xp`");
			}

			bldr.append(") VALUES (");
			bldr.append("'" + Utility.formatPlayerName(player.getUsername().toLowerCase()) + "'");
			bldr.append("," + player.getRights());
			bldr.append("," + totalExp);

			for (int skill = 0; skill < Skills.SKILL_COUNT; skill++) {
				if (skill == Skills.CONSTRUCTION || skill == Skills.DUNGEONEERING || skill == Skills.SUMMONING) {
					continue;
				}
				bldr.append("," + player.getSkill().getExperience()[skill]);
			}
			bldr.append(")");
			bldr.append(" ON DUPLICATE KEY UPDATE ");
			bldr.append("`rights`=" + player.getRights());
			bldr.append(",`overall_xp`=" + totalExp);

			for (int skill = 0; skill < Skills.SKILL_COUNT; skill++) {
				if (skill == Skills.CONSTRUCTION || skill == Skills.DUNGEONEERING || skill == Skills.SUMMONING) {
					continue;
				}
				String skillName = Skills.SKILL_NAMES[skill];
				bldr.append(",`" + skillName + "_xp`=" + player.getSkill().getExperience()[skill]);
			}

			database.executeQuery(bldr.toString());
		});
	}
}