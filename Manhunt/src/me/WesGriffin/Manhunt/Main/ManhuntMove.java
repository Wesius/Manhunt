package me.WesGriffin.Manhunt.Main;

import me.WesGriffin.Manhunt.Main.Manhunt;
import me.WesGriffin.Manhunt.Main.ManhuntClick;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

public class ManhuntMove implements Listener {

	public ManhuntMove(Main main) {
		
	}
	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		if (Manhunt.hasStarted == true) {
			e.getPlayer().setCompassTarget(ManhuntClick.playerSelected.getLocation());
			String message = ChatColor.BOLD + "" +  ChatColor.RED + "Speedrunner Location: " + ChatColor.GREEN + ManhuntClick.playerSelected.getLocation().getBlockX() + ChatColor.RED + ", " + ChatColor.GREEN + ManhuntClick.playerSelected.getLocation().getBlockZ()
			+ ChatColor.RED + " Speedrunner Distance: " + ChatColor.GREEN + ManhuntClick.playerSelected.getLocation().distance(e.getPlayer().getLocation());
			e.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(message));
		}
		else if (Manhunt.hasStarted == false) {
			return;
		}
		
		else if (Manhunt.headStart == true) {
			e.setCancelled(true);
		}
	}
}


/*
String message = "§6§lWesius is at: " + Bukkit.getPlayer("Wesius").getLocation().getBlockX() + ", " + Bukkit.getPlayer("Wesius").getLocation().getBlockZ() + ". He is " + Bukkit.getPlayer("Wesius").getLocation().distance(e.getPlayer().getLocation()) + " blocks away.";
e.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(message));
*/