package com.github.jean2233.blockreward.listener;

import com.github.jean2233.blockreward.BlockRewardPlugin;
import lombok.RequiredArgsConstructor;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

@RequiredArgsConstructor
public class BlockListener implements Listener {

    private final BlockRewardPlugin plugin;

    @EventHandler(ignoreCancelled = true, priority = EventPriority.LOWEST)
    public void onBlockBreak(BlockBreakEvent event) {
        final Block block = event.getBlock();
        if(!isWhitelisted(block.getWorld().getName())) return;

        plugin.getRewardController().execute(event.getPlayer(), block);
    }

    private boolean isWhitelisted(String world) {
        return plugin.getConfig().getStringList("world-whitelist").contains(world);
    }
}