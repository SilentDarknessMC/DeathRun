package com.silentdarknessmc.deathrun.handlers;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.silentdarknessmc.deathrun.utils.LocationUtilties;

public class Game {
	
	private static boolean canStart = false;
	private static boolean hasStarted = false;
	
	@SuppressWarnings("deprecation")
	public static void start() {
		hasStarted = true;
		new Team("Death", new Location(Bukkit.getServer().getWorld("world"), 88, 41, -906));
		new Team("Runner", new Location(Bukkit.getServer().getWorld("world"), 92, 41, -904));
		
		int i = 0;
		for(Player player : Bukkit.getOnlinePlayers()) {
			if (i >= Team.getAllTeams().size())
				i = 0;
			Team.getAllTeams().get(i).add(player);
			LocationUtilties.teleportToGame(player, Team.getAllTeams().get(i));
			i++;
		}
	}

	public static void stop(Team team) {
		hasStarted = false;
		Bukkit.reload();
	}
	
	public static boolean canStart() {
		return canStart;
	}
	
	public static boolean hasStarted() {
		return hasStarted;
	}
	
	public static void setCanStart(boolean b) {
		canStart = b;
	}
}