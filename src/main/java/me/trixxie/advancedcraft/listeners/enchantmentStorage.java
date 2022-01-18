package me.trixxie.advancedcraft.listeners;

import me.trixxie.advancedcraft.Advancedcraft;
import me.trixxie.advancedcraft.utils.Experience;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class enchantmentStorage implements Listener {

    private Advancedcraft plugin = Advancedcraft.get();

    Map<String, Long> cooldown = new HashMap<String, Long>();

    @EventHandler
    public void enchantStorage(PlayerInteractEvent e){

        if (plugin.getConfig().getBoolean("XPstorage")) {
            Player p = e.getPlayer();
            if (plugin.getConfig().getBoolean("use-permissions")) {
                if (!p.hasPermission("advancedcraft.xpstorage")) return;
            }
                if (Experience.getExp(p) >= 7) {
                    if (p.getEquipment().getItemInMainHand().getType().equals(Material.GLASS_BOTTLE)) {
                        if (p.isSneaking()) {
                            if (cooldown.containsKey(e.getPlayer().getName())) {
                                if (cooldown.get(e.getPlayer().getName()) > System.currentTimeMillis()) {
                                    return;
                                }
                            }
                            Experience.changeExp(p, -7);
                            Location pl = p.getLocation();
                            p.getLocation().getWorld().playSound(pl, Sound.ENTITY_WITCH_DRINK, 10, 1);
                            Integer bottleAmount = p.getInventory().getItemInMainHand().getAmount();
                            if (bottleAmount == 1) {
                                p.getInventory().setItemInMainHand(new ItemStack(Material.AIR));
                            } else {
                                p.getInventory().getItemInMainHand().setAmount(bottleAmount - 1);
                            }

                            final ItemStack finalProduct = new ItemStack(Material.EXPERIENCE_BOTTLE, 1);
                            final Map<Integer, ItemStack> map = p.getInventory().addItem(finalProduct);
                            if (!map.isEmpty()) {
                                p.getLocation().getWorld().dropItem(pl, finalProduct);
                            }
                            cooldown.put(e.getPlayer().getName(), System.currentTimeMillis() + 1 * 1000);
                        }
                    }
                }
        }
    }
}
