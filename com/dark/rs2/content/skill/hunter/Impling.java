package com.dark.rs2.content.skill.hunter;

import java.util.HashMap;
import java.util.Random;

import com.dark.core.util.Utility;
import com.dark.rs2.entity.Animation;
import com.dark.rs2.entity.Location;
import com.dark.rs2.entity.item.Item;
import com.dark.rs2.entity.mob.Mob;
import com.dark.rs2.content.achievements.AchievementHandler;
import com.dark.rs2.content.achievements.AchievementList;
import com.dark.rs2.entity.player.Player;
import com.dark.rs2.entity.player.net.out.impl.SendMessage;

/**
 * Manages the functionality of parts of the hunter skill
 * 
 * @author Valiant - http://www.rune-server.org/members/valiant
 * @since April 19th, 2015
 * 
 */
public class Impling {

	/**
	 * The Random Modifier
	 */
	public static final Random random = new Random();

	/** Impling Loot table data */
	public enum ImplingRewards {

		BABY(11238, new int[][] { { 1755, 1 }, { 1734, 1 }, { 1733, 1 }, { 946, 1 }, { 1985, 1 }, { 2347, 1 }, { 1759, 1 }, { 1927, 1 }, { 319, 1 }, { 2007, 1 }, { 1779, 1 }, { 7170, 1 }, { 1438, 1 }, { 2355, 1 }, { 1607, 1 }, { 1743, 1 }, { 379, 1 }, { 1761, 1 }, { 11273, 1 } }),

		YOUNG(11240, new int[][] { { 361, 1 }, { 1902, 1 }, { 1539, 5 }, { 1524, 1 }, { 7936, 1 }, { 855, 1 }, { 1353, 1 }, { 2293, 1 }, { 7178, 1 }, { 247, 1 }, { 453, 1 }, { 1777, 1 }, { 231, 1 }, { 1761, 1 }, { 8778, 1 }, { 133, 1 }, { 2359, 1 }, { 11273, 1 } }),

		GOURMENT(11242, new int[][] { { 365, 1 }, { 361, 1 }, { 2011, 1 }, { 1897, 1 }, { 2327, 1 }, { 2007, 1 }, { 5970, 1 }, { 380, 4 }, { 7179, 1, 5 }, { 386, 3 }, { 1883, 1 }, { 3145, 2 }, { 5755, 1 }, { 5406, 1 }, { 10137, 5 } }),

		EARTH(11244, new int[][] { { 6033, 6 }, { 1440, 1 }, { 5535, 1 }, { 557, 32 }, { 1442, 1 }, { 1784, 4 }, { 1273, 1 }, { 447, 1 }, { 1606, 2 }, { 11273, 1 } }),

		ESSENCE(11246, new int[][] { { 7937, 50 }, { 555, 30 }, { 556, 30 }, { 558, 25 }, { 559, 28 }, { 562, 4 }, { 1448, 1 }, { 564, 4 }, { 563, 13 }, { 565, 7 }, { 566, 11 }, { 11273, 1 } }),

		ECLECTIC(11248, new int[][] { { 1273, 1 }, { 5970, 1 }, { 231, 1 }, { 556, 30, 47 }, { 8779, 4 }, { 1199, 1 }, { 4527, 1 }, { 444, 1 }, { 2358, 5 }, { 7937, 20, 35 }, { 237, 1 }, { 2493, 1 }, { 10083, 1 }, { 1213, 1 }, { 450, 10 }, { 5760, 2 }, { 7208, 1 }, { 5321, 3 }, { 1391, 1 }, { 1601, 1 } }),
		NATURE(11250, new int[][] { { 5100, 1 }, { 5104, 1 }, { 5281, 1 }, { 5294, 1 }, { 6016, 1 }, { 1513, 1 }, { 254, 4 }, { 5313, 1 }, { 5286, 1 }, { 5285, 1 }, { 3000, 1 }, { 5974, 1 }, { 5297, 1 }, { 5299, 1 }, { 5298, 5 }, { 5304, 1 }, { 5295, 1 }, { 270, 2 }, { 5303, 1 }, { 11273, 1 } }),
		MAGPIE(11252, new int[][] { { 1701, 3 }, { 1732, 3 }, { 2569, 3 }, { 3391, 1 }, { 4097, 1 }, { 5541, 1 }, { 1747, 6 }, { 1347, 1 }, { 2571, 4 }, { 4095, 1 }, { 2364, 2 }, { 1215, 1 }, { 1185, 1 }, { 1602, 4 }, { 5287, 1 }, { 987, 1 }, { 985, 1 }, { 993, 1 }, { 5300, 1 }, { 11273, 1 } }),
		NINJA(11254, new int[][] { { 4097, 1 }, { 3385, 1 }, { 892, 70 }, { 140, 4 }, { 1748, 10, 16 }, { 1113, 1 }, { 1215, 1 }, { 1333, 1 }, { 1347, 1 }, { 9342, 2 }, { 5938, 4 }, { 6156, 3 }, { 9194, 4 }, { 6313, 1 }, { 805, 50 }, { 11273, 1 } }),
		DRAGON(11256, new int[][] { { 11212, 10, 30 }, { 9341, 1, 20 }, { 527, 15 }, { 11232, 5, 15 }, { 11237, 2, 16 }, { 9193, 2, 30 }, { 535, 4, 18 }, { 1216, 1 }, { 11230, 5, 30 }, { 5316, 1 }, { 537, 2, 10 }, { 1616, 1, 3 }, { 1705, 1, 2 }, { 5300, 2 }, { 7219, 5, 15 }, { 4093, 1 }, { 5547, 1 }, { 4587, 1 }, { 1701, 1, 2 }, { 11273, 1 } });


		public static HashMap<Integer, ImplingRewards> impReward = new HashMap<>();

		static {
			for (ImplingRewards t : ImplingRewards.values()) {
				impReward.put(t.itemId, t);
			}
		}

		private int itemId;
		private int[][] rewards;

		ImplingRewards(int itemId, int[][] rewards) {
			this.itemId = itemId;
			this.rewards = rewards;
		}

		public int[][] getLoot() {
			return rewards;
		}

		/**
		 * The looing of an impling jar
		 * 
		 * @param player
		 *            the player opening the jar
		 * @param itemId
		 *            the itemId of loot recieved
		 */
		public static void lootImpling(Player player, int itemId) {
			if (!player.getInventory().hasSpaceFor(new Item(itemId))) {
				player.getClient().queueOutgoingPacket(new SendMessage("You don't have enough room to loot the impling."));
				return;
			}
			ImplingRewards t = ImplingRewards.impReward.get(itemId);
			player.getInventory().remove(new Item(itemId));
			player.getInventory().getItemSlot(itemId);
			int r = random.nextInt(t.getLoot().length);
			if (Utility.random(150) == 1) {
				player.getInventory().add(new Item(608, 1));
				player.send(new SendMessage("You Recieved a Credit Scroll For Hunting!."));
			}
			if (t.getLoot()[r].length == 3) {
				int amount = t.getLoot()[r][1] + random.nextInt(t.getLoot()[r][2] - t.getLoot()[r][1]);
				player.getInventory().add(new Item(t.getLoot()[r][0], amount));
			} else {
				player.getInventory().add(new Item(t.getLoot()[r][0], t.getLoot()[r][1]));
			}
			if (Utility.randomNumber(15) == 0) {
				player.getClient().queueOutgoingPacket(new SendMessage("The jar breaks as you open it."));
			} else {
				player.getInventory().add(new Item(11260));
				player.getClient().queueOutgoingPacket(new SendMessage("You loot the impling jar."));
			}
		}

		/** Catachable Impling data */
		public enum Implings {

			BABY_IMPLING(1635, 1, 25, 11238),
			YOUNG_IMPLING(1636, 15, 65, 11240),
			GOURMET_IMPLING(1637, 28, 113, 11242),
			EARTH_IMPLING(1638, 36, 177, 11244),
			ESSENCE_IMPLING(1639, 42, 255, 11246),
			ECLECTIC_IMPLING(1640, 50, 289, 11248),
			NATURE_IMPLING(1641, 58, 353, 11250),
			MAGPIE_IMPLING(1642, 65, 409, 11252),
			NINJA_IMPLING(1643, 74, 481, 11254),
			DRAGON_IMPLING(1644, 83, 553, 11256),
			SCROLL(194, 1, 553, 11273);

			public static HashMap<Integer, Implings> implings = new HashMap<>();

			static {
				for (Implings t : Implings.values()) {
					implings.put(t.npc, t);
				}
			}

			private int npc;
			private int levelRequired;
			private int experience;
			private int itemId;

			Implings(final int npc, final int levelRequired, final int experience, final int itemId) {
				this.npc = npc;
				this.levelRequired = levelRequired;
				this.experience = experience;
				this.itemId = itemId;
			}

			public int getImpId() {
				return npc;
			}

			public int getLevelRequired() {
				return levelRequired;
			}

			public int getXp() {
				return experience;
			}

			public int getJar() {
				return itemId;
			}

			public static Implings forId(int id) {
				return implings.get(id);
			}

			/**
			 * The catching of implings around the game
			 * 
			 * @param player
			 *            the player catching the impling
			 * @param impling
			 *            the impling mob being caught
			 */
			public static void catchImp(Player player, Mob impling) {
				if (player.getEquipment().getItems()[3] == null) {
					return;
				}
				Implings t = Implings.implings.get(impling.getId());

				if (!player.getInventory().hasSpaceFor(new Item(t.getJar()))) {
					player.getClient().queueOutgoingPacket(new SendMessage("You don't have enough room to do this."));
					return;
				}

				if (player.getEquipment().getItems()[3].getId() != 10010) {
					player.getClient().queueOutgoingPacket(new SendMessage("You need a butterfly net to catch this impling."));
					return;
				}

				if (player.getSkill().getLevels()[22] < t.getLevelRequired()) {
					player.getClient().queueOutgoingPacket(new SendMessage("You need a hunter level of " + t.getLevelRequired() + " to catch this impling."));
					return;
				}

				if (!player.getInventory().hasItemId(new Item(11260))) {
					player.getClient().queueOutgoingPacket(new SendMessage("You need an impling jar to catch and store the impling."));
					return;
				}
				if (Utility.randomNumber(25) == 0) {
					player.getUpdateFlags().sendAnimation(new Animation(828));
					player.getClient().queueOutgoingPacket(new SendMessage("You fail to catch the impling."));
				} else {
					player.getUpdateFlags().sendAnimation(new Animation(828));
					player.getClient().queueOutgoingPacket(new SendMessage("You catch the impling and place it in the jar."));
					player.getInventory().add(t.getJar(), 1);
					player.getInventory().remove(11260, 1);
					AchievementHandler.activateAchievement(player, AchievementList.CATCH_25_IMPLINGS, 1);
					AchievementHandler.activateAchievement(player, AchievementList.CATCH_100_IMPLINGS, 1);
					player.getSkill().addExperience(22, t.getXp());
					player.getSkill().update();
					teleportImpling(impling);
				}
			}
		}
	}

	/**
	 * Teleports a caught impling to another location
	 * 
	 * @param impling
	 *            the impling being teleported
	 */
	private static void teleportImpling(Mob impling) {
		int location[][] = { { 3741, 3004 }, { 3744, 3006 }, { 3745, 3004 }, { 3744, 3002 }, { 3747, 3000 }, { 3738, 3001 }, { 3740, 3002 }, { 3741, 2999 }, { 3744, 2997 }, { 3748, 2996 }, { 3746, 2993 }, { 3751, 2994 }, { 3754, 2998 }, { 3754, 3002 }, { 3756, 3003 }, { 3757, 3000 }, { 3737, 2994 }, { 3739, 2991 }, { 3734, 2991 }, { 3729, 2994 }, { 3726, 3000 }, { 3725, 3003 }, { 3723, 3008 }, { 3747, 2997 }, { 3744, 2994 }, { 3743, 2991 }, { 3740, 2989 }, { 3736, 2989 }, { 3732, 2994 }, { 3735, 2998 }, { 3730, 2997 }, { 3734, 3006 }, { 3739, 3006 }};
		int random_location = Utility.randomNumber(location.length - 1);
		impling.teleport(new Location(location[random_location][0], location[random_location][1]));
	}

	/**
	 * Manages the spawning of implings upon server startup
	 * 
	 * @param impling
	 *            the impling being created
	 */
	public static void appendImpling() {
		for (int i = 0; i < 20; i++) {
			int implings[] = { 1635, 1636, 1637, 1638, 1639, 1640, 1641, 1642, 1643, 1644 };
			int location[][] = { { 3741, 3004 }, { 3744, 3006 }, { 3745, 3004 }, { 3744, 3002 }, { 3747, 3000 }, { 3738, 3001 }, { 3740, 3002 }, { 3741, 2999 }, { 3744, 2997 }, { 3748, 2996 }, { 3746, 2993 }, { 3751, 2994 }, { 3754, 2998 }, { 3754, 3002 }, { 3756, 3003 }, { 3757, 3000 }, { 3737, 2994 }, { 3739, 2991 }, { 3734, 2991 }, { 3729, 2994 }, { 3726, 3000 }, { 3725, 3003 }, { 3723, 3008 }, { 3747, 2997 }, { 3744, 2994 }, { 3743, 2991 }, { 3740, 2989 }, { 3736, 2989 }, { 3732, 2994 }, { 3735, 2998 }, { 3730, 2997 }, { 3734, 3006 }, { 3739, 3006 }};
			int npc = Utility.randomNumber(implings.length - 1);
			int random_location = Utility.randomNumber(location.length - 1);
			new Mob(implings[npc], true, new Location(location[random_location][0], location[random_location][1]));
		}
	}
}