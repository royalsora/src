package com.dark.rs2.entity.mob.impl.wild;

import com.dark.core.util.Utility;
import com.dark.rs2.content.combat.Hit;
import com.dark.rs2.entity.Animation;
import com.dark.rs2.entity.Entity;
import com.dark.rs2.entity.Graphic;
import com.dark.rs2.entity.Location;
import com.dark.rs2.entity.mob.Mob;
import com.dark.rs2.entity.player.net.out.impl.SendMessage;

/**
 * Handles the Callisto boss
 * @author Daniel
 *
 */
public class Callisto extends Mob {
	
	/**
	 * Callisto
	 */
	public Callisto() {
		super(6609, true, new Location(3295, 3851, 0));
	}
	
	/**
	 * Handles Callisto hitting entity
	 */
	@Override
	public void onHit(Entity entity, Hit hit) {
		if (entity != null && !entity.isNpc()) {
			if (entity.getPlayer().isStunned()) {
				return;
			}
			int random = Utility.random(10);
			if (random == 1) {
				knockBack(entity.getPlayer());
			}
		}
	}

	/**
	 * Handles Callisto's knock back effect
	 * @param player
	 */
	private void knockBack(Entity player) {
		player.stun(2);
		player.hit(new Hit(2));
		player.getUpdateFlags().sendGraphic(new Graphic(80, true));
		player.getUpdateFlags().sendAnimation(new Animation(3170));
		player.getPlayer().send(new SendMessage("Callisto's roar sends you backwards."));
		player.getPlayer().teleport(new Location(player.getX() + Utility.random(1), player.getY() - Utility.random(2), 0));						
	}

}
