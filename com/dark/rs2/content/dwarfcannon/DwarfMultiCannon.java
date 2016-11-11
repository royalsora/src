package com.dark.rs2.content.dwarfcannon;

import com.dark.core.cache.map.Region;
import com.dark.core.task.TaskQueue;
import com.dark.core.task.impl.ForceMovementTask;
import com.dark.rs2.entity.Location;
import com.dark.rs2.entity.player.Player;
import com.dark.rs2.entity.player.controllers.ControllerManager;
import com.dark.rs2.entity.player.net.out.impl.SendMessage;

public class DwarfMultiCannon {

	public static final int CANNONBALL_ITEM_ID = 12;
	public static final int BASE_ITEM_ID = 16;
	public static final int STAND_ITEM_ID = 18;
	public static final int BARRELS_ITEM_ID = 110;
	public static final int FURNACE_ITEM_ID = 112;
	public static final int DWARF_MULTI_CANNON_OBJECT_ID = 16;
	public static final int CANNON_BASE_OBJECT_ID = 17;
	public static final int CANNON_STAND_OBJECT_ID = 18;
	public static final int CANNON_BARRELS_OBJECT_ID = 19;
	public static final String CANNON_ATTRIBUTE_KEY = "dwarfmulticannon";

	public static DwarfCannon getCannon(Player player) {
		return (DwarfCannon) player.getAttributes().get("dwarfmulticannon");
	}

	public static boolean hasCannon(Player player) {
		return player.getAttributes().get("dwarfmulticannon") != null;
	}

	public static final boolean isCannonSetupClear(Player player) {
		if (!player.getController()
				.equals(ControllerManager.DEFAULT_CONTROLLER)) {
			return false;
		}

		int x = player.getLocation().getX();
		int y = player.getLocation().getY();
		int z = player.getLocation().getZ();

		Region r = Region.getRegion(x, y);

		for (int i = 0; i < 8; i++) {
			if (!r.canMove(x, y, z, i)) {
				return false;
			}
		}

		int x2 = x + com.dark.rs2.GameConstants.DIR[3][0];
		int y2 = y + com.dark.rs2.GameConstants.DIR[3][1];

		if (!Region.getRegion(x2, y2).canMove(x2, y2, z, 3)) {
			return false;
		}

		return true;
	}

	public static final boolean setCannonBase(Player player, int id) {
		if (id == 6) {
			if (isCannonSetupClear(player)) {
				final Location l = new Location(player.getLocation());
				TaskQueue.queue(new ForceMovementTask(player, new Location(l
						.getX() - 2, l.getY()),
						ControllerManager.DEFAULT_CONTROLLER) {
					@Override
					public void onDestination() {
						if (player.getInventory().hasItemId(6)) {
							player.getInventory().remove(6);
							player.getAttributes().set(
									"dwarfmulticannon",
									new DwarfCannon(player, l.getX(), l.getY(), l
											.getZ()));
						}
					}
				});
			} else {
				player.getClient().queueOutgoingPacket(
						new SendMessage("You cannot setup your cannon here."));
			}

			return true;
		}

		return false;
	}
}
