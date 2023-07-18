package com.xxxmkxxx.flexiblespawner;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.minecraft.util.math.BlockPos;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BlockPositionsFileParser {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static List<BlockPos> parse(String fileName) throws IOException {
        List<BlockPos> blockPosList = new ArrayList<>();
        List<Position> positions = mapper.readValue(new File(fileName), new TypeReference<>(){});

        for (Position position : positions) {
            blockPosList.add(new BlockPos(position.getX(), position.getY(), position.getZ()));
        }

        return blockPosList;
    }
}
