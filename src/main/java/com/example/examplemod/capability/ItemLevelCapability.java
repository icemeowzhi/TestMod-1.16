package com.example.examplemod.capability;

import net.minecraft.nbt.CompoundNBT;

public class ItemLevelCapability implements IItemLevelCapability{

    private int level;

    public ItemLevelCapability(int level) {
        this.level = level;
    }

    @Override
    public int getLevel() {
        return level;
    }

    @Override
    public CompoundNBT serializeNBT() {
        CompoundNBT compoundNBT = new CompoundNBT();
        compoundNBT.putInt("level", this.level);
        return compoundNBT;
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        this.level = nbt.getInt("level");
    }
}
