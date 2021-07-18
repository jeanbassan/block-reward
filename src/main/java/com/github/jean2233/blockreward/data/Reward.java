package com.github.jean2233.blockreward.data;

import lombok.Builder;
import lombok.Data;
import org.bukkit.block.Block;
import org.bukkit.material.MaterialData;

import java.util.List;

@Data
@Builder
public class Reward {

    private final String friendlyName;

    private final double chance;

    private final List<String> applicable, commands;

    public boolean isApplicable(MaterialData materialData) {
        for (String value : applicable) {
            final String[] split = value.split(":");

            final int material = Integer.parseInt(split[0]);
            final byte data = Byte.parseByte(split[1]);

            if(materialData.equals(new MaterialData(material, data))) {
                return true;
            }
        }
        return false;
    }
}
