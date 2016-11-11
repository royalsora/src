package com.dark.rs2.entity.player.net.out.impl;

import com.dark.core.cache.map.RSObject;
import com.dark.core.network.StreamBuffer;
import com.dark.rs2.entity.Location;
import com.dark.rs2.entity.player.net.Client;
import com.dark.rs2.entity.player.net.out.OutgoingPacket;

public class SendAnimateObject extends OutgoingPacket {

	private final RSObject object;
	private final int animation;

	public SendAnimateObject(RSObject object, int animation) {
		this.object = object;
		this.animation = animation;
	}

	@Override
	public void execute(Client client) {
		if (object == null) {
			return;
		}

		new SendCoordinates(new Location(object.getX(), object.getY(), object.getZ()), client.getPlayer()).execute(client);
		StreamBuffer.OutBuffer out = StreamBuffer.newOutBuffer(5);
		out.writeHeader(client.getEncryptor(), 160);
		out.writeByte(0, StreamBuffer.ValueType.S);
		out.writeByte((object.getType() << 2) + (object.getFace() & 3), StreamBuffer.ValueType.S);
		out.writeShort(animation, StreamBuffer.ValueType.A);
		client.send(out.getBuffer());
	}

	@Override
	public int getOpcode() {
		return 160;
	}

}
