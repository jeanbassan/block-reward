package com.github.jean2233.blockreward.adapter;

import com.github.jean2233.blockreward.data.Reward;
import org.bukkit.configuration.ConfigurationSection;

import java.util.List;

public class RewardAdapter {

    public Reward read(ConfigurationSection section) {
        final String friendlyName = section.getString("friendly-name");

        final double chance = section.getDouble("chance");

        final List<String> applicable = section.getStringList("applicable-for");
        final List<String> commands = section.getStringList("commands");

        return Reward.builder()
          .friendlyName(friendlyName)
          .chance(chance)
          .applicable(applicable)
          .commands(commands)
          .build();
    }
}