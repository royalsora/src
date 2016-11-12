package com.dark.rs2.content.dialogue.impl.teleport;

import com.dark.rs2.content.dialogue.Dialogue;
import com.dark.rs2.content.dialogue.DialogueManager;
import com.dark.rs2.content.skill.magic.MagicSkill;
import com.dark.rs2.content.skill.magic.MagicSkill.TeleportTypes;
import com.dark.rs2.entity.player.Player;
import com.dark.rs2.entity.player.net.out.impl.SendMessage;

public class EliteSword extends Dialogue {

	private int itemId;
	private boolean operate = false;

	public EliteSword(Player player, boolean operate, int itemId) {
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
			getPlayer().getMagic().teleport(3371, 3895, 0, MagicSkill.TeleportTypes.SPELL_BOOK);
			player.getClient().queueOutgoingPacket( new SendMessage("<col=C60DDE>You teleport to the Fountain of Rune using the power from your sword."));
			player.getDialogue().end();
			break;
		case 9168:
			getPlayer().getMagic().teleport(2272, 4680, 0, MagicSkill.TeleportTypes.SPELL_BOOK);
			player.getClient().queueOutgoingPacket( new SendMessage("<col=C60DDE>You teleport to the KBD Lair using the power from your sword."));
			player.getDialogue().end();
			break;
		case 9169:
			getPlayer().getMagic().teleport(3034, 10158, 0, MagicSkill.TeleportTypes.SPELL_BOOK);
			player.getClient().queueOutgoingPacket( new SendMessage("<col=C60DDE>You teleport to the Wilderness Godwars Dungeon using the power from your sword."));
			player.getDialogue().end();
			break;
		}
		return false;
	}

	@Override
	public void execute() {
		DialogueManager.sendOption(player, new String[] { "@red@Foutain of Rune", "King Black Dragon", "@red@Godwars Wilderness Dungeon" });
	}
}