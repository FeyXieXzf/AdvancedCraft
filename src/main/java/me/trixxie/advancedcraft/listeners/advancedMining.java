package me.trixxie.advancedcraft.listeners;

import me.trixxie.advancedcraft.AdvancedCraft;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class advancedMining implements Listener {

    private final AdvancedCraft plugin = AdvancedCraft.get();

    @EventHandler
    public void stoneBreaking (BlockBreakEvent e){
        Block brokenBlock = e.getBlock();
        Location blockLoc = brokenBlock.getLocation();
        Player p = e.getPlayer();

        if(p.getEquipment().getItemInMainHand().getType().toString().toLowerCase().contains("pickaxe")){
            // Breaking normal ores
            if(brokenBlock.getType().toString().toLowerCase().contains("ore") && !brokenBlock.getType().toString().toLowerCase().contains("deepslate")){
                new BukkitRunnable() {
                    @Override
                    public void run() {
                            blockLoc.getBlock().setType(Material.STONE);
                    }
                }.runTaskLater(plugin, 1);
            }

            // Breaking deepslate ores
            if(brokenBlock.getType().toString().toLowerCase().contains("ore") && brokenBlock.getType().toString().toLowerCase().contains("deepslate")){
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        blockLoc.getBlock().setType(Material.DEEPSLATE);
                    }
                }.runTaskLater(plugin, 1);
            }
        }
    }
}
