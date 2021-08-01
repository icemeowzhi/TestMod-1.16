package com.example.examplemod.gui;

import com.example.examplemod.capability.ModCapability;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.util.IReorderingProcessor;
import net.minecraft.util.text.TranslationTextComponent;

public class SpeedLevelHud extends AbstractGui {
    private final int width;
    private final int height;
    private final Minecraft minecraft;
    private MatrixStack matrixStack;
    private int lvl;

    public SpeedLevelHud(MatrixStack matrixStack) {
        this.width = Minecraft.getInstance().getMainWindow().getScaledWidth();
        this.height = Minecraft.getInstance().getMainWindow().getScaledHeight();
        this.minecraft = Minecraft.getInstance();
        this.matrixStack = matrixStack;
    }
    public void setMatrixStack(MatrixStack stack) {
        this.matrixStack = stack;
    }
    public void render(){
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        assert minecraft.player != null;
        minecraft.player.getCapability(ModCapability.PLAYER_SPEED_LEVEL_CAPABILITY).ifPresent((cap)->{
            lvl = cap.getLevel();
            IReorderingProcessor ireorderingprocessor = new TranslationTextComponent("hud.player_speed_lvl.string",lvl).func_241878_f();
            //shadowless render , see src for details
            minecraft.fontRenderer.func_238422_b_(matrixStack,ireorderingprocessor,(float)((this.width)/2 - minecraft.fontRenderer.func_243245_a(ireorderingprocessor)/2 ),(float) 10,0x00c6c6c6);//0
            minecraft.fontRenderer.func_238422_b_(matrixStack,ireorderingprocessor,(float)((this.width)/2 - minecraft.fontRenderer.func_243245_a(ireorderingprocessor)/2 ),(float) 20,0x01c6c6c6);//1
            minecraft.fontRenderer.func_238422_b_(matrixStack,ireorderingprocessor,(float)((this.width)/2 - minecraft.fontRenderer.func_243245_a(ireorderingprocessor)/2 ),(float) 30,0x02c6c6c6);//2
            minecraft.fontRenderer.func_238422_b_(matrixStack,ireorderingprocessor,(float)((this.width)/2 - minecraft.fontRenderer.func_243245_a(ireorderingprocessor)/2 ),(float) 40,0x03c6c6c6);//3
            minecraft.fontRenderer.func_238422_b_(matrixStack,ireorderingprocessor,(float)((this.width)/2 - minecraft.fontRenderer.func_243245_a(ireorderingprocessor)/2 ),(float) 50,0x09c6c6c6);//9
            minecraft.fontRenderer.func_238422_b_(matrixStack,ireorderingprocessor,(float)((this.width)/2 - minecraft.fontRenderer.func_243245_a(ireorderingprocessor)/2 ),(float) 60,0x0ac6c6c6);//10
            minecraft.fontRenderer.func_238422_b_(matrixStack,ireorderingprocessor,(float)((this.width)/2 - minecraft.fontRenderer.func_243245_a(ireorderingprocessor)/2 ),(float) 70,0x1ec6c6c6);//30
            minecraft.fontRenderer.func_238422_b_(matrixStack,ireorderingprocessor,(float)((this.width)/2 - minecraft.fontRenderer.func_243245_a(ireorderingprocessor)/2 ),(float) 80,0x3cc6c6c6);//60
            minecraft.fontRenderer.func_238422_b_(matrixStack,ireorderingprocessor,(float)((this.width)/2 - minecraft.fontRenderer.func_243245_a(ireorderingprocessor)/2 ),(float) 90,0x5ac6c6c6);//90
            minecraft.fontRenderer.func_238422_b_(matrixStack,ireorderingprocessor,(float)((this.width)/2 - minecraft.fontRenderer.func_243245_a(ireorderingprocessor)/2 ),(float) 100,0x80c6c6c6);//128
            minecraft.fontRenderer.func_238422_b_(matrixStack,ireorderingprocessor,(float)((this.width)/2 - minecraft.fontRenderer.func_243245_a(ireorderingprocessor)/2 ),(float) 110,0xb4c6c6c6);//180
            minecraft.fontRenderer.func_238422_b_(matrixStack,ireorderingprocessor,(float)((this.width)/2 - minecraft.fontRenderer.func_243245_a(ireorderingprocessor)/2 ),(float) 120,0xd2c6c6c6);//210
            minecraft.fontRenderer.func_238422_b_(matrixStack,ireorderingprocessor,(float)((this.width)/2 - minecraft.fontRenderer.func_243245_a(ireorderingprocessor)/2 ),(float) 130,0xfcc6c6c6);//254
            minecraft.fontRenderer.func_238422_b_(matrixStack,ireorderingprocessor,(float)((this.width)/2 - minecraft.fontRenderer.func_243245_a(ireorderingprocessor)/2 ),(float) 140,0xfdc6c6c6);//253
            minecraft.fontRenderer.func_238422_b_(matrixStack,ireorderingprocessor,(float)((this.width)/2 - minecraft.fontRenderer.func_243245_a(ireorderingprocessor)/2 ),(float) 150,0xfec6c6c6);//254
            minecraft.fontRenderer.func_238422_b_(matrixStack,ireorderingprocessor,(float)((this.width)/2 - minecraft.fontRenderer.func_243245_a(ireorderingprocessor)/2 ),(float) 160,0xffc6c6c6);//255

            minecraft.fontRenderer.func_238422_b_(matrixStack,ireorderingprocessor,(float)((this.width)/2 - minecraft.fontRenderer.func_243245_a(ireorderingprocessor)/2 ),(float) 180,0x04c6c6c6);//4
            minecraft.fontRenderer.func_238422_b_(matrixStack,ireorderingprocessor,(float)((this.width)/2 - minecraft.fontRenderer.func_243245_a(ireorderingprocessor)/2 ),(float) 190,0x05c6c6c6);//5
            minecraft.fontRenderer.func_238422_b_(matrixStack,ireorderingprocessor,(float)((this.width)/2 - minecraft.fontRenderer.func_243245_a(ireorderingprocessor)/2 ),(float) 200,0x06c6c6c6);//6
            minecraft.fontRenderer.func_238422_b_(matrixStack,ireorderingprocessor,(float)((this.width)/2 - minecraft.fontRenderer.func_243245_a(ireorderingprocessor)/2 ),(float) 210,0x07c6c6c6);//7
            minecraft.fontRenderer.func_238422_b_(matrixStack,ireorderingprocessor,(float)((this.width)/2 - minecraft.fontRenderer.func_243245_a(ireorderingprocessor)/2 ),(float) 220,0x08c6c6c6);//8

            //drawCenteredString(matrixStack, minecraft.fontRenderer, new TranslationTextComponent("hud.player_speed_lvl.string",lvl),(this.width)/2,6,0x00c6c6c6); //shadowed
        });

    }
}
