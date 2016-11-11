package com.dark.rs2.content.minigames.pestcontrol.monsters;

import com.dark.core.task.Task;
import com.dark.core.task.TaskQueue;
import com.dark.core.task.impl.FollowToEntityTask;
import com.dark.core.util.Utility;
import com.dark.rs2.content.minigames.pestcontrol.Pest;
import com.dark.rs2.content.minigames.pestcontrol.PestControlConstants;
import com.dark.rs2.content.minigames.pestcontrol.PestControlGame;
import com.dark.rs2.entity.Location;

public class Spinner extends Pest {
	
	private final Portal portal;
	private final Task heal = null;

	public Spinner(Location l, PestControlGame game, Portal portal) {
		super(game, PestControlConstants.SPINNERS[Utility.randomNumber(PestControlConstants.SPINNERS.length)], l);
		setRetaliate(false);
		this.portal = portal;
	}

	public void heal() {
		if ((heal == null) || (heal.stopped())) {
			TaskQueue.queue(new FollowToEntityTask(this, portal) {
				@Override
				public void onDestination() {
					getUpdateFlags().sendAnimation(3911, 0);
					portal.heal(5);
					stop();
				}
			});
		}
	}

	@Override
	public void tick() {
		if (portal.isDead()) {
			return;
		}

		if ((portal.isDamaged()) && (Utility.randomNumber(3) == 0)) {
			heal();
		}
	}
}
