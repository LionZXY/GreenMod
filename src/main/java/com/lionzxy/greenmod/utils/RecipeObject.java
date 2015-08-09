package com.lionzxy.greenmod.utils;

import cpw.mods.fml.common.FMLLog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nikit on 09.08.2015.
 */
public class RecipeObject {
    private BarrelRecipeItem[] items = new BarrelRecipeItem[3];

    public RecipeObject(BarrelRecipeItem output, BarrelRecipeItem ... input){
            for(int i = 0; i < 3; i++)
                if(i < input.length)
                    items[i] = input[i];
                else items[i] = new BarrelRecipeItem();
    }

    public List<Integer> checkCraft(BarrelRecipeItem[] input){
        List<Integer> barrelRecipeItems = new ArrayList<Integer>();
        for(BarrelRecipeItem i : items)
            if(findItem(input, i) != -1)
                barrelRecipeItems.add(findItem(input, i));

        return barrelRecipeItems;
    }

    public int findItem(BarrelRecipeItem[] input, BarrelRecipeItem barrelRecipeItem){
        for(int i = 0; i < input.length; i++)
            if(input[i].checkToItem(barrelRecipeItem))
                return i;
        return -1;
    }
}
