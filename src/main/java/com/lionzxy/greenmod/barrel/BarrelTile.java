package com.lionzxy.greenmod.barrel;

import com.lionzxy.greenmod.utils.BarrelRecipeItem;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.*;

/**
 * Created by nikit on 09.08.2015.
 */
public class BarrelTile extends TileEntity {
    private ItemStack output;
    private BarrelRecipeItem[] items = new BarrelRecipeItem[3];

    public BarrelTile(){
    }
    public boolean fillLiquid(ItemStack currentItem, EntityPlayer player, World world){

        if(currentItem.getItem() instanceof IFluidContainerItem){

        }
        else if(FluidContainerRegistry.isContainer(currentItem)){
            if(FluidContainerRegistry.getFluidForFilledItem(currentItem) != null){
                FluidStack fluidStack = FluidContainerRegistry.getFluidForFilledItem(currentItem);
                for(int i = 0; i < items.length; i++)
                    if(items[i] == null){
                        items[i] = new BarrelRecipeItem(fluidStack);
                        player.inventory.setInventorySlotContents(player.inventory.currentItem, FluidContainerRegistry.drainFluidContainer(currentItem));
                        return true;
                    }
                    else if(items[i].getFluid().isFluidEqual(fluidStack) && items[i].getFluid().amount + fluidStack.amount <=getMaxCapacity()){
                        items[i].addAmountForFluid(fluidStack.amount);
                        player.inventory.setInventorySlotContents(player.inventory.currentItem, FluidContainerRegistry.drainFluidContainer(currentItem));
                        return true;
                    }
            }else {
                for(int i =0; i < items.length; i++)
                    if(items[i] != null && items[i].isFluid()){
                        if(FluidContainerRegistry.getContainerCapacity(currentItem) <= items[i].getFluid().amount){
                            ItemStack filledContainer = FluidContainerRegistry.fillFluidContainer(items[i].getFluid(),currentItem);
                            if (player instanceof FakePlayer || !player.inventory.addItemStackToInventory(filledContainer))
                            {
                                world.spawnEntityInWorld(new EntityItem(world, player.posX + 0.5D, player.posY + 1.5D, player.posZ + 0.5D, filledContainer));
                            }
                            else if (player instanceof EntityPlayerMP)
                            {
                                ((EntityPlayerMP) player).sendContainerToPlayer(player.inventoryContainer);
                            }

                            if (--currentItem.stackSize <= 0)
                            {
                                player.inventory.setInventorySlotContents(player.inventory.currentItem, null);
                            }
                            this.removeItem(new FluidStack(items[i].getFluid(), FluidContainerRegistry.getContainerCapacity(items[i].getFluid(),currentItem)));
                        }
                    }
            }
        }
        return false;
    }

    @Override
    public void writeToNBT(NBTTagCompound tag){
        super.writeToNBT(tag);
        NBTTagList nbtTagList = new NBTTagList();
        for(int i = 0; i < items.length; i++)
            if(items[i] != null){
                if(items[i].isItem()){
                    NBTTagCompound nbt = new NBTTagCompound();
                    nbt.setBoolean("Itemli",true);
                    items[i].getItem().writeToNBT(nbt);
                    nbtTagList.appendTag(nbt);
                }
                if(items[i].isFluid()){
                    NBTTagCompound nbt = new NBTTagCompound();
                    nbt.setBoolean("Itemli", false);
                    items[i].getFluid().writeToNBT(nbt);
                    nbtTagList.appendTag(nbt);
                }
            }
        tag.setTag("Items", nbtTagList);
        System.out.println(tag);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt){
        super.readFromNBT(nbt);
        NBTTagList nbtTagList = nbt.getTagList("Items", Constants.NBT.TAG_COMPOUND);
        for(int i = 0; i < nbtTagList.tagCount(); i++){
            if(!nbtTagList.getCompoundTagAt(i).getBoolean("Itemli"))
                items[i]=new BarrelRecipeItem(FluidStack.loadFluidStackFromNBT(nbtTagList.getCompoundTagAt(i)));
            else
                items[i]=new BarrelRecipeItem(ItemStack.loadItemStackFromNBT(nbtTagList.getCompoundTagAt(i)));
        }
    }


    public int getMaxCapacity(){
        return 100000;
    }

    public void removeItem(FluidStack fluidStack){
        for(int i = 0; i < items.length; i++)
            if(items[i] != null && items[i].isFluid() && items[i].getFluid().getUnlocalizedName().equalsIgnoreCase(fluidStack.getUnlocalizedName()))
                if(items[i].getFluid().amount == fluidStack.amount)
                    items[i]=null;
                else items[i].removeAmountFromFluid(fluidStack.amount);
    }
}
