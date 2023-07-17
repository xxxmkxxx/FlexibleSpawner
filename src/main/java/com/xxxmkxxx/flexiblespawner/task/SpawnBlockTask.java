package com.xxxmkxxx.flexiblespawner.task;

import com.xxxmkxxx.timecontrol.task.AbstractTask;
import net.minecraft.block.Block;
import net.minecraft.util.Clearable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.function.Consumer;

public class SpawnBlockTask<B extends Block> extends AbstractTask {
    private BlockPos pos;
    private Consumer<BlockPos> task;

    public SpawnBlockTask(B block, BlockPos pos, World world) {
        super("spawn_" + block.getName().getString() + "_task");
        this.pos = pos;
        this.task = (position) -> {
            Clearable.clear(world.getBlockEntity(position));
            world.setBlockState(position, block.getDefaultState());
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
