package com.lionzxy.greenmod.barrel;

import com.lionzxy.greenmod.utils.BarrelRecipeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.Constants;

/**
 * Created by nikit on 09.08.2015.
 */
public class BarrelTile extends TileEntity{
    private ItemStack output;
    private BarrelRecipeItem[] items = new BarrelRecipeItem[2];

    @Override
    public void writeToNBT(NBTTagCompound tag){
        super.writeToNBT(tag);

        NBTTagList nbtTagList = new NBTTagList();
        for(int i = 0; i < items.length; i++)
            if(items[i] != null){
                if(items[i].isItem()){
                    NBTTagCompound nbt = new NBTTagCompound();
                    nbt.setBoolean("Item",true);
                    items[i].getItem().writeToNBT(nbt);
                    nbtTagList.appendTag(nbt);
                }
                if(items[i].isFluid()){
                    NBTTagCompound nbt = new NBTTagCompound();
                    nbt.setBoolean("Item",false);
                    nbt.setString("Fluid",items[i].getFluid().toString());
                    nbt.setInteger("FluidAmount",items[i].getNeedFluid());
                    nbtTagList.appendTag(nbt);
                }
            }
        tag.setTag("Items", nbtTagList);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt){
        super.readFromNBT(nbt);
        NBTTagList nbtTagList = nbt.getTagList("Items", Constants.NBT.TAG_COMPOUND);
    }
}
