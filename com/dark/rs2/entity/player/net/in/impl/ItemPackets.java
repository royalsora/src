package com.dark.rs2.entity.player.net.in.impl;

import java.util.HashMap;

import com.dark.Constants;
import com.dark.core.network.StreamBuffer;
import com.dark.core.task.Task;
import com.dark.core.task.TaskQueue;
import com.dark.core.task.impl.DigTask;
import com.dark.core.util.GameDefinitionLoader;
import com.dark.core.util.Utility;
import com.dark.rs2.content.DropTable;
import com.dark.rs2.content.ItemInteraction;
import com.dark.rs2.content.ItemOpening;
import com.dark.rs2.content.LegendaryBox;
import com.dark.rs2.content.MysteryBox;
import com.dark.rs2.content.PrizeBox;
import com.dark.rs2.content.StrangeBox;
import com.dark.rs2.content.Unsired;
import com.dark.rs2.content.OmegaEgg;
import com.dark.rs2.content.ZulrahTotem;
import com.dark.rs2.content.achievements.AchievementHandler;
import com.dark.rs2.content.achievements.AchievementList;
import com.dark.rs2.content.ChristmasCracker;
import com.dark.rs2.content.bank.Bank.RearrangeTypes;
import com.dark.rs2.content.cluescroll.ClueScrollManager;
import com.dark.rs2.content.combat.Hit;
import com.dark.rs2.content.consumables.ConsumableType;
import com.dark.rs2.content.dialogue.DialogueManager;
import com.dark.rs2.content.dialogue.Emotion;
import com.dark.rs2.content.dialogue.OptionDialogue;
import com.dark.rs2.content.dialogue.impl.RingOfSlayingDialogue;
import com.dark.rs2.content.dialogue.impl.RottenPotato;
import com.dark.rs2.content.dialogue.impl.RottenPotato2;
import com.dark.rs2.content.dialogue.impl.dicerScrollDialogue;
import com.dark.rs2.content.dialogue.impl.imbueDialogue;
import com.dark.rs2.content.dialogue.impl.maxcapeTeleportsDialogue;
import com.dark.rs2.content.dialogue.impl.slayerhelmImbue;
import com.dark.rs2.content.dialogue.impl.teleport.EliteBanner;
import com.dark.rs2.content.dialogue.impl.teleport.EliteSword;
import com.dark.rs2.content.dialogue.impl.teleport.EliteCloak;
import com.dark.rs2.content.dialogue.impl.teleport.EliteHelm;
import com.dark.rs2.content.dialogue.impl.teleport.GloryDialogue;
import com.dark.rs2.content.dialogue.impl.teleport.RingOfDuelingDialogue;
import com.dark.rs2.content.interfaces.InterfaceHandler;
import com.dark.rs2.content.interfaces.impl.CreditTab;
import com.dark.rs2.content.interfaces.impl.QuestTab;
import com.dark.rs2.content.membership.MembershipBonds;
import com.dark.rs2.content.minigames.weapongame.WeaponGameStore;
import com.dark.rs2.content.pets.BossPets;
import com.dark.rs2.content.skill.Skills;
import com.dark.rs2.content.skill.slayer.Slayer;
import com.dark.rs2.content.skill.crafting.AmuletStringing;
import com.dark.rs2.content.skill.crafting.JewelryCreationTask;
import com.dark.rs2.content.skill.craftingnew.Crafting;
import com.dark.rs2.content.skill.firemaking.Firemaking;
import com.dark.rs2.content.skill.fletching.Fletching;
import com.dark.rs2.content.skill.herblore.CleanHerbTask;
import com.dark.rs2.content.skill.herblore.HerbloreFinishedPotionTask;
import com.dark.rs2.content.skill.herblore.HerbloreGrindingTask;
import com.dark.rs2.content.skill.herblore.HerbloreUnfinishedPotionTask;
import com.dark.rs2.content.skill.herblore.PotionDecanting;
import com.dark.rs2.content.skill.herblore.SuperCombatPotion;
import com.dark.rs2.content.skill.hunter.Impling.ImplingRewards;
import com.dark.rs2.content.skill.magic.MagicSkill;
import com.dark.rs2.content.skill.magic.TabCreation;
import com.dark.rs2.content.skill.magic.MagicSkill.TeleportTypes;
import com.dark.rs2.content.skill.magic.spells.BoltEnchanting;
import com.dark.rs2.content.skill.magic.weapons.TridentOfTheSeas;
import com.dark.rs2.content.skill.magic.weapons.TridentOfTheSwamp;
import com.dark.rs2.content.skill.melee.SerpentineHelmet;
import com.dark.rs2.content.skill.prayer.BoneBurying;
import com.dark.rs2.content.skill.ranged.ToxicBlowpipe;
import com.dark.rs2.content.skill.smithing.SmithingTask;
import com.dark.rs2.content.wilderness.TargetSystem;
import com.dark.rs2.entity.Animation;
import com.dark.rs2.entity.Graphic;
import com.dark.rs2.entity.Location;
import com.dark.rs2.entity.World;
import com.dark.rs2.entity.item.Item;
import com.dark.rs2.entity.item.ItemCreating;
import com.dark.rs2.entity.mob.Mob;
import com.dark.rs2.entity.mob.impl.EnragedCow;
//import com.dark.rs2.entity.mob.impl.EnragedCow;
import com.dark.rs2.entity.player.Player;
import com.dark.rs2.entity.player.net.in.IncomingPacket;
import com.dark.rs2.entity.player.net.out.impl.SendEnterString;
import com.dark.rs2.entity.player.net.out.impl.SendInterface;
import com.dark.rs2.entity.player.net.out.impl.SendMessage;
import com.dark.rs2.entity.player.net.out.impl.SendRemoveInterfaces;
import com.dark.rs2.entity.player.net.out.impl.SendString;
import com.dark.tools.RandomUtils;

/**
 * Handles the item packet
 * 
 * @author goten
 * 
 *         ITEM OPERATE - 75 DROP ITEM - 87 PICKUP ITEM - 236 EQUIP ITEM - 42
 *         USE ITEM ON ITEM - 53 FIRST CLICK ITEM - 122 SECOND CLICK ITEM 16
 *
 */
public class ItemPackets extends IncomingPacket {

	@Override
	public int getMaxDuplicates() {
	return 40;
	}

	@SuppressWarnings("unused")
	@Override
	public void handle(Player player, StreamBuffer.InBuffer in, int opcode, int length) {
	if (player.isStunned() || player.isDead() || !player.getController().canClick()) {
		return;
	}
	int x;
	int magicId;
	int z;

	switch (opcode) {
	case 145:
		int interfaceId = in.readShort(StreamBuffer.ValueType.A);
		int slot = in.readShort(StreamBuffer.ValueType.A);
		int itemId = in.readShort(StreamBuffer.ValueType.A);

		if (Constants.DEV_MODE) {
			player.send(new SendMessage("InterfaceId: " + interfaceId + " | Interface Manager: " + player.getInterfaceManager().getMain()));
		}

		if ((interfaceId != 1688 && interfaceId != 59813 && interfaceId != 56503) && (!player.getInterfaceManager().verify(interfaceId))) {
			return;
		}
		if (player.getMagic().isTeleporting()) {
			return;
		}

		switch (interfaceId) {
		case 56503:
			if (player.getInterfaceManager().main == 56500) {
				WeaponGameStore.select(player, itemId);
			}
			break;
		case 59813:
			if (player.getInterfaceManager().main == 59800) {
				DropTable.itemDetails(player, itemId);
			}
			break;
		case 4393:
			if (player.getInterfaceManager().main == 48500) {
				player.getPriceChecker().withdraw(itemId, slot, 1);
			} else if (player.getInterfaceManager().main == 26700) {
				TabCreation.handle(player, itemId);
			} else if (player.getInterfaceManager().main == 42750) {
				BoltEnchanting.handle(player, itemId);
			} else if (player.getInterfaceManager().main == 59750) {
				String aName = Utility.getAOrAn(GameDefinitionLoader.getItemDef(itemId).getName()) + " " + GameDefinitionLoader.getItemDef(itemId).getName();
				player.getUpdateFlags().sendForceMessage(Utility.randomElement(Constants.ITEM_IDENTIFICATION_MESSAGES).replaceAll("/s/", "" + aName));
			}
			break;

		case 1119:// Smithing
		case 1120:
		case 1121:
		case 1122:
		case 1123:
			SmithingTask.start(player, itemId, 1, interfaceId, slot);
			break;

		case 1688:// Unequip item
			if (!player.getEquipment().slotHasItem(slot)) {
				return;
			}
			player.getEquipment().unequip(slot);
			break;

		case 4233:// Crafting jewlery
		case 4239:
		case 4245:
			JewelryCreationTask.start(player, itemId, 1);
			break;

		case 5064:// Bank & price checker

			if (!player.getInventory().slotContainsItem(slot, itemId)) {
				return;
			}

			if (player.getInterfaceManager().getMain() == 48500) {
				player.getPriceChecker().store(itemId, 1);
				return;
			}

			if (player.getInterfaceManager().hasBankOpen()) {
				bankItem(player, slot, itemId, 1);
				return;
			}
			break;

		case 5382:// Bank
			withdrawBankItem(player, slot, itemId, 1);
			break;

		case 3322:// Trade
			if (player.getTrade().trading())
				handleTradeOffer(player, slot, itemId, 1);
			else if (player.getDueling().isStaking()) {
				player.getDueling().getContainer().offer(itemId, 1, slot);
			}
			break;

		case 3415:// Trade
			if (player.getTrade().trading()) {
				handleTradeRemove(player, slot, itemId, 1);
			}
			break;

		case 6669:// Dueling
			if (player.getDueling().isStaking()) {
				player.getDueling().getContainer().withdraw(slot, 1);
			}
			break;

		case 3900: // Shopping
			player.getShopping().sendSellPrice(itemId);
			break;

		case 3823:// Shopping
			player.getShopping().sendBuyPrice(itemId);
		}

		break;

	case 117:
		interfaceId = in.readShort(true, StreamBuffer.ValueType.A, StreamBuffer.ByteOrder.LITTLE);
		itemId = in.readShort(true, StreamBuffer.ValueType.A, StreamBuffer.ByteOrder.LITTLE);
		slot = in.readShort(true, StreamBuffer.ByteOrder.LITTLE);

		if (player.getMagic().isTeleporting()) {
			return;
		}

		if (Constants.DEV_MODE) {
			player.send(new SendMessage("Item packet 117 | interface " + interfaceId));
		}

		if ((interfaceId != 1688 && interfaceId != 56503) && (!player.getInterfaceManager().verify(interfaceId)))
			return;

		if (ToxicBlowpipe.itemOption(player, 2, itemId)) {
			return;
		}

		if (TridentOfTheSeas.itemOption(player, 2, itemId)) {
			return;
		}

		if (TridentOfTheSwamp.itemOption(player, 2, itemId)) {
			return;
		}

		if (SerpentineHelmet.itemOption(player, 2, itemId)) {
			return;
		}
		// operate
		switch (interfaceId) {
		case 56503:
			if (player.getInterfaceManager().main == 56500) {
				WeaponGameStore.purchase(player, itemId);
			}
			break;
		case 2700:
			if (player.getSummoning().isFamilarBOB()) {
				player.getSummoning().getContainer().withdraw(slot, 5);
			}
			break;
		case 1688:
			if (itemId == 1712 || itemId == 1710 || itemId == 1708 || itemId == 1706 || itemId == 11976 || itemId == 11978) {
				player.start(new GloryDialogue(player, true, itemId));
				return;
			}

			if (itemId == 11905) {
				player.getInventory().remove(11905, 1);
				player.getInventory().add(11908, 1);
				player.send(new SendMessage("You now have the correct trident, it still requires a charge."));
				return;
			}

			if (itemId == 13103) {
				player.start(new RingOfSlayingDialogue(player));
			}
			if (itemId == 13111) {
				player.start(new EliteSword(player, true, itemId));
			}
			if (itemId == 13144) {
				player.start(new EliteBanner(player, true, itemId));
			}
			if (itemId == 13124) {
				player.start(new EliteCloak(player, true, itemId));
			}
			if (itemId == 13140) {
				player.start(new EliteHelm(player, true, itemId));
			}
			if (itemId == 13107) { // elite platebody
				player.getMagic().teleport(3204, 3491, 2, TeleportTypes.SPELL_BOOK);
			}
			if (itemId == 13115) { // elite platelegs
				player.getMagic().teleport(3613, 3189, 0, TeleportTypes.SPELL_BOOK);
			}
			if (itemId == 13128) {
				player.getMagic().teleport(3060, 3262, 0, TeleportTypes.SPELL_BOOK);
				player.send(new SendMessage("You teleport to the Draynor Spirit Tree patch."));
			}
			if (itemId == 13136) {
				player.getMagic().teleport(3426, 2928, 0, TeleportTypes.SPELL_BOOK);
				player.send(new SendMessage("You teleport to the Statue of Elid."));
			}
			if (itemId == 9751) {
				player.getMagic().teleportNoWildernessRequirement(2878, 3546, 0, MagicSkill.TeleportTypes.SPELL_BOOK);
				player.send(new SendMessage("Your cape teleports you to the Warriors guild."));
			}
			if (itemId == 2552 || itemId == 2554 || itemId == 2556 || itemId == 2558 || itemId == 2560 || itemId == 2562 || itemId == 2564 || itemId == 2566) {
				player.start(new RingOfDuelingDialogue(player, true, itemId));
				return;
			}
			if (itemId == 1704) {
				player.getClient().queueOutgoingPacket(new SendMessage("<col=C60DDE>This amulet is all out of charges."));
				return;
			}

			if (itemId == 11283) {
				player.getMagic().onOperateDragonFireShield();
				return;
			}

			// if (itemId == 10499 || itemId == 10498) {
			// player.getRanged().getFromAvasAccumulator();
			// return;
			// }
			break;
		case 1119:
		case 1120:
		case 1121:
		case 1122:
		case 1123:
			SmithingTask.start(player, itemId, 5, interfaceId, slot);
			break;
		case 4233:
		case 4239:
		case 4245:
			JewelryCreationTask.start(player, itemId, 5);
			break;
		case 5064:// Bank & Price checker
			if (!player.getInventory().slotContainsItem(slot, itemId)) {
				return;
			}

			if (player.getInterfaceManager().getMain() == 48500) {
				player.getPriceChecker().store(itemId, 5);
				return;
			}

			if (player.getInterfaceManager().hasBankOpen()) {
				bankItem(player, slot, itemId, 5);
			}
			break;

		case 4393:// Price checker
			if (player.getInterfaceManager().main == 48500) {
				player.getPriceChecker().withdraw(itemId, slot, 5);
			} else if (player.getInterfaceManager().main == 26700) {
				TabCreation.getInfo(player, itemId);
			}
			break;

		case 5382:
			withdrawBankItem(player, slot, itemId, 5);
			break;
		case 3322:
			if (player.getTrade().trading())
				handleTradeOffer(player, slot, itemId, 5);
			else if (player.getDueling().isStaking()) {
				player.getDueling().getContainer().offer(itemId, 5, slot);
			}
			break;
		case 6669:
			if (player.getDueling().isStaking()) {
				player.getDueling().getContainer().withdraw(slot, 5);
			}
			break;
		case 3415:
			if (player.getTrade().trading()) {
				handleTradeRemove(player, slot, itemId, 5);
			}
			break;
		case 3900:
			player.getShopping().buy(itemId, 1, slot);
			break;
		case 3823:
			player.getShopping().sell(itemId, 1, slot);
		}

		break;
	case 43:
		interfaceId = in.readShort(StreamBuffer.ByteOrder.LITTLE);
		itemId = in.readShort(StreamBuffer.ValueType.A);
		slot = in.readShort(StreamBuffer.ValueType.A);

		if (player.getMagic().isTeleporting()) {
			return;
		}

		if (Constants.DEV_MODE) {
			player.send(new SendMessage("Item packet 43 | interface " + interfaceId));
		}
		if (!player.getInterfaceManager().verify(interfaceId)) {
			return;
		}

		switch (interfaceId) {
		case 4393:// Price checker
			player.getPriceChecker().withdraw(itemId, slot, 10);
			break;
		case 2700:
			if (player.getSummoning().isFamilarBOB()) {
				player.getSummoning().getContainer().withdraw(slot, 10);
			}
			break;
		case 1119:
		case 1120:
		case 1121:
		case 1122:
		case 1123:
			SmithingTask.start(player, itemId, 10, interfaceId, slot);
			break;
		case 4233:
		case 4239:
		case 4245:
			JewelryCreationTask.start(player, itemId, 10);
			break;
		case 5064:
			if (!player.getInventory().slotContainsItem(slot, itemId)) {
				return;
			}

			if (player.getInterfaceManager().getMain() == 48500) {
				player.getPriceChecker().store(itemId, 10);
				return;
			}

			if (player.getInterfaceManager().hasBankOpen())
				bankItem(player, slot, itemId, 10);
			else if (player.getSummoning().isFamilarBOB()) {
				player.getSummoning().getContainer().store(itemId, 10, slot);
			}

			break;
		case 5382:
			withdrawBankItem(player, slot, itemId, 10);
			break;
		case 3322:
			if (player.getTrade().trading())
				handleTradeOffer(player, slot, itemId, 10);
			else if (player.getDueling().isStaking()) {
				player.getDueling().getContainer().offer(itemId, 10, slot);
			}
			break;
		case 6669:
			if (player.getDueling().isStaking()) {
				player.getDueling().getContainer().withdraw(slot, 10);
			}
			break;
		case 3415:
			if (player.getTrade().trading()) {
				handleTradeRemove(player, slot, itemId, 10);
			}
			break;
		case 3900:
			player.getShopping().buy(itemId, 5, slot);
			break;
		case 3823:
			player.getShopping().sell(itemId, 5, slot);
		}

		break;
	case 129:
		slot = in.readShort(StreamBuffer.ValueType.A);
		interfaceId = in.readShort();
		itemId = in.readShort(StreamBuffer.ValueType.A);

		if (player.getMagic().isTeleporting()) {
			return;
		}

		if (Constants.DEV_MODE) {
			player.send(new SendMessage("Item packet 129 | interface " + interfaceId));
		}

		if (!player.getInterfaceManager().verify(interfaceId)) {
			return;
		}
		switch (interfaceId) {
		case 4393:// Price checker
			player.getPriceChecker().withdraw(itemId, slot, player.getPriceChecker().getItemAmount(itemId));
			break;
		case 2700:
			if (player.getSummoning().isFamilarBOB()) {
				player.getSummoning().getContainer().withdraw(slot, 2147483647);
			}
			break;
		case 5064:
			if (!player.getInventory().slotContainsItem(slot, itemId)) {
				return;
			}

			if (player.getInterfaceManager().getMain() == 48500) {
				player.getPriceChecker().store(itemId, player.getInventory().getItemAmount(itemId));
				return;
			}

			if (player.getInterfaceManager().hasBankOpen())
				bankItem(player, slot, itemId, 2147483647);
			else if (player.getSummoning().isFamilarBOB()) {
				player.getSummoning().getContainer().store(itemId, 2147483647, slot);
			}
			break;
		case 5382:
			withdrawBankItem(player, slot, itemId, 2147483647);
			break;
		case 3322:
			if (player.getTrade().trading())
				handleTradeOffer(player, slot, itemId, 2147483647);
			else if (player.getDueling().isStaking()) {
				player.getDueling().getContainer().offer(itemId, 2147483647, slot);
			}
			break;
		case 6669:
			if (player.getDueling().isStaking()) {
				player.getDueling().getContainer().withdraw(slot, 2147483647);
			}
			break;
		case 3415:
			if (player.getTrade().trading()) {
				handleTradeRemove(player, slot, itemId, 2147483647);
			}
			break;
		case 3900:
			player.getShopping().buy(itemId, 10, slot);
			break;
		case 3823:
			player.getShopping().sell(itemId, 10, slot);
		}

		break;
	case 41:
		itemId = in.readShort();
		slot = in.readShort(StreamBuffer.ValueType.A);
		in.readShort();

		if (player.getMagic().isTeleporting()) {
			return;
		}

		if (Constants.DEV_MODE) {
			player.send(new SendMessage("Item packet 41"));
		}

		if (!player.getInventory().slotContainsItem(slot, itemId)) {
			return;
		}

		if (ItemInteraction.clickPouch(player, itemId, 2)) {
			return;
		}

		switch (itemId) {
		case 2682:
		case 2683:
		case 2684:
		case 2685:
		case 2803:
		case 2805:
		case 2807:
		case 2809:
		case 2677:
		case 2678:
		case 2679:
		case 2680:
		case 2681:
		case 2801:
		case 2722:
		case 2723:
		return;
		case 4079:// YOYO
			player.getUpdateFlags().sendAnimation(1458, 0);
			return;
		case 12810:// Iron Man Armour
		case 12811:
		case 12812:
		case 12813:
		case 12814:
		case 12815:
			if (player.ironPlayer()) {
				player.getEquipment().equip(player.getInventory().get(slot), slot);
			} else {
				DialogueManager.sendStatement(player, "Only Iron Man may wear this!");
			}
			return;

		}

		player.getEquipment().equip(player.getInventory().get(slot), slot);
		break;
	case 214:
		interfaceId = in.readShort(StreamBuffer.ValueType.A, StreamBuffer.ByteOrder.LITTLE);
		int transfer = in.readByte(StreamBuffer.ValueType.C);
		int fromSlot = in.readShort(StreamBuffer.ValueType.A, StreamBuffer.ByteOrder.LITTLE);
		int toSlot = in.readShort(StreamBuffer.ByteOrder.LITTLE);

		if (Constants.DEV_MODE) {
			player.send(new SendMessage("Item packet 214"));
		}

		switch (interfaceId) {
		case 5382:
			if (player.getTrade().trading()) {
				player.send(new SendMessage("You can not do that right now!"));
				return;
			}

			if (!player.getBank().isSearching()) {
				if (transfer == 2) {
					player.getBank().itemToTab(fromSlot, toSlot, true);
				} else {
					if (transfer == 1) {
						int fromTab = player.getBank().getData(fromSlot, 0);
						int toTab = player.getBank().getData(toSlot, 0);
						player.getBank().changeTabAmount(fromTab, -1, true);
						RearrangeTypes temp = player.getBank().rearrangeType;
						player.getBank().rearrangeType = RearrangeTypes.INSERT;
						player.getBank().swap(toSlot - (toTab > fromTab ? 1 : 0), fromSlot);
						player.getBank().rearrangeType = temp;
						player.getBank().update();
					} else {
						RearrangeTypes temp = player.getBank().rearrangeType;
						player.getBank().rearrangeType = RearrangeTypes.SWAP;
						player.getBank().swap(toSlot, fromSlot);
						player.getBank().rearrangeType = temp;
					}
				}
			}
			break;
		case 3214:
		case 5064:
			player.getInventory().swap(toSlot, fromSlot, false);
			break;
		}

		break;
	case 87:
		itemId = in.readShort(StreamBuffer.ValueType.A);
		in.readShort();
		slot = in.readShort(StreamBuffer.ValueType.A);

		if (!player.getInventory().slotContainsItem(slot, itemId)) {
			return;
		}

		if (player.getMagic().isTeleporting() || !player.getController().canDrop(player)) {
			return;
		}

		if (Constants.DEV_MODE) {
			player.send(new SendMessage("Item packet 87"));
		}

		if (ToxicBlowpipe.itemOption(player, 4, itemId)) {
			return;
		}

		if (TridentOfTheSeas.itemOption(player, 4, itemId)) {
			return;
		}

		if (TridentOfTheSwamp.itemOption(player, 4, itemId)) {
			return;
		}

		if (SerpentineHelmet.itemOption(player, 4, itemId)) {
			return;
		}

		if (itemId == 4045) {
			player.getUpdateFlags().sendAnimation(new Animation(827));
			player.getInventory().remove(new Item(4045, 1));
			player.hit(new Hit(15));
			player.getUpdateFlags().sendForceMessage("Ow! That really hurt my soul!");
			return;
		}

		if (BossPets.spawnPet(player, itemId, false)) {
			return;
		}

		if (player.getRights() == 2) {
			player.send(new SendMessage("You may not do this since you are an Administrator!"));
			return;
		}

		for (int index = 0; index < Constants.ITEM_DISMANTLE_DATA.length; index++) {
			if (itemId == Constants.ITEM_DISMANTLE_DATA[index][0]) {
				player.getInventory().remove(itemId, 1);
				player.getInventory().addOrCreateGroundItem(Constants.ITEM_DISMANTLE_DATA[index][1], 1, true);
				player.getInventory().addOrCreateGroundItem(Constants.ITEM_DISMANTLE_DATA[index][2], 1, true);
				player.send(new SendMessage("You have dismantled your " + GameDefinitionLoader.getItemDef(itemId).getName() + "."));
				player.send(new SendRemoveInterfaces());
				return;
			}
		}

		if (!Item.getDefinition(itemId).isTradable() || Item.getDefinition(itemId).getName().contains("Clue scroll")) {
			player.start(new OptionDialogue("</col>Drop and loose forever", p -> {
				player.getInventory().remove(itemId, 1);
				player.send(new SendMessage("Your " + GameDefinitionLoader.getItemDef(itemId).getName() + " has been dropped and lost forever."));
				player.send(new SendRemoveInterfaces());
			}, "Keep " + GameDefinitionLoader.getItemDef(itemId).getName(), p -> {
				player.send(new SendRemoveInterfaces());
			}));
			return;
		}

		player.getGroundItems().drop(itemId, slot);
		break;
	case 236:
		int y = in.readShort(StreamBuffer.ByteOrder.LITTLE);
		itemId = in.readShort();
		x = in.readShort(StreamBuffer.ByteOrder.LITTLE);

		if (player.getMagic().isTeleporting()) {
			return;
		}

		player.getCombat().reset();

		player.getGroundItems().pickup(x, y, itemId);
		break;
	case 53:
		int firstSlot = in.readShort();
		int secondSlot = in.readShort(StreamBuffer.ValueType.A);

		if ((!player.getInventory().slotHasItem(firstSlot)) || (!player.getInventory().slotHasItem(secondSlot))) {
			return;
		}

		if (player.getMagic().isTeleporting()) {
			return;
		}

		Item usedWith = player.getInventory().get(firstSlot);
		Item itemUsed = player.getInventory().get(secondSlot);

		if ((usedWith == null) || (itemUsed == null)) {
			return;
		}

		if ((usedWith.getId() == 890 && itemUsed.getId() == 590) || (usedWith.getId() == 590 && itemUsed.getId() == 890)) {
			if (!player.getInventory().hasItemAmount(890, 15)) {
				player.send(new SendMessage("You don't have enough adamant arrows."));
				return;
			}
			if (player.getSkill().getLevels()[Skills.FIREMAKING] <= 64) {
				player.send(new SendMessage("You don't have the correct firemaking level."));
				return;
			}
			player.getInventory().remove(890, 15);
			player.getInventory().add(2538, 15);
			player.getSkill().addExperience(Skills.FIREMAKING, 1);
			return;
		}

		if ((usedWith.getId() == 892 && itemUsed.getId() == 590) || (usedWith.getId() == 590 && itemUsed.getId() == 892)) {
			if (!player.getInventory().hasItemAmount(892, 15)) {
				player.send(new SendMessage("You don't have enough rune arrows."));
				return;
			}
			if (player.getSkill().getLevels()[Skills.FIREMAKING] <= 74) {
				player.send(new SendMessage("You don't have the correct firemaking level."));
				return;
			}
			player.getInventory().remove(892, 15);
			player.getInventory().add(2540, 15);
			player.getSkill().addExperience(Skills.FIREMAKING, 2);
			return;
		}
		if ((usedWith.getId() == 11212 && itemUsed.getId() == 590) || (usedWith.getId() == 590 && itemUsed.getId() == 11212)) {
			if (!player.getInventory().hasItemAmount(11212, 15)) {
				player.send(new SendMessage("You don't have enough dragon arrows."));
				return;
			}
			if (player.getSkill().getLevels()[Skills.FIREMAKING] <= 84) {
				player.send(new SendMessage("You don't have the correct firemaking level."));
				return;
			}
			player.getInventory().remove(11212, 15);
			player.getInventory().add(11217, 15);
			player.getSkill().addExperience(Skills.FIREMAKING, 3);
			return;
		}
		if ((usedWith.getId() == 13273 && itemUsed.getId() == 5520) || (usedWith.getId() == 5520 && itemUsed.getId() == 13273)) {
			player.getInventory().remove(13273, 1);
			Unsired.open(player);
		}

		if ((usedWith.getId() == 10534 && itemUsed.getId() == 10540) || (usedWith.getId() == 10540 && itemUsed.getId() == 10534)) {
			player.getInventory().remove(10534, 1);
			player.getInventory().remove(10540, 1);
			player.getInventory().add(10535, 1);
		}
		if ((usedWith.getId() == 10535 && itemUsed.getId() == 10561) || (usedWith.getId() == 10561 && itemUsed.getId() == 10535)) {
			player.getInventory().remove(10535, 1);
			player.getInventory().remove(10561, 1);
			player.getInventory().add(10537, 1);
		}
		if ((usedWith.getId() == 13446 && itemUsed.getId() == 1755) || (usedWith.getId() == 1755 && itemUsed.getId() == 13446)) {
			player.getInventory().remove(13446, 1);
			player.incrementEssenseFragment(4);
			player.getSkill().addExperience(Skills.CRAFTING, 10);
		}

		if ((usedWith.getId() == 749 && itemUsed.getId() == 1755) || (usedWith.getId() == 1755 && itemUsed.getId() == 749)) {
			ZulrahTotem.open(player);
			AchievementHandler.activateAchievement(player, AchievementList.OBTAIN_10_RARE_DROPS, 1);
			return;
		}

		if ((usedWith.getId() == 985 && itemUsed.getId() == 987) || (usedWith.getId() == 987 && itemUsed.getId() == 985)) {
			player.getInventory().remove(985, 1);
			player.getInventory().remove(987, 1);
			player.getInventory().add(989, 1);
			return;
		}
		// Dismantling for scales
		if ((usedWith.getId() == 2347 && itemUsed.getId() == 12922) || (usedWith.getId() == 12922 && itemUsed.getId() == 2347)) {
			player.getInventory().remove(12922, 1);
			player.getInventory().add(12934, 10000);
			player.getUpdateFlags().sendForceMessage("HULK SMASH!");
			player.send(new SendMessage("You smash the Tanzanite fang, in the rubble you find 10k Zulrah Scales."));
			return;
		}

		if ((usedWith.getId() == 2347 && itemUsed.getId() == 12932) || (usedWith.getId() == 12932 && itemUsed.getId() == 2347)) {
			player.getInventory().remove(12932, 1);
			player.getInventory().add(12934, 10000);
			player.getUpdateFlags().sendForceMessage("HULK SMASH!");
			player.send(new SendMessage("You smash the Magic fang, in the rubble you find 10k Zulrah Scales."));
			return;
		}
		if (itemUsed.getId() == 11791 && usedWith.getId() == 12932 || itemUsed.getId() == 12932 && usedWith.getId() == 11791) {
			player.getInventory().remove(itemUsed.getId(), 1);
			player.getInventory().remove(usedWith.getId(), 1);
			player.getInventory().add(12904,1);
			player.send(new SendMessage("You made a Toxic staff of the dead.."));
			return;
		}
		if ((usedWith.getId() == 2347 && itemUsed.getId() == 12927) || (usedWith.getId() == 12927 && itemUsed.getId() == 2347)) {
			player.getInventory().remove(12927, 1);
			player.getInventory().add(12934, 10000);
			player.getUpdateFlags().sendForceMessage("HULK SMASH!");
			player.send(new SendMessage("You smash the Serpentine Visage, in the rubble you find 10k Zulrah Scales."));
			return;
		}

		if ((usedWith.getId() == 6949 && itemUsed.getId() == 11864) || (usedWith.getId() == 11864 && itemUsed.getId() == 6949)) {
			player.getInventory().remove(6949, 1);
			player.getInventory().remove(11864, 1);
			player.getInventory().add(11865, 1);
			return;
		}

		if ((usedWith.getId() == 1927 && itemUsed.getId() == 2398) || (usedWith.getId() == 2398 && itemUsed.getId() == 1927)) {
			player.getInventory().remove(1927, 1);
			player.getInventory().remove(2398, 1);
			player.getInventory().add(7471, 1);
			return;
		}
		if ((usedWith.getId() == 7471 && itemUsed.getId() == 1975) || (usedWith.getId() == 1975 && itemUsed.getId() == 7471)) {
			player.getInventory().remove(1975, 1);
			player.getInventory().remove(7471, 1);
			player.getInventory().add(1977, 1);
			return;
		}

		// Cerberus boots
		if ((usedWith.getId() == 13231 && itemUsed.getId() == 11840) || (usedWith.getId() == 11840 && itemUsed.getId() == 13231)) {
			player.getInventory().remove(11840, 1);
			player.getInventory().remove(13231, 1);
			player.getInventory().add(13239, 1);
			player.getSkill().addExperience(Skills.MAGIC, 65);
			player.getSkill().addExperience(Skills.RUNECRAFTING, 65);
			return;
		}
		if ((usedWith.getId() == 13229 && itemUsed.getId() == 2577) || (usedWith.getId() == 2577 && itemUsed.getId() == 13229)) {
			player.getInventory().remove(2577, 1);
			player.getInventory().remove(13229, 1);
			player.getInventory().add(13237, 1);
			player.getSkill().addExperience(Skills.MAGIC, 65);
			player.getSkill().addExperience(Skills.RUNECRAFTING, 65);
			return;
		}
		if ((usedWith.getId() == 13227 && itemUsed.getId() == 6920) || (usedWith.getId() == 6920 && itemUsed.getId() == 13227)) {
			player.getInventory().remove(6920, 1);
			player.getInventory().remove(13227, 1);
			player.getInventory().add(13235, 1);
			player.getSkill().addExperience(Skills.MAGIC, 70);
			player.getSkill().addExperience(Skills.RUNECRAFTING, 65);
			return;
		}
		// infernal pickaxe
		if ((usedWith.getId() == 13233 && itemUsed.getId() == 11920) || (usedWith.getId() == 11920 && itemUsed.getId() == 13233)) {
			player.getInventory().remove(11920, 1);
			player.getInventory().remove(13233, 1);
			player.getInventory().add(13243, 1);
			player.getUpdateFlags().sendAnimation(4514, 0);
			player.getUpdateFlags().sendGraphic(new Graphic(1240));
			return;
		}
		// infernal axe
		if ((usedWith.getId() == 13233 && itemUsed.getId() == 6739) || (usedWith.getId() == 6739 && itemUsed.getId() == 13233)) {
			player.getInventory().remove(6739, 1);
			player.getInventory().remove(13233, 1);
			player.getInventory().add(13241, 1);
			player.getUpdateFlags().sendAnimation(4512, 0);
			player.getUpdateFlags().sendGraphic(new Graphic(1240));
			return;
		}
		// max cape creation
		// Fire
		if ((usedWith.getId() == 6570 && itemUsed.getId() == 13280) || (usedWith.getId() == 13280 && itemUsed.getId() == 6570)) {
			player.getInventory().remove(13280, 1);
			player.getInventory().remove(13281, 1);
			player.getInventory().remove(6570, 1);
			player.getInventory().add(13330, 1);
			player.getInventory().add(13329, 1);
			return;
		}
		// Zamorak
		if ((usedWith.getId() == 2414 && itemUsed.getId() == 13280) || (usedWith.getId() == 13280 && itemUsed.getId() == 2414)) {
			player.getInventory().remove(13280, 1);
			player.getInventory().remove(13281, 1);
			player.getInventory().remove(2414, 1);
			player.getInventory().add(13334, 1);
			player.getInventory().add(13333, 1);
			return;
		}
		// Guthix
		if ((usedWith.getId() == 2413 && itemUsed.getId() == 13280) || (usedWith.getId() == 13280 && itemUsed.getId() == 2413)) {
			player.getInventory().remove(13280, 1);
			player.getInventory().remove(13281, 1);
			player.getInventory().remove(2413, 1);
			player.getInventory().add(13336, 1);
			player.getInventory().add(13335, 1);
			return;
		}
		// Saradomin
		if ((usedWith.getId() == 2412 && itemUsed.getId() == 13280) || (usedWith.getId() == 13280 && itemUsed.getId() == 2412)) {
			player.getInventory().remove(13280, 1);
			player.getInventory().remove(13281, 1);
			player.getInventory().remove(2412, 1);
			player.getInventory().add(13332, 1);
			player.getInventory().add(13331, 1);
			return;
		}
		// Ava's
		if ((usedWith.getId() == 10499 && itemUsed.getId() == 13280) || (usedWith.getId() == 13280 && itemUsed.getId() == 10499)) {
			player.getInventory().remove(13280, 1);
			player.getInventory().remove(13281, 1);
			player.getInventory().remove(10499, 1);
			player.getInventory().add(13338, 1);
			player.getInventory().add(13337, 1);
			return;
		}
		// Weapon Poison start
		if ((usedWith.getId() == 5974 && itemUsed.getId() == 2347) || (usedWith.getId() == 2347 && itemUsed.getId() == 5974)) {
			player.getInventory().remove(5974, 1);
			player.getInventory().add(5978, 1);
			player.getInventory().add(5935, 1);
			player.send(new SendMessage("You break open the coconut and extract the milk."));
		}
		if ((usedWith.getId() == 6016 && itemUsed.getId() == 5935) || (usedWith.getId() == 5935 && itemUsed.getId() == 6016)) {
			player.getInventory().remove(5935, 1);
			player.getInventory().remove(6016, 1);
			player.getInventory().add(5936, 1);
			player.send(new SendMessage("You create a half-deadly weapon poison."));
		}
		if ((usedWith.getId() == 223 && itemUsed.getId() == 5936) || (usedWith.getId() == 5936 && itemUsed.getId() == 223)) {
			player.getInventory().remove(5936, 1);
			player.getInventory().remove(223, 1);
			player.getInventory().add(5937, 1);
			player.send(new SendMessage("You create a deadly weapon poison."));
		} // end of weapon poison

		if ((itemUsed.getId() == 15733)) {
			player.getInventory().remove(usedWith.getId());
			player.send(new SendMessage("The Rotten potato somehow swallows up that item..."));
		}
		if (Firemaking.attemptFiremaking(player, itemUsed, usedWith)) {
			return;
		}

		if (Fletching.SINGLETON.itemOnItem(player, usedWith, itemUsed)) {
			return;
		}

		if (Crafting.SINGLETON.itemOnItem(player, usedWith, itemUsed)) {
			return;
		}

		if (ItemCreating.handle(player, itemUsed.getId(), usedWith.getId())) {
			return;
		}

		if (ToxicBlowpipe.itemOnItem(player, itemUsed, usedWith)) {
			return;
		}

		if (TridentOfTheSeas.itemOnItem(player, itemUsed, usedWith)) {
			return;
		}

		if (TridentOfTheSwamp.itemOnItem(player, itemUsed, usedWith)) {
			return;
		}

		if (SuperCombatPotion.itemOnItem(player, itemUsed, usedWith)) {
			return;
		}

		if (SerpentineHelmet.itemOnItem(player, itemUsed, usedWith)) {
			return;
		}

		if (itemUsed.getId() == 1759 || usedWith.getId() == 1759) {
			AmuletStringing.stringAmulet(player, itemUsed.getId(), usedWith.getId());
			return;
		}

		if ((usedWith.getId() == 227) || (itemUsed.getId() == 227)) {
			HerbloreUnfinishedPotionTask.displayInterface(player, itemUsed, usedWith);
		} else if (!HerbloreFinishedPotionTask.displayInterface(player, itemUsed, usedWith)) {
			if ((usedWith.getId() == 233) || (itemUsed.getId() == 233)) {
				HerbloreGrindingTask.handleGrindingIngredients(player, itemUsed, usedWith);
			} else if (!Firemaking.attemptFiremaking(player, itemUsed, usedWith)) {
				if ((usedWith.getId() == 1785) || (itemUsed.getId() == 1785)) {
					if ((usedWith.getId() == 1785) && (itemUsed.getId() == 1775))
						player.getClient().queueOutgoingPacket(new SendInterface(11462));
					else if ((itemUsed.getId() == 1785) && (usedWith.getId() == 1775)) {
						player.getClient().queueOutgoingPacket(new SendInterface(11462));
					}

				}

				if (PotionDecanting.decant(player, firstSlot, secondSlot)) {
					return;
				}

			}
		}

		break;
	case 25:
		in.readShort();
		int itemInInven = in.readShort(StreamBuffer.ValueType.A);
		int groundItem = in.readShort();
		y = in.readShort(StreamBuffer.ValueType.A);
		z = player.getLocation().getZ();
		in.readShort();
		x = in.readShort();

	case 237:
		slot = in.readShort();
		itemId = in.readShort(StreamBuffer.ValueType.A);
		interfaceId = in.readShort();
		magicId = in.readShort(StreamBuffer.ValueType.A);

		if (!player.getInterfaceManager().verify(interfaceId)) {
			return;
		}

		if (!player.getInventory().slotContainsItem(slot, itemId)) {
			return;
		}

		if (player.getMagic().isTeleporting()) {
			return;
		}

		player.getAttributes().set("magicitem", Integer.valueOf(itemId));
		player.getMagic().useMagicOnItem(itemId, magicId);
		break;
	case 181:
		y = in.readShort(StreamBuffer.ByteOrder.LITTLE);
		itemId = in.readShort();
		x = in.readShort(StreamBuffer.ByteOrder.LITTLE);
		magicId = in.readShort(StreamBuffer.ValueType.A);
		break;
	case 253:// second click ground item
		x = in.readShort(StreamBuffer.ByteOrder.LITTLE);
		y = in.readShort(StreamBuffer.ValueType.A, StreamBuffer.ByteOrder.LITTLE);
		itemId = in.readShort(StreamBuffer.ValueType.A);
		z = player.getLocation().getZ();

		break;
	case 122:

		if (Constants.DEV_MODE) {
			player.send(new SendMessage("Item packet 122"));
		}

		interfaceId = in.readShort(StreamBuffer.ValueType.A, StreamBuffer.ByteOrder.LITTLE);
		slot = in.readShort(StreamBuffer.ValueType.A);
		itemId = in.readShort(StreamBuffer.ByteOrder.LITTLE);

		CleanHerbTask.attemptHerbCleaning(player, slot);
		if (player.getDelay().elapsed() < 1000) {
			return;
		}
		if (!player.getInventory().slotContainsItem(slot, itemId)) {
			return;
		}

		if (itemId == 7788) {
			player.start(new slayerhelmImbue(player));
		}

		if (itemId == 7938) {
			player.send(new SendMessage("You currently have " + player.getEssenceFragments() + " Essence stored."));
		}

		if (itemId == 11740) {

			player.start(new dicerScrollDialogue(player));
			/*
			 * player.isDicer = true; player.getInventory().remove(11740);
			 * World.sendGlobalMessage("@red@"+ player.getUsername()
			 * +" has just redeemed their dice bag, and can now host dice games."
			 * );
			 */
		}

		if (itemId == 12020)
			if (System.currentTimeMillis() - player.diceDelay >= 3000) {
				if (player.isDicer) {
					int roll = RandomUtils.diceRoll();
					String msg = "<img=8>@red@Dicing@blu@ - " + player.getDisplay() + " just rolled @red@" + roll + "/100 @blu@on the percentile dice.";
					player.clan.sendMessage(msg);
					player.diceDelay = System.currentTimeMillis();
				} else {
					player.send(new SendMessage("You have to redeem the Dicer scroll before using this item."));
				}
			}

		if (itemId == 5733) {
			player.start(new RottenPotato(player));
		}

		if (player.getMagic().isTeleporting()) {
			return;
		}

		if (ClueScrollManager.SINGLETON.clickItem(player, itemId)) {
			return;
		}

		if (ItemOpening.openSet(player, itemId)) {
			return;
		}

		if (ItemInteraction.clickPouch(player, itemId, 1)) {
			return;
		}

		if (itemId == 4079) {
			player.getUpdateFlags().sendAnimation(1457, 0);
			return;
			/*
			 * }
			 * 
			 * if (DwarfMultiCannon.setCannonBase(player, itemId)) { return;
			 */
		}

		if (MembershipBonds.handle(player, itemId)) {
			return;
		}

		if (BoneBurying.bury(player, itemId, slot)) {
			return;
		}

		if ((player.getConsumables().consume(itemId, slot, ConsumableType.FOOD)) || (player.getConsumables().consume(itemId, slot, ConsumableType.POTION))) {
			return;
		}

		if (player.getMagic().clickMagicItems(itemId)) {
			return;
		}
		switch (itemId) {
		case 4278:
			player.setEnterXInterfaceId(55780);
			player.getClient().queueOutgoingPacket(new SendEnterString());
			break;
		case 6199:// Mystery Box
			MysteryBox.open(player);
			break;
		case 12789:// Elite Box
			StrangeBox.open(player);
			break;
		case 292:
			player.getMagic().teleport(1890, 3858, player.getIndex() << 2, TeleportTypes.SPELL_BOOK);
			TaskQueue.queue(new Task(5) {
				@Override
				public void execute() {
				int[][] DATA = { { 6379, 1899, 3858 } };
				for (int i = 0; i < DATA.length; i++) {
					new Mob(player, DATA[i][0], false, false, false, new Location(DATA[i][1], DATA[i][2], player.getZ()));
				}
				stop();
				}

				@Override
				public void onStop() {
				player.send(new SendMessage("Welcome to Dzone Boss!"));
				}
			});
			break;
		case 7959:
			if (player.getCombat().inCombat()) {
				player.send(new SendMessage("You cannot open this while in combat."));
				return;
			}
			LegendaryBox.open(player);
			break;
		case 607:
			if (player.getInventory().hasItemAmount(13307, 10000)) {
				PrizeBox.open(player);
				player.getInventory().remove(13307, 10000);
				player.getInventory().remove(607, 1);
				player.send(new SendMessage("You spent 10k Blood Money to open the prize box.."));
			} else {
				player.send(new SendMessage("You need 10k Blood Money to Use the prize box."));
			}
			break;
		case 608:
			player.setCredits(player.getCredits() + 20);
			InterfaceHandler.writeText(new CreditTab(player));
			InterfaceHandler.writeText(new QuestTab(player));
			player.getClient().queueOutgoingPacket(new SendMessage("You gain 20 Credits from the Scroll."));
			player.getInventory().remove(608, 1);
			break;
		case 600:// bank book
			player.getBank().openBank();
			break;

		case 13273:// unsired
			Unsired.open(player);
			break;

		case 10537:// unsired
			OmegaEgg.open(player);
			break;

		case 962:// Cracker
			ChristmasCracker.open(player);
			break;

		case 12846:
			if (TargetSystem.getInstance().playerHasTarget(player)) {
				Player target = World.getPlayers()[player.targetIndex];
				if (target != null) {
					player.getMagic().teleport(target.getLocation(), TeleportTypes.SPELL_BOOK);
					player.getInventory().remove(12846, 1);
					player.send(new SendMessage("You have teleported to your target."));
				}
			} else {
				player.send(new SendMessage("You do not have a target to teleport to!"));
			}
			break;

		case 405:// Casket
			player.getInventory().remove(itemId, 1);
			int random = Utility.random(100000) + Utility.random(25000) + Utility.random(666666);
			player.getInventory().add(995, random);
			AchievementHandler.activateAchievement(player, AchievementList.OPEN_10_CASKET, 1);
			// AchievementHandler.activateAchievement(player,
			// AchievementList.OPEN_50_CASKET, 1);
			player.send(new SendMessage("You have found " + random + " coins inside the casket"));
			break;

		case 11273:// impling scroll
			int random1 = Utility.random(25);
			if (random1 == 1) {
				player.getInventory().add(2680, 1);
				player.send(new SendMessage("You have found an Easy clue scroll"));
				AchievementHandler.activateAchievement(player, AchievementList.KILL_1_PLAYER, 1);
			}
			if (random1 == 2) {
				player.getInventory().add(2801, 1);
				player.send(new SendMessage("You have found an Medium clue scroll"));
				AchievementHandler.activateAchievement(player, AchievementList.KILL_1_PLAYER, 1);
			}
			if (random1 == 3) {
				player.getInventory().add(2722, 1);
				player.send(new SendMessage("You have found an Hard clue scroll"));
				AchievementHandler.activateAchievement(player, AchievementList.KILL_1_PLAYER, 1);
			}
			player.getInventory().remove(11273, 1);
			break;

		case 10952:// slayer bell
			player.getMagic().teleport(2441, 9806, 0, TeleportTypes.SPELL_BOOK);
			player.send(new SendMessage("The magic from the bell teleports you."));
			break;

		case 16:// Magic whistle aka black night titan
			player.getMagic().teleport(2792, 4722, 0, TeleportTypes.SPELL_BOOK);
			player.send(new SendMessage("The magic whistle teleports you to a distance kingdom"));
			break;

		case 12938:// Zulrah teleport
			player.getInventory().remove(12938, 1);
			player.getMagic().teleport(2197, 3056, 0, TeleportTypes.SPELL_BOOK);
			break;

		case 13249:// Cerberus teleport
			player.getInventory().remove(13249, 1);
			player.getMagic().teleport(1240, 1236, player.getIndex() << 2, TeleportTypes.SPELL_BOOK);
			TaskQueue.queue(new Task(5) {
				@Override
				public void execute() {
				int[][] DATA = { { 5862, 1239, 1246 } };
				for (int i = 0; i < DATA.length; i++) {
					new Mob(player, DATA[i][0], false, false, false, new Location(DATA[i][1], DATA[i][2], player.getZ()));
				}
				stop();
				}

				@Override
				public void onStop() {
				player.send(new SendMessage("Welcome to Cerberus Lair."));
				}
			});
			break;

		case 1977:// cow teleport
			player.getInventory().remove(1977, 1);
			player.getInventory().add(1925, 1);
			player.getMagic().teleport(3256, 3282, player.getIndex() << 2, TeleportTypes.SPELL_BOOK);
			TaskQueue.queue(new Task(5) {
				@Override
				public void execute() {
				EnragedCow.starterdEnraged = true;
				new Mob(player, 2806, false, false, false, new Location(3256, 3284, player.getZ()));
				stop();
				}

				@Override
				public void onStop() {
				player.send(new SendMessage("@dre@Its just a little cow.. what harm could it do?"));
				}
			});
			break;
		case 2528:// Lamp
			player.send(new SendInterface(2808));
			break;

		case 952:// Spade
			TaskQueue.queue(new DigTask(player));
			return;

		case 4283:// Slayer reset
			player.send(new SendMessage("You Reset your slayer task."));
			player.getSlayer().reset();
			player.getSlayer().assign(Slayer.SlayerDifficulty.HIGH);
			String task = player.getSlayer().getTask();
			byte am = player.getSlayer().getAmount();
			DialogueManager.sendNpcChat(player, 403, Emotion.CALM, new String[] { "You have been assigned the task of killing:", "@blu@" + am + " " + task, });
			InterfaceHandler.writeText(new CreditTab(player));
			InterfaceHandler.writeText(new QuestTab(player));
			break;

		case 6479:// Slayer reset
			player.send(new SendMessage("You Reset your slayer task."));
			player.getSlayer().reset();
			player.getSlayer().assign(Slayer.SlayerDifficulty.BOSS);
			String tasks = player.getSlayer().getTask();
			byte amo = player.getSlayer().getAmount();
			DialogueManager.sendNpcChat(player, 403, Emotion.CALM, new String[] { "You have been assigned the task of killing:", "@blu@" + amo + " " + tasks, });
			InterfaceHandler.writeText(new CreditTab(player));
			InterfaceHandler.writeText(new QuestTab(player));
			break;

		case 7789:// Slayer Tome
			player.getSkill().addExperience(Skills.SLAYER, 4000);
			player.getInventory().remove(7789, 1);
			return;

		case 7783:// Agility Tome
			player.getSkill().addExperience(Skills.AGILITY, 1000);
			player.getInventory().remove(7783, 1);
			return;

		case 7798:// Woodcutting Tome
			player.getSkill().addExperience(Skills.WOODCUTTING, 4000);
			player.getInventory().remove(7798, 1);
			return;

		case 7795:// Firemaking Tome
			player.getSkill().addExperience(Skills.FIREMAKING, 4000);
			player.getInventory().remove(7795, 1);
			return;

		case 7792:// Mining Tome
			player.getSkill().addExperience(Skills.MINING, 2000);
			player.getInventory().remove(7792, 1);
			return;

		case 7786:// Thieving Tome
			player.getSkill().addExperience(Skills.THIEVING, 4000);
			player.getInventory().remove(7786, 1);
			return;

		case 7780:// Fishing Tome
			player.getSkill().addExperience(Skills.FISHING, 4000);
			player.getInventory().remove(7780, 1);
			return;

		case 7509:// rock cake
			// player.getUpdateFlags().sendAnimation(new Animation(827));
			player.hit(new Hit(5));
			return;

		case 11169:// news paper
			player.send(new SendString("http://www.Horizon-os.com/forum", 12000));
			// player.send(new
			// SendString("http://Horizon-os.com/forums/index.php?/forum/21-server-updates/",
			// 12000));
			player.send(new SendMessage("Loading website page..."));
			return;

		case 748:// holy force
			if (player.getCredits() > 1) {
				player.setCredits(player.getCredits() - 2);
				InterfaceHandler.writeText(new CreditTab(player));
				InterfaceHandler.writeText(new QuestTab(player));
				player.getUpdateFlags().sendAnimation(new Animation(645));
				player.getSkill().setLevel(Skills.PRAYER, player.getMaxLevels()[Skills.PRAYER]);
				player.getClient().queueOutgoingPacket(new SendMessage("You recharge your prayer points costing you 2 Credits."));
			} else {
				player.send(new SendMessage("You don't have any donator credits, purchase more to use this again!"));
			}
			break;

		case 4155:// Slayer gem
			if (!player.getSlayer().hasTask()) {
				DialogueManager.sendStatement(player, "You currently do not have a task!");
				return;
			}
			DialogueManager.sendStatement(player, "You have been tasked to kill:", player.getSlayer().getAmount() + " " + player.getSlayer().getTask());
			return;

		case 13188:
			player.getEquipment().equip(player.getInventory().get(slot), slot);
			break;

		case 3842:
			AchievementHandler.activateAchievement(player, AchievementList.OBTAIN_3_BOSS_PET, 1);
			break;

		case 11920:
			player.send(new SendMessage("God please no!"));
			break;
		}
		player.getDelay().reset();
		break;
	case 16:
		itemId = in.readShort(StreamBuffer.ValueType.A);
		slot = in.readShort(StreamBuffer.ValueType.A, StreamBuffer.ByteOrder.LITTLE);
		interfaceId = in.readShort(StreamBuffer.ValueType.A, StreamBuffer.ByteOrder.LITTLE);

		if (Constants.DEV_MODE) {
			player.send(new SendMessage("Item packet 16"));
		}

		if (!player.getInventory().slotContainsItem(slot, itemId)) {
			return;
		}
		if (itemId == 11738) {

		}
		if (itemId == 9751) {
			player.getMagic().teleportNoWildernessRequirement(2878, 3546, 0, MagicSkill.TeleportTypes.SPELL_BOOK);
			player.send(new SendMessage("Your cape teleports you to the Warriors guild."));
		}
		if (itemId == 11864 || itemId == 11865) {
			DialogueManager.sendStatement(player, "You have been asked to kill: " + player.getSlayer().getAmount() + " " + player.getSlayer().getTask());
		}
		if (itemId == 13128) {
			player.getMagic().teleport(3060, 3262, 0, TeleportTypes.SPELL_BOOK);
			player.send(new SendMessage("You teleport to the Draynor Spirit Tree patch."));
		}
		if (itemId == 13136) {
			player.getMagic().teleport(3426, 2928, 0, TeleportTypes.SPELL_BOOK);
			player.send(new SendMessage("You teleport to the Statue of Elid."));
		}
		if (itemId == 4283) {
			player.getInventory().remove(4283, 1);
			player.getInventory().add(6479, 1);
			player.send(new SendMessage("Your form will now give you Boss tasks."));
		}
		if (itemId == 6479) {
			player.getInventory().remove(6479, 1);
			player.getInventory().add(4283, 1);
			player.send(new SendMessage("Your form will now give you Hard tasks."));
		}
		if (itemId == 12924) {
			player.getInventory().remove(12924, 1);
			player.getInventory().add(12922, 1);
		}
		if (itemId == 12929) {
			player.getInventory().remove(12929, 1);
			player.getInventory().add(12927, 1);
		}
		if (itemId == 2572) {
			int linePosition = 8145;
			HashMap<String, Integer> map = player.getProperties().getPropertyValues("MOB");

			for (String key : map.keySet()) {
				String line = Utility.formatPlayerName(key.toLowerCase().replaceAll("_", " ")) + ": " + map.get(key);
				player.send(new SendString("Boss Kill Log", 8144));
				player.send(new SendString(line, linePosition++));
			}

			map = player.getProperties().getPropertyValues("BARROWS");
			for (String key : map.keySet()) {
				String line = Utility.formatPlayerName(key.toLowerCase().replaceAll("_", " ")) + ": " + map.get(key);
				player.send(new SendString(line, linePosition++));
			}

			while (linePosition < 8193) {
				player.send(new SendString("", linePosition++));
			}

			player.send(new SendInterface(8134));
		}

		if (itemId == 15733) {
			player.start(new RottenPotato2(player));
		}

		if (player.getMagic().isTeleporting()) {
			return;
		}

		if (ItemInteraction.clickPouch(player, itemId, 3)) {
			return;
		}

		if (ImplingRewards.impReward.containsKey(itemId)) {
			ImplingRewards.lootImpling(player, itemId);
			return;
		}

		if (ToxicBlowpipe.itemOption(player, 1, itemId)) {
			return;
		}

		if (TridentOfTheSeas.itemOption(player, 1, itemId)) {
			return;
		}

		if (TridentOfTheSwamp.itemOption(player, 1, itemId)) {
			return;
		}

		if (SerpentineHelmet.itemOption(player, 1, itemId)) {
			return;
		}

		if (itemId == 4079) {
			player.getUpdateFlags().sendAnimation(1459, 0);
			return;
		}

		if (itemId == 11866 || itemId == 11867 || itemId == 11868 || itemId == 11869 || itemId == 11870 || itemId == 11871 || itemId == 11872 || itemId == 11873) {
			player.start(new RingOfSlayingDialogue(player));
		}

		if (itemId == 13103) {
			player.start(new RingOfSlayingDialogue(player));
		}
		if (itemId == 13111) {
			player.start(new EliteSword(player, true, itemId));
		}
		if (itemId == 13144) {
			player.start(new EliteBanner(player, true, itemId));
		}
		if (itemId == 13124) {
			player.start(new EliteCloak(player, true, itemId));
		}
		if (itemId == 13140) {
			player.start(new EliteHelm(player, true, itemId));
		}
		if (itemId == 13107) { // elite platebody
			player.getMagic().teleport(3204, 3491, 2, TeleportTypes.SPELL_BOOK);
		}
		if (itemId == 13115) { // elite platelegs
			player.getMagic().teleport(3613, 3189, 0, TeleportTypes.SPELL_BOOK);
		}

		if (itemId == 13280 || itemId == 13333 || itemId == 13329 || itemId == 13331 || itemId == 13337 || itemId == 13335) {
			player.start(new maxcapeTeleportsDialogue(player));
		}
		if (itemId == 11283) {
			player.getClient().queueOutgoingPacket(new SendMessage("Your shield has " + player.getMagic().getDragonFireShieldCharges() + " charges."));
			return;
		}

		switch (itemId) {

		case 11802:// ags
		case 11804:// bgs
		case 11806:// sgs
		case 11808:// zgs
			int[][] items = { { 11802, 11810 }, { 11804, 11812 }, { 11806, 11814 }, { 11808, 11816 } };
			if (player.getInventory().getFreeSlots() < 1) {
				DialogueManager.sendItem1(player, "You need at least one free slot to dismantle your godsword.", itemId);
				return;
			}
			for (int i = 0; i < items.length; i++) {
				if (itemId == items[i][0] && player.getInventory().hasItemAmount(items[i][0], 1)) {
					player.getInventory().remove(items[i][0], 1);
					player.getInventory().add(items[i][1], 1);
					player.getInventory().add(11798, 1);
					DialogueManager.sendItem2zoom(player, "You carefully attempt to dismantly your godsword...", "@dre@You were successful!", items[i][1], 11798);
					break;
				}
			}
			break;

		}

		break;
	case 75:
		if (Constants.DEV_MODE) {
			player.send(new SendMessage("Item packet 75"));
		}

		interfaceId = in.readShort(StreamBuffer.ValueType.A, StreamBuffer.ByteOrder.LITTLE);
		slot = in.readShort(StreamBuffer.ByteOrder.LITTLE);
		itemId = in.readShort(true, StreamBuffer.ValueType.A);

		if (!player.getInventory().slotContainsItem(slot, itemId)) {
			return;
		}

		if (player.getMagic().isTeleporting()) {
			return;
		}

		if (ToxicBlowpipe.itemOption(player, 3, itemId)) {
			return;
		}

		if (TridentOfTheSeas.itemOption(player, 3, itemId)) {
			return;
		}

		if (TridentOfTheSwamp.itemOption(player, 3, itemId)) {
			return;
		}

		if (itemId == 11773) {
			player.start(new imbueDialogue(player));
		}
		if (itemId == 11864) {
			player.getInventory().remove(11864, 1);
			player.getInventory().add(4168, 1);
			player.getInventory().add(4164, 1);
			player.getInventory().add(4166, 1);
			player.getInventory().add(8921, 1);
			player.getInventory().add(4551, 1);
			player.getInventory().add(4155, 1);
			player.send(new SendMessage("You have just disassembled your slayer helmet."));

		}
		if (itemId == 12924) {
			player.getInventory().remove(12924, 1);
			player.getInventory().add(12922, 1);
		}
		if (itemId == 12929) {
			player.getInventory().remove(12929, 1);
			player.getInventory().add(12927, 1);
		}

		if (itemId == 1712 || itemId == 1710 || itemId == 1708 || itemId == 1706 || itemId == 11976 || itemId == 11978) {
			player.start(new GloryDialogue(player, false, itemId));
			return;
		}
		if (itemId == 11905) {
			player.getInventory().remove(11905, 1);
			player.getInventory().add(11908, 1);
			player.send(new SendMessage("You now have the correct trident, it still requires a charge."));
			return;
		}
		if (itemId == 13111) {
			player.start(new EliteSword(player, true, itemId));
		}
		if (itemId == 13144) {
			player.start(new EliteBanner(player, true, itemId));
		}
		if (itemId == 13124) {
			player.start(new EliteCloak(player, true, itemId));
		}
		if (itemId == 13140) {
			player.start(new EliteHelm(player, true, itemId));
		}
		if (itemId == 13107) { // elite platebody
			player.getMagic().teleport(3204, 3491, 2, TeleportTypes.SPELL_BOOK);
		}
		if (itemId == 13115) { // elite platelegs
			player.getMagic().teleport(3613, 3189, 0, TeleportTypes.SPELL_BOOK);
		}
		if (itemId == 11980 || itemId == 11982 || itemId == 11984 || itemId == 11986 || itemId == 11988) {
			// hg
			return;
		}
		if (itemId == 2552 || itemId == 2554 || itemId == 2556 || itemId == 2558 || itemId == 2560 || itemId == 2562 || itemId == 2564 || itemId == 2566) {
			player.start(new RingOfDuelingDialogue(player, false, itemId));
			return;
		}
		if (itemId == 1704) {
			player.getClient().queueOutgoingPacket(new SendMessage("<col=C60DDE>This amulet is all out of charges."));
			return;
		}

		if (itemId == 4079) {
			player.getUpdateFlags().sendAnimation(1460, 0);
			return;
		}

		if (itemId == 995) {
			player.getPouch().addPouch();
			return;
		}

		break;
	}
	}

	/**
	 * Handle add item to trade
	 * 
	 * @param player
	 * @param slot
	 * @param itemId
	 * @param amount
	 */
	public void handleTradeOffer(Player player, int slot, int itemId, int amount) {
	player.getTrade().getContainer().offer(itemId, amount, slot);
	}

	/**
	 * Handle removing item from trade
	 * 
	 * @param player
	 * @param slot
	 * @param itemId
	 * @param amount
	 */
	public void handleTradeRemove(Player player, int slot, int itemId, int amount) {
	player.getTrade().getContainer().withdraw(slot, amount);
	}

	/**
	 * Withdraw bank item
	 * 
	 * @param player
	 * @param slot
	 * @param itemId
	 * @param amount
	 */
	public void withdrawBankItem(Player player, int slot, int itemId, int amount) {
	player.getBank().withdraw(itemId, amount);
	// player.getBank().itemToTab(slot, 0, true);
	}

	/**
	 * Bank item
	 * 
	 * @param player
	 * @param slot
	 * @param itemId
	 * @param amount
	 */
	public void bankItem(Player player, int slot, int itemId, int amount) {
	player.getBank().deposit(itemId, amount, slot);
	}

}
