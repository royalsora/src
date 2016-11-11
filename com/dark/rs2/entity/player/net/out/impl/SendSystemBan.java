package com.dark.rs2.entity.player.net.out.impl;

import com.dark.core.network.StreamBuffer;
import com.dark.rs2.entity.player.net.Client;
import com.dark.rs2.entity.player.net.out.OutgoingPacket;

public class SendSystemBan extends OutgoingPacket {

	public SendSystemBan() {
	}

	@Override
	public void execute(Client client) {
		StreamBuffer.OutBuffer out = StreamBuffer.newOutBuffer(1);
		out.writeHeader(client.getEncryptor(), 1);
		client.send(out.getBuffer());
	}

	@Override
	public int getOpcode() {
		return 1;
	}

}
