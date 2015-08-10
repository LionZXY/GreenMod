package com.lionzxy.greenmod.utils;

import cpw.mods.fml.common.FMLLog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nikit on 09.08.2015.
 */
public class RecipeObject {
    private BarrelRecipeItem[] items = new BarrelRecipeItem[3];
    private BarrelRecipeItem output;
    public RecipeObject(BarrelRecipeItem output, BarrelRecipeItem ... input){
            for(int i = 0; i < 3; i++)
                if(i < input.length)
                    items[i] = input[i];
                else items[i] = new BarrelRecipeItem();
            this.output = output;
    }


    public List<Integer> checkCraft(BarrelRecipeItem[] input){
        //Положение крафтовых рецептов в сетке верстака
        List<Integer> barrelRecipeItems = new ArrayList<Integer>();
        for(BarrelRecipeItem i : items){
            if(findItem(input, i) != -1)
                //Добавляет в лист положение крафтового итема в сетке верстака
                barrelRecipeItems.add(findItem(input, i));
            else return null;}
        return barrelRecipeItems;
    }

    public BarrelRecipeItem getOutput(){
        return output;
    }

    public int findItem(BarrelRecipeItem[] input, BarrelRecipeItem barrelRecipeItem){
        for(int i = 0; i < input.length; i++){
            if((input[i] == null || input[i].isNull()) && (barrelRecipeItem == null || barrelRecipeItem.isNull()))
                return i;
            else if(input[i] != null && input[i].checkToItem(barrelRecipeItem))
                    return i;}
        return -1;
    }

    public BarrelRecipeItem getRecipeItem(int i){
        return items[i];
    }
}
