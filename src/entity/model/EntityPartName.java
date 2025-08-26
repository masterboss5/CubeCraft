package entity.model;

public enum EntityPartName {
    ROOT("root"),
    HEAD("head"),
    BODY("body"),
    LEFT_ARM("left_arm"),
    RIGHT_ARM("right_arm"),
    LEFT_LEG("left_leg"),
    RIGHT_LEG("right_leg");

    private final String name;

    EntityPartName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
