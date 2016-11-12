package com.dark.rs2.entity.player.net.out.impl;

import com.dark.core.network.StreamBuffer;
import com.dark.rs2.entity.Location;
import com.dark.rs2.entity.object.GameObject;
import com.dark.rs2.entity.player.Player;
import com.dark.rs2.entity.player.net.Client;
import com.dark.rs2.entity.player.net.out.OutgoingPacket;

/**
 * 
 * @author Rhubarb
 *
 */
public class SendObjectRemove extends OutgoingPacket {

	private final GameObject o;
	private final Location base;

	public SendObjectRemove(Player p, GameObject o) {
		super();
		this.o = o;
		this.base = new Location(p.getCurrentRegion());
	}

	@Override
	public void execute(Client client) {
		new SendCoordinates(o.getLocation(), base).execute(client);
		StreamBuffer.OutBuffer out = StreamBuffer.newOutBuffer(3);
		out.writeHeader(client.getEncryptor(), getOpcode());
		out.writeByte(((o.getType() << 2) + (o.getFace() & 3)), StreamBuffer.ValueType.C);
		out.writeByte(0);
		client.send(out.getBuffer());
	}

	@Override
	public int getOpcode() {
		return 101;
	}
	

}