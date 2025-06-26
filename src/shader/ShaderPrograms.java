package shader;

import graphic.Texture;
import registry.Registries;

public class ShaderPrograms {
    public static final ShaderProgram EMPTY_SHADER_PROGRAM = Registries.register(Registries.SHADER_PROGRAM, "empty_program", new EmptyShaderProgram());
    public static final ShaderProgram CUBE_ALL_TEXTURED_SHADER_PROGRAM = Registries.register(Registries.SHADER_PROGRAM, "texture_shader", new TexturedShaderProgram("src/shader/TextureVertexShader.glsl", "src/shader/TextureFragmentShader.glsl", new Texture("C:\\Users\\Armen\\Desktop\\CubeCraft\\resources\\textures\\grass.png")));
    public static final ShaderProgram COLOR_SHADER_PROGRAM = Registries.register(Registries.SHADER_PROGRAM, "color_shader", new StaticShaderProgram("src/shader/VertexShader.glsl", "src/shader/FragmentShader.glsl"));

    private ShaderPrograms() {
        throw new UnsupportedOperationException();
    }

    public static void loadShaderPrograms() {
    }
}
