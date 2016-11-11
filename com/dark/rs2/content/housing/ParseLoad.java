package com.dark.rs2.content.housing;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import com.dark.rs2.content.io.PlayerSave;
import com.dark.rs2.entity.player.Player;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ParseLoad {

	public static HashMap<String, House> HOUSES = new HashMap<String, House>();

	public static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

	/*
	 * public static void loadHouse(Player player) { Gson gson = new Gson();
	 * 
	 * File file = new File("./data/json/WofGW/" + player.getUsername() +
	 * ".json");
	 * 
	 * if (!file.exists()) { return; }
	 * 
	 * try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
	 * 
	 * // deserialize gson elements into java object House[] details =
	 * gson.fromJson(reader, House[].class); // take the array of objects and
	 * store them in a list Arrays.stream(details).forEach(houses ->
	 * {HOUSES.put(player.getUsername(), player.getHouse())}); } }
	 */

	public static void saveHouse(Player player) throws IOException {
	PlayerSave.save(player);
	File ds = new File("./data/json/housing/" + player.getUsername() + ".json");
	if (!ds.exists()) {
		ds.createNewFile();
	}
	BufferedWriter writer = new BufferedWriter(new FileWriter("./data/json/housing/" + player.getUsername() + ".json", false));
	if (ds.exists()) {
		ds.delete();
		ds.createNewFile();
		HOUSES.put(player.getUsername(), player.getHouse());
		try {
			writer.write(GSON.toJson(HOUSES));
			writer.flush();

		} finally {
			writer.close();
		}
	}
	}

}
