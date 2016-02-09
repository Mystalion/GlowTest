package com.mystalion.glowtest;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class GlowTestPlugin extends JavaPlugin implements Listener {

	private Inventory inventroy;

	@Override
	public void onEnable() {
		ItemStack item = new ItemStack(Material.STAINED_GLASS_PANE);
		inventroy = Bukkit.createInventory(null, 6 * 9, "Testing Inventory Close event");
		for (int i = 0; i < 9; i++) {
			inventroy.setItem(i, item);
		}
		Bukkit.getPluginManager().registerEvents(this, this);
		getCommand("glowtest").setExecutor(new CommandExecutor() {

			public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
				if (sender instanceof Player) {
					((Player) sender).openInventory(inventroy);
				}
				return false;
			}
		});
	}

	@EventHandler
	public void onInventoryClick(InventoryCloseEvent event) {
		System.out.println(event.getPlayer().getName() + " closed inventory: Is our inventory? " + event.getInventory().equals(inventroy));
	}
}
