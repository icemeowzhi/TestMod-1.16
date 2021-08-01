package com.example.examplemod.tileentity;

import com.example.examplemod.RecipeRegistry;
import com.example.examplemod.TileEntityRegistry;
import com.example.examplemod.gui.MachineExampleContainer;
import com.example.examplemod.gui.MachineExampleSync;
import com.example.examplemod.recipes.GAEOvenRecipe;
import com.example.examplemod.utils.Common;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;


public class MachineBlockTileEntity extends TileEntity implements ITickableTileEntity , INamedContainerProvider
{
    private final ItemStackHandler up;
    private final ItemStackHandler down;
    private final ItemStackHandler side;

    private final GAEOvenRecipe recipe = RecipeRegistry.gaeOvenRecipe;

    private final MachineExampleSync data = new MachineExampleSync();

    private boolean isBurning = false;
    private int burnTime;
    private int totalBurnTime;
    private int fuelTime;
    private int totalFuelTime;


    public MachineBlockTileEntity() {
        super(TileEntityRegistry.machineBlockTileEntity.get());
        up = new ItemStackHandler(1){
            @Override
            protected void onContentsChanged(int slot) {
                MachineBlockTileEntity.this.markDirty();
            }
        };
        down = new ItemStackHandler(2){
            @Override
            protected void onContentsChanged(int slot) {
                MachineBlockTileEntity.this.markDirty();
            }
        };
        side = new ItemStackHandler(1){
            @Override
            protected void onContentsChanged(int slot) { MachineBlockTileEntity.this.markDirty(); }
        };
    }


    @Override
    public void tick() {


        //logic by imz
        assert world != null;
        if (!world.isRemote) {


            if (fuelTime > 0) {
                fuelTime--;
                //data.setFuelTime(fuelTime);
                data.set(2,fuelTime);
            }
            if (isBurning && fuelTime == 0) {
                boolean canExtractFuel = recipe.hasFuel(this.side.extractItem(0, 1, true).getItem());
                Item fuel = this.side.extractItem(0, 1, true).getItem();
                totalFuelTime = recipe.getFuelTimeByInput(fuel);
                data.setTotalFuelTime(totalFuelTime);
                //data.set(3,totalFuelTime);
                if (canExtractFuel) {
                    this.side.extractItem(0, 1, false).getItem();
                    fuelTime += totalFuelTime;
                    data.setFuelTime(fuelTime);
                    //data.set(2,fuelTime);
                } else {
                    burnTime = 0;
                    data.setBurnTime(burnTime);
                    //data.set(0,burnTime);
                    isBurning = false;
                }
            }
            ItemStack input = this.up.extractItem(0, 1, true);
            //System.out.println("input" + input.getItem());
            boolean canExtractInput = recipe.hasRecipe(input.getItem());
            //System.out.println("canExtractInput "+canExtractInput);

            ItemStack result = recipe.getRecipeOutputByInput(input.getItem()).getDefaultInstance();
            boolean canInsertOutput = this.down.insertItem(0, result, true).isEmpty() || this.down.insertItem(1, result, true).isEmpty();
            //System.out.println("canInsertOutput "+canInsertOutput);

            if (canExtractInput && canInsertOutput) {
                isBurning = true;
                burnTime++;
                data.setBurnTime(burnTime);
                //data.set(0,burnTime);
                totalBurnTime = recipe.getProgressByInput(input.getItem());
                data.setTotalBurnTime(totalBurnTime);
                //data.set(1,totalBurnTime);
                if (burnTime > totalBurnTime) {
                    burnTime = 0;
                    data.setBurnTime(burnTime);
                    //data.set(0,burnTime);

                    this.up.extractItem(0, 1, false);
                    insertOutput(result);
                    markDirty();
                }

            } else {
                isBurning = false;
                markDirty();
            }
        }


    }

    @Override
    public ITextComponent getDisplayName() {
        return new TranslationTextComponent("gui." + Common.MOD_ID + ".first_container");
    }

    @Nullable
    @Override
    public Container createMenu(int p_createMenu_1_, PlayerInventory p_createMenu_2_, PlayerEntity p_createMenu_3_) {
        assert this.world != null;
        return new MachineExampleContainer(p_createMenu_1_, p_createMenu_2_, this.pos, this.world, data);
    }




    @Override
    public void read(BlockState state, CompoundNBT nbt) {
        super.read(state, nbt);
        this.up.deserializeNBT(nbt.getCompound("raw"));
        this.side.deserializeNBT(nbt.getCompound("fuel"));
        this.down.deserializeNBT(nbt.getCompound("product"));
        this.burnTime = nbt.getInt("burnTime");
        this.fuelTime = nbt.getInt("fuelTime");
        this.totalBurnTime = nbt.getInt("totalBurnTime");
        this.totalFuelTime = nbt.getInt("totalFuelTime");
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.put("raw",this.up.serializeNBT());
        compound.put("fuel",this.side.serializeNBT());
        compound.put("product",this.down.serializeNBT());
        compound.putInt("burnTime",this.burnTime);
        compound.putInt("fuelTime",this.fuelTime);
        compound.putInt("totalBurnTime",this.totalBurnTime);
        compound.putInt("totalFuelTime",this.totalFuelTime);

        return super.write(compound);
    }


    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.equals(cap)){

            if (Direction.UP.equals(side)){return LazyOptional.of(()->this.up).cast();}
            if (Direction.DOWN.equals(side)){return LazyOptional.of(()->this.down).cast();}
            return LazyOptional.of(()->this.side).cast();

        }
        return LazyOptional.empty();
    }

    private void insertOutput(ItemStack output){
        if (this.down.insertItem(0,output,true).isEmpty()){
            this.down.insertItem(0,output,false);
        }else {
            this.down.insertItem(1,output,false);
        }
    }

}
