package shader;

import registry.Registries;
//TODO rename some shaders
public class ShaderPrograms {
    public static final ShaderProgram EMPTY_SHADER_PROGRAM = Registries.register(Registries.SHADER_PROGRAM, "empty_shader", new EmptyShaderProgram());
    public static final ShaderProgram CUBE_ALL_TEXTURED_SHADER_PROGRAM = Registries.register(Registries.SHADER_PROGRAM, "texture_shader", new TexturedShaderProgram("src/shader/TextureVertexShader.glsl", "src/shader/TextureFragmentShader.glsl"));
    public static final ShaderProgram ENTITY_SHADER_PROGRAM = Registries.register(Registries.SHADER_PROGRAM, "entity_shader", new EntityShaderProgram("src/shader/EntityVertexShader.glsl", "src/shader/EntityFragmentShader.glsl"));
    public static final ShaderProgram COLOR_SHADER_PROGRAM = Registries.register(Registries.SHADER_PROGRAM, "color_shader", new StaticShaderProgram("src/shader/VertexShader.glsl", "src/shader/FragmentShader.glsl"));

    private ShaderPrograms() {
        throw new UnsupportedOperationException();
    }

    public static void loadShaderPrograms() {
    }
}
