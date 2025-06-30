package world;

import block.BlockPosition;
import block.GrassBlock;
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
        System.out.println(cameraChunkX);
        System.out.println(cameraChunkZ);

        int dx = chunkPosition.getX() - cameraChunkX;
        int dz = chunkPosition.getZ() - cameraChunkZ;

        return dx * dx + dz * dz <= getRenderDistance() * getRenderDistance();
    };

    public WorldChunkManager(World world) {
        this.world = world;
    }

    public void solidChunk(ChunkPosition chunkPosition) {
        Chunk chunk = new Chunk(chunkPosition, this);

        for (int x = 0; x < ChunkPosition.CHUNK_WIDTH; x++) {
            for (int z = 0; z < ChunkPosition.CHUNK_WIDTH; z++) {
                for (int y = 0; y < 5; y++) {
                    int worldX = chunkPosition.getX() * ChunkPosition.CHUNK_WIDTH + x;
                    int worldZ = chunkPosition.getZ() * ChunkPosition.CHUNK_WIDTH + z;
                    BlockPosition blockPos = new BlockPosition(worldX, y, worldZ);

                    chunk.setBlock(new GrassBlock(blockPos), x, y, z);
                }
            }
        }

        this.cache(chunk);
    }

    public void generateChunks(int centerX, int centerZ) {
        this.solidChunk(new ChunkPosition(0, 0));
        this.solidChunk(new ChunkPosition(1, 0));
        this.solidChunk(new ChunkPosition(2, 0));
        this.solidChunk(new ChunkPosition(3, 0));
        this.solidChunk(new ChunkPosition(4, 0));
        this.solidChunk(new ChunkPosition(5, 0));
        this.solidChunk(new ChunkPosition(6, 0));
        this.solidChunk(new ChunkPosition(7, 0));
        this.solidChunk(new ChunkPosition(8, 0));
        this.solidChunk(new ChunkPosition(9, 0));
    }

    public Chunk[] getVisibleChunks() {
        List<Chunk> visibleChunks = new ArrayList<>();

        for (Chunk chunk : CHUNK_CACHE) {
            if (isInRange.apply(Main.camera.getPosition(), chunk.getChunkPosition())) {
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
