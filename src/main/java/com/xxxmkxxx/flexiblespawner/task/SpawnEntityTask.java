package com.xxxmkxxx.flexiblespawner.task;

import com.xxxmkxxx.timecontrol.task.AbstractTask;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.function.Consumer;

public class SpawnEntityTask extends AbstractTask {
    private final EntityType<? extends Entity> entityType;
    private BlockPos pos;
    private final World world;

    private Consumer<BlockPos> task;

    public SpawnEntityTask(EntityType<? extends Entity> entityType, BlockPos pos, World world) {
        super("spawn_" + entityType.getName().getString() + "_task");
        this.entityType = entityType;
        this.pos = pos;
        this.world = world;
        this.task = (position) -> {
            Entity entity = entityType.create(world);
            entity.setPosition(position.getX(), position.getY(), position.getZ());
            world.spawnEntity(entity);
        };
    }

    @Override
    public void changeAction(Runnable runnable) {
        this.task = pos1 -> runnable.run();
    }

    @Override
    public void execute() {
        this.task.accept(pos);
    }

    public void changePos(BlockPos pos) {
        this.pos = pos;
    }
}
