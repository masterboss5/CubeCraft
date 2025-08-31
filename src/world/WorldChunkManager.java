package world;

import block.*;
import main.Main;
import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.List;

public class WorldChunkManager {
    private static final ArrayList<Chunk> CHUNK_CACHE = new ArrayList<>();
    private static final int RENDER_DISTANCE = 16;
    private final World world;
    public static final ChunkMesher CHUNK_MESHER = new IntegratedChunkMesher();

    public WorldChunkManager(World world) {
        this.world = world;
    }

    private boolean isInRange(Vector3f cameraPosition, ChunkPosition chunkPosition) {
        int cameraChunkX = (int) Math.floor(cameraPosition.x) >> 4;
        int cameraChunkZ = (int) Math.floor(cameraPosition.z) >> 4;

        int dx = chunkPosition.getX() - cameraChunkX;
        int dz = chunkPosition.getZ() - cameraChunkZ;

        return dx * dx + dz * dz <= getRenderDistance() * getRenderDistance();
    }

    public void generateSolidChunk(ChunkPosition chunkPosition) {
        Chunk chunk = new Chunk(chunkPosition, this);

        for (int x = 0; x < Chunk.CHUNK_WIDTH; x++) {
            for (int z = 0; z < Chunk.CHUNK_WIDTH; z++) {
                for (int y = 0; y < 2; y++) {
                    int worldX = chunkPosition.getX() * Chunk.CHUNK_WIDTH + x;
                    int worldZ = chunkPosition.getZ() * Chunk.CHUNK_WIDTH + z;

                    chunk.setBlock(Blocks.GRASS_BLOCK, new BlockPosition(x, y, z));
                }
            }
        }
        chunk.setBlock(Blocks.COBBLESTONE_BLOCK, new BlockPosition(0, 0, 0));

        this.cache(chunk);
    }

    public void generateChunks(int size) {
        for (int x = -size; x <= size; x++) {
            for (int z = -size; z <= size; z++) {
                this.generateSolidChunk(new ChunkPosition(x, z));
            }
        }
    }

    public Chunk[] getVisibleChunks() {
        List<Chunk> visibleChunks = new ArrayList<>();

        for (Chunk chunk : this.getCache()) {
            if (this.isInRange(Main.camera.getPosition(), chunk.getChunkPosition())) {
                visibleChunks.add(chunk);
            }
        }

        return visibleChunks.toArray(new Chunk[0]);
    };

    public void cache(Chunk chunk) {
        CHUNK_CACHE.add(chunk);
    }

    public ArrayList<Chunk> getCache() {
        return CHUNK_CACHE;
    }

    public static int getRenderDistance() {
        return RENDER_DISTANCE;
    }

    public World getWorld() {
        return world;
    }
}
