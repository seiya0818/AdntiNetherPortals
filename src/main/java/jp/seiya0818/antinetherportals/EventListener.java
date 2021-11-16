package jp.seiya0818.antinetherportals;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.World.Environment;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.bukkit.plugin.java.JavaPlugin;

public class EventListener implements Listener
{
	private final AntiNetherPortals plugin;

	public EventListener(AntiNetherPortals main, JavaPlugin plugin)
	{
		this.plugin = main;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onTeleport(PlayerTeleportEvent e)
	{
		FileConfiguration conf = plugin.getConfig();

		if(!e.getCause().equals(TeleportCause.NETHER_PORTAL)) return;

		if(!e.getFrom().getWorld().getEnvironment().equals(Environment.NETHER) && !e.getTo().getWorld().getEnvironment().equals(Environment.NETHER)) return;

		List<String> allowedPlayers = conf.getStringList("allowed-players");
		if(allowedPlayers == null || allowedPlayers.isEmpty())
			allowedPlayers = new ArrayList<>();
		Player player = e.getPlayer();

		if(allowedPlayers.contains(player.getUniqueId().toString()) || allowedPlayers.contains(player.getName())) return;

		if(!conf.getStringList("allowed-worlds").contains(e.getFrom().getWorld().getName()))
		{
			player.sendMessage(AntiNetherPortals.prefix + plugin.getColoredStr("error-message"));
			e.setCancelled(true);
		}
	}
}
