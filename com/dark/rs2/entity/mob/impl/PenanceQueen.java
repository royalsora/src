package com.dark.rs2.entity.mob.impl;

import com.dark.core.util.Utility;
import com.dark.rs2.content.combat.Hit;
import com.dark.rs2.content.combat.Hit.HitTypes;
import com.dark.rs2.entity.Entity;
import com.dark.rs2.entity.Graphic;
import com.dark.rs2.entity.Location;
import com.dark.rs2.entity.mob.Mob;


public class PenanceQueen extends Mob {
	
	private Entity lastKilledBy = null;

	public PenanceQueen() {
	super(5775, true, new Location(1886, 5483));
}

	@Override
	public int getRespawnTime() {
		return 60;
	}
	
	@Override
	public void onDeath() {
		lastKilledBy = getCombat().getLastAttackedBy();
       
	}

	@Override
	public void onHit(Entity entity, Hit hit) {
		if (entity != null && !entity.isNpc()) {
			if (entity.getPlayer().isStunned()) {
				return;
			}
			int random = Utility.random(3);
			if (random == 1) {
				Magic(entity.getPlayer());
			}
			if (random == 3) {
				Ranged(entity.getPlayer());
			}
		}
	}
	
	private void Magic(Entity player) {
	player.hit(new Hit(1 + Utility.random(34), HitTypes.MAGIC));
	player.getUpdateFlags().sendGraphic(new Graphic(869, true));
}
	
	private void Ranged(Entity player) {
	player.hit(new Hit(1 + Utility.random(34), HitTypes.RANGED));
	player.getUpdateFlags().sendGraphic(new Graphic(872, true));
}

	public void transform() {
		transform(getId() == 2806 ? 2806 : 6401);

		if (lastKilledBy != null) {
			getCombat().setAttack(lastKilledBy);
		}
	}
}
