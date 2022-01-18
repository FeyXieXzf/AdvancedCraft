package me.trixxie.advancedcraft.listeners;

import me.trixxie.advancedcraft.Advancedcraft;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class advancedMining implements Listener {

    private Advancedcraft plugin = Advancedcraft.get();

    @EventHandler
    public void stoneBreaking (BlockBreakEvent e){
        Block brokenBlock = e.getBlock();
        Location blockLoc = brokenBlock.getLocation();
        Player p = e.getPlayer();

        if (plugin.getConfig().getBoolean("use-permissions")) {
            if (!p.hasPermission("advancedcraft.layers")) return;
        }

        if(p.getEquipment().getItemInMainHand().getType().toString().toLowerCase().contains("pickaxe")){
            if(brokenBlock.getType().equals(Material.STONE)) {
                if(plugin.getConfig().getBoolean("layered-stone")) {
                    new BukkitRunnable() {

                        @Override
                        public void run() {
                            blockLoc.getBlock().setType(Material.COBBLESTONE);
                        }
                    }.runTaskLater(plugin, 1);
                }
            }
            if(brokenBlock.getType().equals(Material.DEEPSLATE)){
                if(plugin.getConfig().getBoolean("layered-stone")) {
                    new BukkitRunnable() {

                        @Override
                        public void run() {
                            blockLoc.getBlock().setType(Material.COBBLED_DEEPSLATE);
                        }
                    }.runTaskLater(plugin, 1);
                }
            }
            if(brokenBlock.getType().toString().toLowerCase().contains("ore") && brokenBlock.getType().toString().toLowerCase().contains("deepslate")){
                if(plugin.getConfig().getInt("layered-ore") == 0) return;
                new BukkitRunnable() {

                    @Override
                    public void run() {
                        if(plugin.getConfig().getInt("layered-ore") == 2) {
                            blockLoc.getBlock().setType(Material.DEEPSLATE);
                        } else {
                            blockLoc.getBlock().setType(Material.COBBLED_DEEPSLATE);
                        }
                    }
                }.runTaskLater(plugin, 1);
            }
            if(brokenBlock.getType().toString().toLowerCase().contains("ore") && !brokenBlock.getType().toString().toLowerCase().contains("deepslate")){
                if(plugin.getConfig().getInt("layered-ore") == 0) return;
                new BukkitRunnable() {

                    @Override
                    public void run() {
                        if(plugin.getConfig().getInt("layered-ore") == 2) {
                            blockLoc.getBlock().setType(Material.STONE);
                        } else {
                            blockLoc.getBlock().setType(Material.COBBLESTONE);
                        }
                    }
                }.runTaskLater(plugin, 1);
            }
        }
    }
}
