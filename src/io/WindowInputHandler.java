package io;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWWindowSizeCallback;

public class WindowInputHandler implements IInputHandler {
    private Window window;
    private long windowAddress;
    private GLFWWindowSizeCallback windowResizeCallback;

    @Override
    public void createCallbacks() {
        this.windowResizeCallback = new GLFWWindowSizeCallback() {
            @Override
            public void invoke(long window, int width, int height) {
            }
        };
    }

    @Override
    public void bindCallbacks() {
        GLFW.glfwSetWindowSizeCallback(this.windowAddress, this.windowResizeCallback);
    }

    @Override
    public void cleanup() {
        this.windowResizeCallback.free();
    }
}
