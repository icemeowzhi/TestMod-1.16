package com.example.examplemod.capability;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;

public class ModCapability {
    @CapabilityInject(IPlayerSpeedLevelCapability.class)
    public static Capability<IPlayerSpeedLevelCapability>  PLAYER_SPEED_LEVEL_CAPABILITY;

    @CapabilityInject(IItemLevelCapability.class)
    public static  Capability<IItemLevelCapability> ITEM_LEVEL_CAPABILITY;
}