package com.example.examplemod.items;


import com.example.examplemod.CreativeTabRegistry;
import com.example.examplemod.capability.IItemLevelCapability;
import com.example.examplemod.capability.ItemLevelCapability;
import com.example.examplemod.capability.ModCapability;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.*;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class SwordExample extends SwordItem {


    public SwordExample() {
        super(ItemTier.DIAMOND,3, -2.4f, new Item.Properties().group(CreativeTabRegistry.itemGroup));
    }

    @Override
    public void addInformation(@Nonnull ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        LazyOptional<IItemLevelCapability> capability = stack.getCapability(ModCapability.ITEM_LEVEL_CAPABILITY);
        capability.ifPresent((cap)->{
            int lvl = cap.getLevel();
            tooltip.add(new TranslationTextComponent("cap.item.lvl.name",lvl));
        });

    }
}