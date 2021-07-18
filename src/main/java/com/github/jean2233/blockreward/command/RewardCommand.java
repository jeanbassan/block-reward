package com.github.jean2233.blockreward.command;

import com.github.jean2233.blockreward.BlockRewardPlugin;
import com.github.jean2233.blockreward.cache.RewardCache;
import lombok.RequiredArgsConstructor;
import me.saiintbrisson.minecraft.command.annotation.Command;
import me.saiintbrisson.minecraft.command.command.Context;
import org.bukkit.command.CommandSender;

@RequiredArgsConstructor
public class RewardCommand {

    private final BlockRewardPlugin plugin;

    @Command(
      name = "blockreward",
      permission = "server.administrator"
    )
    public void handleCommand(Context<CommandSender> context) {
        context.sendMessage("§cUtilize /blockreward reload");
    }

    @Command(
      name = "blockreward.reload"
    )
    public void handleReloadCommand(Context<CommandSender> context) {
        final RewardCache rewardCache = plugin.getRewardCache();
        rewardCache.getCachedElements().clear();

        plugin.reloadConfig();
        rewardCache.loadRewards();

        context.sendMessage("§aConfigurações recarregadas.");
    }
}
