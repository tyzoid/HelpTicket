package tk.tyzoid.plugins.helpTicket;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class settings {
	private Properties props = new Properties();
	
	private final HashMap<String, String> settingsHolder = new HashMap<String, String>();
	private String pluginname = "HelpTicket";
	
	public void readSettings(){
		try{
			String path = "plugins/HelpTicket";
			File propertiesFile = new File(path + "/HelpTicket.properties");
    		if(!propertiesFile.exists()){
    			(new File(path)).mkdir();
    			propertiesFile.createNewFile();
    		}
		    
			FileInputStream propertiesStream = new FileInputStream(propertiesFile);
			
			props.load(propertiesStream);
			System.out.println("[" + pluginname + "] Properties loaded.");
			propertiesStream.close();
			
			settingsHolder.put("commands", loadProperty("commands", "/nticket,/newticket"));
			settingsHolder.put("reload-commands", loadProperty("reload-commands", "/htr,/htreload"));
			
			FileOutputStream propertiesOutputStream = new FileOutputStream(propertiesFile);
			props.store(propertiesOutputStream, "");
		} catch(Exception e){
			System.out.println("[" + pluginname + "] Failed to load properties. Aborting.");
			System.out.println("[" + pluginname + "] Error: " + e.toString());
		}
	}
	
	public String getProperty(String property){
		return settingsHolder.get(property);
	}
	
	public void reloadData(){
		readSettings();
	}
	
	private String loadProperty(String property, String defaultValue){
		String currentProperty;
		currentProperty = props.getProperty(property);
		String value;
    	if(currentProperty == null){
    		System.out.println("[" + pluginname + "] Property not found: " + property + ". Resetting to: " + defaultValue);
    		props.setProperty(property, defaultValue);
    		value = defaultValue;
    	} else {
    		value = currentProperty;
    	}
    	return value;
	}
	
	@SuppressWarnings("unused")
	private void setCProperty(String property, String value){
		props.setProperty(property, value);		
	}
}