package world;

import block.BlockPosition;
import block.GrassBlock;
import main.Main;
import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.List;
public class WorldChunkManager {
    private static final ArrayList<Chunk> CHUNK_CACHE = new ArrayList<>();
    private static final int RENDER_DISTANCE = 2;
    private final World world;

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

    public void solidChunk(ChunkPosition chunkPosition) {
        Chunk chunk = new Chunk(chunkPosition, this);

        for (int x = 0; x < ChunkPosition.CHUNK_WIDTH; x++) {
            for (int z = 0; z < ChunkPosition.CHUNK_WIDTH; z++) {
                for (int y = 0; y < 5; y++) {
                    int worldX = chunkPosition.getX() * ChunkPosition.CHUNK_WIDTH + x;
                    int worldZ = chunkPosition.getZ() * ChunkPosition.CHUNK_WIDTH + z;
                    BlockPosition blockPos = new BlockPosition(worldX, y, worldZ);

                    chunk.setBlock(new GrassBlock(blockPos), new BlockPosition(x, y, z));
                }
            }
        }

        this.cache(chunk);
    }

    public void generateChunks(int centerX, int centerZ) {
        for (int x = -25; x <= 25; x++) {
            for (int z = -25; z <= 25; z++) {
                this.solidChunk(new ChunkPosition(x, z));
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
}
