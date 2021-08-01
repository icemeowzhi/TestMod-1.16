package com.example.examplemod.tileentity;

import com.example.examplemod.TileEntityRegistry;
import com.example.examplemod.gui.ObsidianFirstContainer;
import com.example.examplemod.gui.ObsidianFirstContainerItemNumber;
import com.example.examplemod.utils.Common;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import javax.annotation.Nullable;

public class ObsidianFirstContainerTileEntity extends TileEntity implements ITickableTileEntity , INamedContainerProvider {
    private final Inventory inventory = new Inventory(1);
    private final ObsidianFirstContainerItemNumber itemNumber = new ObsidianFirstContainerItemNumber();

    public ObsidianFirstContainerTileEntity() {
        super(TileEntityRegistry.obsidianFirstContainerTileEntity.get());
    }

    @Override
    public ITextComponent getDisplayName() {
        return new TranslationTextComponent("gui." + Common.MOD_ID + ".first_container");
    }

    @Nullable
    @Override
    public Container createMenu(int sycID, PlayerInventory inventory, PlayerEntity player) {
        assert this.world != null;
        return new ObsidianFirstContainer(sycID, inventory, this.pos, this.world, itemNumber);
    }

    @Override
    public void read(BlockState state, CompoundNBT nbt) {
        this.inventory.addItem(ItemStack.read(nbt.getCompound("item")));
        super.read(state, nbt);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        ItemStack itemStack = this.inventory.getStackInSlot(0).copy();
        compound.put("item", itemStack.serializeNBT());
        return super.write(compound);
    }

    public Inventory getInventory() {
        return inventory;
    }

    @Override
    public void tick() {
        assert world != null;
        if (!world.isRemote) {
            this.itemNumber.set(0, this.inventory.getStackInSlot(0).getCount());
        }
    }
}
