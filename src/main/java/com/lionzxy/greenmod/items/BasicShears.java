package com.lionzxy.greenmod.items;

import com.lionzxy.greenmod.creativetabs.GreenModTabs;
import com.lionzxy.greenmod.utils.Version;
import net.minecraft.item.ItemShears;

/**
 * Created by nikit on 08.08.2015.
 */
public class BasicShears extends ItemShears {

    public BasicShears(int damage,String name, String texturename){
        this.setMaxStackSize(1);
        this.setMaxDamage(damage);
        this.setTextureName(texturename);
        this.setUnlocalizedName(name);
        this.setCreativeTab(GreenModTabs.tabGeneral);
    }
}
