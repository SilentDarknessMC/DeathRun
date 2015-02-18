package com.silentdarknessmc.deathrun;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

import com.silentdarknessmc.deathrun.handlers.Game;
import com.silentdarknessmc.deathrun.listeners.player.PlayerChat;
import com.silentdarknessmc.deathrun.listeners.player.PlayerDeath;
import com.silentdarknessmc.deathrun.listeners.player.PlayerJoin;
import com.silentdarknessmc.deathrun.listeners.player.PlayerMove;
import com.silentdarknessmc.deathrun.listeners.player.PlayerQuit;

public class Main extends JavaPlugin implements Listener {

	public static Scoreboard board;

	public static List<Player> vips = new ArrayList<Player>();
	public static List<Player> runners = new ArrayList<Player>();
	public static List<Player> deaths = new ArrayList<Player>();
	public static List<Player> spectators = new ArrayList<Player>();
	public static List<Player> playerswaiting = new ArrayList<Player>();

	public static Main instance;

	public void onEnable() {
		Scoreboard();
		Bukkit.getServer().getPluginManager().registerEvents(new PlayerQuit(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new PlayerDeath(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new PlayerMove(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new PlayerChat(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new PlayerJoin(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new Signs(), this);
		Main.instance = this;
		GameState.setState(GameState.IN_LOBBY);
		getConfig().options().copyDefaults(true);
		saveConfig();
	}

	@SuppressWarnings("deprecation")
	public void Scoreboard() {

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
		
		Score blank1 = objective.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "......"));
		blank1.setScore(6);
		
		Score runners = objective.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "Runners"));
		runners.setScore(a);
		
		Score blank2 = objective.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "....."));
		blank2.setScore(5);

		Score deaths = objective.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "Deaths"));
		deaths.setScore(b);
		
		Score blank3 = objective.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "...."));
		blank3.setScore(4);

		Score spectators = objective.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "Spectators"));
		spectators.setScore(c);
		
		Score blank4 = objective.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "..."));
		blank4.setScore(3);

		Score pwaiting = objective.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "Players Waiting"));
		pwaiting.setScore(d);
		
		Score blank5 = objective.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + ".."));
		blank5.setScore(2);

		Score vips = objective.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "VIPs"));
		vips.setScore(e);
		
		Score blank6 = objective.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "."));
		blank6.setScore(1);
	}

	@SuppressWarnings("deprecation")
	public static void updateScoreboard() {
		Objective objective = board.registerNewObjective("DeathRun", "Minigame");
		
		int a = Main.runners.size();
		int b = Main.deaths.size();
		int c = Main.spectators.size();
		int d = Main.playerswaiting.size();
		int e = Main.vips.size();
		
		Score runners = objective.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "Runners"));
		runners.setScore(a);

		Score deaths = objective.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "Deaths"));
		deaths.setScore(b);

		Score spectators = objective.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "Spectators"));
		spectators.setScore(c);

		Score pwaiting = objective.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "Players Waiting"));
		pwaiting.setScore(d);

		Score vips = objective.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "VIPs"));
		vips.setScore(e);
	}

	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player player = (Player) sender;
		if (label.equalsIgnoreCase("updatesb")) {
			Scoreboard();
		}
		
		if (label.equalsIgnoreCase("deathrun")) {
			if(args[0].equalsIgnoreCase("Help")) {
				player.sendMessage(ChatColor.DARK_AQUA + "[" + ChatColor.GOLD + "Deathrun" + ChatColor.DARK_AQUA + "]" + ChatColor.YELLOW + " =========================================");
				player.sendMessage(ChatColor.DARK_AQUA + "[" + ChatColor.GOLD + "Deathrun" + ChatColor.DARK_AQUA + "]" + ChatColor.GOLD + " /DeathRun AdminHelp");
				player.sendMessage(ChatColor.DARK_AQUA + "[" + ChatColor.GOLD + "Deathrun" + ChatColor.DARK_AQUA + "]" + ChatColor.GOLD + " /DeathRun SetupHelp");
				player.sendMessage(ChatColor.DARK_AQUA + "[" + ChatColor.GOLD + "Deathrun" + ChatColor.DARK_AQUA + "]" + ChatColor.GOLD + " /DeathRun TrapHelp");
				player.sendMessage(ChatColor.DARK_AQUA + "[" + ChatColor.GOLD + "Deathrun" + ChatColor.DARK_AQUA + "]" + ChatColor.YELLOW + " =========================================");
			}

			if(args[0].equalsIgnoreCase("AdminHelp")) {
				player.sendMessage(ChatColor.DARK_AQUA + "[" + ChatColor.GOLD + "Deathrun" + ChatColor.DARK_AQUA + "]" + ChatColor.YELLOW + " =========================================");
				player.sendMessage(ChatColor.DARK_AQUA + "[" + ChatColor.GOLD + "Deathrun" + ChatColor.DARK_AQUA + "]" + ChatColor.GOLD + " /DeathRun Start **USE WITH CAUTION!!!!**");
				player.sendMessage(ChatColor.DARK_AQUA + "[" + ChatColor.GOLD + "Deathrun" + ChatColor.DARK_AQUA + "]" + ChatColor.YELLOW + " =========================================");
			}

			if(args[0].equalsIgnoreCase("SetupHelp")) {
				player.sendMessage(ChatColor.DARK_AQUA + "[" + ChatColor.GOLD + "Deathrun" + ChatColor.DARK_AQUA + "]" + ChatColor.YELLOW + " =========================================");
				player.sendMessage(ChatColor.DARK_AQUA + "[" + ChatColor.GOLD + "Deathrun" + ChatColor.DARK_AQUA + "]" + ChatColor.GOLD + " /DeathRun Set Lobby");
				player.sendMessage(ChatColor.DARK_AQUA + "[" + ChatColor.GOLD + "Deathrun" + ChatColor.DARK_AQUA + "]" + ChatColor.GOLD + " /DeathRun Set Runner");
				player.sendMessage(ChatColor.DARK_AQUA + "[" + ChatColor.GOLD + "Deathrun" + ChatColor.DARK_AQUA + "]" + ChatColor.GOLD + " /DeathRun Set Death");
				player.sendMessage(ChatColor.DARK_AQUA + "[" + ChatColor.GOLD + "Deathrun" + ChatColor.DARK_AQUA + "]" + ChatColor.YELLOW + " =========================================");
			}

			if(args[0].equalsIgnoreCase("TrapHelp")) {
				player.sendMessage(ChatColor.DARK_AQUA + "[" + ChatColor.GOLD + "Deathrun" + ChatColor.DARK_AQUA + "]" + ChatColor.YELLOW + " =========================================");
				player.sendMessage(ChatColor.DARK_AQUA + "[" + ChatColor.GOLD + "Deathrun" + ChatColor.DARK_AQUA + "]" + ChatColor.GOLD + " /DeathRun Set Water <1-5>");
				player.sendMessage(ChatColor.DARK_AQUA + "[" + ChatColor.GOLD + "Deathrun" + ChatColor.DARK_AQUA + "]" + ChatColor.GOLD + " /DeathRun Set Lava <1-5>");
				player.sendMessage(ChatColor.DARK_AQUA + "[" + ChatColor.GOLD + "Deathrun" + ChatColor.DARK_AQUA + "]" + ChatColor.GOLD + " /DeathRun Set BlockDisappear <1-5>");
				player.sendMessage(ChatColor.DARK_AQUA + "[" + ChatColor.GOLD + "Deathrun" + ChatColor.DARK_AQUA + "]" + ChatColor.GOLD + " /DeathRun Set Wall <1-5>");
				player.sendMessage(ChatColor.DARK_AQUA + "[" + ChatColor.GOLD + "Deathrun" + ChatColor.DARK_AQUA + "]" + ChatColor.GOLD + " /DeathRun Set Floor <1-5>");
				player.sendMessage(ChatColor.DARK_AQUA + "[" + ChatColor.GOLD + "Deathrun" + ChatColor.DARK_AQUA + "]" + ChatColor.GOLD + " /DeathRun Set Cealing <1-5>");
				player.sendMessage(ChatColor.DARK_AQUA + "[" + ChatColor.GOLD + "Deathrun" + ChatColor.DARK_AQUA + "]" + ChatColor.YELLOW + " =========================================");
			}

			if(args[0].equalsIgnoreCase("Set")) {
				int x = player.getLocation().getBlockX();
				int y = player.getLocation().getBlockY();
				int z = player.getLocation().getBlockZ();

				if(player.hasPermission("deathrun.admin")) {
					if(args[1].equalsIgnoreCase("Lobby")) {
						Main.instance.getConfig().set("Location.Lobby.x", Integer.valueOf(x));
						Main.instance.getConfig().set("Location.Lobby.y", Integer.valueOf(y));
						Main.instance.getConfig().set("Location.Lobby.z", Integer.valueOf(z));
						Main.instance.getConfig().set("Location.Lobby.world", player.getWorld());
						Main.instance.saveConfig();
						player.sendMessage(ChatColor.DARK_AQUA + "[" + ChatColor.GOLD + "Deathrun" + ChatColor.DARK_AQUA + "]" + ChatColor.GOLD + " You Have Set The Lobby Location!");
					}

					if(args[1].equalsIgnoreCase("Runner")) {
						Main.instance.getConfig().set("Location.Runner.x", Integer.valueOf(x));
						Main.instance.getConfig().set("Location.Runner.y", Integer.valueOf(y));
						Main.instance.getConfig().set("Location.Runner.z", Integer.valueOf(z));
						Main.instance.getConfig().set("Location.Runner.world", player.getLocation().getWorld());
						Main.instance.saveConfig();
						player.sendMessage(ChatColor.DARK_AQUA + "[" + ChatColor.GOLD + "Deathrun" + ChatColor.DARK_AQUA + "]" + ChatColor.GOLD + " You Have Set The Runner Location!");
					}

					if(args[1].equalsIgnoreCase("Death")) {
						Main.instance.getConfig().set("Location.Death.x", Integer.valueOf(x));
						Main.instance.getConfig().set("Location.Death.y", Integer.valueOf(y));
						Main.instance.getConfig().set("Location.Death.z", Integer.valueOf(z));
						Main.instance.getConfig().set("Location.Death.world", player.getLocation().getWorld());
						Main.instance.saveConfig();
						player.sendMessage(ChatColor.DARK_AQUA + "[" + ChatColor.GOLD + "Deathrun" + ChatColor.DARK_AQUA + "]" + ChatColor.GOLD + " You Have Set The Death Location!");
					}

					if(args[1].equalsIgnoreCase("Water")) {
						if(args[2].equalsIgnoreCase("1")) {
							Main.instance.getConfig().set("Location.Water1.x", Integer.valueOf(x));
							Main.instance.getConfig().set("Location.Water1.y", Integer.valueOf(y));
							Main.instance.getConfig().set("Location.Water1.z", Integer.valueOf(z));
							Main.instance.saveConfig();
							player.sendMessage(ChatColor.DARK_AQUA + "[" + ChatColor.GOLD + "Deathrun" + ChatColor.DARK_AQUA + "]" + ChatColor.GOLD + " You Have Set The Water Location #1!");
						}

						if(args[2].equalsIgnoreCase("2")) {
							Main.instance.getConfig().set("Location.Water2.x", Integer.valueOf(x));
							Main.instance.getConfig().set("Location.Water2.y", Integer.valueOf(y));
							Main.instance.getConfig().set("Location.Water2.z", Integer.valueOf(z));
							Main.instance.saveConfig();
							player.sendMessage(ChatColor.DARK_AQUA + "[" + ChatColor.GOLD + "Deathrun" + ChatColor.DARK_AQUA + "]" + ChatColor.GOLD + " You Have Set The Water Location #2!");
						}

						if(args[2].equalsIgnoreCase("3")) {
							Main.instance.getConfig().set("Location.Water3.x", Integer.valueOf(x));
							Main.instance.getConfig().set("Location.Water3.y", Integer.valueOf(y));
							Main.instance.getConfig().set("Location.Water3.z", Integer.valueOf(z));
							Main.instance.saveConfig();
							player.sendMessage(ChatColor.DARK_AQUA + "[" + ChatColor.GOLD + "Deathrun" + ChatColor.DARK_AQUA + "]" + ChatColor.GOLD + " You Have Set The Water Location #3!");
						}

						if(args[2].equalsIgnoreCase("4")) {
							Main.instance.getConfig().set("Location.Water4.x", Integer.valueOf(x));
							Main.instance.getConfig().set("Location.Water4.y", Integer.valueOf(y));
							Main.instance.getConfig().set("Location.Water4.z", Integer.valueOf(z));
							Main.instance.saveConfig();
							player.sendMessage(ChatColor.DARK_AQUA + "[" + ChatColor.GOLD + "Deathrun" + ChatColor.DARK_AQUA + "]" + ChatColor.GOLD + " You Have Set The Water Location #4!");
						}

						if(args[2].equalsIgnoreCase("5")) {
							Main.instance.getConfig().set("Location.Water5.x", Integer.valueOf(x));
							Main.instance.getConfig().set("Location.Water5.y", Integer.valueOf(y));
							Main.instance.getConfig().set("Location.Water5.z", Integer.valueOf(z));
							Main.instance.saveConfig();
							player.sendMessage(ChatColor.DARK_AQUA + "[" + ChatColor.GOLD + "Deathrun" + ChatColor.DARK_AQUA + "]" + ChatColor.GOLD + " You Have Set The Water Location #5!");
						}
					}
					
					
					if(args[1].equalsIgnoreCase("BlockDisappear")) {
						if(args[2].equalsIgnoreCase("1")) {
							Main.instance.getConfig().set("Location.BlockDisappear1.x", Integer.valueOf(x));
							Main.instance.getConfig().set("Location.BlockDisappear1.y", Integer.valueOf(y));
							Main.instance.getConfig().set("Location.BlockDisappear1.z", Integer.valueOf(z));
							Main.instance.saveConfig();
							player.sendMessage(ChatColor.DARK_AQUA + "[" + ChatColor.GOLD + "Deathrun" + ChatColor.DARK_AQUA + "]" + ChatColor.GOLD + " You Have Set The BlockDisappear Location #1!");
						}

						if(args[2].equalsIgnoreCase("2")) {
							Main.instance.getConfig().set("Location.BlockDisappear2.x", Integer.valueOf(x));
							Main.instance.getConfig().set("Location.BlockDisappear2.y", Integer.valueOf(y));
							Main.instance.getConfig().set("Location.BlockDisappear2.z", Integer.valueOf(z));
							Main.instance.saveConfig();
							player.sendMessage(ChatColor.DARK_AQUA + "[" + ChatColor.GOLD + "Deathrun" + ChatColor.DARK_AQUA + "]" + ChatColor.GOLD + " You Have Set The BlockDisappear Location #2!");
						}

						if(args[2].equalsIgnoreCase("3")) {
							Main.instance.getConfig().set("Location.BlockDisappear3.x", Integer.valueOf(x));
							Main.instance.getConfig().set("Location.BlockDisappear3.y", Integer.valueOf(y));
							Main.instance.getConfig().set("Location.BlockDisappear3.z", Integer.valueOf(z));
							Main.instance.saveConfig();
							player.sendMessage(ChatColor.DARK_AQUA + "[" + ChatColor.GOLD + "Deathrun" + ChatColor.DARK_AQUA + "]" + ChatColor.GOLD + " You Have Set The BlockDisappear Location #3!");
						}

						if(args[2].equalsIgnoreCase("4")) {
							Main.instance.getConfig().set("Location.BlockDisappear4.x", Integer.valueOf(x));
							Main.instance.getConfig().set("Location.BlockDisappear4.y", Integer.valueOf(y));
							Main.instance.getConfig().set("Location.BlockDisappear4.z", Integer.valueOf(z));
							player.sendMessage(ChatColor.DARK_AQUA + "[" + ChatColor.GOLD + "Deathrun" + ChatColor.DARK_AQUA + "]" + ChatColor.GOLD + " You Have Set The BlockDisappear Location #4!");
						}

						if(args[2].equalsIgnoreCase("5")) {
							Main.instance.getConfig().set("Location.BlockDisappear5.x", Integer.valueOf(x));
							Main.instance.getConfig().set("Location.BlockDisappear5.y", Integer.valueOf(y));
							Main.instance.getConfig().set("Location.BlockDisappear5.z", Integer.valueOf(z));
							Main.instance.saveConfig();
							player.sendMessage(ChatColor.DARK_AQUA + "[" + ChatColor.GOLD + "Deathrun" + ChatColor.DARK_AQUA + "]" + ChatColor.GOLD + " You Have Set The BlockDisappear Location #5!");
						}
					}


					if(args[1].equalsIgnoreCase("Wall")) {
						if(args[2].equalsIgnoreCase("1")) {
							Main.instance.getConfig().set("Location.Wall1.x", Integer.valueOf(x));
							Main.instance.getConfig().set("Location.Wall1.y", Integer.valueOf(y));
							Main.instance.getConfig().set("Location.Wall1.z", Integer.valueOf(z));
							Main.instance.saveConfig();
							player.sendMessage(ChatColor.DARK_AQUA + "[" + ChatColor.GOLD + "Deathrun" + ChatColor.DARK_AQUA + "]" + ChatColor.GOLD + " You Have Set The Wall Location #1!");
						}

						if(args[2].equalsIgnoreCase("2")) {
							Main.instance.getConfig().set("Location.Wall2.x", Integer.valueOf(x));
							Main.instance.getConfig().set("Location.Wall2.y", Integer.valueOf(y));
							Main.instance.getConfig().set("Location.Wall2.z", Integer.valueOf(z));
							Main.instance.saveConfig();
							player.sendMessage(ChatColor.DARK_AQUA + "[" + ChatColor.GOLD + "Deathrun" + ChatColor.DARK_AQUA + "]" + ChatColor.GOLD + " You Have Set The Wall Location #2!");
						}

						if(args[2].equalsIgnoreCase("3")) {
							Main.instance.getConfig().set("Location.Wall3.x", Integer.valueOf(x));
							Main.instance.getConfig().set("Location.Wall3.y", Integer.valueOf(y));
							Main.instance.getConfig().set("Location.Wall3.z", Integer.valueOf(z));
							Main.instance.saveConfig();
							player.sendMessage(ChatColor.DARK_AQUA + "[" + ChatColor.GOLD + "Deathrun" + ChatColor.DARK_AQUA + "]" + ChatColor.GOLD + " You Have Set The Wall Location #3!");
						}

						if(args[2].equalsIgnoreCase("4")) {
							Main.instance.getConfig().set("Location.Wall4.x", Integer.valueOf(x));
							Main.instance.getConfig().set("Location.Wall4.y", Integer.valueOf(y));
							Main.instance.getConfig().set("Location.Wall4.z", Integer.valueOf(z));
							Main.instance.saveConfig();
							player.sendMessage(ChatColor.DARK_AQUA + "[" + ChatColor.GOLD + "Deathrun" + ChatColor.DARK_AQUA + "]" + ChatColor.GOLD + " You Have Set The Wall Location #4!");
						}

						if(args[2].equalsIgnoreCase("5")) {
							Main.instance.getConfig().set("Location.Wall5.x", Integer.valueOf(x));
							Main.instance.getConfig().set("Location.Wall5.y", Integer.valueOf(y));
							Main.instance.getConfig().set("Location.Wall5.z", Integer.valueOf(z));
							Main.instance.saveConfig();
							player.sendMessage(ChatColor.DARK_AQUA + "[" + ChatColor.GOLD + "Deathrun" + ChatColor.DARK_AQUA + "]" + ChatColor.GOLD + " You Have Set The Wall Location #5!");
						}
					}

					if(args[1].equalsIgnoreCase("Floor")) {
						if(args[2].equalsIgnoreCase("1")) {
							player.sendMessage(ChatColor.DARK_AQUA + "[" + ChatColor.GOLD + "Deathrun" + ChatColor.DARK_AQUA + "]" + ChatColor.GOLD + " This Feature Isn't Setup Yet! We Plan To Finish It In V2.0!");
						}

						if(args[2].equalsIgnoreCase("2")) {
							player.sendMessage(ChatColor.DARK_AQUA + "[" + ChatColor.GOLD + "Deathrun" + ChatColor.DARK_AQUA + "]" + ChatColor.GOLD + " This Feature Isn't Setup Yet! We Plan To Finish It In V2.0!");
						}

						if(args[2].equalsIgnoreCase("3")) {
							player.sendMessage(ChatColor.DARK_AQUA + "[" + ChatColor.GOLD + "Deathrun" + ChatColor.DARK_AQUA + "]" + ChatColor.GOLD + " This Feature Isn't Setup Yet! We Plan To Finish It In V2.0!");
						}

						if(args[2].equalsIgnoreCase("4")) {
							player.sendMessage(ChatColor.DARK_AQUA + "[" + ChatColor.GOLD + "Deathrun" + ChatColor.DARK_AQUA + "]" + ChatColor.GOLD + " This Feature Isn't Setup Yet! We Plan To Finish It In V2.0!");
						}

						if(args[2].equalsIgnoreCase("5")) {
							player.sendMessage(ChatColor.DARK_AQUA + "[" + ChatColor.GOLD + "Deathrun" + ChatColor.DARK_AQUA + "]" + ChatColor.GOLD + " This Feature Isn't Setup Yet! We Plan To Finish It In V2.0!");
						}
					}

					if(args[1].equalsIgnoreCase("Cealing")) {
						if(args[2].equalsIgnoreCase("1")) {
							player.sendMessage(ChatColor.DARK_AQUA + "[" + ChatColor.GOLD + "Deathrun" + ChatColor.DARK_AQUA + "]" + ChatColor.GOLD + " This Feature Isn't Setup Yet! We Plan To Finish It In V2.0!");
						}

						if(args[2].equalsIgnoreCase("2")) {
							player.sendMessage(ChatColor.DARK_AQUA + "[" + ChatColor.GOLD + "Deathrun" + ChatColor.DARK_AQUA + "]" + ChatColor.GOLD + " This Feature Isn't Setup Yet! We Plan To Finish It In V2.0!");
						}

						if(args[2].equalsIgnoreCase("3")) {
							player.sendMessage(ChatColor.DARK_AQUA + "[" + ChatColor.GOLD + "Deathrun" + ChatColor.DARK_AQUA + "]" + ChatColor.GOLD + " This Feature Isn't Setup Yet! We Plan To Finish It In V2.0!");
						}

						if(args[2].equalsIgnoreCase("4")) {
							player.sendMessage(ChatColor.DARK_AQUA + "[" + ChatColor.GOLD + "Deathrun" + ChatColor.DARK_AQUA + "]" + ChatColor.GOLD + " This Feature Isn't Setup Yet! We Plan To Finish It In V2.0!");
						}

						if(args[2].equalsIgnoreCase("5")) {
							player.sendMessage(ChatColor.DARK_AQUA + "[" + ChatColor.GOLD + "Deathrun" + ChatColor.DARK_AQUA + "]" + ChatColor.GOLD + " This Feature Isn't Setup Yet! We Plan To Finish It In V2.0!");
						}
					}
				}

				if(!player.hasPermission("deathrun.admin")) {
					player.sendMessage(ChatColor.DARK_AQUA + "[" + ChatColor.GOLD + "Deathrun" + ChatColor.DARK_AQUA + "]" + ChatColor.GOLD + " You Don't Have Permission To Set DeatRun Locations!");
				}
			}

			if(args[0].equalsIgnoreCase("Start")) {
				if(player.hasPermission("deathrun.admin")) {
					if(Bukkit.getOnlinePlayers().length >= 2) {
						Game.start();
					} else {
						player.sendMessage(ChatColor.RED + "There Are Not Enough Players To Start The Game!");
					}
				}
				if(!player.hasPermission("deathrun.admin")) {
					player.sendMessage(ChatColor.DARK_AQUA + "[" + ChatColor.GOLD + "Deathrun" + ChatColor.DARK_AQUA + "]" + ChatColor.GOLD + " You Don't Have Permission To Start A DeathRun Game!");
				}
			}
		}
		return false;
	}
}