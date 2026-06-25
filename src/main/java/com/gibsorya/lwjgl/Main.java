package com.gibsorya.lwjgl;

import com.gibsorya.lwjgl.engine.*;
import com.gibsorya.lwjgl.engine.graph.*;
import com.gibsorya.lwjgl.engine.scene.*;

public class Main implements IAppLogic {
    public static void main(String[] args) {
        Main app = new Main();
        Engine engine = new Engine("LWJGL", new Window.WindowOptions(), app);
        engine.start();
    }

    @Override
    public void cleanup() {
        // TODO Auto-generated method stub
    }

    @Override
    public void init(Window window, Scene scene, Render render) {
        float[] positions = new float[]{
            -0.5f, 0.5f, -1f,
            -0.5f, -0.5f, -1f,
            0.5f, -0.5f, -1f,
            0.5f, 0.5f, -1f,
        };
        float[] colors = new float[]{
            0.5f, 0.0f, 0.0f,
            0.0f, 0.5f, 0.0f,
            0.0f, 0.0f, 0.5f,
            0.0f, 0.5f, 0.5f,
        };
        int[] indices = new int[]{
            0,1,3,
            3,1,2
        };
        Mesh mesh = new Mesh(positions, colors, indices);
        scene.addMesh("quad", mesh);
    }

    @Override
    public void input(Window window, Scene scene, long diffTimeMillis) {
        // TODO Auto-generated method stub
    }

    @Override
    public void update(Window window, Scene scene, long diffTimeMillis) {
        // TODO Auto-generated method stub
    }
 
}
