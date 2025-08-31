package entity.model;

import entity.Entity;
import graphic.ModelPart;
import graphic.ModelPartInstance;

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

    public List<ModelPart> getParts() {
        return parts;
    }

    public List<ModelPartInstance> getPartInstances(Entity entity) {
        List<ModelPartInstance> instances = new ArrayList<>();
        parts.forEach((part) -> {
            instances.add(new ModelPartInstance(part, entity));
        });

        return instances;
    }

    public abstract void appendRootPart();

    public abstract void appendModelParts();
}
