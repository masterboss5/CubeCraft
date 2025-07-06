package shader;

public class TexturedShaderProgram extends ShaderProgram {
    TexturedShaderProgram(String vertexShaderFilePath, String fragmentShaderFilePath) {
        super(vertexShaderFilePath, fragmentShaderFilePath);
    }

    @Override
    public void tickShaderProgram() {
    }
}
