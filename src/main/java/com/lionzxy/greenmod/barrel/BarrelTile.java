package com.lionzxy.greenmod.barrel;

import com.lionzxy.greenmod.utils.BarrelRecipeItem;
import com.lionzxy.greenmod.utils.RecipeObject;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nikit on 09.08.2015.
 */
public class BarrelTile extends TileEntity {
    public static List<RecipeObject> listRecipe = new ArrayList<RecipeObject>();
    private ItemStack output;
    private BarrelRecipeItem[] items = new BarrelRecipeItem[3];

    public BarrelTile(){
    }

    public BarrelRecipeItem[] getItems(){
        return items;
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
                        markDirty();
                        return true;
                    }
                    else if(items[i].getFluid().isFluidEqual(fluidStack) && items[i].getFluid().amount + fluidStack.amount <=getMaxCapacity()){
                        items[i].addAmount(fluidStack.amount);
                        player.inventory.setInventorySlotContents(player.inventory.currentItem, FluidContainerRegistry.drainFluidContainer(currentItem));
                        markDirty();
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

    public void actionWithItem(EntityPlayer player, World world){
        if(player.isSneaking() && player.getCurrentEquippedItem() == null){
            for(int i = 0; i < items.length; i++)
                if(items[i] != null && items[i].isItem()){
                    world.spawnEntityInWorld(new EntityItem(world, player.posX + 0.5, player.posY + 0.5, player.posZ + 0.5, new ItemStack(items[i].getItem().getItem())));
                    removeItem(new ItemStack(items[i].getItem().getItem()));
                    markDirty();
                    return;
                }
        }else {
            if(player.getCurrentEquippedItem() != null){
                for(int i = 0; i < items.length; i++)
                    if(items[i] == null) {
                        items[i] = new BarrelRecipeItem(player.getCurrentEquippedItem(), player.getCurrentEquippedItem().stackSize);
                        player.inventory.setInventorySlotContents(player.inventory.currentItem, null);
                        markDirty();
                        return;
                    }else if(items[i].getItem().getItem() == player.getCurrentEquippedItem().getItem()){
                        if((items[i].getAmount() + player.getCurrentEquippedItem().stackSize) <= getMax()){
                            items[i].addAmount(player.getCurrentEquippedItem().stackSize);
                            player.inventory.setInventorySlotContents(player.inventory.currentItem,null);
                            markDirty();
                            return;
                        }else if(items[i].getAmount() != getMax()){
                            int b = player.getCurrentEquippedItem().stackSize - (getMax() - items[i].getAmount());
                            items[i].addAmount(b);
                            player.inventory.setInventorySlotContents(player.inventory.currentItem, new ItemStack(player.getCurrentEquippedItem().getItem(), player.getCurrentEquippedItem().stackSize - b));
                            markDirty();
                            return;
                        }

                    }



            }
        }

    }

    @Override
    public void writeToNBT(NBTTagCompound tag){
        super.writeToNBT(tag);
        NBTTagList nbtTagList = new NBTTagList();
        for(int i = 0; i < items.length; i++)
            if(items[i] != null){
                if(items[i].isItem()){
                    NBTTagCompound nbt = new NBTTagCompound();
                    nbt.setBoolean("Itemli", true);
                    items[i].getItem().writeToNBT(nbt);
                    nbt.setInteger("Amount", items[i].getAmount());
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
                items[i]=new BarrelRecipeItem(ItemStack.loadItemStackFromNBT(nbtTagList.getCompoundTagAt(i)),nbtTagList.getCompoundTagAt(i).getInteger("StackSize"));
        }
    }


    public int getMaxCapacity(){
        return 100000;
    }

    public int getMax(){
        return 1024;
    }

    public void removeItem(FluidStack fluidStack){
        for(int i = 0; i < items.length; i++)
            if(items[i] != null && items[i].isFluid() && items[i].getFluid().getUnlocalizedName().equalsIgnoreCase(fluidStack.getUnlocalizedName()))
                if(items[i].getFluid().amount == fluidStack.amount) {
                    items[i] = null;
                    return;
                }else {
                    items[i].removeAmount(fluidStack.amount);
                    return;}
    }

    public void removeItem(ItemStack itemStack){
        for(int i = 0; i < items.length; i++)
            if(items[i] != null && items[i].isItem() && items[i].getItem().equals(itemStack.getItem()))
                if(items[i].getAmount() > itemStack.stackSize){
                    items[i].removeAmount(itemStack.stackSize);
                    return;
                } else {
                    items[i] = null;
                    return;
                }
    }

    public void toCraft(World world, EntityPlayer player){
        for(int i = 0; i < listRecipe.size(); i++){
            List<Integer> input = listRecipe.get(i).checkCraft(items);
            System.out.println("Check Craft " + (input != null));
            if(input != null && input.size() == 3){
                System.out.println("Craft!");
                for(int b = 0; b < input.size(); b++)
                     if(items[b] != null){
                        if(items[input.get(b)].isFluid()) {
                            if (items[input.get(b)].getFluid().amount > listRecipe.get(i).getRecipeItem(b).getAmount())
                                items[input.get(b)].removeAmount(listRecipe.get(i).getRecipeItem(b).getAmount());
                            else if (items[input.get(b)].getFluid().amount == listRecipe.get(i).getRecipeItem(b).getAmount())
                                items[input.get(b)] = null;
                        }else if(items[input.get(b)].isItem())
                                if(items[input.get(b)].getAmount() > listRecipe.get(i).getRecipeItem(b).getAmount())
                                    items[input.get(b)].removeAmount(listRecipe.get(i).getRecipeItem(b).getAmount());
                                else if(items[input.get(b)].getAmount() == listRecipe.get(i).getRecipeItem(b).getAmount())
                                    items[input.get(b)] = null;}
                world.spawnEntityInWorld(new EntityItem(world, player.posX + 0.5D, player.posY + 1.5D, player.posZ + 0.5D, listRecipe.get(i).getOutput().getItem()));
            }
        }
    }

    @Override
    public Packet getDescriptionPacket()
    {
        NBTTagCompound tag = new NBTTagCompound();
        this.writeToNBT(tag);

        return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, -1, tag);
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet)
    {
        NBTTagCompound tag = packet.func_148857_g();
        this.readFromNBT(tag);

        worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
    }


}
