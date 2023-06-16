package fr.guby.plugin1;

import org.bukkit.plugin.java.JavaPlugin;

import fr.guby.plugin1.commands.CommandAlert;
import fr.guby.plugin1.commands.CommandInfo;
import fr.guby.plugin1.commands.CommandSpawn;
import fr.guby.plugin1.commands.CommandTest;

public class Main extends JavaPlugin {
	
	@Override
	public void onEnable() {
		saveDefaultConfig();
		System.out.println("Le plugin vient de s'allumer");
		getCommand("test").setExecutor(new CommandTest(this));
		getCommand("info").setExecutor(new CommandInfo());
		getCommand("alert").setExecutor(new CommandAlert());
		getCommand("spawn").setExecutor(new CommandSpawn());
		getServer().getPluginManager().registerEvents(new pluginListeners(), this);
		
		for(String string : getConfig().getConfigurationSection("teleportation").getKeys(false)) {
			System.out.println(getConfig().getConfigurationSection("teleportation").getInt(string + ".id"));
		}
		for(String string : getConfig().getStringList("badwords")){
			System.out.println(string);
		}
		//pour recupérer tout les éléments d'une section
		

	}
	
	@Override
	public void onDisable() {
		System.out.println("Le plugin vient de s'etindre");
	}
	


}
