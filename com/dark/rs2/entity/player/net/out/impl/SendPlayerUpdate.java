package com.dark.rs2.entity.player.net.out.impl;

import com.dark.rs2.entity.player.PlayerUpdateFlags;
import com.dark.rs2.entity.player.net.Client;
import com.dark.rs2.entity.player.net.PlayerUpdating;
import com.dark.rs2.entity.player.net.out.OutgoingPacket;

public class SendPlayerUpdate extends OutgoingPacket {

	private final PlayerUpdateFlags[] pFlags;

	public SendPlayerUpdate(PlayerUpdateFlags[] pFlags) {
		super();
		this.pFlags = pFlags;
	}

	@Override
	public void execute(Client client) {
		PlayerUpdating.update(client.getPlayer(), pFlags);
	}

	@Override
	public int getOpcode() {
		return 81;
	}

}
