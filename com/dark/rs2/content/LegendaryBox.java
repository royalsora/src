package com.dark.rs2.content;

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
public class LegendaryBox {

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
	
	private final static Item LEGEND_BOX = new Item(7959);
	/**
	 * All available items to win
	 */
	public static Chance<Item> available = new Chance<Item>(Arrays.asList(
			new WeightedChance<Item>(WeightedChance.VERY_RARE, new Item(12922, 1)), //Tanzanite fang
			new WeightedChance<Item>(WeightedChance.VERY_RARE, new Item(12932, 1)), //Magic fang
			new WeightedChance<Item>(WeightedChance.VERY_RARE, new Item(12927, 1)), //Serpentine Visage
			new WeightedChance<Item>(WeightedChance.COMMON, new Item(2944, 3)), //Golden Key
			new WeightedChance<Item>(WeightedChance.COMMON, new Item(995, 5000000)), //Coins
			new WeightedChance<Item>(WeightedChance.COMMON, new Item(7789, 1)), //Slayer Tome
			new WeightedChance<Item>(WeightedChance.COMMON, new Item(7783, 1)), //Agility Tome
			new WeightedChance<Item>(WeightedChance.COMMON, new Item(7798, 1)), //Woodcutting Tome
			new WeightedChance<Item>(WeightedChance.COMMON, new Item(7795, 1)), //Firemaking Tome
			new WeightedChance<Item>(WeightedChance.COMMON, new Item(7792, 1)), //Mining Tome
			new WeightedChance<Item>(WeightedChance.COMMON, new Item(7786, 1)), //Thieving Tome
			new WeightedChance<Item>(WeightedChance.COMMON, new Item(7780, 1)), //Fishing Tome
			new WeightedChance<Item>(WeightedChance.COMMON, new Item(608, 2)), //Credit Scrolls
			new WeightedChance<Item>(WeightedChance.RARE, new Item(19544, 1)), 
			new WeightedChance<Item>(WeightedChance.RARE, new Item(749, 1)), //Zul-Andra Totem
			new WeightedChance<Item>(WeightedChance.RARE, new Item(19607, 1)), //Unstrung Heavy Ballista
			new WeightedChance<Item>(WeightedChance.RARE, new Item(19610, 1)), //Monkey Tail
			new WeightedChance<Item>(WeightedChance.RARE, new Item(12528, 1)), //Light Infinity Kit
			new WeightedChance<Item>(WeightedChance.RARE, new Item(12530, 1)), //Dark Infinity kit
			new WeightedChance<Item>(WeightedChance.RARE, new Item(13092, 1)), //Crystal halberd
			new WeightedChance<Item>(WeightedChance.RARE, new Item(11898, 1)), //Decorative Hat
			new WeightedChance<Item>(WeightedChance.RARE, new Item(11896, 1)), //Decorative Robetop
			new WeightedChance<Item>(WeightedChance.RARE, new Item(11897, 1)), //Decorative Robelegs
			new WeightedChance<Item>(WeightedChance.VERY_RARE, new Item(19529, 1)), //Zenyte shard
			new WeightedChance<Item>(WeightedChance.VERY_RARE, new Item(13190, 1)), //5$ bond
			new WeightedChance<Item>(WeightedChance.VERY_RARE, new Item(13679, 1)), //Cabbage Cape
			new WeightedChance<Item>(WeightedChance.VERY_RARE, new Item(13274, 1)), //Bludgeon Spine
			new WeightedChance<Item>(WeightedChance.VERY_RARE, new Item(13275, 1)), //Bludgeon Claw
			new WeightedChance<Item>(WeightedChance.VERY_RARE, new Item(13276, 1)), //Bludgeon Axon
			new WeightedChance<Item>(WeightedChance.VERY_RARE, new Item(13222, 1)), //Music cape
			new WeightedChance<Item>(WeightedChance.VERY_RARE, new Item(13182, 1)), //Bunny feet
			new WeightedChance<Item>(WeightedChance.VERY_RARE, new Item(1037, 1)), //Bunny ears
			new WeightedChance<Item>(WeightedChance.VERY_RARE, new Item(11900, 1)), //Decorative Legs
			new WeightedChance<Item>(WeightedChance.VERY_RARE, new Item(11899, 1)), //Decorative Top
			new WeightedChance<Item>(WeightedChance.VERY_RARE, new Item(11901, 1)), //Decorative Quiver
			new WeightedChance<Item>(WeightedChance.VERY_RARE, new Item(12873, 1)), //Guthans Set
			new WeightedChance<Item>(WeightedChance.VERY_RARE, new Item(12875, 1)), //Veracs set
			new WeightedChance<Item>(WeightedChance.VERY_RARE, new Item(12877, 1)), //Dharoks Set
			new WeightedChance<Item>(WeightedChance.VERY_RARE, new Item(12879, 1)), //Torags set
			new WeightedChance<Item>(WeightedChance.VERY_RARE, new Item(12881, 1)), //Ahrims Set
			new WeightedChance<Item>(WeightedChance.VERY_RARE, new Item(12883, 1)), //Karils set
			new WeightedChance<Item>(WeightedChance.VERY_RARE, new Item(962, 1)), //Christmas Cracker
			new WeightedChance<Item>(WeightedChance.VERY_RARE, new Item(1050, 1)), //Santa Hat
			new WeightedChance<Item>(WeightedChance.VERY_RARE, new Item(13343, 1)), //Black Santa Hat
			new WeightedChance<Item>(WeightedChance.VERY_RARE, new Item(19675, 1)), //Arclight
			new WeightedChance<Item>(WeightedChance.VERY_RARE, new Item(13239, 1)), //Primordial Boots
			new WeightedChance<Item>(WeightedChance.VERY_RARE, new Item(13237, 1)), //Pegasian Boots
			new WeightedChance<Item>(WeightedChance.VERY_RARE, new Item(13239, 1)), //Eternal Boots
			new WeightedChance<Item>(WeightedChance.VERY_RARE, new Item(12831, 1)), //BSS
			new WeightedChance<Item>(WeightedChance.VERY_RARE, new Item(13271, 1))  // Abyssal Dagger
			

	));
	
	/**
	 * Opens the Mystery Box
	 * @param player
	 */
	public static void open(Player player) {
		player.send(new SendString("", 17015));	
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
		//	World.sendGlobalMessage("[ " + MESSAGE_COLOR + "Mystery Box </col>] " + MESSAGE_COLOR + player.determineIcon(player) + " " + player.getUsername() + "</col> has just won " + Utility.getAOrAn(itemDef.getName()) + MESSAGE_COLOR +  " " + itemDef.getName() + "</col>!");
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
