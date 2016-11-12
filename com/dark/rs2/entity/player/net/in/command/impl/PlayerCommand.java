package com.dark.rs2.entity.player.net.in.command.impl;

//import org.Vote.MainLoader;
//import org.Vote.VoteReward;

import com.dark.Constants;
import com.dark.core.network.mysql.Donation;
import com.dark.core.util.Utility;
import com.dark.rs2.content.PlayersOnline;
import com.dark.rs2.content.Yelling;
import com.dark.rs2.content.achievements.AchievementHandler;
import com.dark.rs2.content.achievements.AchievementList;
import com.dark.rs2.content.bot.Bot;
import com.dark.rs2.content.dialogue.DialogueManager;
import com.dark.rs2.content.dialogue.OptionDialogue;
import com.dark.rs2.content.dialogue.impl.ChangePasswordDialogue;
import com.dark.rs2.content.interfaces.InterfaceHandler;
import com.dark.rs2.content.interfaces.impl.CommandInterface;
import com.dark.rs2.content.interfaces.impl.TrainingInterface;
import com.dark.rs2.content.io.PlayerSave;
import com.dark.rs2.content.minigames.war.WarHandler;
import com.dark.rs2.content.profiles.PlayerProfiler;
import com.dark.rs2.content.skill.magic.MagicSkill.TeleportTypes;
import com.dark.rs2.entity.World;
import com.dark.rs2.entity.player.Player;
import com.dark.rs2.entity.player.PlayerConstants;
import com.dark.rs2.entity.player.net.in.command.Command;
import com.dark.rs2.entity.player.net.in.command.CommandParser;
import com.dark.rs2.entity.player.net.out.impl.SendInterface;
import com.dark.rs2.entity.player.net.out.impl.SendMessage;
import com.dark.rs2.entity.player.net.out.impl.SendRemoveInterfaces;
import com.dark.rs2.entity.player.net.out.impl.SendString;

/**
 * A list of commands accessible to all players disregarding rank.
 * 
 * @author Michael | Chexfdf
 */
public class PlayerCommand implements Command {

	@Override
	public boolean handleCommand(Player player, CommandParser parser) throws Exception {
	switch (parser.getCommand()) {

	case "war":
		if(WarHandler.CURRENT_WAR == null) 
			return false;
		
		player.getWar().joinWar(player);
		return true;
	case "claim":
	case "claimdonation":
		Donation.checkDonation(player.getUsername(), player);
		return true;
	/*
	 * Opens the command list
	 */
	case "command":
	case "commands":
	case "commandlist":
	case "commandslist":
		player.send(new SendString("Horizon-OS Command List", 8144));
		InterfaceHandler.writeText(new CommandInterface(player));
		player.send(new SendInterface(8134));
		return true;

	/*
	 * Opens the teleporting interface
	 */
	case "telfeporth":
	case "telesportsh":
	case "telepsortingh":
	case "telepfortingsh":
		InterfaceHandler.writeText(new TrainingInterface(player));
		player.send(new SendInterface(61000));
		player.send(new SendString("Selected: @red@None", 61031));
		player.send(new SendString("Cost: @red@Free", 61032));
		player.send(new SendString("Requirement: @red@None", 61033));
		player.send(new SendString("Other: @red@None", 61034));
		return true;

	/*
	 * Answers TriviaBot
	 */
	case "answer":
		if (parser.hasNext()) {
			String answer = "";
			while (parser.hasNext()) {
				answer += parser.nextString() + " ";
			}
			Bot.answer(player, answer.trim());
	        for (Player players: World.getPlayers()) {
	            if (players != null && players.isActive()) {
	                PlayerSave.save(players);
	            }
	        }
		}
		return true;

	case "triviasetting":
	case "triviasettings":

		player.start(new OptionDialogue("Turn on TriviaBot", p -> {
			p.setWantTrivia(true);
			p.send(new SendMessage("<col=482CB8>You have turned on the TriviaBot."));
			player.send(new SendRemoveInterfaces());
		}, "Turn off TriviaBot", p -> {
			p.setWantTrivia(false);
			p.send(new SendMessage("<col=482CB8>You have turned off the TriviaBot."));
			player.send(new SendRemoveInterfaces());
		}, "Turn on TriviaBot notification", p -> {
			p.setTriviaNotification(true);
			p.send(new SendMessage("<col=482CB8>You have turned on the TriviaBot notification."));
			player.send(new SendRemoveInterfaces());
		}, "Turn off TriviaBot notification", p -> {
			p.setTriviaNotification(false);
			p.send(new SendMessage("<col=482CB8>You have turned off the TriviaBot notification."));
			player.send(new SendRemoveInterfaces());
		}));
		return true;

	/*
	 * Gets amount of online players
	 */
	case "players":
		player.send(new SendMessage("There are currently @red@" + Utility.format(World.getActivePlayers()) + "</col> players online."));
		PlayersOnline.showPlayers(player, p -> {
			return true;
		});
		return true;

	case "checkvote":
	case "voted":
	case "claimvote":
		//handleVoteReward(player);
		return true;

	case "dzone":
	case "dz":
		if (PlayerConstants.isMember(player)) {
			player.getMagic().teleport(2516, 3860, 0, TeleportTypes.SPELL_BOOK);
			AchievementHandler.activateAchievement(player, AchievementList.VISIT_DZ, 1);
		} else {
			player.send(new SendMessage("You are not a donator, type ::donate for more information."));
			return false;
		}
		break;		

	case "shops":
		player.getMagic().teleport(3415, 2915, 0, TeleportTypes.SPELL_BOOK);
		break;

	case "train":
		player.getMagic().teleport(2674, 3712, 0, TeleportTypes.SPELL_BOOK);
		break;

	/*
	 * Opens website page
	 */
	case "forum":
	case "forums":
	case "website":
		player.send(new SendString("http://www.Horizon-OS.org/forums", 12000));
		player.send(new SendMessage("Loading website page..."));
		return true;
	case "donate":
	case "donation":
	case "donating":
	case "store":
		player.send(new SendString("http://www.Horizon-OS.org/webstore/", 12000));
		player.send(new SendMessage("Loading website page..."));
		return true;

	/*
	 * Opens voting page
	 */
	case "vote":
	case "voting":
		player.send(new SendString("http://www.Horizon-OS.org/vote", 12000));
		player.send(new SendMessage("Loading voting page..."));
		return true;

	/*
	 * Finds player to view profile
	 */
	case "find":
		if (parser.hasNext()) {
			String name = parser.nextString();

			while (parser.hasNext()) {
				name += " " + parser.nextString();
			}

			name = name.trim();

			PlayerProfiler.search(player, name);
		}
		return true;

	/**
	 * Withdraw from pouch
	 */
	case "withdrawmp":
		if (parser.hasNext()) {
			try {
				int amount = 1;

				if (parser.hasNext()) {
					long temp = Long.parseLong(parser.nextString().toLowerCase().replaceAll("k", "000").replaceAll("m", "000000").replaceAll("b", "000000000"));

					if (temp > Integer.MAX_VALUE) {
						amount = Integer.MAX_VALUE;
					} else {
						amount = (int) temp;
					}
				}

				player.getPouch().withdrawPouch(amount);

			} catch (Exception e) {
				player.send(new SendMessage("Something went wrong!"));
				e.printStackTrace();
			}

		}
		return true;

	/*
	 * Change the password
	 */
	case "changepassword":
	case "changepass":
		if (parser.hasNext()) {
			try {
				String password = parser.nextString();
				if ((password.length() > 4) && (password.length() < 15))
					player.start(new ChangePasswordDialogue(player, password));
				else
					DialogueManager.sendStatement(player, new String[] { "Your password must be between 4 and 15 characters." });
			} catch (Exception e) {
				player.getClient().queueOutgoingPacket(new SendMessage("Invalid password format, syntax: ::changepass password here"));
			}
		}
		return true;

	/*
	 * Changes yell title
	 */
	case "yelltitle":
		if (player.getRights() == 0 || player.getRights() == 5) {
			player.send(new SendMessage("You need to be a super or extreme member to do this!"));
			return true;
		}
		if (parser.hasNext()) {
			try {
				String message = parser.nextString();
				while (parser.hasNext()) {
					message += " " + parser.nextString();
				}

				for (int i = 0; i < Constants.BAD_STRINGS.length; i++) {
					if (message.contains(Constants.BAD_STRINGS[i])) {
						player.send(new SendMessage("You may not use that in your title!"));
						return true;
					}
				}

				for (int i = 0; i < Constants.BAD_TITLES.length; i++) {
					if (message.contains(Constants.BAD_TITLES[i])) {
						player.send(new SendMessage("You may not use that in your title!"));
						return true;
					}
				}

				player.setYellTitle(message);
				DialogueManager.sendTimedStatement(player, "Your yell title is now @red@" + message);
			} catch (Exception e) {
				player.getClient().queueOutgoingPacket(new SendMessage("Invalid yell format, syntax: -title"));
			}
		}
		return true;

	/*
	 * Yell to server
	 */
	case "yell":
		if (player.getRights() > 0) {
			if (parser.hasNext()) {
				try {
					String message = parser.nextString();
					while (parser.hasNext()) {
						message += " " + parser.nextString();
					}
					Yelling.yell(player, message.trim());
				} catch (Exception e) {
					player.getClient().queueOutgoingPacket(new SendMessage("Invalid yell format, syntax: -messsage"));
				}
			}
		}
		return true;

	/*
	 * Handles player emptying inventory
	 */
	case "empty":
		if (player.getRights() == 2 || player.getRights() == 3) {
			player.getInventory().clear();
			player.send(new SendMessage("You have emptied your inventory."));
			player.send(new SendRemoveInterfaces());
			return true;
		}

		player.start(new OptionDialogue("Yes, empty my inventory.", p -> {
			p.getInventory().clear();
			p.send(new SendMessage("You have emptied your inventory."));
			p.send(new SendRemoveInterfaces());
		}, "Wait, nevermind!", p -> p.send(new SendRemoveInterfaces())));
		return true;

	case "edge":
		if (player.getWildernessLevel() > 20 && player.inWilderness()) {
			player.send(new SendMessage("You cannot teleport above 20 wilderness!"));
			return true;
		}
		player.getMagic().teleport(3087, 3491, 0, TeleportTypes.SPELL_BOOK);
		return true;
		
	case "lunar":
		if (player.getWildernessLevel() > 20 && player.inWilderness()) {
			player.send(new SendMessage("You cannot teleport above 20 wilderness!"));
			return true;
		}
		player.getMagic().teleport(2113, 3913, 0, TeleportTypes.SPELL_BOOK);
		return true;

	/*
	 * Teleport player home
	 */
	case "home":
		if (player.getWildernessLevel() > 20 && player.inWilderness()) {
			player.send(new SendMessage("You cannot teleport above 20 wilderness!"));
			return true;
		}
		player.getMagic().teleport(3434, 2891, 0, TeleportTypes.SPELL_BOOK);
		return true;

	}
	return false;
	}
/*
	private void handleVoteReward(Player player) {
	try {
		VoteReward reward = MainLoader.hasVoted(player.getUsername().toLowerCase().replaceAll(" ", "_"));
		if (reward != null) {
			switch (reward.getReward()) {
			case 0: // reward for id
				Constants.LAST_VOTER = player.getUsername();
				Constants.CURRENT_VOTES++;
				player.setVotePoints(player.getVotePoints() + 2);
				AchievementHandler.activateAchievement(player, AchievementList.VOTE_5_TIMES, 1);
				break;
			case 1: // reward for id
				Constants.LAST_VOTER = player.getUsername();
				Constants.CURRENT_VOTES++;
				player.setVotePoints(player.getVotePoints() + 1);
				player.getInventory().add(989, 1);
				AchievementHandler.activateAchievement(player, AchievementList.VOTE_5_TIMES, 1);
				break;
			default:
				player.send(new SendMessage("That reward could not be found in our database."));
				break;
			}
		} else
			player.send(new SendMessage("We could not find your vote in our database."));
	} catch (Exception e) {
		player.send(new SendMessage("error: " + e + "."));
		e.printStackTrace();
	}
	}*/

	@Override
	public boolean meetsRequirements(Player player) {
	return true;
	}
}