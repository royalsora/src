package com.dark.rs2.content.combat.special.effects;

import com.dark.core.util.Utility;
import com.dark.rs2.content.combat.impl.CombatEffect;
import com.dark.rs2.entity.Entity;
import com.dark.rs2.entity.Graphic;
import com.dark.rs2.entity.player.Player;

/**
 * Handles the Abyssal Tentacle Whip Effect
 * @author Goten
 *
 */
public class AbyssalTentacleEffect implements CombatEffect {
	
	@Override
	public void execute(Player player, Entity entity) {
		if (!entity.isNpc()) {
			Player p2 = com.dark.rs2.entity.World.getPlayers()[entity.getIndex()];
			if (p2 == null) {
				return;
			}
			p2.freeze(10, 5);
			p2.getUpdateFlags().sendGraphic(new Graphic(181));
			if (Utility.random(100) < 50) {
				p2.poison(4);
			}
		}
	}
	
}
