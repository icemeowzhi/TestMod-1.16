package com.example.examplemod;

import com.example.examplemod.recipes.GAEOvenRecipe;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
public class RecipeRegistry {
    public static final GAEOvenRecipe gaeOvenRecipe = new GAEOvenRecipe();


    @SubscribeEvent
    public static void onRecipeRegistry(RegistryEvent<Item> event) {
        System.out.println("registering recipes...");
        System.out.println(ItemRegistry.greenApple.get());
        gaeOvenRecipe.regRecipes(ItemRegistry.greenApple.get(), Items.DIAMOND,120,"1");
        gaeOvenRecipe.regFuels(ItemRegistry.greenApple.get(),480);
        gaeOvenRecipe.regFuels(Items.APPLE,120);

    }
}
