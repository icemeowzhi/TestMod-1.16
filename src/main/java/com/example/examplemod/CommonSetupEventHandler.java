package com.example.examplemod;

import com.example.examplemod.capability.IItemLevelCapability;
import com.example.examplemod.capability.IPlayerSpeedLevelCapability;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonSetupEventHandler {
    @SubscribeEvent
    public static void onSetupEvent(FMLCommonSetupEvent event) {

        event.enqueueWork(() -> CapabilityManager.INSTANCE.register(
                IPlayerSpeedLevelCapability.class,
                new Capability.IStorage<IPlayerSpeedLevelCapability>() {
                    @Nullable
                    @Override
                    public INBT writeNBT(Capability<IPlayerSpeedLevelCapability> capability, IPlayerSpeedLevelCapability instance, Direction side) {
                        return null;
                    }

                    @Override
                    public void readNBT(Capability<IPlayerSpeedLevelCapability> capability, IPlayerSpeedLevelCapability instance, Direction side, INBT nbt) {

                    }
                },
                () -> null
        ));

        event.enqueueWork(()->CapabilityManager.INSTANCE.register(
                IItemLevelCapability.class,
                new Capability.IStorage<IItemLevelCapability>() {
                    @Nullable
                    @Override
                    public INBT writeNBT(Capability<IItemLevelCapability> capability, IItemLevelCapability instance, Direction side) {
                        return null;
                    }

                    @Override
                    public void readNBT(Capability<IItemLevelCapability> capability, IItemLevelCapability instance, Direction side, INBT nbt) {

                    }
                },
                ()->null
        ));
    }
}