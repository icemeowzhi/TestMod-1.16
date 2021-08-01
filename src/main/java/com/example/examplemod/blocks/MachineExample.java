package com.example.examplemod.blocks;

import com.example.examplemod.tileentity.MachineBlockTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.state.DirectionProperty;

import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;

public class MachineExample extends Block {
    // tickable    ok
    // tileentity  ok
    // blockstate  ok
    // capability  ok
    //public static final Block INSTANCE = new MachineExample();

    private static final DirectionProperty facing = BlockStateProperties.FACING;

    public MachineExample() {
        super(Properties.create(Material.ROCK));
        this.setDefaultState(this.getStateContainer().getBaseState().with(facing,Direction.NORTH));
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(facing);
        super.fillStateContainer(builder);
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        com.example.examplemod.utils.Block.createDefaultBlockFacing(facing,worldIn,pos,state,placer);
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new MachineBlockTileEntity();
    }



    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (!worldIn.isRemote && handIn == Hand.MAIN_HAND) {
            MachineBlockTileEntity machineBlockTileEntity = (MachineBlockTileEntity) worldIn.getTileEntity(pos);
            NetworkHooks.openGui((ServerPlayerEntity) player, machineBlockTileEntity, (PacketBuffer packerBuffer) -> {
                assert machineBlockTileEntity != null;
                packerBuffer.writeBlockPos(machineBlockTileEntity.getPos());
            });
        }
        return ActionResultType.SUCCESS;
    }
}
