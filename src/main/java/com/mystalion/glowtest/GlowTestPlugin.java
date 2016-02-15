package com.mystalion.glowtest;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.spigotmc.event.player.PlayerSpawnLocationEvent;

public class GlowTestPlugin extends JavaPlugin implements Listener {

	@Override
	public void onEnable() {
		Bukkit.getPluginManager().registerEvents(this, this);
	}

	@EventHandler
	public void onPlayerSpawnLocation(PlayerSpawnLocationEvent event) {
		Player player = event.getPlayer();
		Location x = event.getSpawnLocation();
		x.add(0, 0, 1000);
		new BukkitRunnable() {

			@Override
			public void run() {
				try {
					Thread.sleep(40); // db query
				} catch (Exception e) {
				}
				Bukkit.getScheduler().scheduleSyncDelayedTask(GlowTestPlugin.this, () -> player.teleport(x));
			}
		}.runTaskAsynchronously(this);
	}
}
