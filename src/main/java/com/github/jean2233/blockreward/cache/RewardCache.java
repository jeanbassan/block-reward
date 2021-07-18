package com.github.jean2233.blockreward.cache;

import com.github.jean2233.blockreward.BlockRewardPlugin;
import com.github.jean2233.blockreward.adapter.RewardAdapter;
import com.github.jean2233.blockreward.data.Reward;
import com.github.jean2233.blockreward.util.Cache;
import lombok.RequiredArgsConstructor;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.material.MaterialData;

import java.util.List;

@RequiredArgsConstructor
public class RewardCache extends Cache<Reward> {

    private final BlockRewardPlugin plugin;

    public List<Reward> getByBlock(Block block) {
        final MaterialData data = new MaterialData(block.getTypeId(), block.getData());

        return getCachedElements(reward -> reward.isApplicable(data));
    }

    public void loadRewards() {
        final RewardAdapter adapter = new RewardAdapter();

        final FileConfiguration configuration = plugin.getConfig();
        for (String key : configuration.getConfigurationSection("rewards").getKeys(false)) {
            addCachedElement(adapter.read(
              configuration.getConfigurationSection("rewards." + key)
            ));
        }
    }
}