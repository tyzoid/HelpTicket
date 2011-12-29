package tk.tyzoid.plugins;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class settings {

	public FileConfiguration config;

	private HelpTicket plugin;
 
	public settings(HelpTicket plugin) {
		this.plugin = plugin;
	}
	
	public void loadConfiguration(){
		
	    config = plugin.getConfig();
	    config.options().copyDefaults(true); 
	    
	    Random gen = new Random();
	    int uuid = gen.nextInt(1000000);
	    
		String path1 = "NumberOfLogLinesRead";
		String path2 = "UUID";
		
	    config.addDefault(path1, 20);
	    config.addDefault(path2, uuid);
	    
	    config.options().copyDefaults(true);
	    plugin.saveConfig();
	}	
}