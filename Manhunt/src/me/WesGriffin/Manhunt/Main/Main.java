package me.WesGriffin.Manhunt.Main;

import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import me.WesGriffin.Manhunt.Main.ManhuntClick;
import me.WesGriffin.Manhunt.Main.ManhuntMove;

public class Main extends JavaPlugin implements Listener{
	
	@Override
	public void onEnable() {
		
		PluginManager pm = this.getServer().getPluginManager();
		pm.registerEvents(new ManhuntMove(this), this);
		pm.registerEvents(new ManhuntClick(this), this);
		this.getServer().getPluginManager().registerEvents(this, this);
		registerCmds();
		Start.whitelist();
		//getConfig().options().copyDefaults(true);
		//this.saveDefaultConfig();
	
	}
	@Override
	public void onDisable() {
		
		
		
	}
	public void registerCmds() {
		
		this.getCommand("manhunt").setExecutor(new Manhunt(this)); // /heal <player-name> heals player to full health
	}
}
