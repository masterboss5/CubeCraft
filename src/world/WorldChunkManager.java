package world;

import main.Main;
import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

public class WorldChunkManager {
    private static final ArrayList<Chunk> CHUNK_CACHE = new ArrayList<>();
    private static final int RENDER_DISTANCE = 4;
    private final World world;

    public final BiFunction<Vector3f, ChunkPosition, Boolean> isInRange = (cameraPosition, chunkPosition) -> {
        int cameraChunkX = (int) Math.floor(cameraPosition.x) >> 4;
        int cameraChunkZ = (int) Math.floor(cameraPosition.z) >> 4;

        int dx = chunkPosition.getX() - cameraChunkX;
        int dz = chunkPosition.getZ() - cameraChunkZ;

        return dx * dx + dz * dz <= RENDER_DISTANCE * RENDER_DISTANCE;
    };

    public WorldChunkManager(World world) {
        this.world = world;
    }

    public void generateChunks(int centerX, int centerZ) {
        for (int dx = -RENDER_DISTANCE; dx <= RENDER_DISTANCE; dx++) {
            for (int dz = -RENDER_DISTANCE; dz <= RENDER_DISTANCE; dz++) {
                ChunkPosition pos = new ChunkPosition(centerX + dx, centerZ + dz);
                Chunk chunk = new Chunk(pos, this);

                // Simple flat terrain (e.g., dirt at y=0 to y=4)
                for (int x = 0; x < ChunkPosition.CHUNK_WIDTH; x++) {
                    for (int z = 0; z < ChunkPosition.CHUNK_WIDTH; z++) {
                        for (int y = 0; y < 5; y++) {
                            chunk.setBlock(x, y, z, world.getBlockRegistry().get("dirt"));
                        }
                    }
                }

                cache(chunk);
            }
        }
    }

    public void cache(Chunk chunk) {
        CHUNK_CACHE.add(chunk);
    }

    public int getRenderDistance() {
        return RENDER_DISTANCE;
    }

    private Chunk[] getVisibleChunks() {
        List<Chunk> visibleChunks = new ArrayList<>();

        for (Chunk chunk : CHUNK_CACHE) {
            if (isInRange.apply(Main.camera.getPosition(), chunk.getChunkPosition())) {
                visibleChunks.add(chunk);
            }
        }

        return visibleChunks.toArray(new Chunk[0]);
    };
}
