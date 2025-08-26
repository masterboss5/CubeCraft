package world;

import block.Block;
import block.BlockPosition;
import block.Blocks;
import entity.Entity;
import entity.EntityType;
import entity.model.EntityModel;
import entity.render.EntityRenderDispatch;
import graphic.ModelPart;
import org.joml.Matrix4f;
import render.RenderSystem;

import java.util.*;

public class World {
    final WorldChunkManager chunkManager = new WorldChunkManager(this);
    private final WorldEntityManager entityManager = new WorldEntityManager(this);
    private final EntityRenderDispatch entityRenderDispatch = new EntityRenderDispatch();
    private Entity entity = EntityType.CUBE_ENTITY.create(0, 0, 0, this);

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
        this.addEntity(entity);
        this.entityRenderDispatch.reload();
    }

    public void tickWorld() {
        this.entityManager.tickEntities();
        entity.setPosition(0, 0, 0);
    }

    public void renderWorld() {
        for (Chunk visibleChunk : this.chunkManager.getVisibleChunks()) {
            visibleChunk.render();
        }

        Map<ModelPart, List<Matrix4f>> instances = new HashMap<>();

        for (Entity entity : this.entityManager.getEntities()) {
            EntityModel<?> model = EntityRenderDispatch.getRenderer(entity.getType()).getModel();

            for (ModelPart part : model.getParts()) {
                instances.putIfAbsent(part, new ArrayList<>());
                instances.get(part).add(part.getModelTransformationMatrix4f());
            }
        }

        RenderSystem.renderEntityInstanced(instances);
    }
}
