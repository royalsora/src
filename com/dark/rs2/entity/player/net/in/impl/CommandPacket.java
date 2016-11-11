package com.dark.rs2.entity.player.net.in.impl;

import com.dark.core.network.StreamBuffer;
import com.dark.rs2.entity.player.Player;
import com.dark.rs2.entity.player.PlayerConstants;
import com.dark.rs2.entity.player.net.in.IncomingPacket;
import com.dark.rs2.entity.player.net.in.command.Command;
import com.dark.rs2.entity.player.net.in.command.CommandParser;
import com.dark.rs2.entity.player.net.in.command.impl.AdministratorCommand;
import com.dark.rs2.entity.player.net.in.command.impl.DeveloperCommand;
import com.dark.rs2.entity.player.net.in.command.impl.ModeratorCommand;
import com.dark.rs2.entity.player.net.in.command.impl.OwnerCommand;
import com.dark.rs2.entity.player.net.in.command.impl.PlayerCommand;
import com.dark.rs2.entity.player.net.out.impl.SendMessage;

public class CommandPacket extends IncomingPacket {

	private static final Command[] COMMANDS = new Command[] {
			new PlayerCommand(),
			new ModeratorCommand(),
			new AdministratorCommand(),
			new DeveloperCommand(),
			new OwnerCommand() };

	@Override
	public int getMaxDuplicates() {
	return 1;
	}

	@Override
	public void handle(Player player, StreamBuffer.InBuffer in, int opcode, int length) {
	CommandParser parser = CommandParser.create(in.readString().toLowerCase().trim());

	if (player == null) {
		return;
	}
	if (parser.getCommand().startsWith("/")) {
		if (player.clan != null) {
			parser = CommandParser.create(" " + parser.toString().substring(1));
			if (parser.hasNext()) {
				String message = "";
				while (parser.hasNext()) {
					message += parser.nextString() + " ";
				}
				if (message.contains("<img") || message.contains("<col")) {
					player.send(new SendMessage("Those symbols have been disabled."));
					return;
				}
				player.clan.sendChat(player, message);
			}
			return;
		} else if (player.clan == null) {
			player.getClient().queueOutgoingPacket(new SendMessage("You can only do this while in a clan chat."));
			return;
		}
	}

	try {
		for (Command command : COMMANDS) {
			if (PlayerConstants.isOwner(player) || command.meetsRequirements(player)) {
				if (command.handleCommand(player, parser)) {
					return;
				}
			}
		}
	} catch (Exception e) {
		e.printStackTrace();
		player.getClient().queueOutgoingPacket(new SendMessage("Invalid command format."));
	}
	}
}