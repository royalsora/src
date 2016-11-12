package com.dark.rs2.content.skill.firemaking;

import com.dark.core.task.Task;
import com.dark.core.task.impl.TaskIdentifier;
import com.dark.rs2.entity.item.Item;
import com.dark.rs2.entity.item.impl.GroundItemHandler;
import com.dark.rs2.entity.object.GameObject;
import com.dark.rs2.entity.object.ObjectManager;
import com.dark.rs2.entity.player.Player;

public class FireTask extends Task {
	private final GameObject object;
	private final Player p;

	public FireTask(Player p, int cycles, GameObject object) {
		super(cycles, false, Task.StackType.STACK, Task.BreakType.NEVER, TaskIdentifier.CURRENT_ACTION);
		this.object = object;
		ObjectManager.getInstance().register(object);
		this.p = p;
	}

	@Override
	public void execute() {
		ObjectManager.getInstance().unregister(object);
		GroundItemHandler.add(new Item(592, 1), object.getLocation(), p, p.ironPlayer() ? p : null);
		stop();
	}

	@Override
	public void onStop() {
	}
}
