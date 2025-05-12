package main;

import graphic.BasicModel;
import io.InputHandler;
import io.Window;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import render.ModelBuilder;
import render.RenderSystem;
import shader.ShaderPrograms;

public class Main {
    public static Main INSTANCE;
    private InputHandler inputHandler;
    public Window window;

    BasicModel testModel;

    float[] vertices = {
            -0.5f, 0.5f, 0f,
            -0.5f, -0.5f, 0f,
            0.5f, -0.5f, 0f,
            0.5f, 0.5f, 0f,
    };

    int[] indices = {
            0, 1, 3,
            3, 1, 2
    };

    public static void main(String[] args) {
        INSTANCE = new Main();
        INSTANCE.start();
    }

    private void render() {
        RenderSystem.render(this.testModel);
        GLFW.glfwSwapBuffers(this.window.getWindowAddress());
    }

    private void update() {
        this.window.updateWindow();
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
        GL11.glClearColor(1, 0, 0, 1F);
        GLFW.glfwPollEvents();
    }

    private void init() {
        this.window = new Window("CubeCraft3D", 600, 800, 0, 0);
        this.window.init();
        GL.createCapabilities();
        GL11.glEnable(GL11.GL_DEPTH_TEST);

        this.inputHandler = new InputHandler(this.window);
        this.testModel = ModelBuilder.buildBasicModel(ShaderPrograms.COLOR_SHADER_PROGRAM, this.vertices, this.indices);
    }

    private void start() {
        this.init();

        while (!this.window.shouldClose()) {
            this.update();
            this.render();

            if (this.inputHandler.isKeyDown(GLFW.GLFW_KEY_F11)) {
                this.window.setFullscreen(!this.window.isFullscreen());
                System.out.println("F11");
            };
        }

        this.cleanUp();
    }

    private void cleanUp() {
        this.inputHandler.cleanUp();

        GLFW.glfwWindowShouldClose(this.window.getWindowAddress());
        GLFW.glfwDestroyWindow(this.window.getWindowAddress());
        GLFW.glfwTerminate();
    }
}
