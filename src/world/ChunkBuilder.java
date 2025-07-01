package world;

import block.Block;

public class ChunkBuilder {
    public ChunkMesh generateMesh(Chunk chunk) {
        ChunkMesh mesh = new ChunkMesh();

        for (int x = 0; x < ChunkPosition.CHUNK_WIDTH; x++) {
            for (int y = 0; y < ChunkPosition.CHUNK_HEIGHT; y++) {
                for (int z = 0; z < ChunkPosition.CHUNK_WIDTH; z++) {
                    Block block = chunk.getBlock(x, y, z);
                    if (block == Block.AIR) continue;

                    // For each face, check if it's exposed
                    for (Direction dir : Direction.values()) {
                        if (isFaceVisible(chunk, x, y, z, dir)) {
                            mesh.addFace(x, y, z, dir, block);
                        }
                    }
                }
            }
        }

        return mesh;
    }

    private boolean isFaceVisible(Chunk chunk, int x, int y, int z, Direction dir) {
        int nx = x + dir.dx, ny = y + dir.dy, nz = z + dir.dz;
        Block neighbor = chunk.getBlock(nx, ny, nz);
        return neighbor == Block.AIR; // Only render faces touching air
    }
}
