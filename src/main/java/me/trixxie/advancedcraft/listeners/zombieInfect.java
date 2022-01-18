package me.trixxie.advancedcraft.listeners;

import me.trixxie.advancedcraft.Advancedcraft;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class zombieInfect implements Listener {

    private Advancedcraft plugin = Advancedcraft.get();

    @EventHandler
    public void zombieKillEvent(EntityDamageByEntityEvent e) {

        if(plugin.getConfig().getBoolean("infected-zombies")) {
            if (e.getEntity() instanceof Player && e.getDamager() instanceof Zombie) {
                Player p = (Player) e.getEntity();
                if (p.getHealth() <= e.getDamage()) {
                    Location loc = p.getLocation();
                    Zombie newZombie = (Zombie) p.getWorld().spawnEntity(loc, EntityType.ZOMBIE);
                    ItemStack skull = new ItemStack(Material.PLAYER_HEAD, 1);
                    SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
                    skullMeta.setOwningPlayer(p);
                    skull.setItemMeta(skullMeta);
                    newZombie.getEquipment().setHelmet(skull);
                }
            }
        }
    }
}
