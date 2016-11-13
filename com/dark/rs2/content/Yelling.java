package com.dark.rs2.content;

import com.dark.core.util.Utility;
import com.dark.rs2.entity.World;
import com.dark.rs2.entity.player.Player;
import com.dark.rs2.entity.player.net.out.impl.SendMessage;

public class Yelling {

	public static final String YELL_COOLDOWN_KEY = "yellcooldown";

	public static String send;

	public static void yell(Player player, String message) {
		
		message = Utility.capitalizeFirstLetter(message);

		int rights = player.getRights();
		boolean member = player.isMember();
		
		if (rights == 1) {
			send = "[@blu@Moderator</col>] <img=0>@bla@" + player.getUsername() + "</col>:@blu@ " + message;
		} else if (rights == 2) {
			send = "[<shad=0><col=FFFF00>Administrator</shad></col>]  <img=1>" + player.getUsername() + "</col>:<shad=0><col=FFFF00> " + message;
		} else if (rights == 3) {
			send = "[@red@Owner</col>] <img=2>@bla@" + player.getUsername() + "</col>:@red@ " + message;
		} else if (rights == 4) {
			send = "[@dre@Gambler</col>] <img=3>@dre@" + player.getUsername() + "</col>: " + message;
		} else if (rights == 5) {
			send = "[<col=D11717>Member</col>] <img=4>" + player.getUsername() + "</col>:<col=D11717> " + message;
		} else if (rights == 6) {
			send = "[<col=0956AD>Sponsor</col>] <img=5>" + player.getUsername() + "</col>:<col=0956AD> " + message;
		} else if (rights == 7) {
			send = "[<col=4D8528>Contributor</col>] <img=6>" + player.getUsername() + "</col>:<col=4D8528> " + message;
		} else if (rights == 8) {
			send = "[<col=971FF2>Legendary</col>] <img=7>" + player.getUsername() + "</col>:<col=971FF2> " + message;
		} else if (rights == 9) {
			send = "[<shad=0>@cya@Helper</shad></col>] <img=8>@bla@" + player.getUsername() + "</col>:<shad=0>@cya@ " + message;
		} else if (member) {
			send = "[<col=3D484E>Iron</col>] <img=10>@bla@" + player.getUsername() + "</col>:<col=3D484E> " + message;		
		} else {

			if (player.getRights() == 0) {
				if (player.getAttributes().get("yellcooldown") == null) {
					player.getAttributes().set("yellcooldown", Long.valueOf(System.currentTimeMillis()));
				} else if (System.currentTimeMillis() - ((Long) player.getAttributes().get("yellcooldown")).longValue() < 3000L) {
					player.getClient().queueOutgoingPacket(new SendMessage("You must wait a few seconds before yelling again."));
					return;
				}
				
				player.getAttributes().set("yellcooldown", Long.valueOf(System.currentTimeMillis()));
			}
			return;
		}

		if (player.isMuted()) {
			player.getClient().queueOutgoingPacket(new SendMessage("You are muted and cannot yell."));
			return;
		}

		if (player.isYellMuted()) {
			player.getClient().queueOutgoingPacket(new SendMessage("You are muted are not allowed to yell."));
			return;
		}

		if (message.contains("<")) {
			player.getClient().queueOutgoingPacket(new SendMessage("You cannot use text arguments when yelling."));
			return;
		}


		for (Player i : World.getPlayers())
			if (i != null && send != null)
				i.getClient().queueOutgoingPacket(new SendMessage(send));
	}
}
