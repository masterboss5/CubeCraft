package gl;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL46;
import org.lwjgl.opengl.GLDebugMessageCallback;
import org.lwjgl.opengl.GLDebugMessageCallbackI;

public class glDebugger {
    private static GLDebugMessageCallbackI debugCallback;

    public static void setDebugCallback(GLDebugMessageCallbackI _debugCallback) {
        debugCallback = _debugCallback;
    }

    public static void init() {
        GL46.glEnable(GL46.GL_DEBUG_OUTPUT);
        GLFW.glfwWindowHint(GLFW.GLFW_OPENGL_DEBUG_CONTEXT, 1);
        GL46.glDebugMessageCallback(((source, type, id, severity, length, message, userParam) -> {
            System.err.println("GL DEBUG: " + GLDebugMessageCallback.getMessage(length, message));
        }), 0);
    }
}
