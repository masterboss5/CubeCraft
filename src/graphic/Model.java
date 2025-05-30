package graphic;

import org.joml.Vector3f;
import org.lwjgl.opengl.GL46;
import shader.ShaderProgram;

public abstract class Model {
    private int vaoID;
    private int vertices;
    private int indices;
    private Vector3f position = new Vector3f(0, 0, -1);
    private Vector3f rotation = new Vector3f();
    private Vector3f scale = new Vector3f(1);
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

    public Vector3f getPosition() {
        return position;
    }

    public void setPosition(Vector3f position) {
        this.position = position;
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
