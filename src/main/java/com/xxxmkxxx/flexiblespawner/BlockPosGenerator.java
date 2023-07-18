package com.xxxmkxxx.flexiblespawner;

import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BlockPosGenerator {
    private static final Random random = new Random();

    public static BlockPos generatePosInZone(BlockPos startPos, BlockPos stopPos) {
        int xPos = random.nextInt(stopPos.getX() - startPos.getX()) + startPos.getX();
        int yPos = random.nextInt(stopPos.getY() - startPos.getY()) + startPos.getY();
        return new BlockPos(xPos, yPos, (startPos.getZ() + stopPos.getZ()) / 2);
    }

    public static List<BlockPos> generatePositionsInZone(int amount, BlockPos startPos, BlockPos stopPos) {
        List<BlockPos> positions = new ArrayList<>(amount);
        for (int i = 0; i < amount; i++) {
            positions.add(generatePosInZone(startPos, stopPos));
        }
        return positions;
    }
}
