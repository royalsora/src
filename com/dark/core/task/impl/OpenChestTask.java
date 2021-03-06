package com.dark.core.task.impl;

import com.dark.core.cache.map.RSObject;
import com.dark.core.cache.map.Region;
import com.dark.core.task.Task;
import com.dark.rs2.entity.object.GameObject;
import com.dark.rs2.entity.object.ObjectManager;
import com.dark.rs2.entity.player.Player;
import com.dark.rs2.entity.player.net.out.impl.SendMessage;

public abstract class OpenChestTask extends Task {

	private final int x;
	private final int y;
	private final int z;
	private final int type;
	private final int face;
	private final int replace;
	private byte stage = 0;

	public OpenChestTask(Player p, int x, int y, int z, int replace, int type, int face) {
		super(p, 1);
		this.x = x;
		this.y = y;
		this.z = z;
		this.type = type;
		this.face = face;
		this.replace = replace;

		p.getClient().queueOutgoingPacket(new SendMessage("You open the chest.."));
		p.getUpdateFlags().sendAnimation(832, 0);
	}

	@Override
	public void execute() {
		if (stage == 0) {
			GameObject o = new GameObject(replace, x, y, z, type, face);
			//ObjectManager.getInstance().unregister(o);
			ObjectManager.getInstance().register(o);
			Region.getRegion(x, y).addObject(new RSObject(x, y, z, replace, type, face));
		} else if (stage == 2) {
			stop();
		}

		stage++;
	}
}
