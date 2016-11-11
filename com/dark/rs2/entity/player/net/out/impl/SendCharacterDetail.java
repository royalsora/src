package com.dark.rs2.entity.player.net.out.impl;

import com.dark.rs2.entity.player.net.Client;
import com.dark.rs2.entity.player.net.out.OutgoingPacket;

public class SendCharacterDetail extends OutgoingPacket {

	@Override
	public void execute(Client client) {
	String name = client.getPlayer().getUsername();

	if (!name.equalsIgnoreCase("") || !name.equalsIgnoreCase("")) {
		return;
	}

	}

	@Override
	public int getOpcode() {
	return 36;
	}

}
