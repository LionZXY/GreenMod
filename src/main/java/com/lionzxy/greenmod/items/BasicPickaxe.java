package com.lionzxy.greenmod.items;

import com.lionzxy.greenmod.creativetabs.GreenModTabs;
import com.lionzxy.greenmod.utils.Version;
import net.minecraft.item.ItemPickaxe;
import net.minecraftforge.common.util.EnumHelper;

/**
 * Created by nikit on 08.08.2015.
 */
public class BasicPickaxe extends ItemPickaxe{


    public BasicPickaxe(String name, String texturename, int quality, int maxDamage, int speed, int damage, int enchant) {
        super(EnumHelper.addToolMaterial(name,quality,maxDamage,speed,damage,enchant));
        this.setCreativeTab(GreenModTabs.tabGeneral);
        this.setTextureName(texturename);
        this.setUnlocalizedName(name);
    }

    public BasicPickaxe(ToolMaterial material, String name, String texturename) {
        super(material);
        this.setCreativeTab(GreenModTabs.tabGeneral);
        this.setTextureName(texturename);
        this.setUnlocalizedName(name);
    }
}
