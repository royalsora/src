package com.dark.rs2.content.skill.magic.effects;

import com.dark.rs2.content.combat.impl.CombatEffect;
import com.dark.rs2.entity.Entity;
import com.dark.rs2.entity.player.Player;
import com.dark.rs2.entity.player.net.out.impl.SendMessage;

public class IceBurstEffect implements CombatEffect {
	@Override
	public void execute(Player p, Entity e) {
		if (!e.isNpc() && !e.isFrozen()) {
			Player p2 = com.dark.rs2.entity.World.getPlayers()[e.getIndex()];
			if (p2 == null) {
				return;
			}
			p2.getClient().queueOutgoingPacket(
					new SendMessage("You have been frozen."));
		}
		e.freeze(10, 5);
	}
}
