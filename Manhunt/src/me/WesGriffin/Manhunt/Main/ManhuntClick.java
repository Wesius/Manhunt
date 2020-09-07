package me.WesGriffin.Manhunt.Main;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class ManhuntClick implements Listener {
	
	public static Player playerSelected;
	public static int playerNum = 0;
	
	public ManhuntClick(Main main) {

	}

	@EventHandler
	public void onClick(PlayerInteractEvent a) {
		if (Manhunt.hasStarted == false) {
			return;
		}
		else if (Manhunt.hasStarted == true && a.getPlayer().getInventory().getItemInMainHand().getType() == Material.COMPASS) {
			playerSelected = Manhunt.speedrunnerList.get(playerNum);
			if (Manhunt.speedrunnerList.size() < playerNum) {
				playerNum = 0;
			}
		}
	}

}
