package com.dark.rs2.content;

import com.dark.rs2.content.achievements.AchievementList;
import com.dark.rs2.entity.player.Player;
import com.dark.rs2.entity.player.net.out.impl.SendConfig;
import com.dark.rs2.entity.player.net.out.impl.SendMessage;

public class LoyaltyShop {

	public static enum TitleButton {

		/**
		 * Main Titles
		 */
		SIR(214231, PlayerTitle.create("Sir", 0xB0720E, false), 15),
		MISS(214239, PlayerTitle.create("Miss", 0xB0720E, false), 15),
		LORD(214247, PlayerTitle.create("Lord", 0xB0720E, false), 20),
		DUDERINO(214255, PlayerTitle.create("Duderino", 0xB0720E, false), 25),
		COPYCAT(215007, PlayerTitle.create("Copycat", 0xB0720E, false), 50),
		KING(215015, PlayerTitle.create("King", 0xB0720E, false), 50),
		QUEEN(215023, PlayerTitle.create("Queen", 0xB0720E, false), 50),
		FAM(215031, PlayerTitle.create("Fam", 0xB0720E, false), 60),
		BROTHER(215039, PlayerTitle.create("Brother", 0xB0720E, false), 65),
		SISTER(215047, PlayerTitle.create("Sister", 0xB0720E, false), 65),
		OVERLOAD(215055, PlayerTitle.create("Overload", 0xB0720E, false), 75),
		THE(215063, PlayerTitle.create("The", 0xB0720E, false), 100),
		RICHIERICH(215071, PlayerTitle.create("RichieRich", 0xB0720E, false), 100),
		EMPEROR(215079, PlayerTitle.create("Emperor", 0xB0720E, false), 125),
		IMMORTAL(215087, PlayerTitle.create("Immortal", 0xB0720E, false), 135),
		THE_GREAT(215095, PlayerTitle.create("The Great", 0xB0720E, false), 140),
		CHAMPION(215103, PlayerTitle.create("Champion", 0xB0720E, false), 150),
		SWAGTASTIC(215111, PlayerTitle.create("Swagtastic", 0xB0720E, false), 175),
		GODLY(215119, PlayerTitle.create("Godly", 0xB0720E, false), 200),
		RESPECTED(215127, PlayerTitle.create("Respected", 0xB0720E, false), 205),
		THE_ONE(215135, PlayerTitle.create("The One", 0xB0720E, false), 210),
		HOLY(215143, PlayerTitle.create("Holy", 0xB0720E, false), 220),
		SKILLED(215151, PlayerTitle.create("The Skilled", 0xB0720E, false), 230),
		BALLER(215159, PlayerTitle.create("The Baller", 0xB0720E, false), 230),
		
		/**
		 * Achievement Titles
		 */
		SKELETAL(215172, PlayerTitle.create("Skeletal", 0xB0720E, false), AchievementList.KILL_250_SKELETAL_WYVERNS),
		NATURE(215180, PlayerTitle.create("Nature", 0xB0720E, false), AchievementList.CRAFT_1000_NATURE_RUNES),
		MULTI_TASK(215188, PlayerTitle.create("Multi-Task", 0xB0720E, false), AchievementList.COMPLETE_100_SLAYER_TASKS),
		PET(215196, PlayerTitle.create("Pet", 0xB0720E, false), AchievementList.OBTAIN_3_BOSS_PET),
		TZTOK(215204, PlayerTitle.create("TzTok", 0xB0720E, false), AchievementList.OBTAIN_25_FIRECAPES),
		BIG_BEAR(215220, PlayerTitle.create("Big Bear", 0xB0720E, false), AchievementList.KILL_100_CALLISTO),


		HARDCORE(215220, PlayerTitle.create("Hardcore", 0xB0720E, false), AchievementList.KILL_100_CALLISTO),
		/**
		 * Colors
		 */
		RED(215233, PlayerTitle.create("null", 0xC22323, false), 30),
		GREEN(215241, PlayerTitle.create("null", 0x0FA80F, false), 30),
		BLUE(215249, PlayerTitle.create("null", 0x2AA4C9, false), 30),
		YELLOW(216001, PlayerTitle.create("null", 0xC9BC28, false), 30),
		ORANGE(216009, PlayerTitle.create("null", 0xB0720E, false), 30),
		PURPLE(216017, PlayerTitle.create("null", 0xC931E8, false), 30),
		PINK(216025, PlayerTitle.create("null", 0xF52CD7, false), 30),
		WHITE(216033, PlayerTitle.create("null", 0xFFFFFF, false), 30);
		
		
		

		private int button;
		private PlayerTitle title;
		private Object price;

		private TitleButton(int button, PlayerTitle title, Object price) {
			this.button = button;
			this.title = title;
			this.price = price;
		}

		public int getButton() {
			return button;
		}

		public Object getPrice() {
			return price;
		}

		public PlayerTitle getTitle() {
			return title;
		}

		public static TitleButton forButton(int button) {
			for (TitleButton titleButton : values()) {
				if (titleButton.getButton() == button) {
					return titleButton;
				}
			}
			return null;
		}
	}

	public static void load(Player player) {
		for (TitleButton titleButton : TitleButton.values()) {
			if (player.unlockedTitles.contains(titleButton.getTitle())) {
				player.send(new SendConfig(1040 + titleButton.ordinal(), 1));
			} else {
				player.send(new SendConfig(1040 + titleButton.ordinal(), 0));
			}
		}
	}

	public static boolean handleButtons(Player player, int buttonId) {
		TitleButton button = TitleButton.forButton(buttonId);

		if (button == null) {
			return false;
		}

		if (player.getPlayerTitle() != null && player.getPlayerTitle().equals(button.getTitle())) {
			player.send(new SendMessage("@dre@You already have this set as your title."));
			return true;
		}

		if (!player.unlockedTitles.contains(button.getTitle())) {
			if (button.getPrice() instanceof Integer) {
				if (player.getCredits() < Integer.parseInt(String.valueOf(button.getPrice()))) {
					player.send(new SendMessage("<col=128>You do not have enough Gold points to buy this."));
					return true;
				}
			}

			if (button.getPrice() instanceof AchievementList) {
				AchievementList achievement = ((AchievementList) button.getPrice());
				if (player.getPlayerAchievements().get(achievement) != achievement.getCompleteAmount()) {
					player.send(new SendMessage("<col=128>Completion of the achievement '" + achievement.getName() + "' is required."));
					return true;
				}
			}

			if (button.ordinal() >= TitleButton.RED.ordinal() && button.ordinal() <= TitleButton.WHITE.ordinal()) {
				if (button.getTitle().getColor() == player.getPlayerTitle().getColor()) {
					player.send(new SendMessage("<col=128>This is already your title color."));
					return true;
				}
				if (!player.getInventory().hasItemAmount(995, Integer.parseInt(String.valueOf(button.getPrice())))) {
					player.send(new SendMessage("<col=128>You need more coins to buy this color."));
					return true;
				}
				player.setPlayerTitle(PlayerTitle.create(player.getPlayerTitle().getTitle(), button.getTitle().getColor(), player.getPlayerTitle().isSuffix()));
				player.setAppearanceUpdateRequired(true);
				player.getInventory().remove(995, Integer.parseInt(String.valueOf(button.getPrice())));
				player.send(new SendMessage("<col=128>You have changed your title color to '<col=" + Integer.toHexString(button.getTitle().getColor()) + ">" + button.name().toLowerCase().replaceAll("_", " ") + "</col>'!"));
			} else {
				player.setPlayerTitle(button.getTitle());
				player.setAppearanceUpdateRequired(true);
				if (button.getPrice() instanceof Integer) {
					//player.setCredits(player.getCredits() - Integer.parseInt(String.valueOf(button.getPrice())));
					player.getInventory().remove(995, Integer.parseInt(String.valueOf(button.getPrice())));
				}
				player.unlockedTitles.add(button.getTitle());
				player.send(new SendConfig(1040 + button.ordinal(), 1));
				player.send(new SendMessage("<col=128>You have unlocked the title '<col=" + Integer.toHexString(button.getTitle().getColor()) + ">" + button.getTitle().getTitle() + "</col>'!"));
			}
			return true;
		} else {
			player.setPlayerTitle(button.getTitle());
			player.setAppearanceUpdateRequired(true);
			player.getInventory().remove(995, Integer.parseInt(String.valueOf(button.getPrice())));
			player.send(new SendMessage("<col=128>You have changed your title to '<col=" + Integer.toHexString(button.getTitle().getColor()) + ">" + button.getTitle().getTitle() + "</col>'."));
			return true;
		}
	}
}