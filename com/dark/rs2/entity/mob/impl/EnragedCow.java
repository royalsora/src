package com.dark.rs2.entity.mob.impl;

import com.dark.core.util.Utility;
import com.dark.rs2.GameConstants;
import com.dark.rs2.content.combat.Hit;
import com.dark.rs2.content.combat.Hit.HitTypes;
import com.dark.rs2.entity.Animation;
import com.dark.rs2.entity.Entity;
import com.dark.rs2.entity.Graphic;
import com.dark.rs2.entity.Location;
import com.dark.rs2.entity.World;
import com.dark.rs2.entity.item.Item;
import com.dark.rs2.entity.mob.Mob;
import com.dark.rs2.entity.player.Player;
import com.dark.rs2.entity.player.net.out.impl.SendMessage;


public class EnragedCow extends Mob {
	
	private Entity lastKilledBy = null;
	public static boolean starterdEnraged = false;
	public EnragedCow() {
		super(6401, true, true, new Location(3256, 3284, 4));
		
	}

	@Override
	public int getRespawnTime() {
		return 30;
	}
	
	@Override
	public int getAffectedDamage(Hit hit) {
		switch (hit.getType()) {
		case RANGED:
		case MAGIC:
			if (getId() == 6401) {
				return 0;
			}
			break;
		case MELEE:
			if (getId() == 6401) {
				if ((hit.getAttacker() != null) && (!hit.getAttacker().isNpc())) {
					Player player = World.getPlayers()[hit.getAttacker().getIndex()];
					Item weapon = player.getEquipment().getItems()[3];
					if (((weapon != null) && (weapon.getId() == 4747) || ((weapon != null) && (weapon.getId() == 4153)) || ((weapon != null) && (weapon.getId() == 13576)) || ((weapon != null) && (weapon.getId() == 1347)) || ((weapon != null) && (weapon.getId() == 7449)))) {
						return hit.getDamage();
					}
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
			if (random == 5) {
				Location spawn = GameConstants.getClearAdjacentLocation(getLocation(), -2);
				Mob Cow = new Mob(getPlayer(), 2808, false, false, true, spawn);
				if (getCombat().getAttacking() != null) {
					if (!getCombat().getAttacking().isNpc()) {
						Cow.getCombat().setAttack(getCombat().getAttacking() == null ? getCombat().getLastAttackedBy() : getCombat().getAttacking());
			}
		}
	}
		}
	}
	/**
	 * Handles Callisto's knock back effect
	 * @param player
	 */
	private void knockBack(Entity player) {
		player.hit(new Hit(1 + Utility.random(68), HitTypes.RANGED));
		player.stun(1);
		player.getUpdateFlags().sendGraphic(new Graphic(80, true));
		player.getUpdateFlags().sendAnimation(new Animation(3170));
		player.getPlayer().send(new SendMessage("The Cow furiously slams the ground knocking you back!"));
		getUpdateFlags().sendForceMessage("ShaZooooo!!!");
		player.getPlayer().teleport(new Location(player.getX() + Utility.random(1), player.getY() - Utility.random(1), 4));						
	}

	public void transform() {
		transform(getId() == 2806 ? 2806 : 6401);

		if (lastKilledBy != null) {
			getCombat().setAttack(lastKilledBy);
		}
	}
}
