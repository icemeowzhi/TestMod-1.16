package com.example.examplemod.capability;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Random;

public class PlayerSpeedLevelCapabilityProvider implements ICapabilityProvider, INBTSerializable<CompoundNBT> {
    private IPlayerSpeedLevelCapability playerSpeedLevelCapability;
    private final PlayerEntity player;

    public PlayerSpeedLevelCapabilityProvider(PlayerEntity playerIn){
        super();
        player = playerIn;
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        return cap == ModCapability.PLAYER_SPEED_LEVEL_CAPABILITY ? LazyOptional.of(this::getOrCreatePlayerSpeedLevelCapability).cast() : LazyOptional.empty();
    }

    @Nonnull
    IPlayerSpeedLevelCapability getOrCreatePlayerSpeedLevelCapability(){
        if (playerSpeedLevelCapability == null) {
            Random random = new Random();
            int lvl = random.nextInt(3) + 1 ;
            this.playerSpeedLevelCapability = new PlayerSpeedLevelCapability(lvl);
        }
        return this.playerSpeedLevelCapability;
    }

    @Override
    public CompoundNBT serializeNBT() {
        return getOrCreatePlayerSpeedLevelCapability().serializeNBT();
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        getOrCreatePlayerSpeedLevelCapability().deserializeNBT(nbt);
    }
}
