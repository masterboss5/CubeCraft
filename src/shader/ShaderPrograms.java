package shader;

public class ShaderPrograms {

    public static final ShaderProgram EMPTY_SHADER_PROGRAM = new EmptyShaderProgram();
    public static final ShaderProgram COLOR_SHADER_PROGRAM = new StaticShaderProgram("src/shader/VertexShader.glsl", "src/shader/FragmentShader.glsl");

    private ShaderPrograms() {
        throw new UnsupportedOperationException();
    }
}
