package com.example.examplemod;

import com.example.examplemod.command.ExampleCommand;
import com.example.examplemod.utils.Common;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.tree.LiteralCommandNode;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class CommandEventHandler {
    @SubscribeEvent
    public static void onServerStaring(RegisterCommandsEvent event) {
        CommandDispatcher<CommandSource> dispatcher = event.getDispatcher();
        LiteralCommandNode<CommandSource> cmd = dispatcher.register(
                Commands.literal(Common.MOD_ID).then(
                        Commands.literal("test")
                                .requires((commandSource) -> commandSource.hasPermissionLevel(0))
                                .executes(ExampleCommand.instance)
                )
        );
        dispatcher.register(Commands.literal("ml").redirect(cmd));
    }
}
