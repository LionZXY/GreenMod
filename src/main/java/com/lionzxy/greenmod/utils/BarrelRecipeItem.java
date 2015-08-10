package com.lionzxy.greenmod.utils;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nullable;

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

    public String getName(){
        if(isFluid())
            return fluid.getFluid().getName();
        else if(isItem())
               return item.getDisplayName();
        return null;
    }

    public boolean isFluid(){
        if(item == null && fluid != null)
            return true;
        return false;
    }

    public int getAmount(){
        if(isFluid())
            return getFluid().amount;
        return amount;
    }
    public boolean checkToItem(@Nullable BarrelRecipeItem barrelRecipeItem){
        if(isNull() && barrelRecipeItem == null)
            return true;
        if(!isNull() && barrelRecipeItem == null)
            return false;
        if((isItem() && barrelRecipeItem.isItem()) && ((item.getItem() == barrelRecipeItem.getItem().getItem()) && item.stackSize >= barrelRecipeItem.getItem().stackSize))
            return true;
        if((isFluid() && barrelRecipeItem.isFluid()) && (fluid.getFluid() == barrelRecipeItem.getFluid().getFluid() && fluid.amount <= barrelRecipeItem.getFluid().amount))
            return true;
        return false;
    }

}
