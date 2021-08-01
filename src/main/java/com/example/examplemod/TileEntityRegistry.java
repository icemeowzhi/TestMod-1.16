package com.example.examplemod;

import com.example.examplemod.tileentity.MachineBlockTileEntity;
import com.example.examplemod.tileentity.ObsidianFirstContainerTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class TileEntityRegistry {
    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, "examplemod");
    public static final RegistryObject<TileEntityType<MachineBlockTileEntity>> machineBlockTileEntity = TILE_ENTITIES.register("machine_block_tile_entity", () -> TileEntityType.Builder.create(MachineBlockTileEntity::new, BlockRegistry.machineBlock.get()).build(null));
    public static final RegistryObject<TileEntityType<ObsidianFirstContainerTileEntity>> obsidianFirstContainerTileEntity = TILE_ENTITIES.register("obsidian_first_container_tile_entity", () -> TileEntityType.Builder.create(ObsidianFirstContainerTileEntity::new, BlockRegistry.obsidianFirstContainerBlock.get()).build(null));

}