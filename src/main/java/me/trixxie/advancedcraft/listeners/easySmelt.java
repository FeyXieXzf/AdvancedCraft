package me.trixxie.advancedcraft.listeners;

import me.trixxie.advancedcraft.Advancedcraft;
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

public class easySmelt implements Listener {

    private Advancedcraft plugin = Advancedcraft.get();

    Map<String, Long> cooldown = new HashMap<String, Long>();

    @EventHandler
    public void smeltingHands(PlayerInteractEvent e) {

        if (plugin.getConfig().getBoolean("hand-smelter")) {
            Player p = e.getPlayer();

            if (plugin.getConfig().getBoolean("use-permissions")) {
                if (!p.hasPermission("advancedcraft.smelt")) return;
            }

                // IRON SMELT
                if (p.getInventory().getItemInMainHand().getType().equals(Material.RAW_IRON)) {
                    for (String items : plugin.getConfig().getStringList("acceptable-fuel")) {
                        if (p.getInventory().getItemInOffHand().getType() == Material.matchMaterial(items)) {
                            if (cooldown.containsKey(e.getPlayer().getName())) {
                                if (cooldown.get(e.getPlayer().getName()) > System.currentTimeMillis()) {
                                    return;
                                }
                            }
                            Location pl = p.getLocation();
                            Integer fuelAmount = p.getInventory().getItemInOffHand().getAmount();
                            Integer smeltAmount = p.getInventory().getItemInMainHand().getAmount();

                            if (fuelAmount == 1) {
                                p.getInventory().setItemInOffHand(new ItemStack(Material.AIR));
                            } else {
                                p.getInventory().getItemInOffHand().setAmount(fuelAmount - 1);
                            }

                            if (smeltAmount == 1) {
                                p.getInventory().setItemInMainHand(new ItemStack(Material.AIR));
                            } else {
                                p.getInventory().getItemInMainHand().setAmount(smeltAmount - 1);
                            }

                            final ItemStack finalProduct = new ItemStack(Material.IRON_INGOT, 1);
                            final Map<Integer, ItemStack> map = p.getInventory().addItem(finalProduct);
                            if (!map.isEmpty()) {
                                p.getLocation().getWorld().dropItem(pl, finalProduct);
                            }
                            p.updateInventory();
                            p.getLocation().getWorld().playSound(pl, Sound.BLOCK_FURNACE_FIRE_CRACKLE, 10, 1);
                            cooldown.put(e.getPlayer().getName(), System.currentTimeMillis() + 1 * 1000);
                        }
                    }
                }
                // GOLD SMELT
                if (p.getInventory().getItemInMainHand().getType().equals(Material.RAW_GOLD)) {
                    for (String items : plugin.getConfig().getStringList("acceptable-fuel")) {
                        if (p.getInventory().getItemInOffHand().getType() == Material.matchMaterial(items)) {
                            if (cooldown.containsKey(e.getPlayer().getName())) {
                                if (cooldown.get(e.getPlayer().getName()) > System.currentTimeMillis()) {
                                    return;
                                }
                            }
                            Location pl = p.getLocation();
                            Integer fuelAmount = p.getInventory().getItemInOffHand().getAmount();
                            Integer smeltAmount = p.getInventory().getItemInMainHand().getAmount();

                            if (fuelAmount == 1) {
                                p.getInventory().setItemInOffHand(new ItemStack(Material.AIR));
                            } else {
                                p.getInventory().getItemInOffHand().setAmount(fuelAmount - 1);
                            }

                            if (smeltAmount == 1) {
                                p.getInventory().setItemInMainHand(new ItemStack(Material.AIR));
                            } else {
                                p.getInventory().getItemInMainHand().setAmount(smeltAmount - 1);
                            }

                            final ItemStack finalProduct = new ItemStack(Material.GOLD_INGOT, 1);
                            final Map<Integer, ItemStack> map = p.getInventory().addItem(finalProduct);
                            if (!map.isEmpty()) {
                                p.getLocation().getWorld().dropItem(pl, finalProduct);
                            }

                            p.updateInventory();
                            p.getLocation().getWorld().playSound(pl, Sound.BLOCK_FURNACE_FIRE_CRACKLE, 10, 1);
                            cooldown.put(e.getPlayer().getName(), System.currentTimeMillis() + 1 * 1000);
                        }
                    }
                }
                // COPPER SMELT
                if (p.getInventory().getItemInMainHand().getType().equals(Material.RAW_COPPER)) {
                    for (String items : plugin.getConfig().getStringList("acceptable-fuel")) {
                        if (p.getInventory().getItemInOffHand().getType() == Material.matchMaterial(items)) {
                            if (cooldown.containsKey(e.getPlayer().getName())) {
                                if (cooldown.get(e.getPlayer().getName()) > System.currentTimeMillis()) {
                                    return;
                                }
                            }
                            Location pl = p.getLocation();
                            Integer fuelAmount = p.getInventory().getItemInOffHand().getAmount();
                            Integer smeltAmount = p.getInventory().getItemInMainHand().getAmount();

                            if (fuelAmount == 1) {
                                p.getInventory().setItemInOffHand(new ItemStack(Material.AIR));
                            } else {
                                p.getInventory().getItemInOffHand().setAmount(fuelAmount - 1);
                            }

                            if (smeltAmount == 1) {
                                p.getInventory().setItemInMainHand(new ItemStack(Material.AIR));
                            } else {
                                p.getInventory().getItemInMainHand().setAmount(smeltAmount - 1);
                            }

                            final ItemStack finalProduct = new ItemStack(Material.COPPER_INGOT, 1);
                            final Map<Integer, ItemStack> map = p.getInventory().addItem(finalProduct);
                            if (!map.isEmpty()) {
                                p.getLocation().getWorld().dropItem(pl, finalProduct);
                            }

                            p.updateInventory();
                            p.getLocation().getWorld().playSound(pl, Sound.BLOCK_FURNACE_FIRE_CRACKLE, 10, 1);
                            cooldown.put(e.getPlayer().getName(), System.currentTimeMillis() + 1 * 1000);
                        }
                    }
                }
        }
    }
}
