package entity.model;

import entity.Entity;
import graphic.ModelPart;
import graphic.ModelPartInstance;
import util.Box;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public abstract class EntityModel<T extends Entity> {
    private ModelPart root;
    private final List<ModelPart> parts = new ArrayList<>();
    private final Map<EntityPartName, ModelPart> partMap = new EnumMap<>(EntityPartName.class);

    public EntityModel() {
        this.appendRootPart();
        this.appendModelParts();
    }

    public final void appendModelPart(ModelPart part) {
        this.parts.add(part);
        this.partMap.put(part.getName(), part);

        if (part.getName() == EntityPartName.ROOT) {
            this.root = part;
        }
    }

    @Override
    public String toString() {
        return "EntityModel{" +
                "root=" + this.root +
                ", parts=" + this.parts +
                ", partMap=" + this.partMap +
                '}';
    }

    public ModelPart getRoot() {
        return root;
    }

    public List<ModelPart> getModelParts() {
        return parts;
    }

    public List<ModelPartInstance> getPartInstances(Entity entity) {
        List<ModelPartInstance> instances = new ArrayList<>();
        parts.forEach((part) -> {
            instances.add(new ModelPartInstance(part, entity));
        });

        return instances;
    }

    public Box getEntityBoundingBox() {
        double minX = Float.POSITIVE_INFINITY, minY = Float.POSITIVE_INFINITY, minZ = Float.POSITIVE_INFINITY;
        double maxX = Float.NEGATIVE_INFINITY, maxY = Float.NEGATIVE_INFINITY, maxZ = Float.NEGATIVE_INFINITY;

        for (ModelPart part : this.getModelParts()) {
            double localMinX = part.getPivotX();
            double localMinY = part.getPivotY();
            double localMinZ = part.getPivotZ();
            double localMaxX = part.getPivotX() + part.getScaleX();
            double localMaxY = part.getPivotY() + part.getScaleY();
            double localMaxZ = part.getPivotZ() + part.getScaleZ();

            minX = Math.min(minX, localMinX);
            minY = Math.min(minY, localMinY);
            minZ = Math.min(minZ, localMinZ);
            maxX = Math.max(maxX, localMaxX);
            maxY = Math.max(maxY, localMaxY);
            maxZ = Math.max(maxZ, localMaxZ);
        }

        return new Box(minX, minY, minZ, maxX, maxY, maxZ);
    }

    public abstract void appendRootPart();

    public abstract void appendModelParts();
}
