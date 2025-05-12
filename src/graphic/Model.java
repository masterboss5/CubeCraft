package graphic;

import org.lwjgl.opengl.GL46;
import shader.ShaderProgram;

public abstract class Model {
    private int vaoID;
    private int vertices;
    private int indices;
    private ShaderProgram shaderProgram;

    protected Model(ShaderProgram shaderProgram, int vaoID, int vertices, int indices) {
        this.shaderProgram = shaderProgram;
        this.vaoID = vaoID;
        this.vertices = vertices;
        this.indices = indices;
    }

    public void startShader() {
        GL46.glUseProgram(shaderProgram.getProgramID());
    }

    public void stopShader() {
        GL46.glUseProgram(0);
    }

    public int getVaoID() {
        return vaoID;
    }

    public int getVertices() {
        return vertices;
    }

    public int getIndices() {
        return indices;
    }
}
