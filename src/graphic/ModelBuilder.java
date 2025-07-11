package graphic;

import gl.VertexBuffer;
import gl.glBufferUsage;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL46;
import shader.ShaderProgram;
import texture.BlockTextureMap;
import texture.BlockUVMap;

public class ModelBuilder {

    private ModelBuilder() {
        throw new UnsupportedOperationException();
    }

/*    public static BlockModel buildBlockModel(ShaderProgram shaderProgram, float[] vertices, int[] indices, float[] uvCoordinates, Texture texture) {
        VertexBuffer vertexBuffer = new VertexBuffer(glUsage.GL_STATIC_DRAW).vertexes(vertices).indices(indices);
        vertexBuffer.build();

        vertexBuffer.createNewVertexBufferObject(uvCoordinates, (byte) 2, false, glUsage.GL_STATIC_DRAW);

        return new BlockModel(shaderProgram, vertexBuffer, texture);
    }*/

    public static BlockModel buildBlockModel(ShaderProgram shaderProgram, float[] vertices, int[] indices, float[] uvCoordinates, BlockTextureMap blockTextureMap) {
        VertexBuffer vertexBuffer = new VertexBuffer(glBufferUsage.GL_STATIC_DRAW).vertexes(vertices).indices(indices);
        vertexBuffer.build();

        vertexBuffer.createNewVertexBufferObject(uvCoordinates, (byte) 2, false, glBufferUsage.GL_STATIC_DRAW);
        vertexBuffer.createNewVertexBufferObject(blockTextureMap.toVertexBuffer(), (byte) 1, false, glBufferUsage.GL_STATIC_DRAW);

        return new BlockModel(shaderProgram, vertexBuffer, blockTextureMap, BlockFaceMap.create(vertices), BlockUVMap.fromPacked(uvCoordinates));
    }

    private static void storeDataInIndicesBuffer(int[] indices) {
        int vboID = GL46.glGenBuffers();
        GL46.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vboID);
        GL46.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, indices, GL15.GL_STATIC_DRAW);
    }

    private static void storeDataInAttributeList(int attributeID, float[] values, int size) {
        int vboID = GL46.glGenBuffers();
        GL46.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboID);
        GL46.glBufferData(GL15.GL_ARRAY_BUFFER, values, GL15.GL_STATIC_DRAW);
        GL46.glVertexAttribPointer(attributeID, size, GL46.GL_FLOAT, false, 0, 0);
        GL46.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
    }

    private static int createVAO() {
        int vaoID = GL46.glGenVertexArrays();
        GL46.glBindVertexArray(vaoID);

        return vaoID;
    }

    private static void unbindVAO() {
        GL46.glBindVertexArray(0);
    }
}
