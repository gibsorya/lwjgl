package com.gibsorya.lwjgl.engine.graph;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.opengl.*;

import com.gibsorya.lwjgl.engine.*;
import com.gibsorya.lwjgl.engine.scene.*;

public class Render {
    public Render() {
        GL.createCapabilities();
    }

    public void cleanup() {

    }

    public void render(Window window, Scene scene) {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    }
}
