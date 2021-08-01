package com.example.examplemod.capability;

import net.minecraft.nbt.CompoundNBT;

public class PlayerSpeedLevelCapability implements IPlayerSpeedLevelCapability{
    private int level;

    public PlayerSpeedLevelCapability(int level) {
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
