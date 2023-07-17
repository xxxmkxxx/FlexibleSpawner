package com.xxxmkxxx.flexiblespawner;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface FlexibleSpawner {
    <E extends Entity> void registerSpawnEntity(SpawnRule spawnRule, EntityType<E> entityType);
    <E extends Entity> void spawnEntity(BlockPos pos, World world, EntityType<E> entityType);
    <E extends Entity> void spawnEntity(BlockPos pos, World world, E entity);
    <B extends Block> void registerSpawnBlock(SpawnRule spawnRule, B block);
    <B extends Block> void spawnBlock(BlockPos pos, World world, B block);
}
