package com.dark.rs2.content.housing.mode.build;

import com.dark.rs2.content.housing.HouseObject;
import com.dark.rs2.content.housing.ParseLoad;
import com.dark.rs2.entity.player.Player;
import com.dark.rs2.entity.player.net.out.impl.SendRemoveInterfaces;

public class MoveObject {

	public void moveUp(Player player, int incrament) {
	if (ParseLoad.HOUSES.containsKey(player.getUsername())) {
		HouseObject oldObject = new HouseObject(player.getObjectBeingEdited().getObjectId(), player.getObjectBeingEdited().getX(), player.getObjectBeingEdited().getY(), player.getObjectBeingEdited().getZ(), 10, player.getObjectBeingEdited().getFace());
		HouseObject newObject = new HouseObject(player.getObjectBeingEdited().getObjectId(), player.getObjectBeingEdited().getX(), player.getObjectBeingEdited().getY() + incrament, player.getObjectBeingEdited().getZ(), 10, player.getObjectBeingEdited().getFace());
		player.getHouseObjectHandler().moveObject(player, oldObject, newObject);
	}

	player.send(new SendRemoveInterfaces());
	}

	public void moveDown(Player player, int incrament) {
	if (ParseLoad.HOUSES.containsKey(player.getUsername())) {
		HouseObject oldObject = new HouseObject(player.getObjectBeingEdited().getObjectId(), player.getObjectBeingEdited().getX(), player.getObjectBeingEdited().getY(), player.getObjectBeingEdited().getZ(), 10, player.getObjectBeingEdited().getFace());
		HouseObject newObject = new HouseObject(player.getObjectBeingEdited().getObjectId(), player.getObjectBeingEdited().getX(), player.getObjectBeingEdited().getY() - incrament, player.getObjectBeingEdited().getZ(), 10, player.getObjectBeingEdited().getFace());
		player.getHouseObjectHandler().moveObject(player, oldObject, newObject);
	}

	player.send(new SendRemoveInterfaces());
	}

	public void moveLeft(Player player, int incrament) {
	if (ParseLoad.HOUSES.containsKey(player.getUsername())) {
		HouseObject oldObject = new HouseObject(player.getObjectBeingEdited().getObjectId(), player.getObjectBeingEdited().getX(), player.getObjectBeingEdited().getY(), player.getObjectBeingEdited().getZ(), 10, player.getObjectBeingEdited().getFace());
		HouseObject newObject = new HouseObject(player.getObjectBeingEdited().getObjectId(), player.getObjectBeingEdited().getX() - incrament, player.getObjectBeingEdited().getY(), player.getObjectBeingEdited().getZ(), 10, player.getObjectBeingEdited().getFace());
		player.getHouseObjectHandler().moveObject(player, oldObject, newObject);

	}

	player.send(new SendRemoveInterfaces());
	}

	public void moveRight(Player player, int incrament) {
	if (ParseLoad.HOUSES.containsKey(player.getUsername())) {
		HouseObject oldObject = new HouseObject(player.getObjectBeingEdited().getObjectId(), player.getObjectBeingEdited().getX(), player.getObjectBeingEdited().getY(), player.getObjectBeingEdited().getZ(), 10, player.getObjectBeingEdited().getFace());
		HouseObject newObject = new HouseObject(player.getObjectBeingEdited().getObjectId(), player.getObjectBeingEdited().getX() + incrament, player.getObjectBeingEdited().getY(), player.getObjectBeingEdited().getZ(), 10, player.getObjectBeingEdited().getFace());
		player.getHouseObjectHandler().moveObject(player, oldObject, newObject);
	}
	player.send(new SendRemoveInterfaces());
	}

	public void rotateFace(Player player, String type) {

	switch (type) {
	case "North":
		HouseObject oldObject = new HouseObject(player.getObjectBeingEdited().getObjectId(), player.getObjectBeingEdited().getX(), player.getObjectBeingEdited().getY(), player.getObjectBeingEdited().getZ(), player.getObjectBeingEdited().getFace());
		player.getObjectBeingEdited().setFace(0);
		HouseObject newObject = new HouseObject(player.getObjectBeingEdited().getObjectId(), player.getObjectBeingEdited().getX(), player.getObjectBeingEdited().getY(), player.getObjectBeingEdited().getZ(), player.getObjectBeingEdited().getFace());
		player.getHouseObjectHandler().rotate(player, oldObject, newObject);
		break;
	case "South":
		HouseObject oldObject1 = new HouseObject(player.getObjectBeingEdited().getObjectId(), player.getObjectBeingEdited().getX(), player.getObjectBeingEdited().getY(), player.getObjectBeingEdited().getZ(), player.getObjectBeingEdited().getFace());
		player.getObjectBeingEdited().setFace(2);
		HouseObject newObject1 = new HouseObject(player.getObjectBeingEdited().getObjectId(), player.getObjectBeingEdited().getX(), player.getObjectBeingEdited().getY(), player.getObjectBeingEdited().getZ(), player.getObjectBeingEdited().getFace());
		player.getHouseObjectHandler().rotate(player, oldObject1, newObject1);

		break;
	case "East":
		HouseObject oldObject2 = new HouseObject(player.getObjectBeingEdited().getObjectId(), player.getObjectBeingEdited().getX(), player.getObjectBeingEdited().getY(), player.getObjectBeingEdited().getZ(), player.getObjectBeingEdited().getFace());
		player.getObjectBeingEdited().setFace(1);
		HouseObject newObject2 = new HouseObject(player.getObjectBeingEdited().getObjectId(), player.getObjectBeingEdited().getX(), player.getObjectBeingEdited().getY(), player.getObjectBeingEdited().getZ(), player.getObjectBeingEdited().getFace());
		player.getHouseObjectHandler().rotate(player, oldObject2, newObject2);

		break;
	case "West":
		HouseObject oldObject3 = new HouseObject(player.getObjectBeingEdited().getObjectId(), player.getObjectBeingEdited().getX(), player.getObjectBeingEdited().getY(), player.getObjectBeingEdited().getZ(), player.getObjectBeingEdited().getFace());
		player.getObjectBeingEdited().setFace(3);
		HouseObject newObject3 = new HouseObject(player.getObjectBeingEdited().getObjectId(), player.getObjectBeingEdited().getX(), player.getObjectBeingEdited().getY(), player.getObjectBeingEdited().getZ(), player.getObjectBeingEdited().getFace());
		player.getHouseObjectHandler().rotate(player, oldObject3, newObject3);
		break;
	}
	player.send(new SendRemoveInterfaces());
	}
}
