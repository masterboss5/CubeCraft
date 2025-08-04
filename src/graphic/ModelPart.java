package graphic;

import entity.model.EntityPartNames;
import render.VertexConsumer;

import java.util.ArrayList;
import java.util.List;

public class ModelPart {
    private final EntityPartNames name;
    private double pivotX;
    private double pivotY;
    private double pivotZ;
    private double rotX;
    private double rotY;
    private double rotZ;
    private double scale;
    private final Cuboid cuboid = new Cuboid(2f);

    public ModelPart(EntityPartNames name) {
        this.name = name;
    }

    public <T> void render(VertexConsumer vertexConsumer) {
    }

    public ModelPart move(double x, double y, double z) {
        this.pivotX += x;
        this.pivotY += y;
        this.pivotZ += z;

        return this;
    }

    public ModelPart rotate(double x, double y, double z) {
        this.rotX += x;
        this.rotY += y;
        this.rotZ += z;

        return this;
    }

    public EntityPartNames getName() {
        return name;
    }

    record Cuboid(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
        public Cuboid(double size) {
            this(-size, -size, -size, size, size, size);
        }
    }
}