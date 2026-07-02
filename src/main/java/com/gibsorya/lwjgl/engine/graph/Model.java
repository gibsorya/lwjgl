package com.gibsorya.lwjgl.engine.graph;

import java.util.*;

import com.gibsorya.lwjgl.engine.scene.*;

public class Model {
    private final String id;
    private List<Entity> entities;
    private List<Material> materials;

    public Model(String id, List<Material> materials) {
        this.id = id;
        this.materials = materials;
        entities = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public List<Material> getMaterials() {
        return materials;
    }

    public void cleanup() {
        materials.forEach(Material::cleanup);
    }
}
