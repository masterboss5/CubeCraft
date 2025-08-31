package gl;

import org.lwjgl.opengl.GL46;

//TODO complete this
public class ShaderStorageBufferObject {
    private int shaderStorageBindingID = 0;

    public ShaderStorageBufferObject() {
        this.shaderStorageBindingID = GL46.glGenBuffers();
    }

    public int getShaderStorageBindingID() {
        return shaderStorageBindingID;
    }
}
