package com.gibsorya.lwjgl.engine.graph;

import java.util.*;

import com.gibsorya.lwjgl.engine.scene.*;

public class Model {
    private final String id;
    private List<Entity> entities;
    private List<Mesh> meshes;

    public Model(String id, List<Mesh> meshes) {
        this.id = id;
        this.meshes = meshes;
        entities = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public List<Mesh> getMeshes() {
        return meshes;
    }

    public void cleanup() {
        meshes.forEach(Mesh::cleanup);
    }
}
