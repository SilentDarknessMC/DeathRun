package com.silentdarknessmc.deathrun.listeners.player;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import com.silentdarknessmc.deathrun.Main;
import com.silentdarknessmc.deathrun.handlers.Game;
import com.silentdarknessmc.deathrun.listeners.MGListener;
import com.silentdarknessmc.deathrun.threads.StartCountdown;
import com.silentdarknessmc.deathrun.utils.ChatUtilties;
import com.silentdarknessmc.deathrun.utils.LocationUtilties;

public class PlayerJoin extends MGListener {

	public PlayerJoin(Main pl) {
		super(pl);
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		Player player = e.getPlayer();
		if(player.hasPermission("deathrun.vip")) {
			Main.vips.add(player);
		}
		Game.setCanStart(Bukkit.getOnlinePlayers().length >= 2);
		LocationUtilties.teleportToSpawn(e.getPlayer());
		ChatUtilties.broadcast(player + " Has Joined The Game!");
		if (Bukkit.getOnlinePlayers().length >= 2) {
			ChatUtilties.broadcast("Game Will Now Start In 60 Seconds!");
			new Thread(new StartCountdown()).start();
		}
		
		if(Game.hasStarted()) {
			if(!player.hasPermission("deathrun.vip")) {
				player.kickPlayer(ChatColor.RED + "The Game Has Already Started!");
			}
		}
		if(Bukkit.getOnlinePlayers().length >= 20) {
			if(!Main.vips.contains(player)) {
				player.kickPlayer(ChatColor.RED + "The Game Is Full!");
			}
			if(Main.vips.contains(player) || player.hasPermission("deathrun.admin") || player.hasPermission("deathrun.vip") || player.isOp()) {
				player.sendMessage("You Have Join A Full Game With Your VIP Benifits!");
			}
		}
	}
}