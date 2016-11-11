package com.dark.core.task.impl;

import com.dark.core.cache.map.Door;
import com.dark.core.task.Task;

public class TickDoorTask extends Task {

	public TickDoorTask(Door door) {
		super(null, 1);
		if (door.original()) {
			stop();
			return;
		}
	}

	@Override
	public void execute() {

		stop();
	}

	@Override
	public void onStop() {
	}

}
