package com.example.examplemod.capability;

import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.INBTSerializable;

public interface IItemLevelCapability extends INBTSerializable<CompoundNBT> {
    int getLevel();
}
