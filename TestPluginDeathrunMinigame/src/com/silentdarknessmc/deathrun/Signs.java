package com.silentdarknessmc.deathrun;

import java.util.ArrayList;
import net.md_5.bungee.api.ChatColor;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitScheduler;

import com.silentdarknessmc.deathrun.TrapLocations;
import com.silentdarknessmc.deathrun.Main;
import com.silentdarknessmc.deathrun.Signs;

public class Signs implements Listener {
	public static ArrayList<Player> Waterused1 = new ArrayList();
	public static ArrayList<Player> Waterused2 = new ArrayList();
	public static ArrayList<Player> Waterused3 = new ArrayList();
	public static ArrayList<Player> Waterused4 = new ArrayList();
	public static ArrayList<Player> Waterused5 = new ArrayList();
	
	public static ArrayList<Player> Wallused1 = new ArrayList();
	public static ArrayList<Player> Wallused2 = new ArrayList();
	public static ArrayList<Player> Wallused3 = new ArrayList();
	public static ArrayList<Player> Wallused4 = new ArrayList();
	public static ArrayList<Player> Wallused5 = new ArrayList();
	
	public static ArrayList<Player> BlockDisappear1 = new ArrayList();
	public static ArrayList<Player> BlockDisappear2 = new ArrayList();
	public static ArrayList<Player> BlockDisappear3 = new ArrayList();
	public static ArrayList<Player> BlockDisappear4 = new ArrayList();
	public static ArrayList<Player> BlockDisappear5 = new ArrayList();
	
	BukkitScheduler scheduler = Bukkit.getServer().getScheduler();

	@EventHandler
	public void onSignChange(SignChangeEvent e) {
		if (e.getLine(0).equalsIgnoreCase("[DeathRun]")) {
			if (e.getLine(1).equalsIgnoreCase("Water #1")) {
				e.setLine(0, "§3[§6DeathRun§3]");
				e.setLine(1, "Water #1");
			}
			if(e.getLine(1).equalsIgnoreCase("Water #2")) {
				e.setLine(0, "§3[DeathRun]");
				e.setLine(1, "Water #2");
			}
			if(e.getLine(1).equalsIgnoreCase("Water #3")) {
				e.setLine(0, "§3[DeathRun]");
				e.setLine(1, "Water #3");
			}
			if(e.getLine(1).equalsIgnoreCase("Water #4")) {
				e.setLine(0, "§3[DeathRun]");
				e.setLine(1, "Water #4");
			}
			if(e.getLine(1).equalsIgnoreCase("Water #5")) {
				e.setLine(0, "§3[DeathRun]");
				e.setLine(1, "Water #5");
			}
			
			if(e.getLine(1).equalsIgnoreCase("wall #1")) {
				e.setLine(0, "§3[DeathRun]");
				e.setLine(1, "Wall #1");
			}
			if(e.getLine(1).equalsIgnoreCase("wall #2")) {
				e.setLine(0, "§3[DeathRun]");
				e.setLine(1, "Wall #2");
			}
			if(e.getLine(1).equalsIgnoreCase("wall #3")) {
				e.setLine(0, "§3[DeathRun]");
				e.setLine(1, "Wall #3");
			}
			if(e.getLine(1).equalsIgnoreCase("wall #4")) {
				e.setLine(0, "§3[DeathRun]");
				e.setLine(1, "Wall #4");
			}
			if(e.getLine(1).equalsIgnoreCase("wall #5")) {
				e.setLine(0, "§3[DeathRun]");
				e.setLine(1, "Wall #5");
			}
			
			if(e.getLine(1).equalsIgnoreCase("blockdisappear 1")) {
				e.setLine(0, "§3[DeathRun]");
				e.setLine(1, "BlockDisappear 1");
			}
			if(e.getLine(1).equalsIgnoreCase("blockdisappear 2")) {
				e.setLine(0, "§3[DeathRun]");
				e.setLine(1, "BlockDisappear 2");
			}
			if(e.getLine(1).equalsIgnoreCase("blockdisappear 3")) {
				e.setLine(0, "§3[DeathRun]");
				e.setLine(1, "BlockDisappear 3");
			}
			if(e.getLine(1).equalsIgnoreCase("blockdisappear 4")) {
				e.setLine(0, "§3[DeathRun]");
				e.setLine(1, "BlockDisappear 4");
			}
			if(e.getLine(1).equalsIgnoreCase("blockdisappear 5")) {
				e.setLine(0, "§3[DeathRun]");
				e.setLine(1, "BlockDisappear 5");
			}
		}
	}

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		if (!(e.getAction() == Action.RIGHT_CLICK_BLOCK)) return;
		if (e.getClickedBlock().getState() instanceof Sign) {
			Sign s = (Sign) e.getClickedBlock().getState();
			if (s.getLine(1).equalsIgnoreCase("Water #1")) {
				if (Waterused1.isEmpty()) {
					final Block block = TrapLocations.water1.getBlock();
					block.setType(Material.WATER);
					Waterused1.add(e.getPlayer());
					scheduler.scheduleSyncDelayedTask(Main.instance, new Runnable()
					{
						public void run() {
							block.setType(Material.AIR);
						}
					}
					, 100L);
				} else {
					e.getPlayer().sendMessage(ChatColor.DARK_RED + "Trap has already been used!");
				}
			}
			if (s.getLine(1).equalsIgnoreCase("Water #2")) {
				if (Waterused2.isEmpty()) {
					final Block block = TrapLocations.water2.getBlock();
					block.setType(Material.WATER);
					Waterused2.add(e.getPlayer());
					scheduler.scheduleSyncDelayedTask(Main.instance, new Runnable()
					{
						public void run() {
							block.setType(Material.AIR);
						}
					}
					, 100L);
				} else {
					e.getPlayer().sendMessage(ChatColor.DARK_RED + "Trap has already been used!");
				}
			}
			if (s.getLine(1).equalsIgnoreCase("Water #3")) {
				if (Waterused3.isEmpty()) {
					final Block block = TrapLocations.water3.getBlock();
					block.setType(Material.WATER);
					Waterused3.add(e.getPlayer());
					scheduler.scheduleSyncDelayedTask(Main.instance, new Runnable()
					{
						public void run() {
							block.setType(Material.AIR);
						}
					}
					, 100L);
				} else {
					e.getPlayer().sendMessage(ChatColor.DARK_RED + "Trap has already been used!");
				}
			}
			if (s.getLine(1).equalsIgnoreCase("Water #4")) {
				if (Waterused4.isEmpty()) {
					final Block block = TrapLocations.water4.getBlock();
					block.setType(Material.WATER);
					Waterused4.add(e.getPlayer());
					scheduler.scheduleSyncDelayedTask(Main.instance, new Runnable()
					{
						public void run() {
							block.setType(Material.AIR);
						}
					}
					, 100L);
				} else {
					e.getPlayer().sendMessage(ChatColor.DARK_RED + "Trap has already been used!");
				}
			}
			if (s.getLine(1).equalsIgnoreCase("Water #5")) {
				if (Waterused5.isEmpty()) {
					final Block block = TrapLocations.water5.getBlock();
					block.setType(Material.WATER);
					Waterused5.add(e.getPlayer());
					scheduler.scheduleSyncDelayedTask(Main.instance, new Runnable()
					{
						public void run() {
							block.setType(Material.AIR);
						}
					}
					, 100L);
				} else {
					e.getPlayer().sendMessage(ChatColor.DARK_RED + "Trap has already been used!");
				}
			}
			
			if (s.getLine(1).equalsIgnoreCase("Wall #1")) {
				if (Wallused1.isEmpty()) {
					final Block block = TrapLocations.wall1.getBlock();
					block.setType(Material.WATER);
					Wallused1.add(e.getPlayer());
					scheduler.scheduleSyncDelayedTask(Main.instance, new Runnable()
					{
						public void run() {
							block.setType(Material.AIR);
						}
					}
					, 100L);
				} else {
					e.getPlayer().sendMessage(ChatColor.DARK_RED + "Trap has already been used!");
				}
			}
			if (s.getLine(1).equalsIgnoreCase("Wall #2")) {
				if (Wallused2.isEmpty()) {
					final Block block = TrapLocations.wall2.getBlock();
					block.setType(Material.WATER);
					Wallused2.add(e.getPlayer());
					scheduler.scheduleSyncDelayedTask(Main.instance, new Runnable()
					{
						public void run() {
							block.setType(Material.AIR);
						}
					}
					, 100L);
				} else {
					e.getPlayer().sendMessage(ChatColor.DARK_RED + "Trap has already been used!");
				}
			}
			if (s.getLine(1).equalsIgnoreCase("Wall #3")) {
				if (Wallused3.isEmpty()) {
					final Block block = TrapLocations.wall3.getBlock();
					block.setType(Material.WATER);
					Wallused3.add(e.getPlayer());
					scheduler.scheduleSyncDelayedTask(Main.instance, new Runnable()
					{
						public void run() {
							block.setType(Material.AIR);
						}
					}
					, 100L);
				} else {
					e.getPlayer().sendMessage(ChatColor.DARK_RED + "Trap has already been used!");
				}
			}
			if (s.getLine(1).equalsIgnoreCase("Wall #4")) {
				if (Wallused4.isEmpty()) {
					final Block block = TrapLocations.wall4.getBlock();
					block.setType(Material.WATER);
					Wallused4.add(e.getPlayer());
					scheduler.scheduleSyncDelayedTask(Main.instance, new Runnable()
					{
						public void run() {
							block.setType(Material.AIR);
						}
					}
					, 100L);
				} else {
					e.getPlayer().sendMessage(ChatColor.DARK_RED + "Trap has already been used!");
				}
			}
			if (s.getLine(1).equalsIgnoreCase("Wall #5")) {
				if (Wallused5.isEmpty()) {
					final Block block = TrapLocations.wall5.getBlock();
					block.setType(Material.WATER);
					Wallused5.add(e.getPlayer());
					scheduler.scheduleSyncDelayedTask(Main.instance, new Runnable()
					{
						public void run() {
							block.setType(Material.AIR);
						}
					}
					, 100L);
				} else {
					e.getPlayer().sendMessage(ChatColor.DARK_RED + "Trap has already been used!");
				}
			}
			
			if (s.getLine(1).equalsIgnoreCase("BlockDisappear 1")) {
				if (BlockDisappear1.isEmpty()) {
					final Block block = TrapLocations.blockdisappear1.getBlock();
					block.setType(Material.WATER);
					BlockDisappear1.add(e.getPlayer());
					scheduler.scheduleSyncDelayedTask(Main.instance, new Runnable()
					{
						public void run() {
							block.setType(Material.AIR);
						}
					}
					, 100L);
				} else {
					e.getPlayer().sendMessage(ChatColor.DARK_RED + "Trap has already been used!");
				}
			}
			if (s.getLine(1).equalsIgnoreCase("BlockDisappear 2")) {
				if (BlockDisappear2.isEmpty()) {
					final Block block = TrapLocations.blockdisappear2.getBlock();
					block.setType(Material.WATER);
					BlockDisappear2.add(e.getPlayer());
					scheduler.scheduleSyncDelayedTask(Main.instance, new Runnable()
					{
						public void run() {
							block.setType(Material.AIR);
						}
					}
					, 100L);
				} else {
					e.getPlayer().sendMessage(ChatColor.DARK_RED + "Trap has already been used!");
				}
			}
			if (s.getLine(1).equalsIgnoreCase("BlockDisappear 3")) {
				if (BlockDisappear3.isEmpty()) {
					final Block block = TrapLocations.blockdisappear3.getBlock();
					block.setType(Material.WATER);
					BlockDisappear3.add(e.getPlayer());
					scheduler.scheduleSyncDelayedTask(Main.instance, new Runnable()
					{
						public void run() {
							block.setType(Material.AIR);
						}
					}
					, 100L);
				} else {
					e.getPlayer().sendMessage(ChatColor.DARK_RED + "Trap has already been used!");
				}
			}
			if (s.getLine(1).equalsIgnoreCase("BlockDisappear 4")) {
				if (BlockDisappear4.isEmpty()) {
					final Block block = TrapLocations.blockdisappear4.getBlock();
					block.setType(Material.WATER);
					BlockDisappear4.add(e.getPlayer());
					scheduler.scheduleSyncDelayedTask(Main.instance, new Runnable()
					{
						public void run() {
							block.setType(Material.AIR);
						}
					}
					, 100L);
				} else {
					e.getPlayer().sendMessage(ChatColor.DARK_RED + "Trap has already been used!");
				}
			}
			if (s.getLine(1).equalsIgnoreCase("BlockDisappear 5")) {
				if (BlockDisappear5.isEmpty()) {
					final Block block = TrapLocations.blockdisappear5.getBlock();
					block.setType(Material.WATER);
					BlockDisappear5.add(e.getPlayer());
					scheduler.scheduleSyncDelayedTask(Main.instance, new Runnable()
					{
						public void run() {
							block.setType(Material.AIR);
						}
					}
					, 100L);
				} else {
					e.getPlayer().sendMessage(ChatColor.DARK_RED + "Trap has already been used!");
				}
			}
		}
	}
}