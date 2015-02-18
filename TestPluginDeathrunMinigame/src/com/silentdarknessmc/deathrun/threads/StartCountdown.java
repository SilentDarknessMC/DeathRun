package com.silentdarknessmc.deathrun.threads;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.silentdarknessmc.deathrun.handlers.Game;
import com.silentdarknessmc.deathrun.utils.ChatUtilties;

public class StartCountdown implements Runnable {
	Player player;

	private static int timeUntilStart;

	public void run() {
		timeUntilStart = 65;
		while (true) {
			for (; timeUntilStart >= 0; timeUntilStart--) {
				if (timeUntilStart == 0) {
					if (Game.canStart()) {
						Game.start();
						break;
					}
				}

				if(timeUntilStart == 65) {
					ChatUtilties.broadcast("Welcome To Deathrun! A SDMC Creation!");
				}

				if (timeUntilStart % 10 == 0 || timeUntilStart < 10) {
					ChatUtilties.broadcast(timeUntilStart + " Seconds Until The Game Starts!");
				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
					Bukkit.shutdown();
				}
			}
		}
	}
}