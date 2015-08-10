package com.lionzxy.greenmod;

import com.lionzxy.greenmod.barrel.BarrelTile;
import com.lionzxy.greenmod.utils.BarrelRecipeItem;
import com.lionzxy.greenmod.utils.RecipeObject;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

/**
 * Created by nikit on 10.08.2015.
 */
public class Recipe {
    public static void addAllRecipe(){
        BarrelTile.listRecipe.add(new RecipeObject(new BarrelRecipeItem(new ItemStack(Blocks.obsidian),1),
                new BarrelRecipeItem(new ItemStack(Blocks.sand), 1),
                new BarrelRecipeItem(new ItemStack(Blocks.glass), 1)));
    }
}
