package com.dark.rs2.content.skill.magic.spells;

import com.dark.rs2.content.skill.Skills;
import com.dark.rs2.content.skill.magic.Spell;
import com.dark.rs2.entity.item.Item;
import com.dark.rs2.entity.player.Player;
import com.dark.rs2.entity.player.net.out.impl.SendMessage;

public class BonesToPeaches extends Spell {

	@Override
	public boolean execute(Player player) {
	int[] bones = {526, 528, 530};

    int bone = 0;

    for (int index = 0; index < bones.length; index++) {
        if (player.getInventory().hasItemId(bones[index])) {
            bone = bones[index];
            continue;
        }
    }

    int amount = player.getInventory().getItemAmount(bone);

    if (amount == 0) {
        player.send(new SendMessage("You have no bones to do this!"));
        return false;
    }

    player.getInventory().remove(bone, amount);
    player.getInventory().add(6883, amount);

    player.send(new SendMessage("You have converted " + amount + " bones to " + ("peaches") + "."));

    return false;
	}

	@Override
	public double getExperience() {
	// TODO Auto-generated method stub
	return 120.0D;
	}

	@Override
	public int getLevel() {
	// TODO Auto-generated method stub
	return 60;
	}

	@Override
	public String getName() {
	// TODO Auto-generated method stub
	return "Bones to Peaches";
	}

	@Override
	public Item[] getRunes() {
	return new Item[] { new Item(561, 2), new Item(555, 4), new Item(557, 4) };
}

}
