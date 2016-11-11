package com.dark.rs2.content;

import java.util.ArrayList;
import java.util.logging.Logger;

import com.dark.core.task.Task;
import com.dark.core.task.TaskQueue;
import com.dark.core.util.Utility;
import com.dark.rs2.entity.World;

/**
 * Handles the global messages
 * @author Daniel
 *
 */
public class GlobalMessages {
	
	/**
	 * The logger for the class
	 */
	private static Logger logger = Logger.getLogger(GlobalMessages.class.getSimpleName());

	/**
	 * The news color text
	 */
	private static String newsColor = "<col=5C2196>";

	/**
	 * The news icon
	 */
	private static String iconNumber = "<img=3>";
	
	/**
	 * Holds all the announcements in a arraylist
	 */
	public final static ArrayList<String> announcements = new ArrayList<String>();

	/**
	 * The random messages that news will send
	 */
	public static final String[] ANNOUNCEMENTS = { 
		"Be sure to read the rules! Don't break them!",
		"Want to support server? ::donate ::vote",
		"Want to help us grow? Vote every 12 hours, you will also get a reward!",
		"Thank you for playing! Make sure you check out the forums aswell",
		"Donators have many benefits including ::yell and access to the Donator Zone.",
		"Check out our forums for the latest news & updates at ::forums!",
		"Voting helps the server and you get rewarded too!.",
		"Do you have an interesting idea? Suggest it to us on forums!",
		"Found a bug or glitch? Post it on the forums!",
		"If you're enjoying the server, tell your friends!",
		"Our website is located at http://www.Horizon-OS.org!",
		"Make sure you Vote every day!",
	};
	
	/**
	 * Declares all the announcements
	 */
	public static void declare() {
		for (int i = 0; i < ANNOUNCEMENTS.length; i++) {
			announcements.add(ANNOUNCEMENTS[i]);
		}
		logger.info(Utility.format(announcements.size()) + " Announcements have been loaded successfully.");
	}

	/**
	 * Initializes the task
	 */
	public static void initialize() {
		TaskQueue.queue(new Task(250, false) {
			@Override
			public void execute() {
				final String announcement = Utility.randomElement(announcements);
				World.sendGlobalMessage(iconNumber + newsColor + " " + announcement);
			}

			@Override
			public void onStop() {
			}
		});
	}
	
}
