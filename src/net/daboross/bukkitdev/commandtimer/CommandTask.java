package net.daboross.bukkitdev.commandtimer;

import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author daboross
 */
public class CommandTask implements Runnable {

    private Logger l;
    private JavaPlugin instance;

    public CommandTask(Logger l, JavaPlugin instance) {
        this.l = l;
        this.instance = instance;
    }

    @Override
    public void run() {
        if (Bukkit.getServer().getOnlinePlayers().length > 0) {
            Controller.runRemoveEntities(l, false);
            OtherCommands.runOtherCommands(l);
            if (instance.isEnabled()) {
                Bukkit.getScheduler().runTaskLater(instance, new CommandTask(l, instance), 6000);
            }
        } else {
            if (instance.isEnabled()) {
                Bukkit.getScheduler().runTaskLater(instance, new CommandTask(l, instance), 12000);
            }
        }
    }
}
