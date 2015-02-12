package com.silentdarknessmc.deathrun.utils;

import static org.bukkit.ChatColor.*;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ChatUtilties {
	@SuppressWarnings("deprecation")
	public static void broadcast(String msg) {
		for (Player player : Bukkit.getOnlinePlayers()) {
			player.sendMessage(starter() + msg);
		}
	}
	private static String starter() {
		return DARK_AQUA + "[" + GOLD + "Deathrun" + DARK_AQUA + "] " + WHITE;
	}
}