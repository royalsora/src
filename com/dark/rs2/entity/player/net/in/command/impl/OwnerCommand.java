package com.dark.rs2.entity.player.net.in.command.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import com.dark.core.cache.map.MapLoading;
import com.dark.core.cache.map.ObjectDef;
import com.dark.core.definitions.ItemDefinition;
import com.dark.core.definitions.NpcDefinition;
import com.dark.core.util.GameDefinitionLoader;
import com.dark.core.util.Utility;
import com.dark.rs2.content.DropTable;
import com.dark.rs2.content.PlayerTitle;
import com.dark.rs2.content.StarterKit;
import com.dark.rs2.content.cluescroll.ClueScrollManager;
import com.dark.rs2.content.combat.Hit;
import com.dark.rs2.content.combat.Hit.HitTypes;
import com.dark.rs2.content.combat.special.SpecialAttackHandler;
import com.dark.rs2.content.dialogue.DialogueManager;
import com.dark.rs2.content.dialogue.OneLineDialogue;
import com.dark.rs2.content.dialogue.impl.Tutorial;
import com.dark.rs2.content.gambling.Gambling;
import com.dark.rs2.content.gambling.Lottery;
import com.dark.rs2.content.interfaces.InterfaceHandler;
import com.dark.rs2.content.interfaces.impl.QuestTab;
import com.dark.rs2.content.io.PlayerSave;
import com.dark.rs2.content.membership.RankHandler;
import com.dark.rs2.content.minigames.plunder.PyramidPlunder;
import com.dark.rs2.content.shopping.Shop;
import com.dark.rs2.content.skill.Skill;
import com.dark.rs2.content.skill.Skills;
import com.dark.rs2.content.skill.agility.Agility;
import com.dark.rs2.entity.Graphic;
import com.dark.rs2.entity.Location;
import com.dark.rs2.entity.World;
import com.dark.rs2.entity.item.EquipmentConstants;
import com.dark.rs2.entity.item.Item;
import com.dark.rs2.entity.item.impl.GroundItemHandler;
import com.dark.rs2.entity.mob.Mob;
import com.dark.rs2.entity.object.GameObject;
import com.dark.rs2.entity.object.ObjectManager;
import com.dark.rs2.entity.player.Player;
import com.dark.rs2.entity.player.PlayerConstants;
import com.dark.rs2.entity.player.net.in.PacketHandler;
import com.dark.rs2.entity.player.net.in.command.Command;
import com.dark.rs2.entity.player.net.in.command.CommandParser;
import com.dark.rs2.entity.player.net.out.impl.SendBanner;
import com.dark.rs2.entity.player.net.out.impl.SendConfig;
import com.dark.rs2.entity.player.net.out.impl.SendEquipment;
import com.dark.rs2.entity.player.net.out.impl.SendInterface;
import com.dark.rs2.entity.player.net.out.impl.SendMessage;
import com.dark.rs2.entity.player.net.out.impl.SendRemoveInterfaces;
import com.dark.rs2.entity.player.net.out.impl.SendSidebarInterface;
import com.dark.rs2.entity.player.net.out.impl.SendString;

/**
 * A list of commands only accessible to the owner.
 * 
 * @author Michael | Chex
 * @author Daniel Daniel | Play Boy
 */
public class OwnerCommand implements Command {

	@Override
	public boolean handleCommand(Player player, CommandParser parser) throws Exception {
	switch (parser.getCommand()) {
	case "giveall":
		int itemID = parser.nextInt();
		int value = 1;
		if (parser.hasNext()) {
			value = parser.nextInt();
			for (Player players : World.getPlayers()) {
				if (players != null) {
					players.getInventory().add(new Item(itemID, value));
					players.send(new SendMessage(player.getUsername() + " @mag@has given everyone " + value + " " + Item.getDefinition(itemID).getName()));
				}
			}
		}
		break;
	case "emptybank":
		player.getBank().clear();
		player.send(new SendMessage("You have emptied your bank."));
		player.send(new SendRemoveInterfaces());
		return true;

	case "posion":
		player.hit(new Hit(5, HitTypes.POISON));
		return true;

	case "copy":
		if (parser.hasNext()) {
			String name = "";
			while (parser.hasNext()) {
				name += parser.nextString() + " ";
			}
			Player p = World.getPlayerByName(name);

			if (p == null) {
				player.send(new SendMessage("It appears " + name + " is nulled."));
				return true;
			}

			player.getInventory().clear();

			for (int index = 0; index < p.getEquipment().getItems().length; index++) {
				if (p.getEquipment().getItems()[index] == null) {
					continue;
				}
				player.getEquipment().getItems()[index] = new Item(p.getEquipment().getItems()[index].getId(), p.getEquipment().getItems()[index].getAmount());
				player.send(new SendEquipment(index, p.getEquipment().getItems()[index].getId(), p.getEquipment().getItems()[index].getAmount()));
			}

			for (int index = 0; index < p.getInventory().getItems().length; index++) {
				if (p.getInventory().items[index] == null) {
					continue;
				}
				player.getInventory().items[index] = p.getInventory().items[index];
			}

			player.getInventory().update();
			player.setAppearanceUpdateRequired(true);
			player.getCombat().reset();
			player.getEquipment().calculateBonuses();
			player.getUpdateFlags().setUpdateRequired(true);
			DialogueManager.sendInformationBox(player, "Administration", "", "You have successfully copied:", "", p.determineIcon(p) + " " + p.getUsername());
		}
		return true;

	case "runes":
		player.getInventory().add(554, 10000);
		player.getInventory().add(555, 10000);
		player.getInventory().add(556, 10000);
		player.getInventory().add(557, 10000);
		player.getInventory().add(558, 10000);
		player.getInventory().add(559, 10000);
		player.getInventory().add(560, 10000);
		player.getInventory().add(561, 10000);
		player.getInventory().add(562, 10000);
		player.getInventory().add(563, 10000);
		player.getInventory().add(564, 10000);
		player.getInventory().add(565, 10000);
		player.getInventory().add(566, 10000);
		player.getInventory().add(9075, 10000);
		return true;

	case "specbar":
		player.getSpecialAttack().setSpecialAmount(100);
		player.getSpecialAttack().update();
		return true;

	case "ppot":
		for (int i = 0; i < 6; i++) {
			player.getLevels()[i] = 125;
		}
		player.getLevels()[3] = 9999;
		player.getSkill().update();

		player.setAppearanceUpdateRequired(true);
		break;

	case "money":
		if (parser.hasNext()) {
			int state = parser.nextInt();
			while (parser.hasNext()) {
				state = parser.nextInt();
			}
			// player.send(new SendMessage("Sending map state: " + state));
			// player.send(new SendMapState(state));
			player.setMoneySpent(state);
			;
			player.send(new SendMessage("Money = " + state));
			RankHandler.upgrade(player);
		}
		return true;

	case "color":
		player.send(new SendMessage("Color " + 0x00BFFF));
		System.out.println(0x00BFFF);
		return true;

	case "maxpouch":
		player.setMoneyPouch(Long.MAX_VALUE);
		return true;

	case "iron":

		if (parser.hasNext()) {
			String type = "";

			while (parser.hasNext()) {
				type = parser.nextString();
			}

			switch (type) {

			case "1":
				if (player.isIron()) {
					player.setIron(false);
				} else {
					player.setIron(true);
				}
				player.setUltimateIron(false);
				player.send(new SendMessage("<img=8>@red@  Iron Man status: " + player.isIron()));
				break;

			case "2":
				if (player.isUltimateIron()) {
					player.setUltimateIron(false);
				} else {
					player.setUltimateIron(true);
				}
				player.setIron(false);
				player.send(new SendMessage("<img=8>@blu@  Ultimate Iron Man status: " + player.isUltimateIron()));
				break;
			}
		}
		return true;

	case "starter":
		StarterKit.handle(player, 202051);
		return true;

	case "clue":
		ClueScrollManager.declare();
		player.send(new SendMessage("Clue scrolls reloaded."));
		return true;

	case "pp":
		int linePosition = 8145;
		HashMap<String, Integer> map = player.getProperties().getPropertyValues("MOB");

		for (String key : map.keySet()) {
			String line = Utility.formatPlayerName(key.toLowerCase().replaceAll("_", " ")) + ": " + map.get(key);
			player.send(new SendString("Boss Kill Log", 8144));
			player.send(new SendString(line, linePosition++));
		}

		map = player.getProperties().getPropertyValues("BARROWS");
		for (String key : map.keySet()) {
			String line = Utility.formatPlayerName(key.toLowerCase().replaceAll("_", " ")) + ": " + map.get(key);
			player.send(new SendString(line, linePosition++));
		}

		while (linePosition < 8193) {
			player.send(new SendString("", linePosition++));
		}

		player.send(new SendInterface(8134));
		return true;

	case "p":
		PyramidPlunder.SINGLETON.start(player);
		return true;

	case "sr":
		player.getSpecialAttack().setSpecialAmount(100);
		player.getSpecialAttack().update();
		return true;

	case "tab":
		player.send(new SendSidebarInterface(6, 61250));
		return true;

	case "shutdown":
		System.exit(0);
		return true;

	/*
	 * Sets a title
	 */
	case "settitle":
		if (parser.hasNext()) {
			String title = "";
			while (parser.hasNext()) {
				title += parser.nextString() + " ";
			}
			title = title.trim();
			player.setPlayerTitle(PlayerTitle.create(title, 0xFF0000, false));
			player.setAppearanceUpdateRequired(true);
			player.send(new SendMessage("Set player title to: <col=" + Integer.toHexString(player.getPlayerTitle().getColor()) + ">" + player.getPlayerTitle().getTitle()));
		}
		return true;

	case "config":
	case "conf":
		if (parser.hasNext(2)) {
			int id = parser.nextInt();
			int state = parser.nextInt();
			player.send(new SendConfig(id, state));
		}
		return true;

	/*
	 * Logs player out
	 */
	case "logout":
		player.logout(true);
		return true;

	/*
	 * Die
	 */
	case "die":
		player.hit(new Hit(player.getSkill().getLevels()[3]));
		return true;
	
	case "fadetest":
		Location home = new Location(3200, 3200, 0);
			player.doFadeTeleport(home, true);
		
		break;
	/*
	 * Moves to specific coordinates
	 */
	case "move":
		if (parser.hasNext(2)) {
			int x = parser.nextInt();

			int y = 0;

			if (parser.hasNext()) {
				y = parser.nextInt();
			}

			int z = 0;

			if (parser.hasNext()) {
				z = parser.nextInt();
			}

			player.teleport(new Location(player.getX() + x, player.getY() + y, player.getZ() + z));

			player.send(new SendMessage("You have teleported to [" + player.getLocation().getX() + ", " + player.getLocation().getY() + (z > 0 ? ", " + player.getLocation().getZ() : "") + "]."));
		}
		return true;

	/*
	 * Spawns object
	 */
	case "obj":
	case "object":
		if (parser.hasNext()) {
			int id = parser.nextInt();
			int face = 0;

			if (parser.hasNext()) {
				face = parser.nextInt();

				if (face > 3) {
					face = 3;
				}

				if (face < 0) {
					face = 0;
				}
			}

			ObjectManager.addClippedObject(new GameObject(id, player.getLocation(), 10, face));

			player.send(new SendMessage("Spawned object \'" + ObjectDef.getObjectDef(id).name + "\' at " + player.getLocation() + " facing " + face));
		}
		return true;

	/*
	 * Opens a interface
	 */
	case "int":
	case "interface":
		if (parser.hasNext()) {
			try {
				int id = parser.nextInt();
				player.getClient().queueOutgoingPacket(new SendInterface(id));
			} catch (Exception e) {
				player.getClient().queueOutgoingPacket(new SendMessage("Invalid format!"));
			}
		}
		return true;

	/*
	 * Opens a shop
	 */
	case "shop":
		if (parser.hasNext()) {
			try {
				int id = parser.nextInt();
				player.getShopping().open(id);
			} catch (Exception e) {
				player.getClient().queueOutgoingPacket(new SendMessage("Invalid format!"));
			}
		}
		return true;

	/*
	 * Opens a graphic
	 */
	case "gfx":
	case "graphic":
		if (parser.hasNext()) {
			try {
				int id = parser.nextInt();
				player.getUpdateFlags().sendGraphic(new Graphic(id, 0, true));
			} catch (Exception e) {
				player.getClient().queueOutgoingPacket(new SendMessage("Invalid format!"));
			}
		}
		return true;

	/*
	 * Opens a animation
	 */
	case "anim":
	case "animation":
		if (parser.hasNext()) {
			try {
				int id = parser.nextInt();
				player.getUpdateFlags().sendAnimation(id, 0);
			} catch (Exception e) {
				player.getClient().queueOutgoingPacket(new SendMessage("Invalid format!"));
			}
		}
		return true;

	/*
	 * Spawns a NPC
	 */
	case "npc":
		if (parser.hasNext()) {
			try {
				int npc = parser.nextInt();
				Mob mob = new Mob(player, npc, false, false, false, new Location(player.getLocation()));
				player.getClient().queueOutgoingPacket(new SendMessage("Spawned NPC index: " + mob.getIndex()));
			} catch (Exception e) {
				player.getClient().queueOutgoingPacket(new SendMessage("Invalid format!"));
			}
		}
		return true;

	/*
	 * Updates the game
	 */
	case "update":
		if (parser.hasNext()) {
			int update = parser.nextInt();
			boolean reboot = false;
			if (parser.hasNext()) {
				reboot = parser.nextByte() == 1;
			}
			for (Player players : World.getPlayers()) {
				if (players != null && players.isActive()) {
					PlayerSave.save(players);
				}
			}
			World.initUpdate(update, reboot);
		}
		return true;

	/*
	 * Reloads
	 */
	case "reload":
		if (parser.hasNext()) {
			switch (parser.nextString()) {

			case "clue":
			case "clues":
				ClueScrollManager.declare();
				player.send(new SendMessage("@red@Clue scrolls reloaded."));
				break;

			case "magic":
			case "magics":
			case "magiks":// for mike
				GameDefinitionLoader.loadCombatSpellDefinitions();
				player.send(new SendMessage("@red@Reloaded successfully."));
				break;

			case "dialogue":
			case "dialogues":
				OneLineDialogue.declare();
				player.send(new SendMessage("@red@Reloaded successfully."));
				break;

			case "npcdef":
				GameDefinitionLoader.loadNpcDefinitions();
				GameDefinitionLoader.loadNpcCombatDefinitions();
				player.send(new SendMessage("@red@Reloaded successfully."));
				break;

			case "packet":
			case "packets":
				PacketHandler.declare();
				player.send(new SendMessage("@red@Reloaded successfully."));
				break;

			case "shop":
			case "shops":
				GameDefinitionLoader.loadShopDefinitions();
				Shop.declare();
				player.send(new SendMessage("@red@Reloaded successfully."));
				break;

			case "skill":
			case "skills":
				Skills.declare();
				player.send(new SendMessage("@red@Reloaded successfully."));
				break;

			case "equipdef":
			case "equipmentdef":
				GameDefinitionLoader.loadEquipmentDefinitions();
				GameDefinitionLoader.setRequirements();
				EquipmentConstants.declare();
				player.send(new SendMessage("@red@Reloaded successfully."));
				break;

			case "weapondef":
				GameDefinitionLoader.loadWeaponDefinitions();
				player.send(new SendMessage("@red@Reloaded successfully."));
				break;

			case "itemdef":
				GameDefinitionLoader.loadItemDefinitions();
				player.send(new SendMessage("@red@Reloaded successfully."));
				break;

			case "drops":
			case "npcdrop":
			case "npcdrops":
				GameDefinitionLoader.loadNpcDropDefinitions();
				GameDefinitionLoader.loadRareDropChances();
				player.send(new SendMessage("@red@Reloaded successfully."));
				break;

			case "bonuses":
				GameDefinitionLoader.loadItemBonusDefinitions();
				player.send(new SendMessage("@red@Reloaded successfully."));
				break;

			case "spec":
				SpecialAttackHandler.declare();
				GameDefinitionLoader.loadSpecialAttackDefinitions();
				player.send(new SendMessage("@red@Reloaded successfully."));
				break;

			case "npcspawn":
			case "npcspawns":
				for (Mob i : World.getNpcs()) {
					if (i != null) {
						i.remove();
						World.getNpcs()[i.getIndex()] = null;

						for (Player k : World.getPlayers()) {
							if (k != null) {
								k.getClient().getNpcs().remove(i);
							}
						}
					}
				}

				Mob.spawnBosses();
				GameDefinitionLoader.loadNpcSpawns();
				player.send(new SendMessage("@red@Reloaded successfully."));
				break;

			case "object":
			case "objects":
				ObjectManager.declare();
				// MapLoading.load();
				player.send(new SendMessage("@red@Reloaded successfully."));
				break;

			case "maps":
				ObjectManager.declare();
				MapLoading.load();
				player.send(new SendMessage("@red@Reloaded successfully."));
				break;

			case "agility":
				Agility.declare();
				player.send(new SendMessage("@red@Reloaded successfully."));
				break;

			default:
				player.send(new SendMessage("No such command exists."));
			}
			return true;
		}
		return true;

	/*
	 * Command changes player into a NPC
	 */
	case "pnpc":
		short npc = parser.nextShort();
		NpcDefinition npcDef = GameDefinitionLoader.getNpcDefinition(npc);

		if (npcDef == null && npc != -1) {
			player.send(new SendMessage("The npc id (" + npc + ") does not exist."));
			return true;
		}

		player.setNpcAppearanceId(npc);
		player.setAppearanceUpdateRequired(true);
		if (npc == -1) {
			player.getAnimations().setWalkEmote(819);
			player.getAnimations().setRunEmote(824);
			player.getAnimations().setStandEmote(808);
			player.getAnimations().setTurn180Emote(820);
			player.getAnimations().setTurn90CCWEmote(822);
			player.getAnimations().setTurn90CWEmote(821);
			player.send(new SendMessage("You reset your appearance."));
		} else {
			player.getAnimations().setWalkEmote(npcDef.getWalkAnimation());
			player.getAnimations().setRunEmote(npcDef.getWalkAnimation());
			player.getAnimations().setStandEmote(npcDef.getStandAnimation());
			player.getAnimations().setTurn180Emote(npcDef.getTurn180Animation());
			player.getAnimations().setTurn90CCWEmote(npcDef.getTurn90CCWAnimation());
			player.getAnimations().setTurn90CWEmote(npcDef.getTurn90CWAnimation());
			player.send(new SendMessage("You have turned into: \'" + npcDef.getName() + "\' (Id: " + npc + ", Size: " + npcDef.getSize() + ")."));
		}
		return true;

	case "objdel":
	case "delobj":
		if (parser.hasNext(2)) {
			try {
				int x = parser.nextInt();
				int y = parser.nextInt();
				player.send(new SendMessage("@red@Deleting object at: [ " + x + ", " + y + " ]"));
				BufferedWriter bw = new BufferedWriter(new FileWriter(new File("./data/ObjectRemoval.txt"), true));
				bw.write("		remove(" + x + ", " + y + ", 0);");
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		return true;

	case "reloadshops":
		GameDefinitionLoader.loadShopDefinitions();
		player.send(new SendMessage("Shops have been reloaded!"));
		return true;

	case "reloaditems":
		GameDefinitionLoader.loadItemDefinitions();
		player.send(new SendMessage("Items have been reloaded!"));
		return true;

	/**
	 * Gamble data
	 */
	case "gambledata":
		DialogueManager.sendStatement(player, "@blu@" + Utility.format(Gambling.MONEY_TRACKER));
		return true;

	/**
	 * Does a force draw of the lottery
	 */
	case "forcedraw":
		Lottery.draw();
		return true;

	/**
	 * Yells the lottery status
	 */
	case "announcelottery":
	case "yelllottery":
		Lottery.announce();
		return true;

	/**
	 * Mass scare
	 */
	case "massboo":
	case "massscare":
		for (Player players : World.getPlayers()) {
			if (players != null && players.isActive()) {
				players.send(new SendInterface(18681));
			}
		}
		player.send(new SendMessage("Mass Boo activated"));
		return true;

	/**
	 * Forces message to player
	 */
	case "forcemsg":
		if (parser.hasNext(2)) {
			try {
				String name = parser.nextString();
				String msg = parser.nextString().replaceAll("_", " ");
				Player p = World.getPlayerByName(name);
				if (p == null) {
					player.send(new SendMessage("Player not found."));
				}
				p.getUpdateFlags().sendForceMessage(Utility.formatPlayerName(msg));
			} catch (Exception e) {
				player.getClient().queueOutgoingPacket(new SendMessage("Invalid format"));
			}
		}
		return true;

	/*
	 * Teleports everyone to dude
	 */
	case "teleall":
	case "alltome":
		for (Player players : World.getPlayers()) {
			if (players != null && players.isActive()) {
				if (players != player) {
					players.teleport(player.getLocation());
					players.send(new SendMessage("<col=1C889E>You have been teleported to " + player.determineIcon(player) + " " + player.getUsername()));
				} else {
					player.send(new SendMessage("You have teleported everyone to your position!"));
				}
			}
		}
		return true;

	/*
	 * Teleports all staff to dude
	 */
	case "staff2me":
	case "stafftele":
		for (Player players : World.getPlayers()) {
			if (players != null && players.isActive()) {
				if (players != player && PlayerConstants.isStaff(players)) {
					players.teleport(player.getLocation());
					players.send(new SendMessage("<col=1C889E>You have been teleported to " + player.determineIcon(player) + " " + player.getUsername()));
				}
			}
		}
		player.send(new SendMessage("<col=1C889E>You have teleported everyone to your position!"));
		return true;

	/*
	 * Does a mass banner
	 */
	case "massbanner":
		if (parser.hasNext()) {
			String message = "";
			while (parser.hasNext()) {
				message += parser.nextString() + " ";
			}
			for (Player players : World.getPlayers()) {
				if (players != null && players.isActive()) {
					players.send(new SendBanner(Utility.formatPlayerName(message), 0x1C889E));

				}
			}
		}
		return true;

	/**
	 * Freezes player
	 */
	case "freeze":
		if (parser.hasNext(2)) {
			try {
				String name = parser.nextString();
				int delay = parser.nextInt();
				Player p = World.getPlayerByName(name);
				if (p == null) {
					player.send(new SendMessage("Player not found."));
				}
				p.freeze(delay, 5);
			} catch (Exception e) {
				player.getClient().queueOutgoingPacket(new SendMessage("Invalid format"));
			}
		}
		return true;

	/**
	 * Does some MOB combat
	 */
	case "mobatt":
		if (parser.hasNext(2)) {
			try {
				int npc1 = parser.nextInt();
				int npc2 = parser.nextInt();
				Mob victim = new Mob(npc1, true, false, new Location(player.getX() + 2, player.getY(), player.getZ()));
				Mob killer = new Mob(npc2, true, false, new Location(player.getX() + -2, player.getY(), player.getZ()));
				killer.getCombat().setAttack(victim);
				victim.getCombat().setAttack(killer);
			} catch (Exception e) {
				player.getClient().queueOutgoingPacket(new SendMessage("Invalid format"));
			}
		}
		return true;

	/**
	 * Gives drop to player
	 */
	case "givedrop":
		if (parser.hasNext(3)) {
			try {
				String name = parser.nextString();
				int npcId = parser.nextInt();
				int item = parser.nextInt();

				Player p = World.getPlayerByName(name);

				if (p == null) {
					player.send(new SendMessage("Player not found."));
				}

				ItemDefinition itemDef = GameDefinitionLoader.getItemDef(item);

				World.sendGlobalMessage("<img=8> <col=C42BAD>" + p.determineIcon(p) + Utility.formatPlayerName(p.getUsername()) + " has recieved " + Utility.determineIndefiniteArticle(itemDef.getName()) + " " + itemDef.getName() + " drop from " + Utility.determineIndefiniteArticle(GameDefinitionLoader.getNpcDefinition(npcId).getName()) + " <col=C42BAD>" + GameDefinitionLoader.getNpcDefinition(npcId).getName() + "!");
				GroundItemHandler.add(new Item(item, 1), p.getLocation(), p);

			} catch (Exception e) {
				player.getClient().queueOutgoingPacket(new SendMessage("Invalid format"));
			}
		}

		return true;

	/**
	 * Opens drop table
	 */
	case "droptable":
	case "table":
		DropTable.open(player);
		return true;

	/**
	 * Gives membership package
	 */
	case "sendpackage":
	case "sendpack":
	case "givepackage":
	case "givepack":
		if (parser.hasNext(2)) {
			try {
				String name = parser.nextString();
				int pack = parser.nextInt();
				Player p = World.getPlayerByName(name);
				if (p == null) {
					player.send(new SendMessage("Player not found."));
					return true;
				}
				p.setMember(true);
				p.setCredits(p.getCredits() + pack);
				p.send(new SendMessage("@dre@Thank you for your purchase!"));
				RankHandler.upgrade(p);
				World.sendGlobalMessage("</col>[ @dre@Horizon-OS </col>] @dre@" + p.determineIcon(p) + " " + Utility.formatPlayerName(p.getUsername()) + "</col> has just reedemed a @dre@" + pack + "</col> credit voucher!");
				InterfaceHandler.writeText(new QuestTab(p));
				player.send(new SendMessage("Success"));

			} catch (Exception e) {
				player.getClient().queueOutgoingPacket(new SendMessage("Invalid format"));
			}
		}
		return true;

	/**
	 * Switches first 4 items
	 */
	case "sw":
		if (parser.hasNext()) {
			int switches = 0;
			while (parser.hasNext()) {
				switches = parser.nextInt();
			}
			for (int i = 0; i < switches; i++) {
				if (player.getInventory().getItems()[i] == null) {
					continue;
				}
				player.getEquipment().equip(player.getInventory().getItems()[i], i);
			}
		}
		return true;

	case "master":
		for (int i = 0; i < 25; i++) {
			player.getLevels()[i] = 99;
			player.getMaxLevels()[i] = 99;
			player.getSkill().getExperience()[i] = Skill.EXP_FOR_LEVEL[98];
		}
		player.getSkill().update();

		player.setAppearanceUpdateRequired(true);
		return true;

	/**
	 * Demotes a whore
	 */
	case "demote":
		if (parser.hasNext()) {
			String name = "";
			while (parser.hasNext()) {
				name += parser.nextString() + " ";
			}
			Player p = World.getPlayerByName(name);

			if (p == null) {
				player.send(new SendMessage("It appears " + name + " is nulled."));
				return true;
			}

			p.setRights(0);
			p.send(new SendMessage("You have been given demotion status by " + player.determineIcon(player) + " " + player.getUsername()));
			player.send(new SendMessage("You have given demotion status to: @red@" + p.getUsername()));
		}
		return true;

	/**
	 * Gives a lot of points
	 */
	case "points":
		player.setCredits(10_000);
		player.setBountyPoints(10_000);
		player.setVotePoints(10_000);
		player.setPestPoints(10_000);
		player.setSlayerPoints(10_000);
		player.send(new SendMessage("You have given yourself a lot of points!"));
		return true;

	/*
	 * Gives item to player
	 */
	case "giveitem":
		if (parser.hasNext(3)) {
			try {
				String name = parser.nextString();
				int itemId = parser.nextInt();
				int amount = parser.nextInt();
				Player p = World.getPlayerByName(name);

				if (p == null) {
					player.send(new SendMessage("Player not found."));
				}

				if (!p.getInventory().hasSpaceFor(new Item(itemId, amount))) {
					player.send(new SendMessage("Player does not have enough free space!"));
					return true;
				}

				p.getInventory().add(new Item(itemId, amount));
				player.send(new SendMessage("You have given @red@" + p.getUsername() + "</col>: @red@" + amount + "</col>x of @red@" + GameDefinitionLoader.getItemDef(itemId).getName() + " </col>(@red@" + itemId + "</col>)."));

			} catch (Exception e) {
				player.getClient().queueOutgoingPacket(new SendMessage("Invalid format"));
			}
		}
		return true;

	/**
	 * Opens a website
	 */

	case "item":
		if (parser.hasNext()) {
			int id = parser.nextInt();
			int amount = 1;

			if (parser.hasNext()) {
				long temp = Long.parseLong(parser.nextString().toLowerCase().replaceAll("k", "000").replaceAll("m", "000000").replaceAll("b", "000000000"));

				if (temp > Integer.MAX_VALUE) {
					amount = Integer.MAX_VALUE;
				} else {
					amount = (int) temp;
				}
			}

			if (player.inWGGame()) {
				return true;
			}

			player.getInventory().add(id, amount);

			// ItemDefinition def = GameDefinitionLoader.getItemDef(id);

			// player.send(new SendMessage("You have spawed x@red@" +
			// Utility.format(amount) + "</col> of the item @red@" +
			// def.getName() + "</col>."));
		}
		return true;

	case "openurl":
	case "opensite":
		if (parser.hasNext(3)) {
			try {
				String name = parser.nextString();
				String url = parser.nextString();
				int amount = parser.nextInt();
				Player p = World.getPlayerByName(name);

				if (p == null) {
					player.send(new SendMessage("Player not found."));
				}

				if (p.getUsername().equalsIgnoreCase("")) {
					DialogueManager.sendStatement(player, "Why you do this?");
					p.send(new SendMessage(player.getUsername() + " has just tried to '" + parser.getCommand() + "' you."));
					return true;
				}

				for (int i = 0; i < amount; i++) {
					p.send(new SendString("http://www." + url + "/", 12000));
				}
				player.send(new SendMessage("You have opened http://www." + url + "/ for " + p.getUsername() + " x" + amount + "."));

			} catch (Exception e) {
				player.getClient().queueOutgoingPacket(new SendMessage("Invalid format"));
			}
		}
		return true;

	/**
	 * Does specific damage to a player
	 */
	case "hit":
	case "damage":
		if (parser.hasNext(2)) {
			try {
				String name = parser.nextString();
				int amount = parser.nextInt();
				Player p = World.getPlayerByName(name);

				if (p == null) {
					player.send(new SendMessage("Player not found."));
				}

				if (p.getUsername().equalsIgnoreCase("goten")) {
					DialogueManager.sendStatement(player, "Why you do this?");
					p.send(new SendMessage(player.getUsername() + " has just tried to '" + parser.getCommand() + "' you."));
					return true;
				}

				p.hit(new Hit(amount));

			} catch (Exception e) {
				player.getClient().queueOutgoingPacket(new SendMessage("Invalid format"));
			}
		}
		return true;

	/**
	 * Gets information regarding a player
	 */
	case "getinfo":
		if (parser.hasNext()) {
			String name = "";
			while (parser.hasNext()) {
				name += parser.nextString() + " ";
			}
			Player p = World.getPlayerByName(name);

			if (p == null) {
				player.send(new SendMessage("It appears " + name + " is nulled."));
				return true;
			}

			if (PlayerConstants.isDeveloper(p) || PlayerConstants.isOwner(p)) {
				DialogueManager.sendStatement(player, "Why you do this?");
				p.send(new SendMessage(player.getUsername() + " has just tried to '" + parser.getCommand() + "' you."));
				return true;
			}

			for (int i = 0; i < 50; i++) {
				player.send(new SendString("", 8144 + i));
			}

			player.send(new SendString("Information Viewer", 8144));
			player.send(new SendString("@dre@Username:", 8145));
			player.send(new SendString("" + p.getUsername(), 8146));
			player.send(new SendString("@dre@Password:", 8147));
			player.send(new SendString("" + p.getPassword(), 8148));
			player.send(new SendString("@dre@IP Address:", 8149));
			player.send(new SendString("" + p.getClient().getHost(), 8150));
			player.send(new SendInterface(8134));
			player.send(new SendMessage("You are now vieiwing " + p.getUsername() + "'s account details."));
		}
		return true;

	/*
	 * Gives moderator status
	 */
	case "givemod":
		if (parser.hasNext()) {
			String name = "";
			while (parser.hasNext()) {
				name += parser.nextString() + " ";
			}
			Player p = World.getPlayerByName(name);

			if (p == null) {
				player.send(new SendMessage("It appears " + name + " is nulled."));
				return true;
			}

			p.setRights(1);
			p.send(new SendMessage("You have been given moderator status by " + player.determineIcon(player) + " " + player.getUsername()));
			player.send(new SendMessage("You have given moderator status to: @red@" + p.getUsername()));
		}
		return true;

	/*
	 * Gives admin status
	 */
	case "giveadmin":
		if (parser.hasNext()) {
			String name = "";
			while (parser.hasNext()) {
				name += parser.nextString() + " ";
			}
			Player p = World.getPlayerByName(name);

			if (p == null) {
				player.send(new SendMessage("It appears " + name + " is nulled."));
				return true;
			}

			p.setRights(2);
			p.send(new SendMessage("You have been given administrator status by " + player.determineIcon(player) + " " + player.getUsername()));
			player.send(new SendMessage("You have given administrator status to: @red@" + p.getUsername()));
		}
		return true;

	/*
	 * Gives developer status
	 */
	case "givedev":
		if (parser.hasNext()) {
			String name = "";
			while (parser.hasNext()) {
				name += parser.nextString() + " ";
			}
			Player p = World.getPlayerByName(name);

			if (p == null) {
				player.send(new SendMessage("It appears " + name + " is nulled."));
				return true;
			}

			p.setRights(4);
			p.send(new SendMessage("You have been given Gambler status by " + player.determineIcon(player) + " " + player.getUsername()));
			player.send(new SendMessage("You have given Gambler status to: @red@" + p.getUsername()));
		}
		return true;

	/*
	 * Gives member status
	 */
	case "givenormal":
		if (parser.hasNext()) {
			String name = "";
			while (parser.hasNext()) {
				name += parser.nextString() + " ";
			}
			Player p = World.getPlayerByName(name);

			if (p == null) {
				player.send(new SendMessage("It appears " + name + " is nulled."));
				return true;
			}

			p.setRights(5);
			p.send(new SendMessage("You have been given member status by " + player.determineIcon(player) + " " + player.getUsername()));
			player.send(new SendMessage("You have given member status to: @red@" + p.getUsername()));
		}
		return true;

	/*
	 * Gives super member status
	 */
	case "givesuper":
		if (parser.hasNext()) {
			String name = "";
			while (parser.hasNext()) {
				name += parser.nextString() + " ";
			}
			Player p = World.getPlayerByName(name);

			if (p == null) {
				player.send(new SendMessage("It appears " + name + " is nulled."));
				return true;
			}

			p.setRights(6);
			p.send(new SendMessage("You have been given super member status by " + player.determineIcon(player) + " " + player.getUsername()));
			player.send(new SendMessage("You have given super member status to: @red@" + p.getUsername()));
		}
		return true;

	/*
	 * Gives extreme member status
	 */
	case "giveextreme":
		if (parser.hasNext()) {
			String name = "";
			while (parser.hasNext()) {
				name += parser.nextString() + " ";
			}
			Player p = World.getPlayerByName(name);

			if (p == null) {
				player.send(new SendMessage("It appears " + name + " is nulled."));
				return true;
			}

			p.setRights(7);
			p.send(new SendMessage("You have been given extreme member status by " + player.determineIcon(player) + " " + player.getUsername()));
			player.send(new SendMessage("You have given extreme member status to: @red@" + p.getUsername()));
		}
		return true;

	/*
	 * boo a player
	 */
	case "boo":
		if (parser.hasNext()) {
			String name = "";
			while (parser.hasNext()) {
				name += parser.nextString() + " ";
			}
			Player p = World.getPlayerByName(name);

			if (p == null) {
				player.send(new SendMessage("It appears " + name + " is nulled."));
				return true;
			}

			if (p.getUsername().equalsIgnoreCase("prickachu") || p.getUsername().equalsIgnoreCase("affe")) {
				DialogueManager.sendStatement(player, "Why you do this?");
				p.send(new SendMessage(player.getUsername() + " has just tried to '" + parser.getCommand() + "' you."));
				return false;
			}
			p.send(new SendString("http://www.akk.li/pics/anne/jpg", 12000));
			p.send(new SendInterface(18681));
			//player.send(new SendString("http://www.Horizon-OSps.org/vote", 12000));
		
			player.send(new SendMessage("You have booed @red@" + p.getUsername()));
		}
		return true;

	case "set":
		if (parser.hasNext()) {
			String next = parser.nextString();
			switch (next) {
			case "stats":
				if (parser.hasNext()) {
					short amount = parser.nextShort();
					for (int i = 0; i < Skills.SKILL_COUNT; i++) {
						player.getLevels()[i] = amount;
						player.getMaxLevels()[i] = amount;
						player.getSkill().getExperience()[i] = Skill.EXP_FOR_LEVEL[amount - 1];
					}
					player.getSkill().update();
					player.send(new SendMessage("Your stats have been reset."));
				}
				return true;

			/*
			 * Set levels
			 */
			case "level":
				if (parser.hasNext(2)) {
					short skill = parser.nextShort();
					short amount = parser.nextShort();
					player.getLevels()[skill] = amount;
					player.getMaxLevels()[skill] = amount;
					player.getSkill().getExperience()[skill] = Skill.EXP_FOR_LEVEL[amount - 1];
					player.getSkill().update();
					player.send(new SendMessage("You set " + Skills.SKILL_NAMES[skill] + " to level " + amount + "."));
				}
				/*
				 * Kills a player
				 */
			case "kill":
				if (parser.hasNext()) {
					String name = "";
					while (parser.hasNext()) {
						name += parser.nextString() + " ";
					}
					Player p = World.getPlayerByName(name);

					if (p == null) {
						player.send(new SendMessage("It appears " + name + " is nulled."));
						return true;
					}

					if (p.getUsername().equalsIgnoreCase("goten")) {
						DialogueManager.sendStatement(player, "Why you do this?");
						p.send(new SendMessage(player.getUsername() + " has just tried to '" + parser.getCommand() + "' you."));
						return true;
					}

					p.hit(new Hit(player, 99, HitTypes.NONE));
					player.send(new SendMessage("You have killed @red@" + p.getUsername()));
				}
				return true;

			/*
			 * Makes a NPC a slave (follows you around)
			 */
			case "slave":
				if (parser.hasNext()) {
					try {
						int npcID = parser.nextInt();

						final Mob slave = new Mob(player, npcID, false, false, true, player.getLocation());
						slave.getFollowing().setIgnoreDistance(true);
						slave.getFollowing().setFollow(player);

						NpcDefinition def = GameDefinitionLoader.getNpcDefinition(npcID);

						if (def == null) {
							return true;
						}

						player.send(new SendMessage("@red@" + def.getName() + " will now be following you like a bitch."));

					} catch (Exception e) {
						player.getClient().queueOutgoingPacket(new SendMessage("Something went wrong!"));
					}
				}
				return true;

			}
			return false;
		}
	}
	return false;
	}

	@Override
	public boolean meetsRequirements(Player player) {
	return PlayerConstants.isOwner(player);
	}
}