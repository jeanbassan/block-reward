package com.github.jean2233.blockreward.controller;

import com.github.jean2233.blockreward.BlockRewardPlugin;
import com.github.jean2233.blockreward.data.Reward;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.List;

@RequiredArgsConstructor
public class RewardController {

    private final BlockRewardPlugin plugin;

    public void execute(Player player, Block block) {
        final List<Reward> rewards = plugin.getRewardCache().getByBlock(block);
        if(rewards.isEmpty()) return;

        for (Reward reward : rewards) {
            final double random = Math.random() * 100;
            if(random < reward.getChance()) {
                for (String command : reward.getCommands()) {
                    Bukkit.dispatchCommand(
                      Bukkit.getConsoleSender(),
                      command.replace("<player>", player.getName())
                    );
                }

                final String[] title = plugin.getConfig().getString("messages.winning-title").split(";");

                player.sendTitle(
                  title[0]
                    .replace("&", "§")
                    .replace("<item>", reward.getFriendlyName()),
                  title[1]
                    .replace("&", "§")
                    .replace("<item>", reward.getFriendlyName())
                );
            }
        }
    }
}