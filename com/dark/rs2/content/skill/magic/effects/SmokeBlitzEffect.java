package com.dark.rs2.content.skill.magic.effects;

import com.dark.core.util.Utility;
import com.dark.rs2.content.combat.impl.CombatEffect;
import com.dark.rs2.entity.Entity;
import com.dark.rs2.entity.player.Player;

public final class SmokeBlitzEffect implements CombatEffect {
	@Override
	public void execute(Player p, Entity e) {
		if ((p.getLastDamageDealt() >= 0) && (Utility.randomNumber(2) == 0))
			e.poison(4);
	}
}
