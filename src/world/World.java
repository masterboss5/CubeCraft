package world;

import block.Block;
import block.BlockPosition;
import entity.Entity;
import entity.EntityType;
import entity.render.EntityRenderDispatch;

import java.util.ArrayList;
import java.util.UUID;

public class World {
    final WorldChunkManager chunkManager = new WorldChunkManager(this);
    private final WorldEntityManager entityManager = new WorldEntityManager(this);
    private final EntityRenderDispatch entityRenderDispatch = new EntityRenderDispatch();

    public World() {
    }

    public void setBlock(BlockPosition position, Block block) {
        Chunk chunk = this.getChunk(position.toChunkPosition());
        chunk.setBlock(block, position);
    }

    public Block getBlock(BlockPosition position) {
        return this.getChunk(position.toChunkPosition()).getBlock(position);
    }

    public void addEntity(Entity entity) {
        this.entityManager.addEntity(entity);
    }

    public Entity getEntity(UUID uuid) {
        return this.entityManager.getEntity(uuid);
    }

    public Chunk getChunk(ChunkPosition chunkPosition) {
        return this.chunkManager.getCache().stream()
                .filter(chunk -> chunk.getChunkPosition().equals(chunkPosition))
                .findFirst()
                .orElse(null);
    }

    public void loadWorld() {
        this.generateWorld();
    }

    public void generateWorld() {
        this.chunkManager.generateChunks(50);
        this.generateEntities();
    }

    public void generateEntities() {
        this.addEntity(EntityType.CUBE_ENTITY.create(0, 15, 0, this));
        this.entityRenderDispatch.reload();
    }

    public void tickWorld() {
        this.entityManager.tickEntities();
    }

    public void renderWorld() {
        for (Chunk visibleChunk : this.chunkManager.getVisibleChunks()) {
            visibleChunk.render();
        }

        for (Entity entity : this.entityManager.getEntities()) {
            this.entityRenderDispatch.render(entity);
        }
    }
}
