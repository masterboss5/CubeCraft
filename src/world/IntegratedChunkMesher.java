package world;

import block.Block;
import block.BlockPosition;
import gl.VertexBuffer;
import gl.glBufferUsage;
import graphic.BlockModel;

import java.util.ArrayList;
import java.util.List;

public class IntegratedChunkMesher implements ChunkMesher {
    private static final int[] INDICES = {0, 1, 2, 2, 3, 0}; // Assumes quad in CCW order

    // Face definitions match your JSON cube exactly
    private static final float[] TOP_FACE = {
            0f, 1f, 0f,
            0f, 1f, 1f,
            1f, 1f, 1f,
            1f, 1f, 0f
    };

    private static final float[] BOTTOM_FACE = {
            0f, 0f, 1f,
            0f, 0f, 0f,
            1f, 0f, 0f,
            1f, 0f, 1f
    };

    private static final float[] FRONT_FACE = {
            0f, 1f, 1f,
            0f, 0f, 1f,
            1f, 0f, 1f,
            1f, 1f, 1f
    };

    private static final float[] BACK_FACE = {
            1f, 1f, 0f,
            1f, 0f, 0f,
            0f, 0f, 0f,
            0f, 1f, 0f
    };

    private static final float[] LEFT_FACE = {
            0f, 1f, 0f,
            0f, 0f, 0f,
            0f, 0f, 1f,
            0f, 1f, 1f
    };

    private static final float[] RIGHT_FACE = {
            1f, 1f, 1f,
            1f, 0f, 1f,
            1f, 0f, 0f,
            1f, 1f, 0f
    };

    @Override
    public ChunkMesh meshChunk(Chunk chunk) {
        List<Float> vertices = new ArrayList<>();
        List<Integer> indices = new ArrayList<>();
        List<Integer> faceIndexes = new ArrayList<>();
        List<Float> uvCoordinates = new ArrayList<>();

        for (int x = 0; x < ChunkPosition.CHUNK_WIDTH; x++) {
            for (int y = 0; y < ChunkPosition.CHUNK_HEIGHT; y++) {
                for (int z = 0; z < ChunkPosition.CHUNK_WIDTH; z++) {
                    BlockPosition pos = new BlockPosition(x, y, z);
                    Block block = chunk.safeGetBlock(pos);

                    if (block.isAirBlock()) {
                        continue;
                    }

                    BlockModel blockModel = block.getModel();

                    if (chunk.safeGetBlock(pos.offset(0, 1, 0)).isAirBlock()) {
                        insertFace(vertices, indices, pos,  blockModel.getFaces().getTopFaceCenteredTo0());
                        insertTextureIndex(faceIndexes, blockModel.getTextures().getTopIndexes());
                        unpackArray(uvCoordinates, blockModel.getUvBlockMap().topUV());
                    }
                    if (chunk.safeGetBlock(pos.offset(0, -1, 0)).isAirBlock()) {
                        insertFace(vertices, indices, pos, blockModel.getFaces().getBottomFaceCenteredTo0());
                        insertTextureIndex(faceIndexes, blockModel.getTextures().getBottomIndexes());
                        unpackArray(uvCoordinates, blockModel.getUvBlockMap().bottomUV());
                    }
                    if (chunk.safeGetBlock(pos.offset(0, 0, 1)).isAirBlock()) {
                        insertFace(vertices, indices, pos, blockModel.getFaces().getFrontFaceCenteredTo0());
                        insertTextureIndex(faceIndexes, blockModel.getTextures().getFrontIndexes());
                        unpackArray(uvCoordinates, blockModel.getUvBlockMap().frontUV());
                    }
                    if (chunk.safeGetBlock(pos.offset(0, 0, -1)).isAirBlock()) {
                        insertFace(vertices, indices, pos, blockModel.getFaces().getBackFaceCenteredTo0());
                        insertTextureIndex(faceIndexes, blockModel.getTextures().getBackIndexes());
                        unpackArray(uvCoordinates, blockModel.getUvBlockMap().backUV());
                    }
                    if (chunk.safeGetBlock(pos.offset(-1, 0, 0)).isAirBlock()) {
                        insertFace(vertices, indices, pos, blockModel.getFaces().getLeftFaceCenteredTo0());
                        insertTextureIndex(faceIndexes, blockModel.getTextures().getLeftIndexes());
                        unpackArray(uvCoordinates, blockModel.getUvBlockMap().leftUV());
                    }
                    if (chunk.safeGetBlock(pos.offset(1, 0, 0)).isAirBlock()) {
                        insertFace(vertices, indices, pos, blockModel.getFaces().getRightFaceCenteredTo0());
                        insertTextureIndex(faceIndexes, blockModel.getTextures().getRightIndexes());
                        unpackArray(uvCoordinates, blockModel.getUvBlockMap().rightUV());
                    }
                }
            }
        }

        float[] vertexArray = new float[vertices.size()];
        for (int i = 0; i < vertices.size(); i++) vertexArray[i] = vertices.get(i);

        int[] indexArray = new int[indices.size()];
        for (int i = 0; i < indices.size(); i++) indexArray[i] = indices.get(i);

        VertexBuffer vertexBuffer = new VertexBuffer(glBufferUsage.GL_STATIC_DRAW)
                .vertexes(vertexArray)
                .indices(indexArray);
        vertexBuffer.build();

        vertexBuffer.createNewVertexBufferObject(toFloatArray(uvCoordinates), (byte) 2, false, glBufferUsage.GL_STATIC_DRAW);
        vertexBuffer.createNewVertexBufferObject(toIntArray(faceIndexes), (byte) 1, false, glBufferUsage.GL_STATIC_DRAW);


        return new ChunkMesh(vertexBuffer);
    }

    private int[] toIntArray(List<Integer> list) {
        int[] array = new int[list.size()];

        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }

        return array;
    }

    private float[] toFloatArray(List<Float> list) {
        float[] array = new float[list.size()];

        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }

        return array;
    }

    private void unpackArray(List<Float> uvList, float[] values) {
        for (float value : values) {
            uvList.add(value);
        }
    }

    private void insertTextureIndex(List<Integer> indexList, int[] indexes) {
        for (int index : indexes) {
            indexList.add(index);
        }
    }

    private void insertFace(List<Float> vertices, List<Integer> indices, BlockPosition pos, float[] face) {
        int vertexCount = vertices.size() / 3;

        for (int i = 0; i < face.length; i += 3) {
            float x = face[i]     + pos.getX();
            float y = face[i + 1] + pos.getY();
            float z = face[i + 2] + pos.getZ();
            vertices.add(x);
            vertices.add(y);
            vertices.add(z);
        }

        for (int index : INDICES) {
            indices.add(vertexCount + index);
        }
    }
}