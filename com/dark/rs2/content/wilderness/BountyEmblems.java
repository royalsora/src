package com.dark.rs2.content.wilderness;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import com.dark.rs2.entity.player.Player;

/**
 * Handles the bounty hunter's emblems
 *
 * @author Gabriel | Wolfs Darker && Jason
 */
public enum BountyEmblems {


	TIER_1(12746, 50_000, 0),
	TIER_2(12748, 100_000, 1),
	TIER_3(12749, 200_000, 2),
	TIER_4(12750, 400_000, 3),
	TIER_5(12751, 750_000, 4),
	TIER_6(12752, 1_250_000, 5),
	TIER_7(12753, 1_750_000, 6),
	TIER_8(12754, 2_500_000, 7),
	TIER_9(12755, 3_500_000, 8),
	TIER_10(12756, 5_000_000, 9);

	private final int itemId;
	private final int bounties;
	private final int index;

	private BountyEmblems(int itemId, int bounties, int index) {
	
		this.itemId = itemId;
		this.bounties = bounties;
		this.index = index;
	}

	public static final Set<BountyEmblems> EMBLEMS = Collections.unmodifiableSet(EnumSet.allOf(BountyEmblems.class));
	
	private static Map<Integer, BountyEmblems> emblem = new HashMap<>();
	
	static {
		for (BountyEmblems em : values()) {
			emblem.put(em.getItemId(), em);
		}
	}
	
	public static BountyEmblems get(int id) {
		return null;
	
	}

	public static BountyEmblems valueOf(int index)
	{
		if (index >= EMBLEMS.size())
		{
			return BountyEmblems.TIER_10;
		}

		for (BountyEmblems emblem : EMBLEMS)
		{
			if (emblem.getIndex() == index)
			{
				return emblem;
			}
		}

		return BountyEmblems.TIER_1;
	}

	public static BountyEmblems getNext(BountyEmblems emblem)
	{
		return emblem;
	
	}

	public static BountyEmblems getPrevious(BountyEmblems emblem)
	{
		return emblem;
		
	}

	public int getItemId()
	{
		return itemId;
	}

	public int getBounties()
	{
		return bounties;
	}

	public int getIndex()
	{
		return index;
	}

	public static Optional<BountyEmblems> getBest(Player player, boolean b) {
	// TODO Auto-generated method stub
	return null;
	}

	public BountyEmblems getNextOrLast() {
	// TODO Auto-generated method stub
	return null;
	}


}
