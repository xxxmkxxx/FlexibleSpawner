package com.xxxmkxxx.flexiblespawner;

import com.xxxmkxxx.flexiblespawner.task.SpawnBlockTask;
import com.xxxmkxxx.flexiblespawner.task.SpawnEntityTask;
import com.xxxmkxxx.timecontrol.TimeControl;
import lombok.RequiredArgsConstructor;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.util.Clearable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

@RequiredArgsConstructor
public class SimpleFlexibleSpawner implements FlexibleSpawner {
    private final TimeControl timeControl;

    @Override
    public <E extends Entity> void registerSpawnEntity(SpawnRule spawnRule, EntityType<E> entityType) {
        SpawnEntityTask spawnEntityTask = new SpawnEntityTask(entityType, new BlockPos(0, 0, 0), spawnRule.world());

        if (spawnRule.isScheduled()) {
            for (long tick : spawnRule.ticks()) {
                for (BlockPos pos : spawnRule.positions()) {
                    spawnEntityTask.changePos(pos);
                    timeControl.getScheduler().scheduleTask(tick, spawnEntityTask);
                }
            }
        } else if (spawnRule.isCyclic()) {
            for (BlockPos pos : spawnRule.positions()) {
                spawnEntityTask.changePos(pos);

                for (int i = 1; i <= spawnRule.amountCycles(); i++) {
                    timeControl.getScheduler().scheduleTask(spawnRule.amountTicks() * i, spawnEntityTask);
                }
            }
        } else {
            for (BlockPos pos : spawnRule.positions()) {
                spawnEntityTask.changePos(pos);
                spawnEntityTask.execute();
            }
        }
    }

    @Override
    public <E extends Entity> void spawnEntity(BlockPos pos, World world, EntityType<E> entityType) {
        E entity = entityType.create(world);
        spawnEntity(pos, world, entity);
    }

    @Override
    public <E extends Entity> void spawnEntity(BlockPos pos, World world, E entity) {
        entity.setPosition(pos.getX(), pos.getY(), pos.getZ());
        world.spawnEntity(entity);
    }

    @Override
    public <B extends Block> void registerSpawnBlock(SpawnRule spawnRule, B block) {
        SpawnBlockTask<B> spawnBlockTask = new SpawnBlockTask<>(block, new BlockPos(0, 0, 0), spawnRule.world());

        if (spawnRule.isScheduled()) {
            for (long tick : spawnRule.ticks()) {
                for (BlockPos pos : spawnRule.positions()) {
                    spawnBlockTask.changePos(pos);
                    timeControl.getScheduler().scheduleTask(tick, spawnBlockTask);
                }
            }
        } else if (spawnRule.isCyclic()) {
            for (BlockPos pos : spawnRule.positions()) {
                spawnBlockTask.changePos(pos);

                for (int i = 1; i <= spawnRule.amountCycles(); i++) {
                    timeControl.getScheduler().scheduleTask(spawnRule.amountTicks() * i, spawnBlockTask);
                }
            }
        } else {
            for (BlockPos pos : spawnRule.positions()) {
                spawnBlockTask.changePos(pos);
                spawnBlockTask.execute();
            }
        }
    }

    @Override
    public <B extends Block> void spawnBlock(BlockPos pos, World world, B block) {
        Clearable.clear(world.getBlockEntity(pos));
        world.setBlockState(pos, block.getDefaultState());
    }
}
