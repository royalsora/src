package com.dark.rs2.content.membership;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Arrays;

import com.dark.core.definitions.ItemDefinition;
import com.dark.core.task.Task;
import com.dark.core.task.TaskQueue;
import com.dark.core.util.GameDefinitionLoader;
import com.dark.core.util.Utility;
import com.dark.core.util.chance.Chance;
import com.dark.core.util.chance.WeightedChance;
import com.dark.core.util.chance.WeightedObject;
import com.dark.rs2.entity.item.Item;
import com.dark.rs2.entity.player.Player;
import com.dark.rs2.entity.player.net.out.impl.SendInterface;
import com.dark.rs2.entity.player.net.out.impl.SendMessage;
import com.dark.rs2.entity.player.net.out.impl.SendRemoveInterfaces;
import com.dark.rs2.entity.player.net.out.impl.SendString;
import com.dark.rs2.entity.player.net.out.impl.SendUpdateItemsAlt;

/**
 * Membership Mystery Box
 * @author Daniel
 */
public class MysteryBoxMinigame {

	/**
	 * The expensive amount
	 */
	private final static int EXPENSIVE_AMOUNT = 1_000_000;
	
	/**
	 * The interface identification 
	 */
	private final static int INTERFACE_ID = 17000;
	
	/**
	 * The container identification
	 */
	private final static int CONTAINER_ID = 17002;
	
	/**
	 * The message color
	 */
	private final static String MESSAGE_COLOR = "<col=255>";
	
	private final static Item ELITE_BOX = new Item(12789);
	/**
	 * All available items to win
	 */
	public static Chance<Item> available = new Chance<Item>(Arrays.asList(
			/* 
			 * Common items
			 */

			/*
			 * Rare items
			 */
			new WeightedChance<Item>(WeightedChance.COMMON, new Item(11840, 1)), //Dragon boots
			
			/*
			 * Very rare items
			 */
			new WeightedChance<Item>(WeightedChance.UNCOMMON, new Item(6585, 1)), //Amulet of fury
			new WeightedChance<Item>(WeightedChance.UNCOMMON, new Item(11283, 1)), //Dragonfire shield
			new WeightedChance<Item>(WeightedChance.UNCOMMON, new Item(11335, 1)), //Dragon full helm
			new WeightedChance<Item>(WeightedChance.UNCOMMON, new Item(3140, 1)), //Dragon chainbody
			new WeightedChance<Item>(WeightedChance.UNCOMMON, new Item(12829, 1)), //Spirit shield
			new WeightedChance<Item>(WeightedChance.RARE, new Item(6731, 1)), //Seers ring
			new WeightedChance<Item>(WeightedChance.RARE, new Item(6733, 1)), //Archers ring
			new WeightedChance<Item>(WeightedChance.RARE, new Item(6735, 1)), //Warrior ring
			new WeightedChance<Item>(WeightedChance.RARE, new Item(6737, 1)), //Berserker ring
			new WeightedChance<Item>(WeightedChance.VERY_RARE, new Item(12922, 1)), //Tanzanite fang
			new WeightedChance<Item>(WeightedChance.VERY_RARE, new Item(12932, 1)), //Magic fang
			new WeightedChance<Item>(WeightedChance.VERY_RARE, new Item(12928, 1)), //Serpentine Visage
			new WeightedChance<Item>(WeightedChance.UNCOMMON, new Item(6570, 1)), //Fire cape
			new WeightedChance<Item>(WeightedChance.UNCOMMON, new Item(12795, 1)), //Steam battlestaff
			
			
			new WeightedChance<Item>(WeightedChance.RARE, new Item(6585, 1)), //Amulet of fury
			new WeightedChance<Item>(WeightedChance.VERY_RARE, new Item(962, 1)), //Christmas Cracker
			new WeightedChance<Item>(WeightedChance.VERY_RARE, new Item(1050, 1)), //Santa Hat
			new WeightedChance<Item>(WeightedChance.VERY_RARE, new Item(13343, 1)), //Black Santa Hat
			new WeightedChance<Item>(WeightedChance.RARE, new Item(13274, 1)), //Bludgeon Spine
			new WeightedChance<Item>(WeightedChance.RARE, new Item(13275, 1)), //Bludgeon Claw
			new WeightedChance<Item>(WeightedChance.RARE, new Item(13276, 1)), //Bludgeon Axon
			new WeightedChance<Item>(WeightedChance.RARE, new Item(13222, 1)), //Music cape
			new WeightedChance<Item>(WeightedChance.RARE, new Item(13182, 1)), //Bunny feet
			new WeightedChance<Item>(WeightedChance.VERY_RARE, new Item(1037, 1)), //Bunny ears
			new WeightedChance<Item>(WeightedChance.RARE, new Item(11900, 1)), //Decorative Legs
			new WeightedChance<Item>(WeightedChance.RARE, new Item(11899, 1)), //Decorative Top
			new WeightedChance<Item>(WeightedChance.RARE, new Item(11901, 1)), //Decorative Quiver
			new WeightedChance<Item>(WeightedChance.RARE, new Item(19610, 1)), //Monkey Tail
			new WeightedChance<Item>(WeightedChance.RARE, new Item(19607, 1)), //Unstrung Heavy Ballista
			new WeightedChance<Item>(WeightedChance.RARE, new Item(12873, 1)), //Guthans Set
			new WeightedChance<Item>(WeightedChance.RARE, new Item(12875, 1)), //Veracs set
			new WeightedChance<Item>(WeightedChance.RARE, new Item(12877, 1)), //Dharoks Set
			new WeightedChance<Item>(WeightedChance.RARE, new Item(12879, 1)), //Torags set
			new WeightedChance<Item>(WeightedChance.RARE, new Item(12881, 1)), //Ahrims Set
			new WeightedChance<Item>(WeightedChance.RARE, new Item(12883, 1)), //Karils set
			new WeightedChance<Item>(WeightedChance.RARE, new Item(8942, 1)) //Monkey Pet
			

	));
	
	/**
	 * Opens the Mystery Box
	 * @param player
	 */
	public static void open(Player player) {
		player.send(new SendString("", 17015));	
		player.getInventory().remove(ELITE_BOX);
		//player.send(new SendString("</col>Gold points: @gre@" + Utility.format(player.getCredits()), 17006));
		//player.send(new SendString("Mystery Box is a gambling minigame where you can bet @gre@" + CREDITS_REQUIRED + " </col>Gold points.", 17008));
		player.send(new SendUpdateItemsAlt(CONTAINER_ID, -1, 0, 0));
		player.send(new SendInterface(INTERFACE_ID));
	}
	
	/**
	 * Check if player can play the Mystery Box
	 * @param player
	 * @return
	 */
	public static boolean can(Player player) {
		if (player.getInterfaceManager().main != INTERFACE_ID) {
			player.send(new SendRemoveInterfaces());
			return false;
		}
		if (player.getInventory().getFreeSlots() == 0) {
			player.send(new SendMessage(MESSAGE_COLOR + "You do not have enough inventory spaces to do this!"));
			return false;
		}
		if (player.playingMB) {
			player.send(new SendMessage(MESSAGE_COLOR + "Please wait before doing this!"));
			return false;
		}
		return true;
	}
	
	/**
	 * Handles playing the Mystery Box
	 * @param player
	 */
	public static void play(Player player) {
		if (!can(player)) {
			return;
		}
		
		player.playingMB = true;
	//	player.setCredits(player.getCredits() - CREDITS_REQUIRED);
		//player.send(new SendString("</col>Gold Points: @gre@" + Utility.format(player.getCredits()), 17006));
		TaskQueue.queue(new Task(player, 1, true) {
			int ticks = 10;
			int cycles = 0;
			
			@Override
			public void execute() {
				Item item = available.nextObject().get(); 
				player.send(new SendUpdateItemsAlt(CONTAINER_ID, item.getId(), item.getAmount(), 0));
				if (cycles++ == ticks) {
					reward(player, item);
					stop();
					player.send(new SendRemoveInterfaces());
				}
			}
			@Override
			public void onStop() {
				player.playingMB = false;
				player.send(new SendRemoveInterfaces());
			}
		});	
	}
	
	/**
	 * The reward for playing Mystery Box
	 * @param player
	 * @param itemWon
	 */
	public static void reward(Player player, Item itemWon) {
		ItemDefinition itemDef = GameDefinitionLoader.getItemDef(itemWon.getId());
	
		player.send(new SendMessage(MESSAGE_COLOR + "Congratulations! You have won " + Utility.getAOrAn(itemDef.getName()) + " " + itemDef.getName() + "!"));
		player.send(new SendString("Item won: " + itemWon.getDefinition().getName() + "!", 17015));
		player.getInventory().add(itemWon);
		
		if (itemWon.getDefinition().getGeneralPrice() >= EXPENSIVE_AMOUNT) {
			//World.sendGlobalMessage("[ " + MESSAGE_COLOR + "Mystery Box </col>] " + MESSAGE_COLOR + player.determineIcon(player) + " " + player.getUsername() + "</col> has just won " + Utility.getAOrAn(itemDef.getName()) + MESSAGE_COLOR +  " " + itemDef.getName() + "</col>!");
		}
	}
	
	/**
	 * Debug
	 * @param args
	 */
	public static void main(String[] args) {
		int common = 0;
		int uncommon = 0;
		int rare = 0;
		int very_rare = 0;
		double trials = 1_000_000.0;
		
		for (int i = 0; i < trials; i++) {
			WeightedObject<Item> item = available.nextObject();
			switch ((int) item.getWeight()) {
			
			case 10:
				common++;
				break;
				
			case 7:
				uncommon++;
				break;
				
			case 3:
				rare++;
				break;
				
			case 1:
				very_rare++;
				break;
			}
		}
		DecimalFormat formatter = new DecimalFormat("#.##");
		formatter.setRoundingMode(RoundingMode.DOWN);
		System.out.println("runs: " + trials);
		trials = (common + uncommon + rare + very_rare);
		System.out.println(formatter.format(common*100/trials) + "% - common: " + Utility.format(common));
		System.out.println(formatter.format(uncommon*100/trials) + "% - uncommon: " + Utility.format(uncommon));
		System.out.println(formatter.format(rare*100/trials) + "% - rares: " + Utility.format(rare));
		System.out.println(formatter.format(very_rare*100/trials) + "% - very rares: " + Utility.format(very_rare));
	}

}
