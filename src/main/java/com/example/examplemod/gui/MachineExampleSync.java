package com.example.examplemod.gui;

import net.minecraft.util.IIntArray;

public class MachineExampleSync implements IIntArray {
    private int burnTime;  //0
    private int totalBurnTime;  //1
    private int fuelTime;  //2
    private int totalFuelTime;  //3 default

    @Override
    public int get(int index) {
        switch (index){
            case 0:
                return burnTime;
            case 1:
                return totalBurnTime;
            case 2:
                return fuelTime;
            default:
                return totalFuelTime;

        }
    }

    @Override
    public void set(int index, int value) {
        switch (index){
            case 0:
                burnTime = value;
                break;
            case 1:
                totalBurnTime = value;
                break;
            case 2:
                fuelTime = value;
                break;
            default:
                totalFuelTime = value;

        }
    }

    @Override
    public int size() {
        return 4;
    }

    public int getBurnTime() {
        return burnTime;
    }

    public void setBurnTime(int burnTime) {
        this.burnTime = burnTime;
    }

    public int getTotalBurnTime() {
        return totalBurnTime;
    }

    public void setTotalBurnTime(int totalBurnTime) {
        this.totalBurnTime = totalBurnTime;
    }

    public int getFuelTime() {
        return fuelTime;
    }

    public void setFuelTime(int fuelTime) {
        this.fuelTime = fuelTime;
    }

    public int getTotalFuelTime() {
        return totalFuelTime;
    }

    public void setTotalFuelTime(int totalFuelTime) {
        this.totalFuelTime = totalFuelTime;
    }
}
