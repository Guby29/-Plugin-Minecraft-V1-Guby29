package fr.guby.plugin1;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;


public class pluginListeners implements Listener {


	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		
		Player player = event.getPlayer();
		player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 2));
		player.getInventory().clear();
		
		ItemStack customcompass = new ItemStack(Material.COMPASS, 1);
		ItemMeta customM = customcompass.getItemMeta();
		customM.setDisplayName("§2Menu de navigation");
		customM.setLore(Arrays.asList("premiere ligne","deuxieme ligne"));
		customM.addEnchant(Enchantment.DAMAGE_ALL, 200, true);
		customM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		customcompass.setItemMeta(customM);
		
		player.getInventory().setItem(4, customcompass);
		player.updateInventory();
		
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		
		Player player = event.getPlayer();
		ItemStack it = event.getItem();
		
		
		if(it.getType() == Material.COMPASS && it.hasItemMeta() && it.getItemMeta().hasDisplayName() && it.getItemMeta().getDisplayName().equalsIgnoreCase("§2Menu de navigation")) {
			
			Inventory inv = Bukkit.createInventory(null, 27, "§8Menu de navigation");
			player.sendMessage("§a Vous venez d ouvrir le menu de naviagtion");
			
			inv.setItem(11, getItem(Material.APPLE, "§ePassage en Gamemode 3"));
			inv.setItem(15, getItem(Material.ANVIL, "§aSe teleporter au spawn"));
			
			player.openInventory(inv);
		}
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent event) {
		Inventory inv = event.getInventory();
		Player player = (Player)event.getWhoClicked();
		ItemStack current = event.getCurrentItem();
		
		if(current == null) return;
		
		if(inv.getName().equalsIgnoreCase("§8Menu de navigation")) {
			
			event.setCancelled(true);
			player.closeInventory();
			
			if(current.getType() == Material.APPLE) {
				player.setGameMode(GameMode.SPECTATOR);
				
			}
			
		}
		
		
		
	}
	
	
	public ItemStack getItem(Material material, String customName) {
		ItemStack it = new ItemStack(material, 1);
		ItemMeta itM = it.getItemMeta();
		if(customName != null) itM.setDisplayName(customName);
		it.setItemMeta(itM);
		return it;
	}

}	
