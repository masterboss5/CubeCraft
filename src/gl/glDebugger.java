package gl;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;
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

    public static void _glPolygonMode() {
//        GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_LINE);
    }

    public static void _glCullFace() {
        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glCullFace(GL11.GL_BACK);
//        GL11.glCullFace(GL11.GL_FRONT_AND_BACK);
        GL11.glFrontFace(GL11.GL_CCW); // Counter-clockwise is default; use GL_CW for clockwise
    }
}
