package com.lionzxy.greenmod.creativetabs;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * Created by nikit on 08.08.2015.
 */
public class GreenModTab extends CreativeTabs {
    ItemStack item;

    public GreenModTab(String lable) {
        super(lable);
    }

    public void setTabIconItem(ItemStack item) {
        this.item = item;
    }

    @Override
    public Item getTabIconItem() {
        return item.getItem();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ItemStack getIconItemStack() {
        return item;
    }
}
