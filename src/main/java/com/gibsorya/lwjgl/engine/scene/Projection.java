package com.gibsorya.lwjgl.engine.scene;

import org.joml.Matrix4f;

public class Projection {
    private static final float FOV = (float) Math.toRadians(60.0f);
    private static final float Z_FAR = 1000.0f;
    private static final float Z_NEAR = 0.01f;

    private Matrix4f transform;

    public Projection(int width, int height) {
        transform = new Matrix4f();
        updateTransform(width, height);
    }

    public void updateTransform(int width, int height) {
        transform.setPerspective(FOV, (float) width / height, Z_NEAR, Z_FAR);
    }

    public Matrix4f getTransform() {
        return transform;
    }
}
