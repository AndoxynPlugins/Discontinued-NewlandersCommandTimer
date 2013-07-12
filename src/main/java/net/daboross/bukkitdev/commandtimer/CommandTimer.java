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

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author daboross
 */
public class CommandTimer extends JavaPlugin {

    @Override
    public void onEnable() {
        PlayerListener pl = new PlayerListener(this);
        Bukkit.getServer().getPluginManager().registerEvents(pl, this);
        Runnable task = new CommandTask(this);
        Bukkit.getScheduler().runTask(this, task);
    }

    @Override
    public void onDisable() {
        Bukkit.getScheduler().cancelTasks(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("refreshtimer")) {
            if (args.length > 0) {
                sender.sendMessage(ChatColor.DARK_RED + "There Are Too Many Words After " + ChatColor.RED + "/" + label);
                return true;
            }
            Controller.runRemoveEntities(getLogger(), false);
            OtherCommands.runOtherCommands(getLogger());

            return true;
        }
        return false;
    }
}
