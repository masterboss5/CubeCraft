package entity.render;

import entity.CubeEntity;
import entity.Entity;
import entity.model.CubeEntityModel;
import gl.VertexBuffer;
import gl.glBufferUsage;
import graphic.ModelPart;
import render.RenderSystem;
import util.ArrayUtils;

import java.util.ArrayList;
import java.util.List;

public class CubeEntityRenderer extends EntityRenderer<CubeEntity> {
    protected CubeEntityRenderer() {
        super(new CubeEntityModel());
    }
}
