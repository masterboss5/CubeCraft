package graphic;

import entity.model.EntityPartName;
import org.joml.Matrix4f;
import util.MathUtils;

import java.util.Objects;

public class ModelPart {
    private final EntityPartName name;
    private float pivotX;
    private float pivotY;
    private float pivotZ;
    private float rotX;
    private float rotY;
    private float rotZ;
    private float scale;
    private final Cuboid cuboid = new Cuboid(1f);
    private final ModelPartTextureData textureData;

    public ModelPart(EntityPartName name, ModelPartTextureData textureData) {
        this.name = name;
        this.textureData = textureData;
    }

    public ModelPart pivot(float x, float y, float z) {
        this.pivotX += x;
        this.pivotY += y;
        this.pivotZ += z;

        return this;
    }

    public ModelPart size(float sizeX, float sizeY, float sizeZ) {
        this.cuboid.sizeX = sizeX;
        this.cuboid.sizeY = sizeY;
        this.cuboid.sizeZ = sizeZ;

        return this;
    }

    public ModelPart size(float size) {
        this.cuboid.sizeX = size;
        this.cuboid.sizeY = size;
        this.cuboid.sizeZ = size;

        return this;
    }

/*    public ModelPart rotate(double x, double y, double z) {
        this.rotX += x;
        this.rotY += y;
        this.rotZ += z;

        return this;
    }*/

    public double getPivotX() {
        return pivotX;
    }

    public double getPivotY() {
        return pivotY;
    }

    public double getPivotZ() {
        return pivotZ;
    }

    public double getRotX() {
        return rotX;
    }

    public double getRotY() {
        return rotY;
    }

    public double getRotZ() {
        return rotZ;
    }

    public EntityPartName getName() {
        return name;
    }

    public Cuboid getCuboid() {
        return cuboid;
    }

    public ModelPartTextureData getTextureData() {
        return textureData;
    }

    public Matrix4f getModelTransformationMatrix4f() {
        return new Matrix4f().identity()
                .translate(this.pivotX, this.pivotY, this.pivotZ)
                .rotate((float) Math.toRadians(this.rotX), 1, 0, 0)
                .rotate((float) Math.toRadians(this.rotY), 0, 1, 0)
                .rotate((float) Math.toRadians(this.rotZ), 0, 0, 1)
                .scale(this.scale);
    }

    @Override
    public boolean equals(Object other) {
        if (other == null || getClass() != other.getClass()) return false;
        ModelPart part = (ModelPart) other;
        return Objects.equals(this.getCuboid(), part.getCuboid());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.getCuboid());
    }

    public class Cuboid {
        private float sizeX;
        private float sizeY;
        private float sizeZ;

        public Cuboid(float size) {
            this(size, size, size);
        }

        public Cuboid(float sizeX, float sizeY, float sizeZ) {
            this.sizeX = sizeX;
            this.sizeY = sizeY;
            this.sizeZ = sizeZ;
        }

        public float getSizeX() {
            return sizeX;
        }

        public float getSizeY() {
            return sizeY;
        }

        public float getSizeZ() {
            return sizeZ;
        }

        @Override
        public boolean equals(Object other) {
            if (other == null || getClass() != other.getClass()) return false;
            Cuboid cuboid = (Cuboid) other;
            return Float.compare(this.getSizeX(), cuboid.getSizeX()) == 0 && Float.compare(this.getSizeY(), cuboid.getSizeY()) == 0 && Float.compare(this.getSizeZ(), cuboid.getSizeZ()) == 0;
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.getSizeX(), this.getSizeY(), this.getSizeZ());
        }
    }
}