package com.dark.rs2.content.combat.special.specials;

import com.dark.rs2.content.combat.special.Special;
import com.dark.rs2.entity.player.Player;

public class DragonBattleaxeSpecialAttack implements Special {
	@Override
	public boolean checkRequirements(Player player) {
		return false;
	}

	@Override
	public int getSpecialAmountRequired() {
		return 0;
	}

	@Override
	public void handleAttack(Player player) {
	}
}
