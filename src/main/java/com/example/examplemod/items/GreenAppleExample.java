package com.example.examplemod.items;

import com.example.examplemod.CreativeTabRegistry;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;


public class GreenAppleExample extends Item {

    private static final Food food = (new Food.Builder()).saturation(10).hunger(20).build();

    public GreenAppleExample() {
        super(new Properties().food(food).group(CreativeTabRegistry.itemGroup));
    }



}


