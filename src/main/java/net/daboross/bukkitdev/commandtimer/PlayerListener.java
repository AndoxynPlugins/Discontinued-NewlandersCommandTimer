package net.daboross.bukkitdev.commandtimer;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author daboross
 */
public class PlayerListener implements Listener {

    JavaPlugin instance;

    protected PlayerListener(JavaPlugin instance) {
        this.instance = instance;
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onLogout(PlayerQuitEvent qu) {
        int onlinePlayers = Bukkit.getServer().getOnlinePlayers().length;
        if (onlinePlayers < 2) {
            Controller.runRemoveEntities(instance.getLogger(), true);
            OtherCommands.runSave();
        }
    }
}
