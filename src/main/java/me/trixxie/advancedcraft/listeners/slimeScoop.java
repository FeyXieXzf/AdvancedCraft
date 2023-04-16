package me.trixxie.advancedcraft.listeners;

import me.trixxie.advancedcraft.AdvancedCraft;
import org.bukkit.Material;
import org.bukkit.entity.MagmaCube;
import org.bukkit.entity.Player;
import org.bukkit.entity.Slime;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class slimeScoop implements Listener {

    private final AdvancedCraft plugin = AdvancedCraft.get();

    @EventHandler
    public void slimeDrops(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Player) {
            Player p = (Player) e.getDamager();
            if (plugin.getConfig().getBoolean("use-permissions")) {
                if (!p.hasPermission("advancedcraft.slimescooping")) return;
            }
            if (p.getEquipment().getItemInMainHand().getType().toString().toLowerCase().contains("shovel")) {
                if (e.getEntity() instanceof Slime) {
                    if (plugin.getConfig().getBoolean("slime-scooping")) {
                        if (countChance()) {
                            e.getEntity().getLocation().getWorld().dropItem(e.getEntity().getLocation(), new ItemStack(Material.SLIME_BALL, plugin.getConfig().getInt("extra-slime-count")));
                        }
                    }
                }
                if (e.getEntity() instanceof MagmaCube) {
                    if (plugin.getConfig().getBoolean("magmacube-scooping")) {
                        if (countChance()) {
                            e.getEntity().getLocation().getWorld().dropItem(e.getEntity().getLocation(), new ItemStack(Material.MAGMA_CREAM, plugin.getConfig().getInt("extra-slime-count")));
                        }
                    }
                }
            }
        }
    }

    private boolean countChance() {
        int chancePercentage = plugin.getConfig().getInt("slime-drop-chance");
        Random rand = new Random();

        int n = rand.nextInt(100) + 1;
        return n <= chancePercentage;
    }
}
