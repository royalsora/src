package com.dark.rs2.content.combat.special;

import com.dark.rs2.entity.player.Player;

public abstract interface Special {
	
	public abstract boolean checkRequirements(Player player);

	public abstract int getSpecialAmountRequired();

	public abstract void handleAttack(Player player);
}
