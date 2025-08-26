package entity.render;

import entity.Entity;
import entity.model.EntityModel;
import gl.VertexBuffer;
import gl.glBufferUsage;
import graphic.ModelPart;
import graphic.ModelPartTextureData;
import render.RenderSystem;
import util.ArrayUtils;

import java.util.ArrayList;
import java.util.List;

public abstract class EntityRenderer<T extends Entity> {
    protected final EntityModel<T> model;
    static float[] DEFAULT_UV = new float[] { 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 1.0f, 0.0f };

    protected EntityRenderer(EntityModel<T> model) {
        this.model = model;
    }

    public EntityModel<T> getModel() {
        return model;
    }

    public void render(T entity) {
        VertexBuffer vertexBuffer = new VertexBuffer(glBufferUsage.GL_STATIC_DRAW);
        List<Float> vertices = new ArrayList<>();
        List<Integer> indices = new ArrayList<>();
        List<Long> textures = new ArrayList<>();
        List<Float> uvCoordinates = new ArrayList<>();

        this.model.getParts().forEach(modelPart -> {
            ModelPartTextureData textureData = modelPart.getTextureData();
            ModelPart.Cuboid cuboid = modelPart.getCuboid();

            float offsetX = (float) modelPart.getPivotX();
            float offsetY = (float) modelPart.getPivotY();
            float offsetZ = (float) modelPart.getPivotZ();

            float halfWidth = cuboid.getSizeX() / 2f;
            float halfHeight = cuboid.getSizeY() / 2f;
            float halfDepth = cuboid.getSizeZ() / 2f;

            float minX = offsetX - halfWidth;
            float maxX = offsetX + halfWidth;
            float minY = offsetY - halfHeight;
            float maxY = offsetY + halfHeight;
            float minZ = offsetZ - halfDepth;
            float maxZ = offsetZ + halfDepth;

            int baseIndex = vertices.size() / 3;

            // TOP (+Y)
            vertex(vertices, minX, maxY, minZ);
            vertex(vertices, minX, maxY, maxZ);
            vertex(vertices, maxX, maxY, maxZ);
            vertex(vertices, maxX, maxY, minZ);
            repeatHandle(textures, textureData.topPointer());
            unpackArray(uvCoordinates, DEFAULT_UV);

            // BOTTOM (-Y)
            vertex(vertices, minX, minY, maxZ);
            vertex(vertices, minX, minY, minZ);
            vertex(vertices, maxX, minY, minZ);
            vertex(vertices, maxX, minY, maxZ);
            repeatHandle(textures, textureData.bottomPointer());
            unpackArray(uvCoordinates, DEFAULT_UV);

            // FRONT (+Z)
            vertex(vertices, minX, maxY, maxZ);
            vertex(vertices, minX, minY, maxZ);
            vertex(vertices, maxX, minY, maxZ);
            vertex(vertices, maxX, maxY, maxZ);
            repeatHandle(textures, textureData.frontPointer());
            unpackArray(uvCoordinates, DEFAULT_UV);

            // BACK (-Z)
            vertex(vertices, maxX, maxY, minZ);
            vertex(vertices, maxX, minY, minZ);
            vertex(vertices, minX, minY, minZ);
            vertex(vertices, minX, maxY, minZ);
            repeatHandle(textures, textureData.backPointer());
            unpackArray(uvCoordinates, DEFAULT_UV);

            // LEFT (-X)
            vertex(vertices, minX, maxY, minZ);
            vertex(vertices, minX, minY, minZ);
            vertex(vertices, minX, minY, maxZ);
            vertex(vertices, minX, maxY, maxZ);
            repeatHandle(textures, textureData.leftPointer());
            unpackArray(uvCoordinates, DEFAULT_UV);

            // RIGHT (+X)
            vertex(vertices, maxX, maxY, maxZ);
            vertex(vertices, maxX, minY, maxZ);
            vertex(vertices, maxX, minY, minZ);
            vertex(vertices, maxX, maxY, minZ);
            repeatHandle(textures, textureData.rightPointer());
            unpackArray(uvCoordinates, DEFAULT_UV);

            int[] ind = {
                    0, 1, 2, 2, 3, 0,
                    4, 5, 6, 6, 7, 4,
                    8, 9, 10, 10, 11, 8,
                    12, 13, 14, 14, 15, 12,
                    16, 17, 18, 18, 19, 16,
                    20, 21, 22, 22, 23, 20
            };
            for (int i : ind) indices.add(i + baseIndex);
        });

        vertexBuffer.vertexes(ArrayUtils.convertListFloatToPrimitiveFloatArray(vertices));
        vertexBuffer.indices(indices.stream().mapToInt(i -> i).toArray());
        vertexBuffer.build();
        vertexBuffer.createNewVertexBufferObject(toFloatArray(uvCoordinates), (byte) 2, false, glBufferUsage.GL_STATIC_DRAW);
        vertexBuffer.createNewVertexBufferObject(textures.stream().mapToLong(i -> i).toArray(), (byte) 1, false, glBufferUsage.GL_STATIC_DRAW);

        RenderSystem.renderEntity(vertexBuffer, entity);
    }

    private static void vertex(List<Float> vertices, float x, float y, float z) {
        vertices.add(x);
        vertices.add(y);
        vertices.add(z);
    }

    private static void unpackArray(List<Float> uvList, float[] values) {
        for (float value : values) uvList.add(value);
    }

    private static float[] toFloatArray(List<Float> list) {
        float[] array = new float[list.size()];
        for (int i = 0; i < list.size(); i++) array[i] = list.get(i);
        return array;
    }

    private static void repeatHandle(List<Long> textures, long handle) {
        for (int i = 0; i < 4; i++) textures.add(handle);
    }
}