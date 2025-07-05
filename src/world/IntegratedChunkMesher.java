package world;

import block.Block;
import block.BlockPosition;
import gl.VertexBuffer;
import gl.glUsage;

import java.util.ArrayList;
import java.util.List;

public class IntegratedChunkMesher implements ChunkMesher {
    // Front (+Z)
    private static final float[] BACK_FACE = {
            0, 0, 1,
            1, 0, 1,
            1, 1, 1,
            0, 1, 1
    };
    // Back (−Z)
    private static final float[] FRONT_FACE = {
            1, 0, 0,
            0, 0, 0,
            0, 1, 0,
            1, 1, 0
    };
    // Right (+X)
    private static final float[] RIGHT_FACE = {
            1, 0, 1,
            1, 0, 0,
            1, 1, 0,
            1, 1, 1
    };
    // Left (−X)
    private static final float[] LEFT_FACE = {
            0, 0, 0,
            0, 0, 1,
            0, 1, 1,
            0, 1, 0
    };
    // Top (+Y)
    private static final float[] TOP_FACE = {
            0, 1, 1,
            1, 1, 1,
            1, 1, 0,
            0, 1, 0
    };
    // Bottom (−Y)
    private static final float[] BOTTOM_FACE = {
            0, 0, 0,
            1, 0, 0,
            1, 0, 1,
            0, 0, 1
    };

    private static final int[] INDICES = {0, 1, 2, 2, 3, 0};

    @Override
    public ChunkMesh meshChunk(Chunk chunk) {
        List<Float> vertices = new ArrayList<>();
        List<Integer> indices = new ArrayList<>();

        for (int x = 0; x < ChunkPosition.CHUNK_WIDTH; x++) {
            for (int y = 0; y < ChunkPosition.CHUNK_HEIGHT; y++) {
                for (int z = 0; z < ChunkPosition.CHUNK_WIDTH; z++) {
                    BlockPosition position = new BlockPosition(x, y, z);
                    Block block = chunk.safeGetBlock(position);

                    if (block.isAirBlock()) continue;

                    if (chunk.safeGetBlock(position.offset(0, 0, -1)).isAirBlock()) {
                        this.insertFace(vertices, indices, position, FRONT_FACE);
                    }
                    if (chunk.safeGetBlock(position.offset(0, 0, 1)).isAirBlock()) {
                        this.insertFace(vertices, indices, position, BACK_FACE);
                    }
                    if (chunk.safeGetBlock(position.offset(1, 0, 0)).isAirBlock()) {
                        this.insertFace(vertices, indices, position, RIGHT_FACE);
                    }
                    if (chunk.safeGetBlock(position.offset(-1, 0, 0)).isAirBlock()) {
                        this.insertFace(vertices, indices, position, LEFT_FACE);
                    }
                    if (chunk.safeGetBlock(position.offset(0, 1, 0)).isAirBlock()) {
                        this.insertFace(vertices, indices, position, TOP_FACE);
                    }
                    if (chunk.safeGetBlock(position.offset(0, -1, 0)).isAirBlock()) {
                        this.insertFace(vertices, indices, position, BOTTOM_FACE);
                    }
                }
            }
        }

        float[] array = new float[vertices.size()];
        for (int i = 0; i < vertices.size(); i++) {
            array[i] = vertices.get(i);
        }

        int[] array2 = new int[indices.size()];
        for (int i = 0; i < indices.size(); i++) {
            array2[i] = indices.get(i);
        }

        VertexBuffer vertexBuffer = new VertexBuffer(glUsage.GL_STATIC_DRAW).vertexes(array).indices(array2);
        vertexBuffer.build();

        return new ChunkMesh(vertexBuffer);
    }

    private void insertFace(List<Float> vertices, List<Integer> indices, BlockPosition position, float[] faces) {
        int vertexCount = vertices.size() / 3;

        for (int i = 0; i < faces.length; i += 3) {
            float vx = faces[i] + position.getX();
            float vy = faces[i + 1] + position.getY();
            float vz = faces[i + 2] + position.getZ();

            vertices.add(vx);
            vertices.add(vy);
            vertices.add(vz);
        }

        for (int index : INDICES) {
            indices.add(vertexCount + index);
        }
    }
}