package com.mystalion.glowtest;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class GlowTestPlugin extends JavaPlugin implements Listener {

	private Location x1;
	private Location x2;
	private boolean u = false;

	@Override
	public void onEnable() {
		World world = Bukkit.getWorlds().get(0);
		x1 = new Location(world, 0, 150, 0);
		x2 = new Location(world, 1000, 150, 10);
		for (int x = x1.getBlockX(); x < x2.getBlockX(); x++) {
			for (int z = x1.getBlockZ(); z < x2.getBlockZ(); z++) {
				Block block = world.getBlockAt(x, 150, z);
				block.setType(Material.STONE);
			}
		}
		x1 = new Location(world, 5, 151, 5);
		x2 = new Location(world, 995, 151, 5);
		getCommand("glowtest").setExecutor((sender, command, label, args) -> {
			if (sender instanceof Player) {
				if (u) {
					((Player) sender).teleport(x1);
				} else {
					((Player) sender).teleport(x2);
				}
				u = !u;
			}
			return true;
		});
	}
}
