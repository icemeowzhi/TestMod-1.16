package com.example.examplemod.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockExample extends Block {
    public BlockExample() {
        super(Properties.create(Material.ROCK).hardnessAndResistance(10));
    }
}
