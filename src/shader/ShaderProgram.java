package shader;

import org.lwjgl.opengl.GL46;

public abstract class ShaderProgram implements glUniformManager, ShaderLoader {
    private int programID;
    private int vertexShaderID;
    private int fragmentShaderID;

    protected ShaderProgram() {
        this.programID = 0;
        this.vertexShaderID = 0;
        this.fragmentShaderID = 0;
    }

    protected ShaderProgram(String vertexShaderFilePath, String fragmentShaderFilePath) {
        this.vertexShaderID = this.loadShader(vertexShaderFilePath, GL46.GL_VERTEX_SHADER);
        this.fragmentShaderID = this.loadShader(fragmentShaderFilePath, GL46.GL_FRAGMENT_SHADER);
        this.programID = GL46.glCreateProgram();
        GL46.glAttachShader(this.programID, this.vertexShaderID);
        GL46.glAttachShader(this.programID, this.fragmentShaderID);
        GL46.glLinkProgram(this.programID);
    }

    @Override
    public int getProgramID() {
        return this.programID;
    }
}
