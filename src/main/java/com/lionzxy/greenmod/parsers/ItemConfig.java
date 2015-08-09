package com.lionzxy.greenmod.parsers;

import com.lionzxy.greenmod.GreenMod;
import com.lionzxy.greenmod.items.*;
import com.lionzxy.greenmod.utils.Version;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

/**
 * Created by nikit on 09.08.2015.
 */
public class ItemConfig {

    public static void addAll(){
        MainConfig.Init();
        MainConfig.getConfig().load();
        addAllAxe();
        addAllHoe();
        addAllPickaxe();
        addAllShears();
        addAllSword();
        registerAll();
        MainConfig.getConfig().save();
    }

    private static void addAllAxe(){
        for(int i = 0; i < MainConfig.getConfig().getInt("Axe Items","general",1,0,256,""); i++){
            GreenMod.items.add(new BasicAxe(
                    MainConfig.getConfig().getString("Name","Axe."+i,"Axes."+i,""),
                    MainConfig.getConfig().getString("TextureName","Axes."+i, Version.MODID + ":Axe."+i,""),
                    MainConfig.getConfig().getInt("Axe Level", "Axes." + i, 1, 0, 20, ""),
                    MainConfig.getConfig().getInt("Max Damage","Axes."+i,1024,0,999999999,""),
                    MainConfig.getConfig().getInt("Speed Level","Axes."+i,1,0,20,""),
                    MainConfig.getConfig().getInt("Damage","Axes."+i,10,0,999999999,""),
                    MainConfig.getConfig().getInt("Enchant","Axes."+i,10,0,100,"")
                    ));
        }
    }

    private static void addAllHoe(){
        String type = "Hoe";
        for(int i = 0; i < MainConfig.getConfig().getInt(type+" Items","general",1,0,256,""); i++){
            GreenMod.items.add(new BasicHoe(
                    MainConfig.getConfig().getString("Name",type+"."+i,type+"s."+i,""),
                    MainConfig.getConfig().getString("TextureName",type+"s."+i,Version.MODID + ":"+type+"."+i,""),
                    MainConfig.getConfig().getInt(type+" Level", type+"s." + i, 1, 0, 20, ""),
                    MainConfig.getConfig().getInt("Max Damage",type+"s."+i,1024,0,999999999,""),
                    MainConfig.getConfig().getInt("Speed Level",type+"s."+i,1,0,20,""),
                    MainConfig.getConfig().getInt("Damage", type + "s." + i, 10, 0, 999999999, ""),
                    MainConfig.getConfig().getInt("Enchant", type + "s." + i, 10, 0, 100, "")
            ));
        }
    }

    private static void addAllPickaxe(){
        String type = "Pickaxe";
        for(int i = 0; i < MainConfig.getConfig().getInt(type+" Items","general",1,0,256,""); i++){
            GreenMod.items.add(new BasicPickaxe(
                    MainConfig.getConfig().getString("Name",type+"."+i,type+"s."+i,""),
                    MainConfig.getConfig().getString("TextureName",type+"s."+i,Version.MODID + ":"+type+"."+i,""),
                    MainConfig.getConfig().getInt(type+" Level", type+"s." + i, 1, 0, 20, ""),
                    MainConfig.getConfig().getInt("Max Damage",type+"s."+i,1024,0,999999999,""),
                    MainConfig.getConfig().getInt("Speed Level",type+"s."+i,1,0,20,""),
                    MainConfig.getConfig().getInt("Damage",type+"s."+i,10,0,999999999,""),
                    MainConfig.getConfig().getInt("Enchant",type+"s."+i,10,0,100,"")
            ));
        }
    }

    private static void addAllSword(){
        String type = "Sword";
        for(int i = 0; i < MainConfig.getConfig().getInt(type+" Items","general",1,0,256,""); i++){
            GreenMod.items.add(new BasicSword(
                    MainConfig.getConfig().getString("Name",type+"s."+i,type+"."+i,""),
                    MainConfig.getConfig().getString("TextureName",type+"s."+i,Version.MODID + ":"+type+"."+i,""),
                    MainConfig.getConfig().getInt(type+" Level", type+"s." + i, 1, 0, 20, ""),
                    MainConfig.getConfig().getInt("Max Damage",type+"s."+i,1024,0,999999999,""),
                    MainConfig.getConfig().getInt("Speed Level",type+"s."+i,1,0,20,""),
                    MainConfig.getConfig().getInt("Damage",type+"s."+i,10,0,999999999,""),
                    MainConfig.getConfig().getInt("Enchant",type+"s."+i,10,0,100,"")
            ));
        }
    }

    private static void addAllShears(){
        String type = "Shears";
        for(int i = 0; i < MainConfig.getConfig().getInt(type+" Items","general",1,0,256,""); i++){
            GreenMod.items.add(new BasicShears(
                    MainConfig.getConfig().getInt("Max Damage",type+"."+i,1024,0,999999999,""),
                    MainConfig.getConfig().getString("Name",type+"."+i,type+"."+i,""),
                    MainConfig.getConfig().getString("TextureName",type+"."+i,Version.MODID + ":"+type+"."+i,"")
            ));
        }
    }

    private static void registerAll(){
        for(Item item : GreenMod.items)
            GameRegistry.registerItem(item,item.getUnlocalizedName());
    }
}
