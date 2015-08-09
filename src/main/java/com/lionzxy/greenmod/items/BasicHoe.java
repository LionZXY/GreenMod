package com.lionzxy.greenmod.items;

import com.lionzxy.greenmod.creativetabs.GreenModTabs;
import com.lionzxy.greenmod.utils.Version;
import net.minecraft.item.ItemHoe;
import net.minecraftforge.common.util.EnumHelper;

/**
 * Created by nikit on 09.08.2015.
 */
public class BasicHoe extends ItemHoe {

    public BasicHoe(String name, String texturename, int quality, int maxDamage, int speed, int damage, int enchant) {
        super(EnumHelper.addToolMaterial(name, quality, maxDamage, speed, damage, enchant));
        this.setCreativeTab(GreenModTabs.tabGeneral);
        this.setTextureName(texturename);
        this.setUnlocalizedName(name);
    }

    public BasicHoe(ToolMaterial material, String name, String texturename) {
        super(material);
        this.setCreativeTab(GreenModTabs.tabGeneral);
        this.setTextureName(texturename);
        this.setUnlocalizedName(name);
    }
}
