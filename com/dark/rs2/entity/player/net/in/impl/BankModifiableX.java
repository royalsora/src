package com.dark.rs2.entity.player.net.in.impl;

import com.dark.core.network.StreamBuffer;
import com.dark.core.network.StreamBuffer.ByteOrder;
import com.dark.core.network.StreamBuffer.ValueType;
import com.dark.rs2.entity.player.Player;
import com.dark.rs2.entity.player.net.in.IncomingPacket;

public class BankModifiableX extends IncomingPacket {

	@Override
	public void handle(Player player, StreamBuffer.InBuffer in, int opcode, int length) {
		in.readShort(ValueType.A, ByteOrder.BIG);
		in.readShort();
		int item = in.readShort(ValueType.A, ByteOrder.BIG);
		int amount = in.readInt();
		player.getBank().withdraw(item, amount);
	}

	@Override
	public int getMaxDuplicates() {
		return 1;
	}
}