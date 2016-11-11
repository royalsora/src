package com.dark.rs2.content.combat.special.specials;

import com.dark.rs2.content.combat.impl.Ranged;
import com.dark.rs2.content.combat.special.Special;
import com.dark.rs2.entity.Graphic;
import com.dark.rs2.entity.player.Player;
/**
 * Handles the Toxic blowpipe special attack
 * @author Goten
 *
 */
public class ToxicBlowpipeSpecialAttack implements Special {
	
	@Override
	public boolean checkRequirements(Player player) {
		if (player.getToxicBlowpipe().getBlowpipeAmmo() == null) {
			return false;
		}
		if (player.getToxicBlowpipe().getBlowpipeAmmo().getAmount() <= 0) {
			return false;
		}
		return true;
	}

	@Override
	public int getSpecialAmountRequired() {
		return 50;
	}

	@Override
	public void handleAttack(Player player) {
		Ranged r = player.getCombat().getRanged();

		r.setStart(new Graphic(278, 5, true));
		r.setStartGfxOffset((byte) 1);

		r.getProjectile().setDelay(35);

		//r.execute(player.getCombat().getAttacking());

		r.setStartGfxOffset((byte) 0);
		r.setProjectileOffset(0);

		
	}
}