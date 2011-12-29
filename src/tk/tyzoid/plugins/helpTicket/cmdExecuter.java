package tk.tyzoid.plugins;

import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.io.*;
import java.util.ArrayList;

public class cmdExecuter implements CommandExecutor {
    

	public ArrayList<String> plugins = new ArrayList<String>();
	
	@SuppressWarnings("unused")
	private HelpTicket plugin;
 
	public cmdExecuter(HelpTicket plugin) {
		this.plugin = plugin;
	}

	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		Player player = null;
	     if(command.getName().equalsIgnoreCase("newticket") || command.getName().equalsIgnoreCase("nticket")){
		if (sender instanceof Player) {
			player = (Player) sender;
			if(plugin.hasPermission(player, "helpticket.*") || plugin.hasPermission(player, "helpticket.send")){
		    	 player.sendMessage("Sender: " + player.getName());
		    	 player.sendMessage("Java Info: " + javaInfo());
		    	 player.sendMessage("OS Info: " + osInfo());
		    	 Plugin[] all = getPlugins();
	                for(Plugin plugin:all){
			    		 String s = plugin.getDescription().getName();
			    		 plugins.add(s);
			    	 }
	             player.sendMessage("Plugins: " + plugins);
	             player.sendMessage("UUID: " + plugin.getUUID());
	             File file = new File("server.log");
	             player.sendMessage(ChatColor.RED + "Log: " + readLog(file, plugin.getLines()));
	             player.sendMessage("Lines: " + plugin.getLines());
		    	 return true;
			}else{
				player.sendMessage(ChatColor.RED + "You ain't got no perms for this!");
			}
		}else{
			System.out.println("[HelpTicket] Plaease run this command as a player");
			return true;
		}
	     }else if(command.getName().equalsIgnoreCase("htreload") || command.getName().equalsIgnoreCase("htr")){
	 		if (sender instanceof Player) {
	    	 player = (Player) sender;
				if(!(plugin.hasPermission(player, "helpticket.*") || plugin.hasPermission(player, "helpticket.send"))){
					player.sendMessage(ChatColor.RED + "You ain't got no perms for this!");
					return false;
				}
	 		}
	    	 plugin.reloadData(player);
	    	 return true;
	     }
		return false;
}
	
	public String javaInfo(){
		String version = System.getProperty("java.version");
		String vendor = System.getProperty("java.vendor");
		return (vendor + " " + version);
	}
	
	public String osInfo(){
		String os = System.getProperty("os.name");
		String version = System.getProperty("os.version");
		String arch = System.getProperty("os.arch");
		return (os + " " + version + " " + arch);
	}
	
	public Plugin[] getPlugins(){
		Plugin[] list = plugin.getServer().getPluginManager().getPlugins();
		return list;
	}
	
	public String readLog( File file, int lines) {
		    try {
		        java.io.RandomAccessFile fileHandler = new java.io.RandomAccessFile( file, "r" );
		        long fileLength = file.length() - 1;
		        StringBuilder sb = new StringBuilder();
		        int line = 0;

		        for( long filePointer = fileLength; filePointer != -1; filePointer-- ) {
		            fileHandler.seek( filePointer );
		            int readByte = fileHandler.readByte();

		            if( readByte == 0xA ) {
		                if (line == lines) {
		                    if (filePointer == fileLength) {
		                        continue;
		                    } else {
		                        break;
		                    }
		                }
		            } else if( readByte == 0xD ) {
		                line = line + 1;
		                if (line == lines) {
		                    if (filePointer == fileLength - 1) {
		                        continue;
		                    } else {
		                        break;
		                    }
		                }
		            }
		           sb.append( ( char ) readByte );
		        }

		        sb.deleteCharAt(sb.length()-1);
		        String lastLine = sb.reverse().toString();
		        return lastLine;
		    } catch( java.io.FileNotFoundException e ) {
		        e.printStackTrace();
		        return null;
		    } catch( java.io.IOException e ) {
		        e.printStackTrace();
		        return null;
		    }
		}

}