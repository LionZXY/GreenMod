package com.lionzxy.greenmod.items;

import com.lionzxy.greenmod.creativetabs.GreenModTabs;
import com.lionzxy.greenmod.utils.Version;
import net.minecraft.item.ItemAxe;
import net.minecraftforge.common.util.EnumHelper;

/**
 * Created by nikit on 09.08.2015.
 */
public class BasicAxe extends ItemAxe {

    public BasicAxe(String name, String texturename, int quality, int maxDamage, int speed, int damage, int enchant) {
        super(EnumHelper.addToolMaterial(name, quality, maxDamage, speed, damage, enchant));
        this.setCreativeTab(GreenModTabs.tabGeneral);
        this.setTextureName(texturename);
        this.setUnlocalizedName(name);
    }

    public BasicAxe(ToolMaterial material, String name, String texturename) {
        super(material);
        this.setCreativeTab(GreenModTabs.tabGeneral);
        this.setTextureName(texturename);
        this.setUnlocalizedName(name);
    }
}
