package com.dark.rs2.entity.mob.impl;

import com.dark.rs2.content.combat.Hit;
import com.dark.rs2.entity.Entity;
import com.dark.rs2.entity.Location;
import com.dark.rs2.entity.World;
import com.dark.rs2.entity.item.Item;
import com.dark.rs2.entity.mob.Mob;
import com.dark.rs2.entity.player.Player;

public class KalphiteQueen extends Mob {
	
	private Entity lastKilledBy = null;

	public KalphiteQueen() {
		super(963, true, new Location(3480, 9495));
	}

	@Override
	public int getAffectedDamage(Hit hit) {
		switch (hit.getType()) {
		case RANGED:
		case MAGIC:
			if (getId() == 4303) {
				return 0;
			}
			break;
		case MELEE:
			if (getId() == 963) {
				if ((hit.getAttacker() != null) && (!hit.getAttacker().isNpc())) {
					Player player = World.getPlayers()[hit.getAttacker().getIndex()];
					Item weapon = player.getEquipment().getItems()[3];
					/*if ((player != null) && (player.getMelee().isVeracEffectActive())) {
						return hit.getDamage();
					}
					if (((weapon != null) && (weapon.getId() == 13263))) {
						return hit.getDamage();
					}*/
				}
				return 0;
		}
			break;
		default:
			return hit.getDamage();
		}

		return hit.getDamage();
	}

	@Override
	public void onDeath() {
		lastKilledBy = getCombat().getLastAttackedBy();
	}

	public void transform() {
		transform(getId() == 963 ? 963 : 963);

		if (lastKilledBy != null) {
			getCombat().setAttack(lastKilledBy);
		}
	}
}
