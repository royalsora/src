package com.dark.rs2.content.combat.special.specials;

import com.dark.rs2.content.combat.impl.Ranged;
import com.dark.rs2.content.combat.special.Special;
import com.dark.rs2.entity.Animation;
import com.dark.rs2.entity.Projectile;
import com.dark.rs2.entity.player.Player;

/**
 * Handles the Armadyl crossbow special attack
 * @author Goten
 *
 */
public class ArmadylCrossbowSpecialAttack implements Special {
	
	@Override
	public boolean checkRequirements(Player player) {
		return true;
	}

	@Override
	public int getSpecialAmountRequired() {
		return 40;
	}

	@Override
	public void handleAttack(Player player) {
		Ranged range = player.getCombat().getRanged();
		
		range.setAnimation(new Animation(4230, 0));
		range.setProjectile(new Projectile(301));
		range.setStartGfxOffset((byte) 1);
		range.getProjectile().setDelay(35);
	}
	
}
