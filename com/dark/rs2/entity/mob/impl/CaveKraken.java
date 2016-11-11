package com.dark.rs2.entity.mob.impl;


import com.dark.rs2.entity.Location;
import com.dark.rs2.entity.mob.Mob;
import com.dark.rs2.entity.player.Player;


public class CaveKraken extends Mob {

	public CaveKraken(Player player, Location location) {
	super(player, 492, false, false, false, location);
//	TIME = System.currentTimeMillis();
}
//	public CaveKraken() {
	//super(492, false, new Location(2482, 9810));

		//super(492, false, getLocation());
	//}
	@Override
	public void onDeath() {
		for (Mob mobs : getOwner().tentacles) {
			if (!mobs.isDead()) {
				mobs.remove();
			}
		}
	//	getOwner().tentacles.clear();
		//getOwner().whirlpoolsHit = 0;
	//	getOwner().send(new SendMessage("Fight duration: @red@" + new SimpleDateFormat("m:ss").format(System.currentTimeMillis() - TIME) + "</col>."));
	}
	@Override
	public int getRespawnTime() {
		return 30;
	}
}
