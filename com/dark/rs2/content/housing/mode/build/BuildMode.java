package com.dark.rs2.content.housing.mode.build;

import java.io.IOException;

import com.dark.rs2.content.housing.HouseObject;
import com.dark.rs2.content.housing.ObjectHandler;
import com.dark.rs2.content.housing.ParseLoad;
import com.dark.rs2.content.housing.mode.CatagoryManager.CATAGORY;
import com.dark.rs2.entity.Location;
import com.dark.rs2.entity.object.GameObject;
import com.dark.rs2.entity.object.ObjectManager;
import com.dark.rs2.entity.player.Player;
import com.dark.rs2.entity.player.net.out.impl.SendMessage;
import com.dark.rs2.entity.player.net.out.impl.SendString;

public class BuildMode {

	private CATAGORY CURRENT;
	private int selectedId;

	public void loadList(CATAGORY type, Player player) {
	clearList(player);
	switch (type) {
	case BANK:
		loadBankObjects(type, player);
		break;
	case ALTAR:
		loadAltarObjects(type, player);
		break;
	case SKILLING:
		loadSkillObjects(type, player);
		break;
	case MISC:
		loadMiscObjects(type, player);
		break;
	}
	setCURRENT(type);
	}

	public void loadBankObjects(CATAGORY type, Player player) {
	for (int i = 0; i < type.getType().getObjectId().length; i++) {
		player.send(new SendString(type.getType().getObjectName(type.getType().getObjectId()[i][0]), 14031 + i));
	}
	}

	public void loadAltarObjects(CATAGORY type, Player player) {

	for (int i = 0; i < type.getType().getObjectId().length; i++) {
		player.send(new SendString(type.getType().getObjectName(type.getType().getObjectId()[i][0]), 14031 + i));
	}
	}

	public void loadSkillObjects(CATAGORY type, Player player) {
	for (int i = 0; i < type.getType().getObjectId().length; i++) {
		player.send(new SendString(type.getType().getObjectName(type.getType().getObjectId()[i][0]), 14031 + i));
	}
	}

	public void loadMiscObjects(CATAGORY type, Player player) {
	for (int i = 0; i < type.getType().getObjectId().length; i++) {
		player.send(new SendString(type.getType().getObjectName(type.getType().getObjectId()[i][0]), 14031 + i));
	}
	}

	public void clearList(Player player) {
	for (int i = 0; i < 25; i++)
		player.send(new SendString("", 14031 + i));
	}

	public String format(int amount) {
	String format = "";
	if (amount % 1000 == 0) {
		format = amount / 1000 + "k";
	}
	if (amount % 1000000 == 0) {
		format = amount / 1000000 + "m";
	}
	return format;
	}

	public boolean loadSelectedPrice(Player player, int actionId) {
	if (actionId > 54206 && actionId < 54219) {
		actionId = actionId - 54207;
		setSelectedId(actionId);
		player.send(new SendString("Cost: " + format(getCURRENT().getType().getObjectId()[actionId][1]), 14020));
		return true;

	}
	return false;
	}

	public boolean spawnObject(Player player, int actionId) {
	if (actionId == 54197) {
		if (player.getInventory().hasItemAmount(995, getCURRENT().getType().getObjectId()[getSelectedId()][1])) {
			player.getInventory().remove(995, getCURRENT().getType().getObjectId()[getSelectedId()][1]);
			player.setLastAdded(new HouseObject(getCURRENT().getType().getObjectId()[getSelectedId()][0], player.getX(), player.getY(), player.getIndex() << 2, 10, 0));
			player.getHouse().setObject(player.getLastAdded());
			player.setHouseObjectHandler(new ObjectHandler(player, player.getX(), player.getY(), player.getLastAdded().getObjectId(), player.getLastAdded().getFace(), false));
			try {
				ParseLoad.saveHouse(player);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			player.send(new SendMessage("You dont have enough coins to purchase this object."));
		}

		return true;
	}
	return false;
	}

	public boolean itemOnObject(Player player, int itemId, int objectId) {
	if (!player.isInHouse())
		return false;
	if (!player.getHouseMode().equals("Build")) {
		player.send(new SendMessage("You must be in build mode in order to edit objects."));
		return false;
	}
	for (HouseObject objects : ParseLoad.HOUSES.get(player.getUsername()).getObject()) {
		if (itemId == 2347 && objects.getObjectId() == objectId) {
			// send build dialouge
			player.setObjectBeingEdited(objects);
			player.start(new HammerOnObjectDialogue());
			return true;
		}
	}
	return false;
	}

	public CATAGORY getCURRENT() {
	return CURRENT;
	}

	public void setCURRENT(CATAGORY cURRENT) {
	CURRENT = cURRENT;
	}

	public int getSelectedId() {
	return selectedId;
	}

	public void setSelectedId(int selectedId) {
	this.selectedId = selectedId;
	}

}
