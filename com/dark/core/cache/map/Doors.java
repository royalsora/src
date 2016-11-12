package com.dark.core.cache.map;

import com.dark.Constants;
import com.dark.core.task.TaskQueue;
import com.dark.core.task.impl.TickDoorTask;
import com.dark.rs2.entity.object.GameObject;
import com.dark.rs2.entity.object.ObjectManager;
import com.dark.rs2.entity.player.Player;
import com.dark.rs2.entity.player.net.out.impl.SendSound;

public class Doors {

	public static final int[][] JAMMED_DOORS = { { 1852, 5212, 0 }, { 1832, 5224, 0 }, { 1832, 5225, 0 } };
        private static GameObject blankObj1 = null,blankObj2 = null;
	public static boolean clickDoor(int object, int x, int y, int z) {
		for (int i = 0; i < Constants.BLOCKED_DOORS.length; i ++) {
			if (object == Constants.BLOCKED_DOORS[i]) {
				return false;
			}
		}
		if (Region.isDoor(x, y, z)) {
			Door door = Region.getDoor(x, y, z);

			if (door == null) {
				return false;
			}

			if (door.getId() == 733 || door.getId() == 734) {
				return false;
			}

			if (door.getX() == 2998 && door.getY() == 3931 || door.getX() == 2997 && door.getY() == 3931 || door.getX() == 2998 && door.getY() == 3917 || door.getX() == 3044 && door.getY() == 3956 || door.getX() == 3041 && door.getY() == 3959 || door.getX() == 3038 && door.getY() == 3956 || door.getX() == 3190 && door.getY() == 3957 || door.getX() == 3191 && door.getY() == 3963) {
				return false;
			}

			if (z > 3) {
				z %= 4;
			}

			boolean original = door.original();

			if (original) {
				ObjectManager.getInstance().registerWithoutClipping(new GameObject(2376, x, y, z, door.getType(), door.getCurrentFace(), true));
			} else {
				ObjectManager.getInstance().registerWithoutClipping(new GameObject(2376, x, y, z, door.getType(), door.getCurrentFace(), true));
			}

			Region.getRegion(x, y).appendDoor(door.getCurrentId(), x, y, z);

			if (original) {
				ObjectManager.getInstance().unregister(new GameObject(2376, door.getX(), door.getY(), z, door.getType(), door.getCurrentFace(), false));
				ObjectManager.getInstance().registerWithoutClipping(new GameObject(door.getCurrentId(), door.getX(), door.getY(), z, door.getType(), door.getCurrentFace(), true));
			} else {
				ObjectManager.getInstance().registerWithoutClipping(new GameObject(door.getCurrentId(), door.getX(), door.getY(), z, door.getType(), door.getCurrentFace(), true));
			
				TaskQueue.queue(new TickDoorTask(door));
                        }

			return true;
		}

		if (Region.isDoubleDoor(x, y, z)) {
			DoubleDoor door = Region.getDoubleDoor(x, y, z);

			if (door == null) {
				return false;
			}

			if (door.getX1() == 2328 && door.getY1() == 3805 || door.getX1() == 2328 && door.getY1() == 3805) {
				return false;
			}

			if (door.getX1() == 2998 && door.getY1() == 3931 || door.getX1() == 2997 && door.getY1() == 3931) {
				return false;
			}

			if (z > 3) {
				z %= 4;
			}

                        blankObj1 = new GameObject(2376, door.getX1(), door.getY1(), z, door.getType(), door.getCurrentFace1(), false);
                        blankObj2 = new GameObject(2376, door.getX2(), door.getY2(), z, door.getType(), door.getCurrentFace2(), false);
			
                        ObjectManager.getInstance().registerWithoutClipping(blankObj1);
                        ObjectManager.getInstance().registerWithoutClipping(blankObj2);

			Region.getRegion(x, y).appendDoubleDoor(door.getCurrentId1(), door.getX1(), door.getY1(), z);

			ObjectManager.getInstance().registerWithoutClipping(new GameObject(door.getCurrentId1(), door.getX1(), door.getY1(), z, door.getType(), door.getCurrentFace1(), true));
			ObjectManager.getInstance().registerWithoutClipping(new GameObject(door.getCurrentId2(), door.getX2(), door.getY2(), z, door.getType(), door.getCurrentFace2(), true));

			return true;
		}

		return false;
	}

	public static boolean clickDoor(Player player, int object, int x, int y, int z) {

		if (clickDoor(object, x, y, z)) {
			player.getClient().queueOutgoingPacket(new SendSound(326, 0, 0));
			return true;
		}
		return false;
	}

	public static boolean isDoorJammed(Player player, int x, int y, int z) {
		for (int[] i : JAMMED_DOORS) {
			if ((x == i[0]) && (y == i[1]) && (z == i[2])) {
				return true;
			}
		}
		return false;
	}
}