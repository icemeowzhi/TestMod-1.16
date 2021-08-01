package com.example.examplemod.recipes;

import net.minecraft.item.Item;

public interface IFuelTimeHandlerIMZ {
    void regFuels(Item input, int fuelTime);
    boolean hasFuel(Item item);
    int getFuelTimeByInput(Item item);
}
