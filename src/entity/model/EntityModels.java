package entity.model;

public class EntityModels {
    private EntityModels() {
        throw new UnsupportedOperationException();
    }

    public static final CubeEntityModel CUBE_ENTITY_MODEL = new CubeEntityModel();
    public static final PlayerEntityModel PLAYER_ENTITY_MODEL = new PlayerEntityModel();

    public static void loadEntityModels() {
    }
}
