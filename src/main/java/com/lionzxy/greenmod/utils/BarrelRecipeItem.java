package com.lionzxy.greenmod.utils;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

/**
 * Created by nikit on 09.08.2015.
 */
public class BarrelRecipeItem {
    private ItemStack item;
    private FluidStack fluid;
    private int needFluid;

    public BarrelRecipeItem(ItemStack item){
        this.item = item;
    }

    public BarrelRecipeItem(FluidStack fluid, int needFluid){
        this.fluid = fluid;
        this.needFluid = needFluid;
    }
    public boolean checkToItem(FluidStack fluid){
        if(this.fluid != null)
            return this.fluid.getFluid() == fluid.getFluid() ;
        return false;
    }

    public boolean checkToItem(ItemStack item){
        if(this.item != null)
            return this.item.getItem() == item.getItem() &&
                   this.item.stackSize >= item.stackSize ;
        return false;
    }

    public boolean isItem(){
        if(item != null && fluid == null)
            return true;
        return false;
    }

    public boolean isNull(){
        if(this.item == null && this.fluid == null)
            return true;
        return false;
    }

    public ItemStack getItem(){
        return this.item;
    }

    public FluidStack getFluid(){
        return this.fluid;
    }

    public int getNeedFluid(){
        return needFluid;
    }

    public boolean isFluid(){
        if(item == null && fluid != null)
            return true;
        return false;
    }

    public boolean checkToItem(BarrelRecipeItem barrelRecipeItem){
        if(barrelRecipeItem.isItem() && this.isItem() && this.checkToItem(barrelRecipeItem.getItem()))
            return true;
        if(barrelRecipeItem.isFluid() && this.isFluid() && this.checkToItem(barrelRecipeItem.getFluid()))
            return true;
        if(barrelRecipeItem.isNull() && this.isNull())
            return true;
        return false;
    }
}
