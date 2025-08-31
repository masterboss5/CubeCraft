package shader;

import org.lwjgl.opengl.GL20;

import java.util.Random;

public class StaticShaderProgram extends ShaderProgram {
    public StaticShaderProgram(String vertexShaderFilePath, String fragmentShaderFilePath) {
        super(vertexShaderFilePath, fragmentShaderFilePath);
    }
}
