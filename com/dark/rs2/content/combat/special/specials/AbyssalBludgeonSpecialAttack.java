package com.dark.rs2.content.combat.special.specials;

import com.dark.rs2.content.combat.special.Special;
import com.dark.rs2.entity.Animation;
import com.dark.rs2.entity.Graphic;
import com.dark.rs2.entity.player.Player;

/**
 * Handles the Abyssal Bludgeon special attack
 * @author Jamian
 *
 */
public class AbyssalBludgeonSpecialAttack implements Special {
	
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
		player.getCombat().getMelee().setAnimation(new Animation(3299, 0));
		player.getUpdateFlags().sendGraphic(Graphic.highGraphic(8, 0));
	}
	
}
