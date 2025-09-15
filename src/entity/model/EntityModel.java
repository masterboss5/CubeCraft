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

    private static double roundToThousandth(double value) {
        return Math.round(value * 1000.0) / 1000.0;
    }

    public void setEntityBoundingBox(Box box) {
        double minX = Double.POSITIVE_INFINITY;
        double minY = Double.POSITIVE_INFINITY;
        double minZ = Double.POSITIVE_INFINITY;
        double maxX = Double.NEGATIVE_INFINITY;
        double maxY = Double.NEGATIVE_INFINITY;
        double maxZ = Double.NEGATIVE_INFINITY;

        for (ModelPart part : this.getModelParts()) {
            double halfScaleX = part.getScaleX() / 2.0;
            double halfScaleY = part.getScaleY() / 2.0;
            double halfScaleZ = part.getScaleZ() / 2.0;

            double localMinX = part.getPivotX() - halfScaleX;
            double localMinY = part.getPivotY() - halfScaleY;
            double localMinZ = part.getPivotZ() - halfScaleZ;

            double localMaxX = part.getPivotX() + halfScaleX;
            double localMaxY = part.getPivotY() + halfScaleY;
            double localMaxZ = part.getPivotZ() + halfScaleZ;

            minX = Math.min(minX, roundToThousandth(localMinX));
            minY = Math.min(minY, roundToThousandth(localMinY));
            minZ = Math.min(minZ, roundToThousandth(localMinZ));
            maxX = Math.max(maxX, roundToThousandth(localMaxX));
            maxY = Math.max(maxY, roundToThousandth(localMaxY));
            maxZ = Math.max(maxZ, roundToThousandth(localMaxZ));
        }

        box.setPosition(minX, minY, minZ, maxX, maxY, maxZ);
    }

    public abstract void appendRootPart();

    public abstract void appendModelParts();
}
