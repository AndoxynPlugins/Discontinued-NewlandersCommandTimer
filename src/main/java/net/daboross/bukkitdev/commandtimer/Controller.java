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

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;

/**
 *
 * @author daboross
 */
public class Controller {

    public static void runRemoveEntities(Logger l, boolean survival) {
        String message = "";
        int c = removeEntities(Bukkit.getWorld("Creative"), true);
        int nc = removeEntities(Bukkit.getWorld("NewCreative"), true);
        int pvp = removeEntities(Bukkit.getWorld("PvPWorld"), true);
        int pvprl = removeEntities(Bukkit.getWorld("PvPrl"), true);
        if (c > 0) {
            message += c + " from Creative";
        }
        if (nc > 0) {
            if (!message.equals("")) {
                message += ", ";
            }
            message += nc + " from New Creative";
        }
        if (pvp > 0) {
            if (!message.equals("")) {
                message += ", ";
            }
            message += pvp + " from PvP";
        }
        if (pvprl > 0) {
            if (!message.equals("")) {
                message += ", ";
            }
            message += pvprl + " from PvPrl";
        }
        if (survival) {
            int s = removeEntities(Bukkit.getWorld("Survival"), false);
            int sn = removeEntities(Bukkit.getWorld("Survival_nether"), false);
            int ste = removeEntities(Bukkit.getWorld("Survival_the_end"), false);
            if (s > 0) {
                if (!message.equals("")) {
                    message += ", ";
                }
                message += s + " from Survival";
            }
            if (sn > 0) {
                if (!message.equals("")) {
                    message += ", ";
                }
                message += sn + " from Survival Nether";
            }
            if (ste > 0) {
                if (!message.equals("")) {
                    message += ", ";
                }
                message += ste + " from Survival The End";
            }
        }
        if (!message.equals("")) {
            message += ".";
            l.log(Level.INFO, message);
        }
    }

    public static int removeEntities(World world, boolean removeAnimals) {
        if (world == null) {
            return -1;
        }
        int removed = 0;
        List<Entity> entities = world.getEntities();
        for (Entity e : entities) {
            if ((e instanceof LivingEntity)) {
                if ((e instanceof Monster)) {
                    e.remove();
                    removed++;
                }
                if (removeAnimals && !(e instanceof Player)) {
                    e.remove();
                    removed++;
                }
            } else {
                e.remove();
                removed++;
            }
        }
        return removed;
    }
}
