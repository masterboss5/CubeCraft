package graphic;

import gl.VertexBuffer;
import org.joml.Vector3f;
import org.lwjgl.opengl.GL46;
import shader.ShaderProgram;

public abstract class Model {
    private final VertexBuffer vertexBuffer;
    private Vector3f rotation = new Vector3f();
    private Vector3f scale = new Vector3f(1F);
    private final ShaderProgram shaderProgram;

    protected Model(ShaderProgram shaderProgram, VertexBuffer vertexBuffer) {
        this.shaderProgram = shaderProgram;
        this.vertexBuffer = vertexBuffer;
    }

    public void startShader() {
        GL46.glUseProgram(this.shaderProgram.getProgramID());
    }

    public void stopShader() {
        GL46.glUseProgram(0);
    }

    public void tickShaderProgram() {
        this.shaderProgram.tickShaderProgram();
    }

    public Vector3f getRotation() {
        return rotation;
    }

    public void setRotation(Vector3f rotation) {
        this.rotation = rotation;
    }

    public Vector3f getScale() {
        return scale;
    }

    public void setScale(Vector3f scale) {
        this.scale = scale;
    }

    public ShaderProgram getShaderProgram() {
        return shaderProgram;
    }

    public VertexBuffer getVertexBuffer() {
        return vertexBuffer;
    }

    public int getVerticesCount() {
        return this.vertexBuffer.getVerticesCount();
    }

    public int getIndicesCount() {
        return this.vertexBuffer.getIndicesCount();
    }

    public Float[] getVertices() {
        return this.vertexBuffer.getVertices();
    }

    public int[] getIndices() {
        return this.vertexBuffer.getIndices();
    }
}
