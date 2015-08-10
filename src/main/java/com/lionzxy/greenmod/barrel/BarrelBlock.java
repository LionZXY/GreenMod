package com.lionzxy.greenmod.barrel;

import com.lionzxy.greenmod.creativetabs.GreenModTabs;
import com.lionzxy.greenmod.utils.BarrelRecipeItem;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBucket;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import net.minecraftforge.fluids.*;

/**
 * Created by nikit on 09.08.2015.
 */
public class BarrelBlock extends BlockContainer{


    public BarrelBlock() {
        super(Material.wood);
        this.setCreativeTab(GreenModTabs.tabGeneral);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_)
    {
        BarrelTile tileEntity = (BarrelTile) world.getTileEntity(x,y,z);
                if(player.getCurrentEquippedItem() != null)
            if((player.getCurrentEquippedItem().getItem() instanceof  IFluidContainerItem || FluidContainerRegistry.isContainer(player.getCurrentEquippedItem()))){
                tileEntity.fillLiquid(player.getCurrentEquippedItem(), player, world);
                return true;}
        tileEntity.actionWithItem(player, world);
        for(BarrelRecipeItem i : tileEntity.getItems())
            if(i != null && !player.worldObj.isRemote) player.addChatComponentMessage(new ChatComponentText(i.getName() + " " + i.getAmount()));
        tileEntity.toCraft(world,player);

        return true;
    }


    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new BarrelTile();
    }
}
