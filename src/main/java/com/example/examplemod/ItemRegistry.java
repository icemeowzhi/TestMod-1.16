package com.example.examplemod;

import com.example.examplemod.items.ChestPlateExample;
import com.example.examplemod.items.GreenAppleExample;
import com.example.examplemod.items.SwordExample;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemRegistry{
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,"examplemod");
    public static RegistryObject<Item> greenApple = ITEMS.register("green_apple", GreenAppleExample::new);
    public static RegistryObject<Item> greenAppleSword = ITEMS.register("green_apple_sword", SwordExample::new);
    public static RegistryObject<Item> greenAppleChestplate = ITEMS.register("green_apple_chestplate", ChestPlateExample::new);
    public static final RegistryObject<Item> exampleBlock = ITEMS.register("green_apple_block", () -> new BlockItem(BlockRegistry.greenAppleBlock.get(), new Item.Properties().group(CreativeTabRegistry.itemGroup)));
    public static final RegistryObject<Item> exampleMachine = ITEMS.register("machine_block", () -> new BlockItem(BlockRegistry.machineBlock.get(), new Item.Properties().group(CreativeTabRegistry.itemGroup)));
    public static final RegistryObject<Item> obsidianFirstContainerBlock = ITEMS.register("obsidian_first_block", () -> new BlockItem(BlockRegistry.obsidianFirstContainerBlock.get(), new Item.Properties().group(CreativeTabRegistry.itemGroup)));


}