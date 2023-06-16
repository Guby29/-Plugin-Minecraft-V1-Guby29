package fr.guby.plugin1.commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandSpawn implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(sender instanceof Player) {
			Player player = (Player) sender;
			Location spawn = new Location(player.getWorld(), -101.456, 68, -3.402, 90.4f, 1.8f);
			player.sendMessage("§a Vous avez ete teleporte au spawn !");
			player.teleport(spawn);
		}
		
		return false;
	}

}
