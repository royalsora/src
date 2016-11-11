package com.dark.rs2.content.skill.fletching.fletchable;

import com.dark.rs2.entity.item.Item;

public interface Fletchable {
	
	public int getAnimation();
	
	public Item getUse();
	
	public Item getWith();
	
	public FletchableItem[] getFletchableItems();
	
	public Item[] getIngediants();
	
	public String getProductionMessage();
}