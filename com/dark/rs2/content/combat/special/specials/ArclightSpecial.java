package com.dark.rs2.content.combat.special.specials;

import com.dark.rs2.content.combat.special.Special;
import com.dark.rs2.entity.Animation;
import com.dark.rs2.entity.Graphic;
import com.dark.rs2.entity.player.Player;

/**
 * Handles the arclight special attack
 * @author Goten
 *
 */
public class ArclightSpecial implements Special {
	
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
		player.getCombat().getMelee().setAnimation(new Animation(1168, 0));
		player.getUpdateFlags().sendGraphic(Graphic.highGraphic(8, 0));
	}
	
}
