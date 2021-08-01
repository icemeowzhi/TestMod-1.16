package com.example.examplemod.capability;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Random;

public class ItemLevelCapabilityProvider implements ICapabilityProvider, INBTSerializable<CompoundNBT> {
    private ItemLevelCapability capability;

    //TODO
    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        return cap == ModCapability.ITEM_LEVEL_CAPABILITY ? LazyOptional.of(this::getOrCreateItemLevelCapability).cast() : LazyOptional.empty();
    }

    ItemLevelCapability getOrCreateItemLevelCapability(){
        if (capability==null){
            Random random = new Random();
            this.capability = new ItemLevelCapability(random.nextInt(3) + 1);
        }
        return  this.capability;
    }

    @Override
    public CompoundNBT serializeNBT() {
        return getOrCreateItemLevelCapability().serializeNBT();
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        getOrCreateItemLevelCapability().deserializeNBT(nbt);
    }
}
