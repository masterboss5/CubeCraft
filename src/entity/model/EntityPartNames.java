package entity.model;

public enum EntityPartNames {
    ROOT("root"),
    HEAD("head"),
    BODY("body"),
    LEFT_ARM("left_arm"),
    RIGHT_ARM("right_arm"),
    LEFT_LEG("left_leg"),
    RIGHT_LEG("right_leg");

    private final String name;

    EntityPartNames(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
