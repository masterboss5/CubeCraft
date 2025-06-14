package render;

import graphic.BasicModel;
import graphic.BlockModel;
import graphic.Models;
import graphic.UVCoordinates;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL45;
import org.lwjgl.opengl.GL46;
import shader.EmptyShaderProgram;
import shader.ShaderProgram;
import shader.ShaderPrograms;
import shader.TexturedShaderProgram;

public class ModelBuilder {

    private ModelBuilder() {
        throw new UnsupportedOperationException();
    }

    public static BasicModel buildBasicModel(ShaderProgram shaderProgram, float[] positions, int[] indices) {
        int vaoID = createVAO();
        storeDataInAttributeList(0, positions, 3);
        storeDataInIndicesBuffer(indices);
        unbindVAO();

        return new BasicModel(shaderProgram, vaoID, positions.length, indices.length);
    }

    public static BlockModel buildBlockModel(TexturedShaderProgram shaderProgram, float[] positions, int[] indices, UVCoordinates uvCoordinates) {
        int vaoID = createVAO();
        storeDataInAttributeList(0, positions, 3);
        storeDataInAttributeList(1, Models.UV, 2);
        storeDataInIndicesBuffer(indices);
        unbindVAO();

        return new BlockModel(shaderProgram, vaoID, positions.length, indices.length);
    }

    private static void storeDataInIndicesBuffer(int[] indices) {
        int vboID = GL46.glGenBuffers();
        GL46.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vboID);
        GL46.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, indices, GL15.GL_STATIC_DRAW);
    }

    private static void storeDataInAttributeList(int attributeID, float[] values, int size) {
        int vboID = GL46.glGenBuffers();
        GL46.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboID);
        GL46.glBufferData(GL15.GL_ARRAY_BUFFER, values, GL46.GL_STATIC_DRAW);
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
