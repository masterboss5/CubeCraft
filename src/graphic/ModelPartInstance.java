package graphic;

import entity.Entity;

public record ModelPartInstance(
        ModelPart modelPart,
        Entity entity
) {}
