package com.dark;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.logging.Logger;

//import org.Vote.MainLoader;

import com.dark.core.GameThread;
import com.dark.core.network.mysql.HiscoreUpdater;
import com.dark.core.util.logger.PlayerLogger;
import com.dark.rs2.content.clanchat.ClanManager;
import com.dark.rs2.content.io.PlayerSave;
import com.dark.rs2.entity.World;
import com.dark.rs2.entity.player.Player;

/**
 * Initializes the server
 * 
 * @author Michael Sasse
 * 
 */
public class Server {

	/**
	 * The logger for printing information.
	 */
	private static Logger logger = Logger.getLogger(Server.class.getSimpleName());

	/**
	 * Accept connection to vote database
	 */
	//public static MainLoader vote = new MainLoader("31.220.105.216", "darkasyl_vote", "WcwCTku2aT,!", "darkasyl_vote");

	/**
	 * Handles the clan chat.
	 */
	public static ClanManager clanManager = new ClanManager();

	/**
	 * Gets the Dark-Asylum time
	 */
	public static String vencillioTime() {
	return new SimpleDateFormat("HH:mm aa").format(new Date());
	}

	/**
	 * Gets the server date
	 */
	public static String vencillioDate() {
	return new SimpleDateFormat("EEEE MMM dd yyyy ").format(new Date());
	}

	/**
	 * Gets the server uptime
	 * 
	 * @return
	 */
	public static String getUptime() {
	RuntimeMXBean mx = ManagementFactory.getRuntimeMXBean();
	DateFormat df = new SimpleDateFormat("DD 'D', HH 'H', mm 'M'");
	df.setTimeZone(TimeZone.getTimeZone("GMT+0"));
	return "" + df.format(new Date(mx.getUptime()));
	}

	/**
	 * The main method of the server that initializes everything
	 * 
	 * @param args
	 *            The startup arguments
	 */
	public static void main(String[] args) {
	if (args != null && args.length > 0) {
		Constants.DEV_MODE = Boolean.valueOf(args[0]);
	}

	logger.info("Development mode: " + (Constants.DEV_MODE ? "Online" : "Offline") + ".");
	logger.info("Staff mode: " + (Constants.STAFF_ONLY ? "Online" : "Offline") + ".");

	if (!Constants.DEV_MODE) {
		try {
				//HiscoreUpdater.prepare();
		} catch (Exception ex) {
			
			ex.printStackTrace();
		}

		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			for (Player players : World.getPlayers()) {
				if (players != null && players.isActive()) {
					PlayerSave.save(players);
				}
			}
			
		//	HiscoreUpdater.shutdown();

			PlayerLogger.SHUTDOWN_LOGGER.log("Logs", String.format("Server shutdown with %s online.", World.getActivePlayers()));
		}));
	}

	GameThread.init();
	}
}
