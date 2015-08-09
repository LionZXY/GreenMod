package com.lionzxy.greenmod;

import com.lionzxy.greenmod.barrel.BarrelBlock;
import com.lionzxy.greenmod.barrel.BarrelTile;
import com.lionzxy.greenmod.creativetabs.GreenModTabs;
import com.lionzxy.greenmod.parsers.ItemConfig;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nikit on 08.08.2015.
 */
@Mod(name = "Green Mod", modid = "gm")
public class GreenMod {

    public static List<Item> items = new ArrayList<Item>();
    public static Block barrel = new BarrelBlock();

    @Mod.EventHandler
    public void Init(FMLInitializationEvent event){
        GreenModTabs.Init();
        ItemConfig.addAll();
        GameRegistry.registerBlock(barrel, "barrel");
        GameRegistry.registerTileEntity(BarrelTile.class,"barreltile");
    }
}
