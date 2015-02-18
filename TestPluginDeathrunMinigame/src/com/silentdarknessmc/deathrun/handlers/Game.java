package com.silentdarknessmc.deathrun.handlers;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.silentdarknessmc.deathrun.GameState;
import com.silentdarknessmc.deathrun.Main;
import com.silentdarknessmc.deathrun.utils.ChatUtilties;
import com.silentdarknessmc.deathrun.utils.LocationUtilties;

public class Game {
	static Player player;
	
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
		
		Main.playerswaiting.clear();
		
		GameState.setState(GameState.IN_GAME);
	}

	public static void stop(Team team) {
		Main.deaths.clear();
		Main.runners.clear();
		Main.playerswaiting.clear();
		Main.spectators.clear();
		Main.vips.clear();
		
		ChatUtilties.broadcast("The Deathrun Game Has Now Ended! Thanks For Playing!");
		
		GameState.setState(GameState.RESETTING);
		
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