package main;

import gl.glDebugger;
import graphic.Camera;
import graphic.Models;
import io.*;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import render.RenderSystem;
import shader.ShaderPrograms;
import world.ChunkPosition;
import world.World;
import world.WorldChunkManager;

public class Main {
    public static Main INSTANCE;
    private Mouse mouse;
    public Window window;
    public static Camera camera;
    private World world;

/*    public static void main(String[] args) {
        WorldChunkManager manager = new WorldChunkManager();
        Vector3f cameraPos = new Vector3f(64, 0, 64);
        ChunkPosition chunk = new ChunkPosition(8, 5);
        System.out.println(manager.isInRange.apply(cameraPos, chunk));
    }*/

    public static void main(String[] args) {
        INSTANCE = new Main();
        INSTANCE.start();
    }

    private void render() {
        this.world.renderWorld();
        this.world.tickWorld();
        GLFW.glfwSwapBuffers(this.window.getPointer());
    }

    private void update() {
        this.window.updateWindow();
        this.camera.tick();
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
        GL11.glClearColor(0.53f, 0.81f, 0.92f, 1.0f);
        GLFW.glfwPollEvents();
    }

    private void initInputs() {
        InputManager.registerMouseInput(new Mouse(this.window));
        InputManager.registerKeyboardInput(new Keyboard(this.window));
        InputManager.registerWindowInput(new WindowInput(this.window));
    }

    private void init() {
        this.window = new Window("CubeCraft3D", 1200, 2000, 0, 0);
        this.window.init();

        GL.createCapabilities();
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        glDebugger._glCullFace();
        glDebugger._glPolygonMode();

        this.initInputs();
        ShaderPrograms.loadShaderPrograms();
        Models.loadModels();
        this.world = new World();
        this.world.loadWorld();
        this.camera = new Camera(this.window);
        RenderSystem.init(this.window, this.camera);

        glDebugger.init();
    }

    private void start() {
        this.init();

        while (!this.window.shouldClose()) {
            this.update();
            this.render();

            if (InputManager.getKeyboard().isKeyDown(GLFW.GLFW_KEY_F11)) {
                this.window.setFullscreen(!this.window.isFullscreen());
                System.out.println("F11");
            };
        }

        this.cleanUp();
    }

    private void cleanUp() {
        InputManager.cleanUp();

        GLFW.glfwWindowShouldClose(this.window.getPointer());
        GLFW.glfwDestroyWindow(this.window.getPointer());
        GLFW.glfwTerminate();
    }
}
