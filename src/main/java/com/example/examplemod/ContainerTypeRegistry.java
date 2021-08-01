package com.example.examplemod;

import com.example.examplemod.gui.MachineExampleContainer;
import com.example.examplemod.gui.MachineExampleSync;
import com.example.examplemod.gui.ObsidianFirstContainer;
import com.example.examplemod.gui.ObsidianFirstContainerItemNumber;
import com.example.examplemod.utils.Common;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ContainerTypeRegistry {
    public static final DeferredRegister<ContainerType<?>> CONTAINER = DeferredRegister.create(ForgeRegistries.CONTAINERS, Common.MOD_ID);


    public static final RegistryObject<ContainerType<MachineExampleContainer>> exampleMachineContainer =
            CONTAINER.register("example_machine_container",
                    ()-> IForgeContainerType.create(
                            (int windowId, PlayerInventory inv, PacketBuffer data) -> {
                                assert Minecraft.getInstance().world != null;
                                return new MachineExampleContainer(windowId,inv,data.readBlockPos(),
                                        Minecraft.getInstance().world, new MachineExampleSync());
                            }));


    public static final RegistryObject<ContainerType<ObsidianFirstContainer>> obsidianFirstContainer =
            CONTAINER.register("obsidian_first_container",
                    () -> IForgeContainerType.create(
                            (int windowId, PlayerInventory inv, PacketBuffer data) -> {
                                assert Minecraft.getInstance().world != null;
                                return new ObsidianFirstContainer(windowId, inv, data.readBlockPos(),
                                        Minecraft.getInstance().world, new ObsidianFirstContainerItemNumber());
                            }));
}
