package com.dark.rs2.content.skill.magic.effects;

import com.dark.core.util.Utility;
import com.dark.rs2.content.combat.impl.CombatEffect;
import com.dark.rs2.entity.Entity;
import com.dark.rs2.entity.player.Player;

public class FlamesOfZamorakEffect implements CombatEffect {
	
	@Override
	public void execute(Player p, Entity e) {
		if ((Utility.randomNumber(4) == 0) && (p.getLastDamageDealt() > 0) && (!e.isNpc())) {
			Player other = com.dark.rs2.entity.World.getPlayers()[e.getIndex()];

			if (other != null) {
				short[] tmp40_35 = other.getLevels();
				tmp40_35[6] = ((short) (int) (tmp40_35[6] - other.getLevels()[6] * 0.05D));

				if (other.getLevels()[6] < 0) {
					other.getLevels()[6] = 0;
				}

				other.getSkill().update(6);
			}
		}
	}
}