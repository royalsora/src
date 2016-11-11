package com.dark.rs2.content.skill.magic;

import com.dark.rs2.entity.item.Item;
import com.dark.rs2.entity.player.Player;

public abstract class Spell {

	public abstract boolean execute(Player paramPlayer);

	public abstract double getExperience();

	public abstract int getLevel();

	public abstract String getName();

	public abstract Item[] getRunes();
}
