package shader;

import org.lwjgl.opengl.GL20;

import java.util.Random;

public class StaticShaderProgram extends ShaderProgram {
    public StaticShaderProgram(String vertexShaderFilePath, String fragmentShaderFilePath) {
        super(vertexShaderFilePath, fragmentShaderFilePath);
    }

    @Override
    public void tickShaderProgram() {
        GL20.glUniform3f(getLocation("colorValue"), new Random().nextFloat(0, 1), new Random().nextFloat(0, 1), new Random().nextFloat(0, 1));
    }
}
