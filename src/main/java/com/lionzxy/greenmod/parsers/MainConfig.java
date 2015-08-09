package com.lionzxy.greenmod.parsers;

import cpw.mods.fml.common.Loader;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

/**
 * Created by nikit on 09.08.2015.
 */
public class MainConfig {
    private static Configuration config;

    public static void Init(){
        config = new Configuration(new File(Loader.instance().getConfigDir(), "GreenMod.cfg"));
        config.save();
    }

    public static Configuration getConfig(){
        return config;
    }
}
