package net.daboross.bukkitdev.commandtimer;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

/**
 *
 * @author daboross
 */
public class OtherCommands {

    public static void runOtherCommands(Logger l) {
        World[] worldsToSetTime = new World[]{Bukkit.getWorld("Creative"), Bukkit.getWorld("NewCreative"), Bukkit.getWorld("PvPWorld"), Bukkit.getWorld("PvPrl"), Bukkit.getWorld("Flat")};
        for (World wd : worldsToSetTime) {
            wd.setTime(0);
        }
        Bukkit.savePlayers();
        for (World wd : Bukkit.getServer().getWorlds()) {
            l.log(Level.INFO, "Saving {0}", wd.getName());
            wd.save();
            l.log(Level.INFO, "Done Saving {0}", wd.getName());
        }
        l.log(Level.INFO, "Online Players:");
        Server server = Bukkit.getServer();
        ConsoleCommandSender ccs = server.getConsoleSender();
        for (Player p : Bukkit.getServer().getOnlinePlayers()) {
            ccs.sendMessage(ChatColor.WHITE + p.getName() + ChatColor.GRAY + " / " + ChatColor.RED + p.getDisplayName());
        }
    }

    public static void runSave() {
        Bukkit.savePlayers();
        for (World wd : Bukkit.getServer().getWorlds()) {
            wd.save();
        }
    }
}
