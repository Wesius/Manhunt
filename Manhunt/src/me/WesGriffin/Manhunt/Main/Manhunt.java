package me.WesGriffin.Manhunt.Main;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import net.md_5.bungee.api.ChatColor;

public class Manhunt implements CommandExecutor {
	
	static Main plugin;
	
	public Manhunt(Main main) {

		plugin = main;
		
	}
	public static boolean hasStarted = false;
	public static boolean headStart = false;
	public static List<Player> hunterList = new ArrayList<Player>();
	public static List<Player> speedrunnerList = new ArrayList<Player>();
	public static Location loc;
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if (args.length == 1 && args[0].toString().equals("clear")) {
			hunterList.clear();
			speedrunnerList.clear();
			sender.sendMessage(ChatColor.RED + "Cleared both teams");
		}
		else if (args.length == 1 && args[0].toString().equals("start")) {
			startGame(speedrunnerList, hunterList, sender);
			
		}
		
		
		if (args.length == 2) {
			if (args[0].toString().equals("hunter") && Bukkit.getPlayer(args[1].toString()) != null) {
				hunterList.add(Bukkit.getPlayer(args[1]));
				sender.sendMessage(ChatColor.RED + "Successfully added " + args[1] + " to the hunter team");
				sender.sendMessage(ChatColor.RED + "Current hunter team: " + hunterList.toString());
			}
			else if (args[0].toString().equals("speedrunner") && Bukkit.getPlayer(args[1].toString()) != null) {
				speedrunnerList.add(Bukkit.getPlayer(args[1]));
				sender.sendMessage(ChatColor.RED + "Successfully added " + args[1] + " to the speedrunner team");
				sender.sendMessage(ChatColor.RED + "Current speedrunner team: " + speedrunnerList.toString());
			}
			
			else {
				sender.sendMessage("The first argument must be either hunter or speedrunner. Refer to the docs for more info. Also, the player specified must be online.");
			}
			return true;
		}
		return false;
	}
	
	public void startGame(List<Player> speedrunners, List<Player> hunters, CommandSender send) {
		if (speedrunners.size() == 0 || hunters.size() == 0) {
			send.sendMessage("There must be players in both groups for the game to start!");
			return;
		}
		hasStarted = true;
		loc = speedrunners.get(0).getLocation();
		for (Player on : Bukkit.getOnlinePlayers()) {
			on.getInventory().clear();
			on.sendMessage(ChatColor.BOLD + "" + ChatColor.GREEN + "--------------------------------");
			on.sendMessage(ChatColor.BOLD + "" +  ChatColor.WHITE + "" + "              Manhunt             ");
			on.sendMessage(ChatColor.YELLOW + "In Minecraft Manhunt, ");
			on.sendMessage(ChatColor.YELLOW + "hunters to kill the speedrunners before they");
			on.sendMessage(ChatColor.YELLOW + "manage to kill the " + ChatColor.BOLD + "" + ChatColor.DARK_RED + "Ender Dragon");
			on.sendMessage(ChatColor.YELLOW + "Hunters have infinite lives, but speedrunners have only one.");
			on.sendMessage(ChatColor.YELLOW + "Speedrunners start with 4 peces of wood, as well as a 1 min head start");
			on.sendMessage(ChatColor.YELLOW + "Craft a compass to be able to track the opposing team!");
			on.sendMessage(ChatColor.YELLOW + "Hunters can kill the ender dragon to force the speedrunners to respawn it");
			on.sendMessage(ChatColor.YELLOW + "After 10 mins, each player gets a compass");
			on.sendMessage(ChatColor.YELLOW + "After 25 mins, each player gets 10 obsidian");
			on.sendMessage(ChatColor.YELLOW + "After 35 mins, each player gets 2 diamonds");
			on.sendMessage(ChatColor.YELLOW + "After 50 mins, each player gets 8 diamonds");
			on.sendMessage(ChatColor.YELLOW + "After 60 mins, each player gets 10 blaze rods");
			on.sendMessage(ChatColor.YELLOW + "After 80 mins, each player gets 10 ender pearls");
			on.sendMessage(ChatColor.YELLOW + "After 100 mins, each player is teleported to the end");
			on.sendMessage(ChatColor.YELLOW + "If nobody kills the dragon in 120 mins, the hunters win!");
			on.sendMessage(ChatColor.YELLOW + "Good Luck, and Good Hunting");
			on.sendMessage(ChatColor.BOLD + "" + ChatColor.GREEN+ "--------------------------------");
			if (speedrunners.contains(on)) {
				for (int i = 0; i < 4; i++) {
				on.getInventory().addItem(new ItemStack(Material.OAK_WOOD));
				}
			}
			
			
		}
		new BukkitRunnable(){
			int i = 0;
			public void run() {
				
				
					
					if (i < 60) {
						
						headStart = true;
						for (Player on : Bukkit.getOnlinePlayers()) {
							
							if(speedrunners.contains(on)) {
								
								on.sendMessage(ChatColor.RED + "" + ((i-60)*-1)+ " Seconds left!");
								on.setFoodLevel(20);
								on.setSaturation(20);
								on.setHealth(20);
								continue;
							}
							on.setFoodLevel(20);
							on.setHealth(20);
							on.setSaturation(20);
							on.getInventory().clear();
							on.sendMessage(ChatColor.RED + "" + ((i-60)*-1)+ " Seconds left!");
						}
					}
					
					if (i > 60) {
						
						headStart = false;
					}
					
					if (i > 600) {
						for (Player on : Bukkit.getOnlinePlayers()) {
							on.getInventory().addItem(new ItemStack(Material.COMPASS));
						}
					}
					
					if (i > 1500) {
						for (Player on : Bukkit.getOnlinePlayers()) {
							for (int z = 0; z < 10; z++) {
								on.getInventory().addItem(new ItemStack(Material.OBSIDIAN));
							}
						}
					}
					
					if (i > 2100) {
						for (Player on : Bukkit.getOnlinePlayers()) {
							for (int z = 0; z < 2; z++) {
								on.getInventory().addItem(new ItemStack(Material.DIAMOND));
							}
						}
					}
					
					if (i > 3000) {
						for (Player on : Bukkit.getOnlinePlayers()) {
							for (int z = 0; z < 8; z++) {
								on.getInventory().addItem(new ItemStack(Material.DIAMOND));
							}
						}
					}
					
					if (i > 3600) {
						for (Player on : Bukkit.getOnlinePlayers()) {
							for (int z = 0; z < 10; z++) {
								on.getInventory().addItem(new ItemStack(Material.BLAZE_ROD));
							}
						}
					}
					
					if (i > 4800) {
						for (Player on : Bukkit.getOnlinePlayers()) {
							for (int z = 0; z < 10; z++) {
								on.getInventory().addItem(new ItemStack(Material.ENDER_PEARL));
							}
						}
					}
					
					if (i > 6000) {
						for (Player on : Bukkit.getOnlinePlayers()) {
							//on.teleport(new Location(on.getServer().getWorld("world_end"), on.getLocation().getX()/7, on.getLocation().getY(), on.getLocation().getZ()/7));
						}
					}
					
					i++;
				}
				
				
				
				
		}.runTaskTimer(plugin, 0, 20); 
	}
}
