package tk.tyzoid.plugins.helpTicket;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerListener;

public class PListener extends PlayerListener {
	private final HelpTicket plugin;
	private String pluginname;
	
	PListener(HelpTicket instance){
		plugin = instance;
		pluginname = plugin.pluginname;
	}
	
	public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
    	String[] split = event.getMessage().split(" ");
    	String mess = event.getMessage();
		Player player = event.getPlayer();
		
		//the /newTicket command
		
	}
}
