package com.example.examplemod.recipes;

import net.minecraft.item.Item;

public interface IRecipeIMZ {
    public Item getInput();
    public Item getOutput();
    public String getID();
    public int getProgress();

}
