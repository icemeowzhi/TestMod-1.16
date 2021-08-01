package com.example.examplemod.items;

import com.example.examplemod.CreativeTabRegistry;
import com.example.examplemod.armormaterials.MaterialGreenApple;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;

public class ChestPlateExample extends ArmorItem {
    public ChestPlateExample() {
        super(MaterialGreenApple.GREEN_APPLE, EquipmentSlotType.CHEST, new Properties().group(CreativeTabRegistry.itemGroup));
    }


}
