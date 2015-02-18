package com.silentdarknessmc.deathrun.listeners.player;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;

import com.silentdarknessmc.deathrun.Main;
import com.silentdarknessmc.deathrun.listeners.MGListener;

public class PlayerMove extends MGListener {
	Main plugin;

	public PlayerMove(Main pl) {
		super(pl);
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e) {
		
		Player player = e.getPlayer();
		
		Material to = e.getTo().getBlock().getType();
		Block to2 = e.getTo().getBlock();

		if(to.equals(Material.WATER) || to.equals(Material.STATIONARY_WATER)) {
			player.setHealth(0.0);
			to2.setTypeId(79);
		}
	}
}