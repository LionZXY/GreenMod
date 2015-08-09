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
    private int amount;
    private FluidStack fluid;

    public BarrelRecipeItem(ItemStack item,int stackSize){
        this.item = item;
        amount = stackSize;
    }

    public BarrelRecipeItem(FluidStack fluid){
        this.fluid = fluid;
    }
    public BarrelRecipeItem(){

    }
    public boolean checkToItem(FluidStack fluidIn){
        if(this.fluid != null)
            return this.fluid.getFluid().getName().equalsIgnoreCase(fluid.getFluid().getName()) &&
                   fluidIn.amount >= this.fluid.amount ;
        return false;
    }

    public boolean checkToItem(ItemStack item){
        if(this.item != null)
            return this.item == item &&
                   this.amount >= item.stackSize ;
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

    public void addAmount(int i){
        if(!isNull())
            if (isFluid())
                this.fluid.amount = this.fluid.amount + i;
            else if (isItem())
                this.amount = this.amount + i;
    }

    public void removeAmount(int i){
        if(!isNull())
            if(isFluid())
                this.fluid.amount = this.fluid.amount - i;
            else if(isItem())
                this.amount = this.amount- i;
    }

    public boolean isFluid(){
        if(item == null && fluid != null)
            return true;
        return false;
    }

    public int getAmount(){
        return amount;
    }
    public boolean checkToItem(BarrelRecipeItem barrelRecipeItem){
        if(barrelRecipeItem.isItem() && this.isItem() && this.checkToItem(barrelRecipeItem.getItem()))
            return true;
        if(barrelRecipeItem.isFluid() && this.isFluid() && this.checkToItem(barrelRecipeItem.getFluid()))
            return true;
        if((barrelRecipeItem.isNull() && this.isNull()) || (barrelRecipeItem == null && barrelRecipeItem.isNull()))
            return true;
        return false;
    }

}
