package com.dark.core.task.impl;

import com.dark.core.task.Task;
import com.dark.rs2.entity.Entity;
import com.dark.rs2.entity.Graphic;

/**
 * A graphic to queue.
 * 
 * @author Michael Sasse
 * 
 */
public class GraphicTask extends Task {

	/**
	 * The graphic.
	 */
	private final Graphic graphic;
	/**
	 * The entity.
	 */
	private final Entity entity;

	/**
	 * Creates a new graphic to queue.
	 * 
	 * @param graphic
	 *            the graphic.
	 * @param delay
	 *            the action delay.
	 */
	public GraphicTask(int delay, boolean immediate, Graphic graphic,
			Entity entity) {
		super(entity, delay, immediate, StackType.STACK, BreakType.NEVER, TaskIdentifier.CURRENT_ACTION);
		this.graphic = graphic;
		this.entity = entity;
	}

	@Override
	public void execute() {
		entity.getUpdateFlags().sendGraphic(graphic);
		stop();
	}

	@Override
	public void onStop() {
		// TODO Auto-generated method stub

	}
}
