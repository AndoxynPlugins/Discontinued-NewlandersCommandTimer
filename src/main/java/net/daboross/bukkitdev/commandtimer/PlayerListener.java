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
