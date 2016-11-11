package com.dark.rs2.content.dialogue.impl.teleport;

import com.dark.core.task.Task;
import com.dark.core.task.TaskQueue;
import com.dark.rs2.content.dialogue.Dialogue;
import com.dark.rs2.content.dialogue.DialogueManager;
import com.dark.rs2.content.skill.magic.MagicSkill;
import com.dark.rs2.content.skill.magic.MagicSkill.TeleportTypes;
import com.dark.rs2.entity.Animation;
import com.dark.rs2.entity.Location;
import com.dark.rs2.entity.item.Item;
import com.dark.rs2.entity.mob.impl.Zulrah;
import com.dark.rs2.entity.player.Player;
import com.dark.rs2.entity.player.net.out.impl.SendMessage;

public class EliteBanner extends Dialogue {

	private int itemId;
	private boolean operate = false;

	public EliteBanner(Player player, boolean operate, int itemId) {
		this.player = player;
		this.operate = operate;
		this.itemId = itemId;
	}

	@Override
	public boolean clickButton(int id) {
		if (!player.getPlayer().getMagic().canTeleport(TeleportTypes.SPELL_BOOK)) {
			player.getDialogue().end();
			return false;
		}
		
		if (player.getMagic().isTeleporting()) {
			return false;
		}
		
		switch (id) {
		case 9157:
			getPlayer().getMagic().teleport(2692, 3202, 0, MagicSkill.TeleportTypes.SPELL_BOOK);
			player.getClient().queueOutgoingPacket( new SendMessage("<col=C60DDE>You teleport to Pest Island using your banner."));
			player.getDialogue().end();
			break;
		case 9158:
			player.getMagic().teleport(2268, 3070, player.getIndex() << 2, TeleportTypes.SPELL_BOOK);
			TaskQueue.queue(new Task(5) {
				@Override
				public void execute() {
					Zulrah mob = new Zulrah(player, new Location(2266, 3073, player.getIndex() << 2));
					mob.face(player);
					mob.getUpdateFlags().sendAnimation(new Animation(5071));
					player.face(mob);
					player.getClient().queueOutgoingPacket( new SendMessage("<col=C60DDE>You teleport to Zulrah using your banner."));
					stop();
				}

				@Override
				public void onStop() {
				}
			});
			break;
		}
		return false;
	}

	@Override
	public void execute() {
		DialogueManager.sendOption(player, new String[] { "Pest Island", "Zulrah" });
	}
}