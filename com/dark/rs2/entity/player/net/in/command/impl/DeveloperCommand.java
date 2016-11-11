package com.dark.rs2.entity.player.net.in.command.impl;

import com.dark.Constants;
import com.dark.rs2.content.housing.HouseObject;
import com.dark.rs2.content.housing.ParseLoad;
import com.dark.rs2.content.minigames.raid.MainHandler;
import com.dark.rs2.entity.player.Player;
import com.dark.rs2.entity.player.PlayerConstants;
import com.dark.rs2.entity.player.net.in.command.Command;
import com.dark.rs2.entity.player.net.in.command.CommandParser;
import com.dark.rs2.entity.player.net.out.impl.SendInterface;

/**
 * A list of Gambler commands accessible to all players with the developer's
 * rank.
 * 
 * @author goten
 */
public class DeveloperCommand implements Command {

	@Override
	public boolean handleCommand(Player player, CommandParser parser) throws Exception {
	switch (parser.getCommand()) {
	case "buyhouse":
		player.getHouseManager().buyHouse(player);
		break;
	case "h":
		player.send(new SendInterface(14000));
		break;
	case "enterhouse":
		if (player.getHouse() != null) {
			System.out.println("Used hosue command");
			player.getHouseManager().enterHouse(player);
		}
		break;
	case "hashouse":
		if (player.getHouse() != null) {
			System.out.println("Has House");
		}
		break;
	case "savehouse":
		if (player.getHouse() != null) {
			ParseLoad.saveHouse(player);
		}
		break;
	case "setname":
		String name = "";
		if (parser.hasNext()) {
			while (parser.hasNext()) {
				name += parser.nextString() + " ";
			}
			player.getHouse().setName(name);
			ParseLoad.saveHouse(player);
		}

		break;
		
	case "dobj":
		player.getHouse().getObject().remove(player.getLastAdded());
		System.out.println(player.getHouse().getObject().size());
		ParseLoad.saveHouse(player);
		break;
	case "dxpoff":
		Constants.doubleExperience = false;
		break;

	case "dxpon":
		Constants.doubleExperience = true;
		break;
	case "rstart":
		MainHandler.startRaid(player);
		break;

	}
	return false;
	}

	@Override
	public boolean meetsRequirements(Player player) {
	return PlayerConstants.isDeveloper(player);
	}
}