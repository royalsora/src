package com.dark.rs2.content;

import com.dark.rs2.entity.player.Player;

/**
 * Item handle
 * @author Daniel
 *
 */
public interface CreationHandle {

	/**
	 * Handle
	 * @param player
	 */
	public void handle(Player player, ItemCreation data);

}
