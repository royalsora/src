package com.dark.rs2.entity.player.net.in.command.impl;

import com.dark.core.util.GameDefinitionLoader;
import com.dark.rs2.content.io.PlayerSave;
import com.dark.rs2.entity.Location;
import com.dark.rs2.entity.World;
import com.dark.rs2.entity.player.Player;
import com.dark.rs2.entity.player.net.in.command.Command;
import com.dark.rs2.entity.player.net.in.command.CommandParser;
import com.dark.rs2.entity.player.net.out.impl.SendMessage;

/**
 * A list of commands accessible to all players with the administrator's rank.
 * 
 * @author Michael | Chex
 * @author Daniel | Play Boy
 */
public class AdministratorCommand implements Command {

    @Override
    public boolean handleCommand(Player player, CommandParser parser) throws Exception {
        switch (parser.getCommand()) {
		case "reloadshops":
			GameDefinitionLoader.loadShopDefinitions();
			player.send(new SendMessage("Shops have been reloaded!"));
			return true;
			
		case "reloaditems":
			GameDefinitionLoader.loadItemDefinitions();
			player.send(new SendMessage("Items have been reloaded!"));
			return true;
                /*
                 * Teleport to specific coordinates
                 */
            case "tele":
                if (parser.hasNext(2)) {
                    int x = parser.nextInt();
                    int y = parser.nextInt();
                    int z = player.getLocation().getZ();

                    if (parser.hasNext()) {
                        z = parser.nextInt();
                    }

                    player.teleport(new Location(x, y, z));

                    player.send(new SendMessage("You have teleported to [" + x + ", " + y + (z > 0 ? ", " + z : "") + "]."));
                }
                return true;

                /*
                 * Gets the player's coordinates
                 */
            case "mypos":
            case "coords":
            case "pos":
                player.send(new SendMessage("You are at: " + player.getLocation() + "."));
                System.out.println("new Location(" + player.getX() + ", " + player.getY() + (player.getZ() > 0 ? ", " + player.getZ() : "") + ")");
                return true;

                /*
                 * Gives a specific item to bank
                 */

                /*
                 * Does a mass banner
                 */
            case "masssave":
            case "saveall":
                for (Player players: World.getPlayers()) {
                    if (players != null && players.isActive()) {
                        PlayerSave.save(players);
                    }
                }
                player.send(new SendMessage(World.getActivePlayers() + " players have been saved!"));
                return true;

            case "bank":
                player.getBank().openBank();
                return true;
        }
		return false;
    }
    @
    Override
    public boolean meetsRequirements(Player player) {
        return player.getRights() >= 2 && player.getRights() < 5;
    }
        }