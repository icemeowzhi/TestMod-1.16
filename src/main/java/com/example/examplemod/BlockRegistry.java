package com.example.examplemod;

import com.example.examplemod.blocks.BlockExample;
import com.example.examplemod.blocks.MachineExample;
import com.example.examplemod.blocks.ObsidianFirstContainerBlock;
import net.minecraft.block.Block;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockRegistry {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS,"examplemod");
    public static final RegistryObject<Block> greenAppleBlock = BLOCKS.register("green_apple_block",BlockExample::new);
    public static final RegistryObject<Block> machineBlock = BLOCKS.register("machine_block", MachineExample::new);
    public static final RegistryObject<Block> obsidianFirstContainerBlock = BLOCKS.register("obsidian_first_block", ObsidianFirstContainerBlock::new);

}
