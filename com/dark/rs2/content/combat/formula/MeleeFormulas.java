package com.dark.rs2.content.combat.formula;

import com.dark.rs2.content.skill.Skills;
import com.dark.rs2.content.skill.prayer.PrayerBook.Prayer;
import com.dark.rs2.content.skill.slayer.Slayer;
import com.dark.rs2.entity.Entity;
import com.dark.rs2.entity.World;
import com.dark.rs2.entity.item.Equipment;
import com.dark.rs2.entity.item.EquipmentConstants;
import com.dark.rs2.entity.item.Item;
import com.dark.rs2.entity.item.ItemCheck;
import com.dark.rs2.entity.mob.Mob;
import com.dark.rs2.entity.mob.MobConstants;
import com.dark.rs2.entity.player.Player;

/**
 * @author Valiant (http://www.rune-server.org/members/Valiant) Represents an
 *         attackers/victims rolls in melee combat
 * @since Todays Date
 */
public class MeleeFormulas {

	public static double getDefenceRoll(Entity attacking, Entity defending) {
		Player blocker = null;
		if (!defending.isNpc()) {
			blocker = com.dark.rs2.entity.World.getPlayers()[defending.getIndex()];
		} else {
			if (defending.getBonuses() != null) {
				return getEffectiveDefence(defending) + defending.getBonuses()[attacking.getAttackType().ordinal()];
			}
			return getEffectiveDefence(defending);
		}
		double effectiveDefence = getEffectiveDefence(defending);
		effectiveDefence += blocker.getBonuses()[5 + attacking.getAttackType().ordinal()] - 10;
		int styleBonusDefence = 0;
		if (blocker.getEquipment().getAttackStyle() == Equipment.AttackStyles.ACCURATE)
			styleBonusDefence += 3;
		else if (blocker.getEquipment().getAttackStyle() == Equipment.AttackStyles.CONTROLLED) {
			styleBonusDefence += 1;
		}
		effectiveDefence *= (1 + (styleBonusDefence) / 64);
		if (ItemCheck.wearingFullBarrows(blocker, "Verac")) {
			effectiveDefence *= 0.75;
		}
		return effectiveDefence;
	}

	public static double getEffectiveDefence(Entity entity) {
		Player blocker = null;
		if (!entity.isNpc()) {
			blocker = com.dark.rs2.entity.World.getPlayers()[entity.getIndex()];
		} else {
			if (entity.getLevels() != null) {
				return entity.getLevels()[1] + 8;
			}
			return 8;
		}
		double baseDefence = blocker.getSkill().getLevels()[1];
		if (blocker.getPrayer().active(Prayer.THICK_SKIN)) {
			baseDefence += 0.5;
		} else if (blocker.getPrayer().active(Prayer.ROCK_SKIN)) {
			baseDefence += 0.7;
		} else if (blocker.getPrayer().active(Prayer.STEEL_SKIN)) {
			baseDefence += 1.3;
		} else if (blocker.getPrayer().active(Prayer.CHIVALRY)) {
			baseDefence += 1.14;
		} else if (blocker.getPrayer().active(Prayer.PIETY)) {
			baseDefence *= 1.18;
		}
		return Math.floor(baseDefence) + 8;
	}

	public static double getAttackRoll(Entity entity) {
		Player attacker = null;

		if (!entity.isNpc()) {
			attacker = com.dark.rs2.entity.World.getPlayers()[entity.getIndex()];
			
			if (attacker == null) {
				return getEffectiveAccuracy(entity);
			}
		} else {
			return getEffectiveAccuracy(entity);
		}

		double specAccuracy = getSpecialAccuracy(attacker);
		double effectiveAccuracy = getEffectiveAccuracy(entity);
		int styleBonusAttack = 0;

		if (attacker.getEquipment().getAttackStyle() == Equipment.AttackStyles.ACCURATE)
			styleBonusAttack = 3;
		else if (attacker.getEquipment().getAttackStyle() == Equipment.AttackStyles.CONTROLLED) {
			styleBonusAttack = 1;
		}
		effectiveAccuracy *= (1 + (styleBonusAttack) / 64);
		
		if (ItemCheck.wearingFullBarrows(attacker, "Dharok")) {
			effectiveAccuracy *= 2.30;
		}
		return (int) (effectiveAccuracy * specAccuracy);
	}

	public static double getEffectiveAccuracy(Entity entity) {
		double attackBonus;
		double baseAttack;
		if (!entity.isNpc()) {
			Player attacker = World.getPlayers()[entity.getIndex()];
			if (attacker == null) {
				return 0.0;
			}
			attackBonus = attacker.getBonuses()[attacker.getAttackType().ordinal()];
			baseAttack = attacker.getLevels()[0];
			
			if (attacker.getPrayer().active(Prayer.CLARITY_OF_THOUGHT)) {
				baseAttack += 1.05;
			} else if (attacker.getPrayer().active(Prayer.IMPROVED_REFLEXES)) {
				baseAttack += 1.10;
			} else if (attacker.getPrayer().active(Prayer.INCREDIBLE_REFLEXES)) {
				baseAttack += 1.15;
			} else if (attacker.getPrayer().active(Prayer.CHIVALRY)) {
				baseAttack += 1.20;
			} else if (attacker.getPrayer().active(Prayer.PIETY)) {
				baseAttack *= 1.23;
			}
		} else {
			if (entity.getBonuses() != null) {
				attackBonus = entity.getBonuses()[entity.getAttackType().ordinal()];
			} else {
				attackBonus = 0;
			}
			
			if (entity.getLevels() != null) {
				baseAttack = entity.getLevels()[0];
			} else {
				baseAttack = 0;
			}
		}

		return Math.floor(baseAttack + attackBonus) + 16;
	}

	/**
	 * Calculates the attackers base damage output
	 * 
	 * @param player
	 * @param special
	 * @return the value
	 */
	public static double calculateBaseDamage(Player player) {

		Entity defending = player.getCombat().getAttacking();

		double base = 0;
		double effective = getEffectiveStr(player);
		double specialBonus = getSpecialStr(player);
		double strengthBonus = player.getBonuses()[10];

		base = (13 + effective + (strengthBonus / 8) + ((effective * strengthBonus) / 64)) / 10;

		if (player.getEquipment().getItems()[EquipmentConstants.WEAPON_SLOT] != null) {
			switch (player.getEquipment().getItems()[EquipmentConstants.WEAPON_SLOT].getId()) {
			case 4718:
			case 4886:
			case 4887:
			case 4888:
			case 4889:
				if (ItemCheck.wearingFullBarrows(player, "Dharok")) {
					int maximumHitpoints = player.getMaxLevels()[Skills.HITPOINTS];
					int currentHitpoints = player.getLevels()[Skills.HITPOINTS];
					double dharokEffect = ((maximumHitpoints - currentHitpoints) * 0.01) + 1.3;
					base *= dharokEffect;
					
				}
			}
		}

		Item helm = player.getEquipment().getItems()[0];
		Item weapon = player.getEquipment().getItems()[3];
		Item Gloves = player.getEquipment().getItems()[9];
		Item amulet = player.getEquipment().getItems()[2];
		
		if (((helm != null) && (helm.getId() == 11864)) || ((helm != null) && (helm.getId() == 19641)) || ((helm != null) && (helm.getId() == 19645)) || ((helm != null) && (helm.getId() == 19649)) || ((helm != null) && (helm.getId() == 15492)) || ((helm != null) && (helm.getId() == 11865) && (defending.isNpc()) && (player.getSlayer().hasTask()))) {
			Mob m = com.dark.rs2.entity.World.getNpcs()[defending.getIndex()];
			if ((m != null) && (Slayer.isSlayerTask(player, m))) {
				base += 0.15;
			}

		}

		if (((helm != null) && (helm.getId() == 12931))) {
			Mob m = com.dark.rs2.entity.World.getNpcs()[defending.getIndex()];
			if ((m != null)) {
			player.curePoison(100);
			}
		}
		
		if (((amulet != null) && (amulet.getId() == 13136)) && (player.getCombat().getAttacking().isNpc())) {
			Mob m = com.dark.rs2.entity.World.getNpcs()[player.getCombat().getAttacking().getIndex()];
			if ((m.getId() == 955 || m.getId() == 957 || m.getId() == 959 || m.getId() == 963) ) {
				base += 0.125D;
			}

		}

		if (((weapon != null) && (weapon.getId() == 19675)) && (player.getCombat().getAttacking().getMob() != null)) {
			Mob m = com.dark.rs2.entity.World.getNpcs()[defending.getIndex()];
			if ((m.getId() == 4004 || m.getId() == 416 || m.getId() == 6379 || m.getId() == 5918 || m.getId() == 5916 || m.getId() == 2030 || m.getId() == 2031 || m.getId() == 2032 || m.getId() == 2025 || m.getId() == 2026 || m.getId() == 2029 || m.getId() == 2027 || m.getId() == 2028 || m.getId() == 2018 || m.getId() == 1432 || m.getId() == 5884)) {
				base += 0.600;
			}

		}
		if (((Gloves != null) && (Gloves.getId() == 13103))) {
			player.curePoison(1000);
			}

		

		if ((ItemCheck.isUsingBalmung(player)) && (defending.isNpc())) {
			Mob m = com.dark.rs2.entity.World.getNpcs()[defending.getIndex()];
			if ((m != null) && (MobConstants.isDagannothKing(m))) {
				base += 0.25;
			}
		}

		base = (base * specialBonus);

		if (ItemCheck.hasBNeckAndObbyMaulCombo(player) || ItemCheck.wearingFullVoidMelee(player)) {
			base = (base * 1.25);
		}
		return Math.floor(base);
	}

	public static double getSpecialStr(Player player) {
		Item weapon = player.getEquipment().getItems()[3];
		if (weapon == null || !player.getSpecialAttack().isInitialized()) {
			return 1.0;
		}
		switch (weapon.getId()) {
		case 11802:
			return 1.55;
		case 11804:
		case 11806:
		case 11808:
		case 13576:
			return 1.30;
		case 4587:
		case 4153:
		case 13263:
			return 1.0;
		case 5698:
		case 5680:
		case 1231:
			return 1.10;
		case 1215:
			return 1.10;
		case 13265:
		case 13271:
			return 1.10;
		case 3204:
		case 13092:
			return 1.05;
		case 1305:
			return 1.15;
		case 1434:
			return 1.35;
		case 4151:
		case 861: 
			return 1.1;
		case 12006:
			return 1.1;
		case 10877:
			return 1.2933;
		case 13188:
			return 1.05;			
			
		}
		return 0.5D;
	}

	public static double getSpecialAccuracy(Player player) {
		if (player == null) {
			return 0.0D;
		}
		Item weapon = player.getEquipment().getItems()[3];
		if (weapon == null || !player.getSpecialAttack().isInitialized()) {
			return 1.0;
		}
		switch (weapon.getId()) {
		case 5698:
		case 5680:
		case 1231:
			return 2.0;
		case 1215:
			return 2.05;
		case 13265:
		case 13271:
			return 2.05;
		case 3204:
		case 13092:
			return 1.10;
		case 13263:
			return 1.20;
		case 1305:
			return 1.20;
		case 1434:
			return 1.35;
		case 11802:
			return 2.0;
		case 13576:
			return 1.55;
		case 11804:
		case 11808:
			return 1.30;

		case 11806:
			return 1.5;
		case 4151:
		case 861:
		case 4587:
		case 12006:
			return 1.15;
		case 10877:
			return 1.2933;
		case 13188:
			return 1.25;
		}
		return 0.0D;
	}

	/*
	 * Gets the attackers effective strength output
	 */
	public static double getEffectiveStr(Player player) {
		return ((player.getLevels()[2]) * getPrayerStr(player));
	}

	/**
	 * Calculates and returns the attackers prayer strength modification bonus
	 * 
	 * @param player
	 * @return
	 */
	public static double getPrayerStr(Player player) {
		if (player.getPrayer().active(Prayer.BURST_OF_STRENGTH))
			return 1.05;
		else if (player.getPrayer().active(Prayer.SUPERHUMAN_STRENGTH))
			return 1.1;
		else if (player.getPrayer().active(Prayer.ULTIMATE_STRENGTH))
			return 1.15;
		else if (player.getPrayer().active(Prayer.CHIVALRY))
			return 1.18;
		else if (player.getPrayer().active(Prayer.PIETY))
			return 1.23;
		return 1.0;
	}

}