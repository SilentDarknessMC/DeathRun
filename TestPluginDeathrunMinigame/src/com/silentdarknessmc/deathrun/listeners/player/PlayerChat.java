package com.silentdarknessmc.deathrun.listeners.player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerChatEvent;

import com.silentdarknessmc.deathrun.GameState;
import com.silentdarknessmc.deathrun.Main;
import com.silentdarknessmc.deathrun.listeners.MGListener;

@SuppressWarnings("deprecation")
public class PlayerChat extends MGListener {

	public PlayerChat(Main pl) {
		super(pl);
	}
	
	@EventHandler
	public void onPlayerChat(PlayerChatEvent e) {
		if(GameState.isState(GameState.IN_INTRO)) {
			e.setCancelled(true);
		}
	}
}