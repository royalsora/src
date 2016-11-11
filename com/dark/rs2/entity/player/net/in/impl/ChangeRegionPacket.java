package com.dark.rs2.entity.player.net.in.impl;

import com.dark.core.network.StreamBuffer;
import com.dark.rs2.entity.player.Player;
import com.dark.rs2.entity.player.net.in.IncomingPacket;
import com.dark.rs2.entity.player.net.out.impl.SendDetails;

public class ChangeRegionPacket extends IncomingPacket {
	
	@Override
	public int getMaxDuplicates() {
		return 1;
	}

	@Override
	public void handle(Player player, StreamBuffer.InBuffer in, int opcode, int length) {
		player.getClient().queueOutgoingPacket(new SendDetails(player.getIndex()));
		player.getGroundItems().onRegionChange();
		player.getObjects().onRegionChange();

		if (player.getDueling().isStaking()) {
			player.getDueling().decline();
		}

		if (player.getTrade().trading()) {
			player.getTrade().end(false);
		}

		player.resetAggression();
	}
}
