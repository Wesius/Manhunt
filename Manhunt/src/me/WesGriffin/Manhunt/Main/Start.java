package me.WesGriffin.Manhunt.Main;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

public class Start implements Listener{

	public static void whitelist() {
		Bukkit.getServer().setWhitelist(true);
		Bukkit.getPlayer("Wesius").setWhitelisted(true);
	}
}
