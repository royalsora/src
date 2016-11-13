package com.dark.rs2.content.dialogue.impl;

import com.dark.rs2.content.StarterKit;
import com.dark.rs2.content.dialogue.Dialogue;
import com.dark.rs2.content.dialogue.DialogueManager;
import com.dark.rs2.content.dialogue.Emotion;
import com.dark.rs2.entity.Location;
import com.dark.rs2.entity.player.Player;
import com.dark.rs2.entity.player.controllers.DefaultController;
import com.dark.rs2.entity.player.net.out.impl.SendInterface;
import com.dark.rs2.entity.player.net.out.impl.SendSidebarInterface;

public class Tutorial extends Dialogue {

	public static class TutorialController extends DefaultController {

		@Override
		public boolean canAttackNPC() {
			return false;
		}

		@Override
		public boolean canClick() {
			return false;
		}

		@Override
		public boolean canMove(Player p) {
			return false;
		}

		@Override
		public boolean canTeleport() {
			return false;
		}

		@Override
		public boolean canTrade() {
			return false;
		}

		@Override
		public void onDisconnect(Player p) {
		}

		@Override
		public boolean transitionOnWalk(Player p) {
			return false;
		}
	}

	public static final TutorialController TUTORIAL_CONTROLLER = new TutorialController();

	public static final int GUIDE = 306;

	public Tutorial(Player player) {
		this.player = player;
		player.setController(TUTORIAL_CONTROLLER);
	}

	@Override
	public boolean clickButton(int id) {
		switch (id) {
		case 9157:
			if (option == 1) {
				next = 3;
				execute();
			}
			return true;
		case 9158:
			if (option == 1) {
				next = 2;
				execute();
			}
			return true;
		}
		return false;
	}

	public static final int[] SIDEBAR_INTERFACE_IDS = { -1, -1, -1, 3213, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 };

	@Override
	public void execute() {

		for (int i = 0; i < SIDEBAR_INTERFACE_IDS.length; i++) {
			player.send(new SendSidebarInterface(i, SIDEBAR_INTERFACE_IDS[i]));
		}
		
		switch (next) {

		case 0:
			DialogueManager.sendNpcChat(player, GUIDE, Emotion.HAPPY_TALK, "Hello @blu@" + player.getUsername() + "</col>, Welcome to Horizon-OS!", "Would you like a tour?");
			next++;
			break;
		case 1:
			DialogueManager.sendOption(player, new String[] { "Yes.", "No." });
			option = 1;
			break;
		case 2:
			end();
			StarterKit.handle(player, 202051);
			player.send(new SendInterface(51750));
			break;
		case 3:
			nChat(new String[] {"Hello @blu@" + player.getUsername() + "</col>, Welcome to Horizon-OS!", "Let's get started!"});
			break;
		case 4:
			nChat(new String[] { "We have many different attractions such as", "Zulrah, Cerberus, Kraken.", "We also have unique content coming in daily!" });
			break;
		case 5:
			nChat(new String[] { "Welcome to your home area, the bane of all things Horizon." });
			break;
		case 6:
			tele(3098, 3484);
			nChat(new String[] { "This is Vannaka; he can give you a slayer task.", "You may also get a co-op slayer task." });
			break;
		case 7:
			tele(2467, 9815);
			nChat(new String[] { "Here are some of the monsters", "You'll be killing through slayer.", "Slayer is one of the best ways to make money!" });
			break;
		case 8:
			tele(3096, 3504);
			nChat(new String[] { "These are the shops.", "They're spread out but everything you need is here." });
			break;
		case 9:
			tele(3101, 3490);
			nChat(new String[] { "This is a good thieving area.", "Thieving is another good way to make money." });
			break;
		case 10:
			tele(3086, 3512);
			nChat(new String[] { "This is the Emblem Trader.", "He will reward you for all your hard work from the", "Bounty Hunter." });
			break;
		case 11:
			tele(3095, 3486);
			nChat(new String[] { "Like mystery boxes?", "You can get them from many stores,", "including the Trivia shop!" });
			break;
		case 12:
			tele(3567, 3298);
			nChat(new String[] { "If you still need help making money.", "check out barrows!", "the drop rate is roughly 1 in 3" });
			break;
		case 13:
			tele(3088, 3499);
			nChat(new String[] { "If you have any more questions please speak to a", "<img=0>@blu@ Moderator</col> or any other staff member." });
			break;
		case 14:
			nChat(new String[] { "Also check out our forums! (@red@www.Horizon-OS.com</col>)", "Make sure to vote to keep the server active." });
			break;
		case 15:
			nChat(new String[] { "There is tons more of content to explore.", "Good luck with your adventure!" });
			break;
		case 16:
			end();
			StarterKit.handle(player, 202051);
			player.send(new SendInterface(51750));
			break;
		}

	}

	public void nChat(String[] chat) {
		DialogueManager.sendNpcChat(player, GUIDE, Emotion.HAPPY_TALK, chat);
		next += 1;
	}

	public void pChat(String[] chat) {
		DialogueManager.sendPlayerChat(player, Emotion.HAPPY, chat);
		next += 1;
	}

	public void tele(int x, int y) {
		player.teleport(new Location(x, y, 0));
	}

	public void tele(int x, int y, int z) {
		player.teleport(new Location(x, y, z));
	}
}
