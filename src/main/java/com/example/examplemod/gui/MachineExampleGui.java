package com.example.examplemod.gui;

import com.example.examplemod.utils.Common;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class MachineExampleGui extends ContainerScreen<MachineExampleContainer> {

    private final ResourceLocation TEXTURE = new ResourceLocation(Common.MOD_ID ,"textures/gui/container/gae_oven.png");


    public MachineExampleGui(MachineExampleContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
        this.xSize = 176;
        this.ySize = 166;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, float partialTicks, int x, int y) {
        this.renderBackground(matrixStack);
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        assert this.minecraft != null;
        this.minecraft.getTextureManager().bindTexture(TEXTURE);
        int left = (this.width - this.xSize) / 2;
        int top = (this.height - this.ySize) / 2;
        //this.drawTexturedModalRect(left,top,0,0,this.xSize,this.ySize);
        //blit(matrixStack,left,top,0,0,176,166,176,166);
        blit(matrixStack,left,top,0,0,176,166);

        int barFuelHeight = 16;

        float percentFuel = (float)this.getContainer().getData().getFuelTime() / (float) this.getContainer().getData().getTotalFuelTime();
        int barFuelWidth = Math.round(percentFuel * 16) ;



        blit(matrixStack,left + 36,top + 35,176,31,barFuelWidth,barFuelHeight);


        int barBurnTimeHeight = 17;
        float percentBurnTime = (float) this.getContainer().getData().getBurnTime() / (float) this.getContainer().getData().getTotalBurnTime();
        int barBurnTimeWidth = Math.round(percentBurnTime * 24) ;

        blit(matrixStack,left + 59,top + 35,176,14,barBurnTimeWidth,barBurnTimeHeight);




    }

    @Override
    protected void drawGuiContainerForegroundLayer(MatrixStack matrixStack, int x, int y) {
        String text = I18n.format("tile.gae_oven.gui.name");
        drawCenteredString(matrixStack,this.font,text,(this.xSize)/2,6,0x00404040);
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        renderHoveredTooltip(matrixStack, mouseX, mouseY);
    }
}
