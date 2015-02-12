package com.silentdarknessmc.deathrun.listeners.player;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerQuitEvent;

import com.silentdarknessmc.deathrun.GameState;
import com.silentdarknessmc.deathrun.Main;
import com.silentdarknessmc.deathrun.handlers.Game;
import com.silentdarknessmc.deathrun.handlers.Team;
import com.silentdarknessmc.deathrun.listeners.MGListener;

public class PlayerQuit extends MGListener {

	public PlayerQuit(Main pl) {
		super(pl);
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent e) {
		if (GameState.isState(GameState.IN_LOBBY))
			Game.setCanStart(Bukkit.getOnlinePlayers().length - 1 >= 2);

		Player player = e.getPlayer();

		if(Game.hasStarted()) {
			Team.getTeam(player).remove(player);
		}
		
		if(Main.vips.contains(player)) {
			Main.vips.remove(player);
		}
	}
}