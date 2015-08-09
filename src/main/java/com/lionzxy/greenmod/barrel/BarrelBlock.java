package com.lionzxy.greenmod.barrel;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by nikit on 09.08.2015.
 */
public class BarrelBlock extends BlockContainer{


    protected BarrelBlock() {
        super(Material.wood);
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new BarrelTile();
    }
}
