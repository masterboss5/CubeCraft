package main;

import block.*;
import entity.model.EntityModels;
import entity.render.EntityRenderers;
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
import texture.BindlessTextureManager;
import texture.Textures;
import util.collection.PaletteContainer;
import world.Chunk;
import world.World;

import java.util.Arrays;

public class Main {
    public static BindlessTextureManager TEXTURE_MANAGER;
    public static Main INSTANCE;
    private Mouse mouse;
    public Window window;
    public static Camera camera;
    private World world;

    public static void main(String[] args) {
        INSTANCE = new Main();
        INSTANCE.start();

        PaletteContainer<Block> blockPaletteContainer = new PaletteContainer<>(Chunk.CHUNK_WIDTH * Chunk.CHUNK_WIDTH * Chunk.CHUNK_HEIGHT);


        /*blockPaletteContainer.set(0, Blocks.COBBLESTONE_BLOCK);
        System.out.println(blockPaletteContainer.storage.toString());
        System.out.println(blockPaletteContainer.palette.toString());
        System.out.println(blockPaletteContainer.get(0));

        blockPaletteContainer.set(1, Blocks.OAK_PLANKS_BLOCK);
        System.out.println(blockPaletteContainer.get(1));

        blockPaletteContainer.set(2, Blocks.GRASS_BLOCK);
        blockPaletteContainer.set(3, Blocks.GRASS_BLOCK);
        blockPaletteContainer.set(4, Blocks.GRASS_BLOCK);

        System.out.println(Arrays.toString(blockPaletteContainer.storage.getUnpackedData()));*/


    }

    private void render() {
        camera.tick();
        this.world.tickWorld();
        this.world.renderWorld();
        GLFW.glfwSwapBuffers(this.window.getPointer());
    }

    private void update() {
        this.window.updateWindow();
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
        GL11.glClearColor(0.53f, 0.81f, 0.92f, 1.0f);
        GLFW.glfwPollEvents();
        this.world.setBlock(new BlockPosition(-1, 7, 0), Blocks.DIRT_BLOCK);
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
        Blocks.loadBlocks();
        TextureArrays.loadTextureArrays();
        TEXTURE_MANAGER = new BindlessTextureManager("Main Texture Manager");
        EntityRenderers.loadEntityRenderers();
        this.world = new World();
        this.world.loadWorld();
        camera = new Camera(this.window);
        RenderSystem.init(this.window, camera);
        EntityModels.loadEntityModels();

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
