package entity;

import entity.model.EntityModel;
import entity.model.EntityModels;
import world.World;

import java.util.Objects;

public class EntityType<T extends Entity> {
    private final String name;
    private final EntityFactory<T> factory;
    private final EntityModel<?> model;

    public static final EntityType<CubeEntity> CUBE_ENTITY = Builder.builder(CubeEntity::new).name("cube").model(EntityModels.CUBE_ENTITY_MODEL).build();
    public static final EntityType<PlayerEntity> PLAYER_ENTITY = Builder.builder(PlayerEntity::new).name("player").model(EntityModels.PLAYER_ENTITY_MODEL).build();

    public EntityType(String name, EntityFactory<T> factory, EntityModel<?> model) {
        this.name = name;
        this.factory = factory;
        this.model = model;
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

    public String getName() {
        return name;
    }

    public EntityFactory<T> getFactory() {
        return factory;
    }

    public EntityModel<?> getModel() {
        return model;
    }

    private static class Builder<T extends Entity> {
        private String name;
        private final EntityFactory<T> factory;
        private EntityModel<?> model;

        private Builder(EntityFactory<T> factory) {
            this.factory = factory;
        }

        private static <T extends Entity> Builder<T> builder(EntityFactory<T> factory) {
            return new Builder<>(factory);
        }

        public Builder<T> name(String name) {
            this.name = name;

            return this;
        }

        public Builder<T> model(EntityModel<T> model) {
            this.model = model;

            return this;
        }

        private EntityType<T> build() {
            return new EntityType<>(this.name, this.factory, this.model);
        }
    }
}
