package com.example.examplemod.gui;

import com.example.examplemod.capability.IPlayerSpeedLevelCapability;
import com.example.examplemod.capability.ModCapability;
import com.example.examplemod.capability.PlayerSpeedLevelCapability;
import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class PackSpeedLvl {
    //package to sync between client and server
    private final int lvl;

    public PackSpeedLvl(PacketBuffer buffer) {
        lvl = buffer.readInt();
    }

    public PackSpeedLvl(int lvl) {
        this.lvl = lvl;
    }

    public void toBytes(PacketBuffer buf) {
        buf.writeInt(this.lvl);
    }

    public void handler(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            IPlayerSpeedLevelCapability synced = new PlayerSpeedLevelCapability(lvl);
            assert Minecraft.getInstance().player != null;
            Minecraft.getInstance().player.getCapability(ModCapability.PLAYER_SPEED_LEVEL_CAPABILITY).ifPresent((cap)->cap.deserializeNBT(synced.serializeNBT()));
            System.out.println("syncing speedlvl...");
            System.out.println("lvl:"+lvl);
        });
        ctx.get().setPacketHandled(true);
    }
}
