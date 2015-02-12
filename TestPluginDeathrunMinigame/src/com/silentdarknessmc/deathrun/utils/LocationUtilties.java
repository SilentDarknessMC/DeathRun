package com.silentdarknessmc.deathrun.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.silentdarknessmc.deathrun.handlers.Team;

public class LocationUtilties {
	
	private static Location spawnLocation = new Location(Bukkit.getServer().getWorld("world"), 93, 40, -894);
	
	public static void teleportToSpawn(Player player) {
		player.teleport(spawnLocation);
	}
	
	@SuppressWarnings("deprecation")
	public static void teleportAllToSpawn() {
		for (Player p : Bukkit.getOnlinePlayers())
			teleportToSpawn(p);
	}
	
	public static void teleportToGame(Player player, Team team) {
		player.teleport(team.getSpawn());
	}
}