package com.lionzxy.greenmod.creativetabs;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

/**
 * Created by nikit on 08.08.2015.
 */
public class GreenModTabs {
    public static final CreativeTabs tabGeneral = new GreenModTab("tabGeneral");
    public static void Init() {
        ((GreenModTab) tabGeneral).setTabIconItem(new ItemStack(Items.stick));
    }
}
