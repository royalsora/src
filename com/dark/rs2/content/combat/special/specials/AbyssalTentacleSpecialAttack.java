package com.dark.rs2.content.combat.special.specials;

import com.dark.rs2.content.combat.special.Special;
import com.dark.rs2.entity.Animation;
import com.dark.rs2.entity.Graphic;
import com.dark.rs2.entity.player.Player;

/**
 * Handles the Abyssal Tentacle Special Attack
 * @author Goten
 *
 */
public class AbyssalTentacleSpecialAttack implements Special {
	
	@Override
	public boolean checkRequirements(Player player) {
		return true;
	}

	@Override
	public int getSpecialAmountRequired() {
		return 50;
	}

	@Override
	public void handleAttack(Player player) {
		player.getCombat().getAttacking().getUpdateFlags().sendGraphic(Graphic.highGraphic(341, 0));
		player.getCombat().getMelee().setAnimation(new Animation(1658, 0));
	}
}
