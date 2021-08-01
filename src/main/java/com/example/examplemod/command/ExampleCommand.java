package com.example.examplemod.command;

import com.example.examplemod.capability.IPlayerSpeedLevelCapability;
import com.example.examplemod.capability.ModCapability;
import com.example.examplemod.utils.Common;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.util.LazyOptional;


public class ExampleCommand implements Command<CommandSource> {

    public static ExampleCommand instance = new ExampleCommand();
    @Override
    public int run(CommandContext<CommandSource> context) throws CommandSyntaxException {
        if (context.getSource().getEntity() instanceof PlayerEntity) {
            LazyOptional<IPlayerSpeedLevelCapability> playerSpeedLevelCapability = context.getSource().getEntity().getCapability(ModCapability.PLAYER_SPEED_LEVEL_CAPABILITY);
            playerSpeedLevelCapability.ifPresent((capability) -> {
                int level = capability.getLevel();
                context.getSource().sendFeedback(new TranslationTextComponent("cmd." + Common.MOD_ID + ".show_speed_lvl", level), false);
            });
        }
        return 0;
    }
}
