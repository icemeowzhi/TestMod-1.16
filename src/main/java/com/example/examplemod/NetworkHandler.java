package com.example.examplemod;

import com.example.examplemod.gui.PackSpeedLvl;
import com.example.examplemod.utils.Common;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class NetworkHandler {
    public static SimpleChannel INSTANCE;
    public static final String VERSION = "1.0";
    private static int ID = 0;

    public static int nextID() {
        return ID++;
    }

    public static void registerMessage() {
        INSTANCE = NetworkRegistry.newSimpleChannel(
                new ResourceLocation(Common.MOD_ID, "first_networking"),
                () -> VERSION,
                (version) -> version.equals(VERSION),
                (version) -> version.equals(VERSION)
        );
        INSTANCE.messageBuilder(PackSpeedLvl.class, nextID())
                .encoder(PackSpeedLvl::toBytes)
                .decoder(PackSpeedLvl:: new)
                .consumer(PackSpeedLvl::handler)
                .add();
    }
}
