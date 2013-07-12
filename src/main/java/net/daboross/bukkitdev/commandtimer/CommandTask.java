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
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author daboross
 */
public class CommandTask implements Runnable {

    private JavaPlugin instance;

    public CommandTask(JavaPlugin instance) {
        this.instance = instance;
    }

    @Override
    public void run() {
        if (Bukkit.getServer().getOnlinePlayers().length > 0) {
            Controller.runRemoveEntities(instance.getLogger(), false);
            OtherCommands.runOtherCommands(instance.getLogger());
            if (instance.isEnabled()) {
                Bukkit.getScheduler().runTaskLater(instance, this, 6000);
            }
        } else {
            if (instance.isEnabled()) {
                Bukkit.getScheduler().runTaskLater(instance, this, 12000);
            }
        }
    }
}
