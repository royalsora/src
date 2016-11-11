package com.dark.rs2.content.minigames.raid.rounds;

import com.dark.core.task.Task;
import com.dark.core.task.TaskQueue;
import com.dark.core.util.Utility;
import com.dark.rs2.entity.Location;
import com.dark.rs2.entity.mob.Mob;
import com.dark.rs2.entity.player.Player;

public class Bandos extends RaidRound {

	@Override
	public void startRound(Player creator, int currentRound, int maxRound) {
	creator.getRaid().currentRound++;
	if (creator.getRaid().currentRound > 5) {
		System.out.println("Raid ended here.");
		endRaid(creator);
		return;
	}
	switch (creator.getRaid().currentRound) {
	// 2273 4695
	case 1:
		for (int i = 0; i <= (creator.getRaid().doubleNpcs == true ? 1 : 0); i++) {
			Mob mob = new Mob(creator.getRaid().region, 2215, true, false, new Location(2273 - Utility.random(3), 4695 + Utility.random(3), creator.getIndex() << 2));
			Mob r1mob2 = new Mob(creator.getRaid().region, 2217, true, false, new Location(2273 - Utility.random(5), 4695 + Utility.random(2), creator.getIndex() << 2));
			Mob r1mob3 = new Mob(creator.getRaid().region, 2217, true, false, new Location(2273 - Utility.random(5), 4695 + Utility.random(2), creator.getIndex() << 2));
			Mob r1mob4 = new Mob(creator.getRaid().region, 2217, true, false, new Location(2273 - Utility.random(7), 4695 + Utility.random(2), creator.getIndex() << 2));
			Mob r1mob5 = new Mob(creator.getRaid().region, 2218, true, false, new Location(2273 + Utility.random(2), 4695 - Utility.random(2), creator.getIndex() << 2));
			Mob r1mob6 = new Mob(creator.getRaid().region, 2218, true, false, new Location(2273 + Utility.random(4), 4695 - Utility.random(2), creator.getIndex() << 2));
			Mob r1mob7 = new Mob(creator.getRaid().region, 2218, true, false, new Location(2273 + Utility.random(4), 4695 - Utility.random(2), creator.getIndex() << 2));
			creator.getRaid().listOfRoundMobs.add(mob);
			creator.getRaid().listOfRoundMobs.add(r1mob2);
			creator.getRaid().listOfRoundMobs.add(r1mob3);
			creator.getRaid().listOfRoundMobs.add(r1mob4);
			creator.getRaid().listOfRoundMobs.add(r1mob5);
			creator.getRaid().listOfRoundMobs.add(r1mob6);
			creator.getRaid().listOfRoundMobs.add(r1mob7);
		}
		break;

	case 2:
		for (int i = 0; i <= (creator.getRaid().doubleNpcs == true ? 1 : 0); i++) {
			Mob r2mob1 = new Mob(creator.getRaid().region, 2215, true, false, new Location(2273 - Utility.random(3), 4695 + Utility.random(3), creator.getIndex() << 2));
			Mob r2mob2 = new Mob(creator.getRaid().region, 2216, true, false, new Location(r2mob1.getX(), r2mob1.getY() - 1, creator.getIndex() << 2));
			Mob r2mob3 = new Mob(creator.getRaid().region, 2216, true, false, new Location(r2mob1.getX(), r2mob1.getY() + 1, creator.getIndex() << 2));
			Mob r2mob4 = new Mob(creator.getRaid().region, 2216, true, false, new Location(r2mob1.getX(), r2mob1.getY() + 1, creator.getIndex() << 2));
			creator.getRaid().listOfRoundMobs.add(r2mob1);
			creator.getRaid().listOfRoundMobs.add(r2mob2);
			creator.getRaid().listOfRoundMobs.add(r2mob3);
			creator.getRaid().listOfRoundMobs.add(r2mob4);
		}
		break;

	case 3:
		for (int i = 0; i <= (creator.getRaid().doubleNpcs == true ? 1 : 0); i++) {
			Mob r3mob1 = new Mob(creator.getRaid().region, 2215, true, false, new Location(2273 + Utility.random(3), 4695 - Utility.random(3), creator.getIndex() << 2));
			Mob r3mob2 = new Mob(creator.getRaid().region, 2215, true, false, new Location(2273 + Utility.random(3), 4695 - Utility.random(3), creator.getIndex() << 2));
			Mob r3mob3 = new Mob(creator.getRaid().region, 2217, true, false, new Location(2273 + Utility.random(2), 4695 - Utility.random(2), creator.getIndex() << 2));
			Mob r3mob4 = new Mob(creator.getRaid().region, 2217, true, false, new Location(2273 + Utility.random(4), 4695 - Utility.random(2), creator.getIndex() << 2));
			Mob r3mob5 = new Mob(creator.getRaid().region, 2217, true, false, new Location(2273 + Utility.random(4), 4695 - Utility.random(2), creator.getIndex() << 2));
			creator.getRaid().listOfRoundMobs.add(r3mob1);
			creator.getRaid().listOfRoundMobs.add(r3mob2);
			creator.getRaid().listOfRoundMobs.add(r3mob3);
			creator.getRaid().listOfRoundMobs.add(r3mob4);
			creator.getRaid().listOfRoundMobs.add(r3mob5);
			
		}
		break;
	case 4:
		for (int i = 0; i <= (creator.getRaid().doubleNpcs == true ? 1 : 0); i++) {
			System.out.println("Starting round " + creator.getRaid().currentRound);
			Mob r4mob1 = new Mob(creator.getRaid().region, 2215, true, false, new Location(2273 + Utility.random(3), 4695 - Utility.random(3), creator.getIndex() << 2));
			Mob r4mob2 = new Mob(creator.getRaid().region, 2215, true, false, new Location(2273 + Utility.random(3), 4695 - Utility.random(3), creator.getIndex() << 2));
			Mob r4mob3 = new Mob(creator.getRaid().region, 2218, true, false, new Location(2273 + Utility.random(2), 4695 - Utility.random(2), creator.getIndex() << 2));
			Mob r4mob4 = new Mob(creator.getRaid().region, 2218, true, false, new Location(2273 + Utility.random(4), 4695 - Utility.random(2), creator.getIndex() << 2));
			Mob r4mob5 = new Mob(creator.getRaid().region, 2218, true, false, new Location(2273 + Utility.random(4), 4695 - Utility.random(2), creator.getIndex() << 2));
			creator.getRaid().listOfRoundMobs.add(r4mob1);
			creator.getRaid().listOfRoundMobs.add(r4mob2);
			creator.getRaid().listOfRoundMobs.add(r4mob3);
			creator.getRaid().listOfRoundMobs.add(r4mob4);
			creator.getRaid().listOfRoundMobs.add(r4mob5);
		}
		break;
	case 5:
		for (int i = 0; i <= (creator.getRaid().doubleNpcs == true ? 1 : 0); i++) {
			Mob r5mob1 = new Mob(creator.getRaid().region, 2216, true, false, new Location(2273 + Utility.random(3), 4695 - Utility.random(3), creator.getIndex() << 2));
			Mob r5mob2 = new Mob(creator.getRaid().region, 2216, true, false, new Location(2273 + Utility.random(3), 4695 - Utility.random(3), creator.getIndex() << 2));
			Mob r5mob3 = new Mob(creator.getRaid().region, 2216, true, false, new Location(2273 - Utility.random(3), 4695 + Utility.random(3), creator.getIndex() << 2));
			Mob r5mob4 = new Mob(creator.getRaid().region, 2216, true, false, new Location(2273 - Utility.random(3), 4695 + Utility.random(3), creator.getIndex() << 2));
			Mob r5mob5 = new Mob(creator.getRaid().region, 2216, true, false, new Location(2273 + Utility.random(3), 4695 + Utility.random(3), creator.getIndex() << 2));
			Mob r5mob6 = new Mob(creator.getRaid().region, 2216, true, false, new Location(2273 + Utility.random(3), 4695 + Utility.random(3), creator.getIndex() << 2));
			Mob r5mob7 = new Mob(creator.getRaid().region, 2216, true, false, new Location(2273 + Utility.random(3), 4695 + Utility.random(3), creator.getIndex() << 2));
			Mob r5mob10 = new Mob(creator.getRaid().region, 2216, true, false, new Location(2273 + Utility.random(3), 4695 + Utility.random(3), creator.getIndex() << 2));
			Mob r5mob17 = new Mob(creator.getRaid().region, 2217, true, false, new Location(2273 + Utility.random(2), 4695 - Utility.random(2), creator.getIndex() << 2));
			Mob r5mob18 = new Mob(creator.getRaid().region, 2217, true, false, new Location(2273 + Utility.random(4), 4695 - Utility.random(2), creator.getIndex() << 2));
			Mob r5mob19 = new Mob(creator.getRaid().region, 2217, true, false, new Location(2273 + Utility.random(4), 4695 - Utility.random(2), creator.getIndex() << 2));
			Mob r5mob20 = new Mob(creator.getRaid().region, 2217, true, false, new Location(2273 - Utility.random(5), 4695 + Utility.random(2), creator.getIndex() << 2));
			Mob r5mob21 = new Mob(creator.getRaid().region, 2217, true, false, new Location(2273 - Utility.random(5), 4695 + Utility.random(2), creator.getIndex() << 2));
			Mob r5mob22 = new Mob(creator.getRaid().region, 2217, true, false, new Location(2273 - Utility.random(7), 4695 + Utility.random(2), creator.getIndex() << 2));
			Mob r5mob23 = new Mob(creator.getRaid().region, 2218, true, false, new Location(2273 + Utility.random(2), 4695 - Utility.random(2), creator.getIndex() << 2));
			Mob r5mob24 = new Mob(creator.getRaid().region, 2218, true, false, new Location(2273 + Utility.random(4), 4695 - Utility.random(2), creator.getIndex() << 2));
			Mob r5mob25 = new Mob(creator.getRaid().region, 2218, true, false, new Location(2273 + Utility.random(4), 4695 - Utility.random(2), creator.getIndex() << 2));
			creator.getRaid().listOfRoundMobs.add(r5mob1);
			creator.getRaid().listOfRoundMobs.add(r5mob2);
			creator.getRaid().listOfRoundMobs.add(r5mob3);
			creator.getRaid().listOfRoundMobs.add(r5mob4);
			creator.getRaid().listOfRoundMobs.add(r5mob5);
			creator.getRaid().listOfRoundMobs.add(r5mob6);
			creator.getRaid().listOfRoundMobs.add(r5mob7);
			creator.getRaid().listOfRoundMobs.add(r5mob10);
			creator.getRaid().listOfRoundMobs.add(r5mob17);
			creator.getRaid().listOfRoundMobs.add(r5mob18);
			creator.getRaid().listOfRoundMobs.add(r5mob19);
			creator.getRaid().listOfRoundMobs.add(r5mob20);
			creator.getRaid().listOfRoundMobs.add(r5mob21);
			creator.getRaid().listOfRoundMobs.add(r5mob22);
			creator.getRaid().listOfRoundMobs.add(r5mob23);
			creator.getRaid().listOfRoundMobs.add(r5mob24);
			creator.getRaid().listOfRoundMobs.add(r5mob25);
		}
		break;
	}
	}

	@Override
	public void endRaid(Player creator) {
	// Give Rewards here.
	TaskQueue.queue(new Task(15) {

		@Override
		public void execute() {
		RoundHandler.endRaid(creator);
		stop();
		}

		@Override
		public void onStop() {
		// TODO Auto-generated method stub

		}
	});

	}

}
