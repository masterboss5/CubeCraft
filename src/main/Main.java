package main;

import block.*;
import gl.glDebugger;
import graphic.Camera;
import graphic.Models;
import io.*;
import json.TextureArrays;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import render.RenderSystem;
import shader.ShaderPrograms;
import texture.Textures;
import util.collection.PaletteContainer;
import world.Chunk;
import world.World;

import java.util.Arrays;

public class Main {
    public static Main INSTANCE;
    private Mouse mouse;
    public Window window;
    public static Camera camera;
    private World world;

    public static void main(String[] args) {
        INSTANCE = new Main();
        INSTANCE.start();

        PaletteContainer<Block> blockPaletteContainer = new PaletteContainer<>(Chunk.CHUNK_WIDTH * Chunk.CHUNK_WIDTH * Chunk.CHUNK_HEIGHT);


        blockPaletteContainer.set(0, new CobblestoneBlock());
/*        System.out.println(blockPaletteContainer.storage.toString());
        System.out.println(blockPaletteContainer.palette.toString());*/
        System.out.println(blockPaletteContainer.get(0));

        blockPaletteContainer.set(1, new OakPlanksBlock());
        System.out.println(blockPaletteContainer.get(1));

        blockPaletteContainer.set(2, new GrassBlock());
        blockPaletteContainer.set(3, new GrassBlock());
        blockPaletteContainer.set(4, new GrassBlock());

        System.out.println(Arrays.toString(blockPaletteContainer.storage.getUnpackedData()));


    }

    private void render() {
        this.world.renderWorld();
        this.world.tickWorld();
        GLFW.glfwSwapBuffers(this.window.getPointer());
    }

    private void update() {
        this.window.updateWindow();
        camera.tick();
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
        GL11.glClearColor(0.53f, 0.81f, 0.92f, 1.0f);
        GLFW.glfwPollEvents();
        this.world.setBlock(new BlockPosition(-1, 7, 0), new DirtBlock());
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
        Textures.loadTextures();
        Models.loadModels();
        TextureArrays.loadTextureArrays();
        this.world = new World();
        this.world.loadWorld();
        camera = new Camera(this.window);
        RenderSystem.init(this.window, camera);

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
            }
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
