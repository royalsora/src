package com.dark.rs2.content.interfaces.impl;

import com.dark.rs2.entity.player.Player;
import com.dark.rs2.entity.player.net.out.impl.SendString;

public class MiscInterfaces {
	
	public static void startUp(Player player) {
	
	//Magic Tab - Normal
	player.send(new SendString("Home Teleport", 19220));
	player.send(new SendString("Teleport home", 19222));	
	player.send(new SendString("Training Teleport", 19641));
	player.send(new SendString("Opens training menu", 19642));	
	player.send(new SendString("Skilling Teleport", 19722));
	player.send(new SendString("Opens skilling menu", 19723));	
	player.send(new SendString("PvP Teleport", 19803));
	player.send(new SendString("Opens pvp menu", 19804));
	player.send(new SendString("PvM Teleport", 19960));
	player.send(new SendString("Opens pvm menu", 19961));
	player.send(new SendString("Minigame Teleport", 20195));
	player.send(new SendString("Opens minigame menu", 20196));	
	player.send(new SendString("City Teleport", 20354));
	player.send(new SendString("Opens city menu", 20355));	
	
	//Magic Tab - Ancients
	player.send(new SendString("Home Teleport", 21756));
	player.send(new SendString("Teleport home", 21757));	
	player.send(new SendString("Training Teleport", 21833));
	player.send(new SendString("Opens training menu", 21834));	
	player.send(new SendString("Skilling Teleport", 21933));
	player.send(new SendString("Opens skilling menu", 21934));	
	player.send(new SendString("PvP Teleport", 22052));
	player.send(new SendString("Opens pvp menu", 22053));
	player.send(new SendString("PvM Teleport", 22123));
	player.send(new SendString("Opens pvm menu", 22124));
	player.send(new SendString("Minigame Teleport", 22232));
	player.send(new SendString("Opens minigame menu", 22233));	
	player.send(new SendString("City Teleport", 22307));
	player.send(new SendString("Opens city menu", 22308));
	
		

	
	}

}
