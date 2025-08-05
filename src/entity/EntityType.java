package entity;

import world.World;

import java.util.Objects;

public class EntityType<T extends Entity> {
    private final String name;
    private final EntityFactory<T> factory;

    public static final EntityType<CubeEntity> CUBE_ENTITY = Builder.builder(CubeEntity::new).name("cube").build();

    public EntityType(String name, EntityFactory<T> factory) {
        this.name = name;
        this.factory = factory;
    }

    public T create(double x, double y, double z, World world) {
        return this.factory.create(x, y, z, world);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || this.getClass() != o.getClass()) return false;
        EntityType<?> that = (EntityType<?>) o;
        return Objects.equals(this.name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    private static class Builder<T extends Entity> {
        private String name;
        private final EntityFactory<T> factory;

        private Builder(EntityFactory<T> factory) {
            this.factory = factory;
        }

        private static <T extends Entity> Builder<T> builder(EntityFactory<T> factory) {
            return new Builder<>(factory);
        }

        private Builder<T> name(String name) {
            this.name = name;

            return this;
        }

        private EntityType<T> build() {
            return new EntityType<>(this.name, this.factory);
        }
    }
}
