package com.silentdarknessmc.deathrun.listeners.player;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;

import com.silentdarknessmc.deathrun.Main;
import com.silentdarknessmc.deathrun.handlers.Team;
import com.silentdarknessmc.deathrun.listeners.MGListener;

public class PlayerDeath extends MGListener {

	public PlayerDeath(Main pl) {
		super(pl);
	}
	
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent e) {
		Player player = e.getEntity();
		Team.getTeam(player).remove(player);
		player.setGameMode(GameMode.SPECTATOR);
	}
}