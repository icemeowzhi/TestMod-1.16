package com.example.examplemod.creativetabs;

import com.example.examplemod.ItemRegistry;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ExampleCreativeTab extends ItemGroup {
    public ExampleCreativeTab() {
        super("example_mod");
    }

    @Override
    public ItemStack createIcon() {
        return ItemRegistry.greenApple.get().getDefaultInstance();
    }
}
