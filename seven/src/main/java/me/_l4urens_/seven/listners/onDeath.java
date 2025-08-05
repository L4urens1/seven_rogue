package me._l4urens_.seven.listners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

public class onDeath implements Listener {
    @EventHandler
    public void onDeath(EntityDamageEvent e){

        Player p = (Player) e.getEntity();

        if (e.getDamage() - p.getHealth() >= 0) {
            e.setCancelled(true);
            p.setHealth(20);
            //after add the lociton to telport to after death
        }


    }
}
