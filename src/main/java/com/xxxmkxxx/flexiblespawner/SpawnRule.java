package com.xxxmkxxx.flexiblespawner;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.HashSet;
import java.util.Set;

public record SpawnRule(
        World world, Set<BlockPos> positions,
        boolean isCyclic, long amountTicks, long amountCycles,
        boolean isScheduled, long ... ticks
) {
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Set<BlockPos> positions;
        private boolean isCyclic;
        private long amountTicks;
        private long amountCycles;
        private boolean isScheduled;
        private long[] ticks;

        public Builder() {
            this.positions = new HashSet<>();
            this.isCyclic = false;
            this.isScheduled = false;
        }

        public Builder position(float x, float y, float z) {
            positions.add(new BlockPos(x, y, z));
            return this;
        }
        public Builder position(BlockPos blockPos) {
            positions.add(blockPos);
            return this;
        }
        public Builder positions(Set<BlockPos> positions) {
            this.positions = positions;
            return this;
        }
        public Builder setCyclicSpawn(long amountTicks, long amountCycles) {
            isCyclic = true;
            this.amountTicks = amountTicks;
            this.amountCycles = amountCycles;
            return this;
        }
        public Builder scheduleSpawn(long ... ticks) {
            isScheduled = true;
            this.ticks = ticks;
            return this;
        }

        public SpawnRule build(World world) {
            return new SpawnRule(world, positions, isCyclic, amountTicks, amountCycles, isScheduled, ticks);
        }
    }
}
