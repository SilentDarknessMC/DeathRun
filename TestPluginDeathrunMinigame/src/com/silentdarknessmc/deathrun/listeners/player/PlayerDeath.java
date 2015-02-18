package com.silentdarknessmc.deathrun.listeners.player;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.silentdarknessmc.deathrun.Main;
import com.silentdarknessmc.deathrun.handlers.Team;
import com.silentdarknessmc.deathrun.listeners.MGListener;
import com.silentdarknessmc.deathrun.utils.ChatUtilties;

public class PlayerDeath extends MGListener {
	
	Location spectator = new Location(Bukkit.getServer().getWorld("world"), 93, 42, -904);

	public PlayerDeath(Main pl) {
		super(pl);
	}

	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent e) {
		
		Player player = e.getEntity();
		
		e.getEntity().setScoreboard(Main.board);
		
		if(Team.hasTeam(player)) {
			Team.getTeam(player).remove(player);
		}
		
		if(Main.runners.contains(player)) {
			Main.runners.remove(player);
		}
		
		if(Main.deaths.contains(player)) {
			Main.deaths.remove(player);
		}
		
		ChatUtilties.broadcast(player.getDisplayName() + " Has Been Eliminated!");
		
		Main.spectators.add(player);
		
		player.setAllowFlight(true);
		player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 18000, 2));
		
		player.teleport(spectator);
	}
}