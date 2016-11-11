package com.dark.rs2.entity.player.net.in.impl;

import com.dark.core.network.StreamBuffer;
import com.dark.core.network.StreamBuffer.InBuffer;
import com.dark.rs2.content.dialogue.DialogueManager;
import com.dark.rs2.entity.item.Item;
import com.dark.rs2.entity.player.Player;
import com.dark.rs2.entity.player.PlayerConstants;
import com.dark.rs2.entity.player.net.in.IncomingPacket;
import com.dark.rs2.entity.player.net.out.impl.SendMessage;

public class SpawnTab extends IncomingPacket {

	@Override
	public int getMaxDuplicates() {
	// TODO Auto-generated method stub
	return 0;
	}

	@Override
	public void handle(Player paramPlayer, InBuffer paramInBuffer, int paramInt1, int paramInt2) {
	// TODO Auto-generated method stub
	
	}

	
}