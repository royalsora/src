package com.dark.core.network;

import com.dark.rs2.entity.World;
import com.dark.rs2.entity.player.Player;
import com.dark.rs2.entity.player.net.Client;

public class ClientMap {

	public static boolean allow(Client client) {
		byte am = 0;

		for (Player p : World.getPlayers()) {
			if (p != null && p.getClient().getHost() != null
					&& p.getClient().getHost().equals(client.getHost())) {
				am++;
			}
		}

		return am < 2;
	}

	private ClientMap() {
	}

}
