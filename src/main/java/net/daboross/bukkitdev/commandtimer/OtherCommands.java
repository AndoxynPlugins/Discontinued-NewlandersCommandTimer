/*
 * Copyright (C) 2013 Dabo Ross <http://www.daboross.net/>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
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
