package com.dark.rs2.content.housing;

import java.io.IOException;

import com.dark.core.cache.map.RSObject;
import com.dark.core.cache.map.Region;
import com.dark.rs2.content.io.PlayerSave;
import com.dark.rs2.entity.object.GameObject;
import com.dark.rs2.entity.object.ObjectManager;
import com.dark.rs2.entity.player.Player;
import com.dark.rs2.entity.player.net.out.impl.SendMessage;

public class ObjectHandler extends RSObject {

	public ObjectHandler(Player player, int x, int y, int id, int face, boolean delete) {
	super(x, y, player.getZ() * 4, id, 10, face);
	if (delete) {
		Region.getRegion(getX(), getY()).removeObject(this);
		ObjectManager.getInstance().unregister(getGameObject());
		return;
	} else
		Region.getRegion(x, y).addObject(this);
	ObjectManager.getInstance().register(getGameObject());
	return;

	}

	public GameObject getGameObject() {
	return new GameObject(getId(), getX(), getY(), getZ(), getType(), getFace());
	}

	public void spawnObjects(Player player) {
	System.out.println("Spawning");
	for (HouseObject objects : ParseLoad.HOUSES.get(player.getUsername()).getObject()) {
		new ObjectHandler(player, objects.getX(), objects.getY(), objects.getObjectId(), objects.getFace(), false);
	}
	}

	public void removeObjects(Player player) {
	System.out.println("Deleting");
	for (HouseObject objects : ParseLoad.HOUSES.get(player.getUsername()).getObject()) {
		new ObjectHandler(player, objects.getX(), objects.getY(), objects.getObjectId(), objects.getFace(), true);
	}
	}

	public void moveObject(Player player, HouseObject oldObject, HouseObject newObject) {
	if (!player.objectInBuildingArea(newObject.getX(), newObject.getY())) {
		player.send(new SendMessage("You cannot move an object out of bounds."));
		return;
	}
	player.setHouseObjectHandler(new ObjectHandler(player, oldObject.getX(), oldObject.getY(), oldObject.getObjectId(), oldObject.getFace(), true));
	player.setHouseObjectHandler(new ObjectHandler(player, newObject.getX(), newObject.getY(), newObject.getObjectId(), newObject.getFace(), false));
	ParseLoad.HOUSES.get(player.getUsername()).getObject().remove(player.getObjectBeingEdited());
	ParseLoad.HOUSES.get(player.getUsername()).setObject(newObject);
	try {
		PlayerSave.save(player);
		ParseLoad.saveHouse(player);
		// player.getHouseManager().resetHouse(player);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}

	public void rotate(Player player, HouseObject oldObject, HouseObject newObject) {
	player.setHouseObjectHandler(new ObjectHandler(player, oldObject.getX(), oldObject.getY(), oldObject.getObjectId(), oldObject.getFace(), true));
	player.setHouseObjectHandler(new ObjectHandler(player, newObject.getX(), newObject.getY(), newObject.getObjectId(), newObject.getFace(), false));
	ParseLoad.HOUSES.get(player.getUsername()).getObject().remove(player.getObjectBeingEdited());
	ParseLoad.HOUSES.get(player.getUsername()).setObject(newObject);
	try {
		PlayerSave.save(player);
		ParseLoad.saveHouse(player);
		// player.getHouseManager().resetHouse(player);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}

	public void delete(Player player, HouseObject toDelete) {
	if (ParseLoad.HOUSES.get(player.getUsername()).getObject().contains(toDelete)) {
		ParseLoad.HOUSES.get(player.getUsername()).getObject().remove(toDelete);
		player.setHouseObjectHandler(new ObjectHandler(player, toDelete.getX(), toDelete.getY(), toDelete.getObjectId(), toDelete.getFace(), true));
		try {
			PlayerSave.save(player);
			ParseLoad.saveHouse(player);
			// player.getHouseManager().resetHouse(player);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	}
}
