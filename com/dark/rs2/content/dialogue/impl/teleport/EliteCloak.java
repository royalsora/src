package com.dark.rs2.content.dialogue.impl.teleport;

import com.dark.rs2.content.dialogue.Dialogue;
import com.dark.rs2.content.dialogue.DialogueManager;
import com.dark.rs2.content.skill.magic.MagicSkill;
import com.dark.rs2.content.skill.magic.MagicSkill.TeleportTypes;
import com.dark.rs2.entity.player.Player;
import com.dark.rs2.entity.player.net.out.impl.SendMessage;

public class EliteCloak extends Dialogue {

	private int itemId;
	private boolean operate = false;

	public EliteCloak(Player player, boolean operate, int itemId) {
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
		case 9167:
			getPlayer().getMagic().teleport(2606, 3211, 0, MagicSkill.TeleportTypes.SPELL_BOOK);
			player.getClient().queueOutgoingPacket( new SendMessage("<col=2CD3EB>You teleport to the Ardougne Monestary using the magic from your cloak."));
			player.getDialogue().end();
			break;
		case 9168:
			getPlayer().getMagic().teleport(2673, 3375, 0, MagicSkill.TeleportTypes.SPELL_BOOK);
			player.getClient().queueOutgoingPacket( new SendMessage("<col=2CD3EB>You teleport to the Ardougne Farm using the magic from your cloak."));
			player.getDialogue().end();
			break;
		case 9169:
			getPlayer().getMagic().teleport(1993, 4661, 0, MagicSkill.TeleportTypes.SPELL_BOOK);
			player.getClient().queueOutgoingPacket( new SendMessage("<col=2CD3EB>You teleport to the Dark Beast lair using the magic from your cloak."));
			player.getDialogue().end();
			break;
		}
		return false;
	}

	@Override
	public void execute() {
		DialogueManager.sendOption(player, new String[] { "Ardougne Monestary", "Ardougne Farm", "Dark Beasts" });
	}
}