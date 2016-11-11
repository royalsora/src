package com.dark.rs2.content.housing.mode;

import com.dark.core.cache.map.ObjectDef;
import com.dark.rs2.entity.player.Player;

public class CatagoryManager {

	public enum CATAGORY {
		BANK(CatagoryObjects.BANK),
		ALTAR(CatagoryObjects.ALTAR),
		SKILLING(CatagoryObjects.SKILLING),
		MISC(CatagoryObjects.MISC);

		private CatagoryObjects type;

		CATAGORY(CatagoryObjects type) {
		this.type = type;
		}

		public CatagoryObjects getType() {
		return type;
		}

		public void setCatagory(CatagoryObjects type) {
		this.type = type;
		}
	}

	public enum CatagoryObjects {
		BANK(new int[][] { { 10517, 3000000 } }),
		ALTAR(new int[][] { { 410, 500000 }, { 409, 1000000 }, { 6552, 3000000 } }),
		SKILLING(new int[][] { { 8151, 50_000_000 } }),
		MISC(new int[][] { { 10504, 1000 }, { 10503, 50000 }, { 26796, 90000 }, { 10491, 40000 } });

		CatagoryObjects(int[][] objectId) {
		this.objectId = objectId;
		}

		private int[][] objectId;

		public int[][] getObjectId() {
		return objectId;
		}

		public String getObjectName(int objectId) {
		if (objectId == 8151) {
			return "Herb patch";
		}
		return ObjectDef.getObjectDef(objectId).name;
		}
	}

	public static boolean switchCatagory(Player player, int id) {
	switch (id) {
	case 54178:
		player.getBuildMode().loadList(CATAGORY.BANK, player);
		return true;
	case 54181:
		player.getBuildMode().loadList(CATAGORY.ALTAR, player);
		return true;
	case 54184:
		player.getBuildMode().loadList(CATAGORY.SKILLING, player);
		return true;
	case 54187:
		player.getBuildMode().loadList(CATAGORY.MISC, player);
		return true;
	}
	return false;
	}

}
