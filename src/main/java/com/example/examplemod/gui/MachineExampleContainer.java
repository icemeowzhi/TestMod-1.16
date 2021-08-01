package com.example.examplemod.gui;

import com.example.examplemod.ContainerTypeRegistry;
import com.example.examplemod.ItemRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;


public class MachineExampleContainer extends Container {

    private IItemHandler up;
    private IItemHandler down;
    private IItemHandler side;
    private final MachineExampleSync data ;

    public MachineExampleContainer(int id, PlayerInventory playerInventory, BlockPos pos, World world, MachineExampleSync data) {
        super(ContainerTypeRegistry.exampleMachineContainer.get(),id);
        TileEntity machineBlockTileEntity = world.getTileEntity(pos);
        this.data = data;
        trackIntArray(data);
        layoutPlayerInventorySlots(playerInventory, 8, 84);

        Capability<IItemHandler> itemHandlerCapability = CapabilityItemHandler.ITEM_HANDLER_CAPABILITY;

        assert machineBlockTileEntity != null;
        machineBlockTileEntity.getCapability(itemHandlerCapability, Direction.UP).ifPresent((s)-> this.up = s);
        machineBlockTileEntity.getCapability(itemHandlerCapability, Direction.DOWN).ifPresent((s)-> this.down = s);
        machineBlockTileEntity.getCapability(itemHandlerCapability, Direction.NORTH).ifPresent((s)-> this.side = s);

        this.addSlot(new SlotItemHandler(this.up,0,36,17));
        this.addSlot(new SlotItemHandler(this.side,0,36,53){
            @Override
            public boolean isItemValid(@Nonnull ItemStack stack) {
                return (stack.getItem() == ItemRegistry.greenApple.get() ||
                        stack.getItem() == Items.APPLE ) &&
                        super.isItemValid(stack);
            }
        });

        this.addSlot(new SlotItemHandler(this.down,0,96,35));
        this.addSlot(new SlotItemHandler(this.down,1,125,35));
    }

    public MachineExampleSync getData() {
        return data;
    }

    @Override
    public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
        return ItemStack.EMPTY;
    }


    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return true;
    }



    private int addSlotRange(IInventory inventory, int index, int x, int y, int amount, int dx) {
        for (int i = 0; i < amount; i++) {
            addSlot(new Slot(inventory, index, x, y));
            x += dx;
            index++;
        }
        return index;
    }

    private int addSlotBox(IInventory inventory, int index, int x, int y, int horAmount, int dx, int verAmount, int dy) {
        for (int j = 0; j < verAmount; j++) {
            index = addSlotRange(inventory, index, x, y, horAmount, dx);
            y += dy;
        }
        return index;
    }


    private void layoutPlayerInventorySlots(IInventory inventory, int leftCol, int topRow) {
        // Player inventory
        addSlotBox(inventory, 9, leftCol, topRow, 9, 18, 3, 18);

        // Hotbar
        topRow += 58;
        addSlotRange(inventory, 0, leftCol, topRow, 9, 18);
    }



}
