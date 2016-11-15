package com.dark.rs2.content.bot;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

import com.dark.Constants;
import com.dark.core.task.Task;
import com.dark.core.task.TaskQueue;
import com.dark.core.util.Utility;
import com.dark.rs2.content.achievements.AchievementHandler;
import com.dark.rs2.content.achievements.AchievementList;
import com.dark.rs2.entity.World;
import com.dark.rs2.entity.player.Player;
import com.dark.rs2.entity.player.net.out.impl.SendBanner;
import com.dark.rs2.entity.player.net.out.impl.SendMessage;

/**
 * Vencillio Bot - Asks random questions which players can race to answer
 * @author Daniel
 *
 */
public class Bot {
	
	/**
	 * The logger for the class
	 */
	private static Logger logger = Logger.getLogger(Bot.class.getSimpleName());
	
	/**
	 * Holds all the bot data
	 */
	private final static Set<BotData> BOT_DATA = new HashSet<>();
	
	/**
	 * The current question/answer set
	 */
	private static BotData current = null;
	
	/*
	 * Holds all the VencillioBot attempted answers
	 */
	public final static ArrayList<String> attempts = new ArrayList<String>();
	
	/**
	 * Color of the VencillioBot messages
	 */
	private static final String COLOR = "<shad=0><col=006700>";
	
	/**
	 * Declares the Vencillio data
	 */
	public static void declare() {
		for (BotData data : BotData.values()) {
			BOT_DATA.add(data);
		}
		logger.info("Loaded " + BOT_DATA.size() + " Trivia questions.");
	}
	
	/**
	 * Initializes the VencillioBot task
	 */
	public static void initialize() {
		TaskQueue.queue(new Task(450, false) {
			@Override
			public void execute() {
				if (current == null) {
					assign();	
					return;
				}
				sendMessage( COLOR +"[" + COLOR + "Trivia]</col></shad>" + " " + current.getQuestion());
				sendNotification("[" + COLOR + "Trivia:</col>]" + " " + current.getQuestion());
			}
			@Override
			public void onStop() {
			}
		});	
	}
	
	/**
	 * Assigns a new question
	 */
	private static void assign() {
	
		current = Utility.randomElement(BOT_DATA);
		sendMessage(COLOR + "[Trivia]</col></shad>" + " " + current.getQuestion());
	}
	
	/**
	 * Handles player answering the question
	 * @param player
	 * @param answer
	 */
	public static void answer(Player player, String answer) {
		if (current == null) {
			return;
		}
		for (int i = 0; i < Constants.BAD_STRINGS.length; i++) {
			if (answer.contains(Constants.BAD_STRINGS[i])) {
				player.send(new SendMessage(COLOR + "[Trivia]</col></shad> That was an offensive answer! Contain yourself or be punished."));
				return;
			}
		}
		for (int i = 0; i < current.getAnswers().length; i++) {
			if (current.getAnswers()[i].equalsIgnoreCase(answer)) {
				answered(player, answer);
				return;
			}
		}
		player.send(new SendMessage(COLOR + "[Trivia]</col></shad> Sorry, the answer you have entered is incorrect! Try again!"));
		attempts.add(answer);
	}
	
	/**
	 * Handles player answering the question successfully 
	 * @param player
	 * @param answer
	 */
	private static void answered(Player player, String answer) {
		sendMessage(COLOR + "[Trivia]</col></shad> " + player.determineIcon(player) + " " + player.getUsername() + "</col>" + COLOR + " has answered the question correctly! Answer:" + COLOR + " " + Utility.capitalizeFirstLetter(answer) + "</col>.");
		if (attempts.size() > 0) {
			//sendMessage("[" + COLOR + "Trivia</col>] Attempted answers: " + COLOR + "" + attempts.toString() + "</col>!");
		}
		int REWARD = Utility.random(250_000);
		player.getInventory().addOrCreateGroundItem(995, REWARD, true);
		player.send(new SendMessage("You recieved 1 Trivia Point, Nice job!"));
		player.setTriviaPoints(player.getTriviaPoints() + 1);
		AchievementHandler.activateAchievement(player, AchievementList.ANSWER_15_TRIVIABOTS_CORRECTLY, 1);
		AchievementHandler.activateAchievement(player, AchievementList.ANSWER_30_TRIVIABOTS_CORRECTLY, 1);
		reset();
	}
	
	/**
	 * Resets the VencillioBot
	 */
	private static final void reset() {
		current = null;
		attempts.clear();
	}
	
	/**
	 * Sends message to server
	 * @param message
	 */
	public static void sendMessage(String message) {
		for (Player players : World.getPlayers()) {
			if (players != null && players.isWantTrivia()) {
				players.send(new SendMessage(message));
			}
		}
	}
	
	/**
	 * Sends notification to server
	 * @param message
	 */
	public static void sendNotification(String message) {
		for (Player players : World.getPlayers()) {
			if (players != null && players.isWantTrivia() && players.isTriviaNotification()) {
				players.send(new SendBanner(message, 0x8E9CA3));
			}
		}
	}

}
