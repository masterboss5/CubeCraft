package shader;

import graphic.Texture;

public class ShaderPrograms {

    public static final ShaderProgram EMPTY_SHADER_PROGRAM = new EmptyShaderProgram();
    public static final ShaderProgram COLOR_SHADER_PROGRAM = new StaticShaderProgram("src/shader/VertexShader.glsl", "src/shader/FragmentShader.glsl");
    public static final TexturedShaderProgram CUBE_ALL_TEXTURED_SHADER_PROGRAM = new TexturedShaderProgram("src/shader/TextureVertexShader.glsl", "src/shader/TextureFragmentShader.glsl", new Texture("C:\\Users\\Armen\\Desktop\\CubeCraft\\resources\\textures\\grass.png"));

    private ShaderPrograms() {
        throw new UnsupportedOperationException();
    }
}
