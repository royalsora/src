package com.dark.rs2.entity.player.net.out.impl;

import com.dark.core.network.StreamBuffer;
import com.dark.rs2.entity.player.net.Client;
import com.dark.rs2.entity.player.net.out.OutgoingPacket;

public class SendString extends OutgoingPacket {

	private final String message;

	private final int id;

	public SendString(String message, int id) {
		this.message = message;
		this.id = id;
	}

	@Override
	public void execute(Client client) {
		if(!client.checkSendString(message, id)) {
			return;
		}
		StreamBuffer.OutBuffer out = StreamBuffer.newOutBuffer(message.length() + 6);
		out.writeVariableShortPacketHeader(client.getEncryptor(), 126);
		out.writeString(message);
		out.writeShort(id, StreamBuffer.ValueType.A);
		out.finishVariableShortPacketHeader();
		client.send(out.getBuffer());
	}

	@Override
	public int getOpcode() {
		return 126;
	}
}
