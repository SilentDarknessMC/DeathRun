package com.silentdarknessmc.deathrun;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

public class ScoreBoard implements Listener {
	static Scoreboard board;
	
	@SuppressWarnings("deprecation")
	public static void Scoreboard() {
		
		ScoreboardManager manager = Bukkit.getScoreboardManager();
		board = manager.getNewScoreboard();

		Objective objective = board.registerNewObjective("DeathRun", "Minigame");
		objective.setDisplayName(ChatColor.DARK_AQUA + "[" + ChatColor.GOLD + "Deathrun" + ChatColor.DARK_AQUA + "]");
		objective.setDisplaySlot(DisplaySlot.SIDEBAR);

		Team team = board.registerNewTeam("Team");
		team.setDisplayName(ChatColor.DARK_AQUA + "");

		int a = Main.runners.size();
		int b = Main.deaths.size();
		int c = Main.spectators.size();
		int d = Main.playerswaiting.size();
		int e = Main.vips.size();

		Score score = objective.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "Runners"));
		score.setScore(a);

		Score score2 = objective.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "Deaths"));
		score2.setScore(b);

		Score score3 = objective.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "Spectators"));
		score3.setScore(c);

		Score score4 = objective.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "Players Waiting"));
		score4.setScore(d);
		
		Score score5 = objective.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "VIPs"));
		score5.setScore(e);
	}
}