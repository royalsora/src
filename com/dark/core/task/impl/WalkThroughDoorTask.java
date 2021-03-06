package com.dark.core.task.impl;

import com.dark.core.cache.map.Door;
import com.dark.core.cache.map.Region;
import com.dark.core.task.Task;
import com.dark.rs2.entity.Location;
import com.dark.rs2.entity.object.GameObject;
import com.dark.rs2.entity.object.ObjectManager;
import com.dark.rs2.entity.player.Player;
import com.dark.rs2.entity.player.controllers.Controller;
import com.dark.rs2.entity.player.controllers.ControllerManager;
import com.dark.rs2.entity.player.net.out.impl.SendSound;

public class WalkThroughDoorTask extends Task {

    protected final Player p;
    protected final Door door;
    protected final int xMod;
    protected final int yMod;
    protected byte stage = 0;
    protected final Controller start;
    private final Location dest;

    public WalkThroughDoorTask(Player p, int x, int y, int z, Location dest) {
        super(p, 1, true);
        this.p = p;
        this.door = Region.getDoor(x, y, z);
        start = p.getController();
        p.setController(ControllerManager.FORCE_MOVEMENT_CONTROLLER);
        p.getMovementHandler().setForceMove(true);

        xMod = dest.getX() - p.getLocation().getX();
        yMod = dest.getY() - p.getLocation().getY();

        this.dest = dest;

        if ((xMod != 0 && yMod != 0) || door == null) {
            p.setController(start);
            stop();
        }
    }

    @Override
    public void execute() {
        if (stage == 0) {
            p.getClient().queueOutgoingPacket(new SendSound(326, 0, 0));
            ObjectManager.getInstance().registerWithoutClipping(new GameObject(ObjectManager.BLANK_OBJECT_ID, door.getX(), door.getY(), door.getZ(), door.getType(), door.getCurrentFace()));
            door.append();
            ObjectManager.getInstance().registerWithoutClipping(new GameObject(door.getCurrentId(), door.getX(), door.getY(), door.getZ(), door.getType(), door.getCurrentFace()));
        } else if (stage == 1) {
            p.getMovementHandler().walkTo(xMod, yMod);
            p.setController(start);
        } else if (stage == 2) {
            ObjectManager.getInstance().registerWithoutClipping(new GameObject(ObjectManager.BLANK_OBJECT_ID, door.getX(), door.getY(), door.getZ(), door.getType(), door.getCurrentFace()));
            door.append();
            ObjectManager.getInstance().register(new GameObject(door.getCurrentId(), door.getX(), door.getY(), door.getZ(), door.getType(), door.getCurrentFace()));
            stop();
        }

        stage++;
    }

    @Override
    public void onStop() {
        p.setController(start);
        p.teleport(dest);
        p.getMovementHandler().setForceMove(false);
    }

}
