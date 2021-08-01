package com.example.examplemod.utils;

import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.DirectionProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class Block {
    public static void createDefaultBlockFacing(DirectionProperty facing, World worldIn, BlockPos pos, BlockState state, @Nullable LivingEntity placer){
        worldIn.setBlockState(pos,state.with(facing,placer.getHorizontalFacing().getOpposite()));
    }
}
