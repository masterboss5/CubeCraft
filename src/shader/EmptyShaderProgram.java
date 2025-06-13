package shader;

public class EmptyShaderProgram extends ShaderProgram {
    @Override
    public void tickShaderProgram() {
    }

    @Override
    public int getProgramID() {
        return 0;
    }
}
