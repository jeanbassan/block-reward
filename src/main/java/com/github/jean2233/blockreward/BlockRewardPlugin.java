package com.github.jean2233.blockreward;

import com.github.jean2233.blockreward.cache.RewardCache;
import com.github.jean2233.blockreward.command.RewardCommand;
import com.github.jean2233.blockreward.controller.RewardController;
import com.github.jean2233.blockreward.listener.BlockListener;
import lombok.Getter;
import me.saiintbrisson.bukkit.command.BukkitFrame;
import me.saiintbrisson.minecraft.command.message.MessageHolder;
import me.saiintbrisson.minecraft.command.message.MessageType;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public final class BlockRewardPlugin extends JavaPlugin {

    private RewardCache rewardCache;
    private RewardController rewardController;

    @Override
    public void onLoad() {
        this.rewardCache = new RewardCache(this);
        this.rewardController = new RewardController(this);
    }

    @Override
    public void onEnable() {
        saveDefaultConfig();

        rewardCache.loadRewards();
        createDefaultRegistry();
    }

    private void createDefaultRegistry() {
        this.registerCommands();

        final PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new BlockListener(this), this);
    }

    private void registerCommands() {
        final BukkitFrame frame = new BukkitFrame(this);
        final MessageHolder messageHolder = frame.getMessageHolder();

        messageHolder.setMessage(MessageType.INCORRECT_USAGE, "§cUtilize /{usage}.");
        messageHolder.setMessage(MessageType.NO_PERMISSION, "§cVocê não possui permissão para isso.");
        messageHolder.setMessage(MessageType.ERROR, "§cAconteceu um erro ao executar este comando. Contate um administrador.");

        frame.registerCommands(
          new RewardCommand(this)
        );
    }
}