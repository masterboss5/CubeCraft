package world;

import main.Main;
import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

public class WorldChunkManager {
    private static final ArrayList<Chunk> CHUNK_CACHE = new ArrayList<>();
    private static final int RENDER_DISTANCE = 4; //in chunks
    public final BiFunction<Vector3f, ChunkPosition, Boolean> isInRange = (cameraPosition, chunkPosition) -> {
        int cameraChunkX = (int) Math.floor(cameraPosition.x) >> 4;
        int cameraChunkZ = (int) Math.floor(cameraPosition.z) >> 4;

        int dx = chunkPosition.getX() - cameraChunkX;
        int dz = chunkPosition.getZ() - cameraChunkZ;

        return dx * dx + dz * dz <= RENDER_DISTANCE * RENDER_DISTANCE;
    };

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
