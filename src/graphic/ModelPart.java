package graphic;

import entity.Entity;
import entity.model.EntityPartName;
import gl.VertexBuffer;
import gl.glBufferUsage;
import org.joml.Matrix4f;

import java.util.Objects;

public class ModelPart {
    private final EntityPartName name;
    private float pivotX;
    private float pivotY;
    private float pivotZ;
    private float rotX;
    private float rotY;
    private float rotZ;
    private float scaleX = 1f;
    private float scaleY = 1f;
    private float scaleZ = 1f;
    private final Cuboid cuboid = new Cuboid(1f);
    private final ModelPartTexture textureData;
    public static final VertexBuffer UNIFORM_CUBE_VERTEX_BUFFER;

    static {
        UNIFORM_CUBE_VERTEX_BUFFER = new VertexBuffer(glBufferUsage.GL_STATIC_DRAW)
                .vertexes(new float[]
                        {
                                // TOP (+Y)
                                -0.5f, 0.5f, -0.5f,
                                -0.5f, 0.5f, 0.5f,
                                0.5f, 0.5f, 0.5f,
                                0.5f, 0.5f, -0.5f,

                                // BOTTOM (−Y)
                                -0.5f, -0.5f, 0.5f,
                                -0.5f, -0.5f, -0.5f,
                                0.5f, -0.5f, -0.5f,
                                0.5f, -0.5f, 0.5f,

                                // FRONT (+Z)
                                -0.5f, 0.5f, 0.5f,
                                -0.5f, -0.5f, 0.5f,
                                0.5f, -0.5f, 0.5f,
                                0.5f, 0.5f, 0.5f,

                                // BACK (−Z)
                                0.5f, 0.5f, -0.5f,
                                0.5f, -0.5f, -0.5f,
                                -0.5f, -0.5f, -0.5f,
                                -0.5f, 0.5f, -0.5f,

                                // LEFT (−X)
                                -0.5f, 0.5f, -0.5f,
                                -0.5f, -0.5f, -0.5f,
                                -0.5f, -0.5f, 0.5f,
                                -0.5f, 0.5f, 0.5f,

                                // RIGHT (+X)
                                0.5f, 0.5f, 0.5f,
                                0.5f, -0.5f, 0.5f,
                                0.5f, -0.5f, -0.5f,
                                0.5f, 0.5f, -0.5f
                        }).indices(new int[]{
                        0, 1, 2, 2, 3, 0,
                        4, 5, 6, 6, 7, 4,
                        8, 9, 10, 10, 11, 8,
                        12, 13, 14, 14, 15, 12,
                        16, 17, 18, 18, 19, 16,
                        20, 21, 22, 22, 23, 20
                });

        UNIFORM_CUBE_VERTEX_BUFFER.build();
        UNIFORM_CUBE_VERTEX_BUFFER.createNewVertexBufferObject(new float[] {
                // Top
                0f, 0f,  0f, 1f,  1f, 1f,  1f, 0f,
                // Bottom
                0f, 0f,  0f, 1f,  1f, 1f,  1f, 0f,
                // Front
                0f, 0f,  0f, 1f,  1f, 1f,  1f, 0f,
                // Back
                0f, 0f,  0f, 1f,  1f, 1f,  1f, 0f,
                // Left
                0f, 0f,  0f, 1f,  1f, 1f,  1f, 0f,
                // Right
                0f, 0f,  0f, 1f,  1f, 1f,  1f, 0f
        }, (byte) 2, false, glBufferUsage.GL_STATIC_DRAW);
    }

    public ModelPart(EntityPartName name, ModelPartTexture textureData) {
        this.name = name;
        this.textureData = textureData;
    }

    public ModelPart pivot(float x, float y, float z) {
        this.pivotX += x;
        this.pivotY += y;
        this.pivotZ += z;

        return this;
    }

    public ModelPart scale(float scaleX, float scaleY, float scaleZ) {
        this.scaleX = scaleX;
        this.scaleY = scaleY;
        this.scaleZ = scaleZ;

        return this;
    }

    public ModelPart rotate(float x, float y, float z) {
        this.rotX += x;
        this.rotY += y;
        this.rotZ += z;

        return this;
    }

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

    public ModelPartTexture getTextureData() {
        return textureData;
    }

    public Matrix4f getModelTransformationMatrix4f(Entity entity) {
        return new Matrix4f().identity()

                // 1. Translate to entity center
                .translate((float) entity.getX(), (float) entity.getY(), (float) entity.getZ())

                // 2. Rotate around entity center (orbit effect)
                .rotateY((float) -entity.getRotY())

                // 3. Offset part outward from center (e.g. arm to the side)
                .translate(this.pivotX, this.pivotY, this.pivotZ)

                // 4. Apply part-local rotation
                .rotateX((float) Math.toRadians(this.rotX))
                .rotateY((float) Math.toRadians(this.rotY))
                .rotateZ((float) Math.toRadians(this.rotZ))

                // 5. Apply part-local scale
                .scale(this.scaleX, this.scaleY, this.scaleZ);
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