package com.dark.rs2.entity.player.net.in.impl;

import com.dark.Constants;
import com.dark.Server;
import com.dark.core.network.StreamBuffer;
import com.dark.core.util.Utility;
import com.dark.rs2.content.DropTable;
import com.dark.rs2.content.PlayerTitle;
import com.dark.rs2.content.clanchat.Clan;
import com.dark.rs2.content.dialogue.DialogueManager;
import com.dark.rs2.content.gambling.Gambling;
import com.dark.rs2.entity.World;
import com.dark.rs2.entity.player.Player;
import com.dark.rs2.entity.player.net.in.IncomingPacket;
import com.dark.rs2.entity.player.net.out.impl.SendMessage;
import com.dark.rs2.entity.player.net.out.impl.SendRemoveInterfaces;

public class StringInputPacket extends IncomingPacket {

	@Override
	public int getMaxDuplicates() {
	return 1;
	}

	@Override
	public void handle(Player player, StreamBuffer.InBuffer in, int opcode, int length) {
	String input = Utility.longToPlayerName2(in.readLong());
	input = input.replaceAll("_", " ");

	if (player.getInterfaceManager().getMain() == 41750) {
		player.reportName = Utility.capitalize(input);
		return;
	}

	if (player.getInterfaceManager().getMain() == 59800) {
		DropTable.searchItem(player, input);
		return;
	}

	if (player.getEnterXInterfaceId() == 56000) {
		Gambling.play(player, Integer.parseInt(input));
		return;
	}

	if (player.getEnterXInterfaceId() == 56002) {
		for (int i = 0; i < Constants.BAD_STRINGS.length; i++) {
			if (input.equalsIgnoreCase(Constants.BAD_STRINGS[i])) {
				DialogueManager.sendStatement(player, "Grow up! That title can not be used.");
				return;
			}
		}
		if (input.length() >= 15) {
			DialogueManager.sendStatement(player, "Titles can not exceed 15 characters!");
			return;
		}
		player.setPlayerTitle(PlayerTitle.create(input, player.getPlayerTitle().getColor(), false));
		player.setAppearanceUpdateRequired(true);
		player.send(new SendRemoveInterfaces());
		return;
	}

	if (player.getEnterXInterfaceId() == 55776) {
		player.setCredits(player.getCredits() - 10);
		player.setShopMotto(Utility.capitalize(input));
		DialogueManager.sendInformationBox(player, "Player Owned Shops Exchange", "You have successfully changed your shop motto.", "Motto:", "@red@" + Utility.capitalize(input), "");
		return;
	}
	if (player.getEnterXInterfaceId() == 55785) {
		if (World.getPlayerByName(input) != null)
			player.getHouseManager().sendInvite(player, World.getPlayerByName(input));
		else
			player.send(new SendMessage("Player does not exists"));
	}

	if (player.getEnterXInterfaceId() == 55790) {
		if (World.getPlayerByName(input) != null)
			player.getPowerHandler().banPlayer(player, World.getPlayerByName(input));
		else
			player.send(new SendMessage("Name is invalid. Please try again."));
		return;
	}
	if (player.getEnterXInterfaceId() == 55791) {
		if (World.getPlayerByName(input) != null)
			player.getPowerHandler().unbanPlayer(player, World.getPlayerByName(input));
		else
			player.send(new SendMessage("Name is invalid. Please try again."));
		return;
	}
	if (player.getEnterXInterfaceId() == 55792) {
		if (World.getPlayerByName(input) != null)
			player.getPowerHandler().kickPlayer(player, World.getPlayerByName(input));
		else
			player.send(new SendMessage("Name is invalid. Please try again."));
		return;
	}
	
	if (player.getEnterXInterfaceId() == 100) {
		player.getSlayer().setSocialSlayerPartner(input);
		return;
	}

	if (player.getEnterXInterfaceId() == 55777) {
		player.getShopping().open(World.getPlayerByName(input));
		return;
	}

	if (player.getEnterXInterfaceId() == 55778) {
		player.getPlayerShop().setSearch(input);
		return;
	}
	if (player.getEnterXInterfaceId() == 55780) {
		player.getWar().tokensToReward(player, input);
		return;
	}
	if (player.getEnterXInterfaceId() == 55794) {
		int temp = 0;
		try {
			temp = Integer.parseInt(input);
		} catch (NumberFormatException e) {
			player.send(new SendMessage("Numbers only please."));
			player.send(new SendRemoveInterfaces());
			return;
		}
		player.getMoveObject().moveUp(player, temp);
		return;
	}
	if (player.getEnterXInterfaceId() == 55795) {
		int temp = 0;
		try {
			temp = Integer.parseInt(input);
		} catch (NumberFormatException e) {
			player.send(new SendMessage("Numbers only please."));
			player.send(new SendRemoveInterfaces());
			return;
		}
		player.getMoveObject().moveDown(player, temp);
		return;
	}
	
	if (player.getEnterXInterfaceId() == 55796) {
		int temp = 0;
		try {
			temp = Integer.parseInt(input);
		} catch (NumberFormatException e) {
			player.send(new SendMessage("Numbers only please."));
			player.send(new SendRemoveInterfaces());
			return;
		}
		player.getMoveObject().moveLeft(player, temp);
		return;
	}
	if (player.getEnterXInterfaceId() == 55797) {
		int temp = 0;
		try {
			temp = Integer.parseInt(input);
		} catch (NumberFormatException e) {
			player.send(new SendMessage("Numbers only please."));
			player.send(new SendRemoveInterfaces());
			return;
		}
		player.getMoveObject().moveRight(player, temp);
		return;
	}
	if (player.getEnterXInterfaceId() == 55778) {
		player.getPlayerShop().setSearch(input);
		return;
	}
	if (player.getEnterXInterfaceId() == 55780) {
		player.getWar().tokensToReward(player, input);
		return;
	}
	if (player.getEnterXInterfaceId() == 6969) {
		if ((input != null) && (input.length() > 0) && (player.clan == null)) {
			Clan localClan = Server.clanManager.getClan(input);
			if (localClan != null)
				localClan.addMember(player);
			else if (input.equalsIgnoreCase(player.getUsername()))
				Server.clanManager.create(player);
			else {
				player.getClient().queueOutgoingPacket(new SendMessage(Utility.formatPlayerName(input) + " has not created a clan yet."));
			}
		}
	} else {
		return;
	}
	}
}
