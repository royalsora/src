package com.dark.rs2.entity.mob.abilities;

import com.dark.core.util.Utility;
import com.dark.rs2.content.combat.CombatEffect;
import com.dark.rs2.content.skill.prayer.PrayerBook.Prayer;
import com.dark.rs2.entity.Entity;
import com.dark.rs2.entity.World;
import com.dark.rs2.entity.player.Player;

/**
 * Barrelchest
 * 
 * @author Daniel
 */
public class BarrelchestAbility implements CombatEffect {

	@Override
	public void execute(Entity e1, Entity e2) {
		if (e1.getLastDamageDealt() <= 0) {
			return;
		}

		if ((e2.getLevels()[5] > 0) && (!e2.isNpc())) {
			Player p = World.getPlayers()[e2.getIndex()];

			if (p != null) {
				p.getPrayer().drain(10 + Utility.randomNumber(10));
				if (p.getPrayer().active(Prayer.PROTECT_FROM_MELEE)) {
					p.getPrayer().disable(Prayer.PROTECT_FROM_MELEE);
				}
			}
		}
	}
}
