package com.dark.rs2.entity.player.net.out.impl;

import com.dark.core.network.StreamBuffer;
import com.dark.rs2.entity.player.net.Client;
import com.dark.rs2.entity.player.net.out.OutgoingPacket;

public class SendPlayerOption extends OutgoingPacket {

	private final String option;

	private final int id;

	public SendPlayerOption(String option, int id) {
		super();
		this.option = option;
		this.id = id;
	}

	@Override
	public void execute(Client client) {
		// if (!client.getLastPlayerOption().equals(option)) {
		client.setLastPlayerOption(option);
		StreamBuffer.OutBuffer out = StreamBuffer.newOutBuffer(option.length() + 6);
		out.writeVariablePacketHeader(client.getEncryptor(), 104);
		out.writeByte(id, StreamBuffer.ValueType.C);
		out.writeByte(0, StreamBuffer.ValueType.A);
		out.writeString(option);
		out.finishVariablePacketHeader();
		client.send(out.getBuffer());
		// }
	}

	@Override
	public int getOpcode() {
		return 104;
	}

}
