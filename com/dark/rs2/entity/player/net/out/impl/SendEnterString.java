package com.dark.rs2.entity.player.net.out.impl;

import com.dark.core.network.StreamBuffer;
import com.dark.core.network.StreamBuffer.OutBuffer;
import com.dark.rs2.entity.player.net.Client;
import com.dark.rs2.entity.player.net.out.OutgoingPacket;

public class SendEnterString extends OutgoingPacket {

	@Override
	public void execute(Client client) {
		OutBuffer outBuffer = StreamBuffer.newOutBuffer(5);
		outBuffer.writeHeader(client.getEncryptor(), getOpcode());
		client.send(outBuffer.getBuffer());
	}

	@Override
	public int getOpcode() {
		return 187;
	}

}
