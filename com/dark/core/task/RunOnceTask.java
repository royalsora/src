package com.dark.core.task;

import com.dark.rs2.entity.Entity;

public abstract class RunOnceTask extends Task {

	public RunOnceTask(Entity entity, int delay) {
		super(entity, delay);
	}

	@Override
	public void execute() {
		stop();
	}

	@Override
	public abstract void onStop();

}
