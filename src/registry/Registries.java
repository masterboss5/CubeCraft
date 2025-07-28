package registry;

import block.Block;
import exception.RegistriesFrozenException;
import graphic.Model;
import graphic.Models;
import shader.EmptyShaderProgram;
import shader.ShaderProgram;
import texture.Texture;

import java.util.function.Supplier;

public class Registries {
    private static boolean FROZEN = false;
    public static final RootRegistry ROOT = new RootRegistry();
    public static final SimpleRegistry<Block> BLOCK = createSimple("block");
    public static final DefaultedRegistry<Model> MODEL = createDefaulted("model", () -> Models.AIR_BLOCK_MODEL);
    public static final DefaultedRegistry<ShaderProgram> SHADER_PROGRAM = createDefaulted("shader_program", EmptyShaderProgram::new);
    public static final SimpleRegistry<Texture> TEXTURES = createSimple("textures");

    public static <R, T extends R> R register(Registry<R> registry, String key, T entry) {
        return registry.register(key, entry);
    }

    public static <R, T extends R> R register(Registry<R> registry, RegistryKey<R> registryKey, T entry) {
        return register(registry, registryKey.getKey(), entry);
    }

    private static <T> SimpleRegistry<T> createSimple(String name) {
        assertNotFrozen();
        return ROOT.register(new SimpleRegistry<>(), name);
    }

    private static <T> DefaultedRegistry<T> createDefaulted(String name, Supplier<T> defaultSupplier) {
        assertNotFrozen();
        return ROOT.register(new DefaultedRegistry<>(defaultSupplier), name);
    }

    public static void freezeRegistries() {
        FROZEN = true;
    }

    private static void assertNotFrozen() {
        if (FROZEN) throw new RegistriesFrozenException();
    }
}
