package com.gibsorya.lwjgl.engine;

import com.gibsorya.lwjgl.engine.graph.*;
import com.gibsorya.lwjgl.engine.scene.*;

public interface IAppLogic {
    void cleanup();

    void init(Window window, Scene scene, Render render);

    void input(Window window, Scene scene, long diffTimeMillis);

    void update(Window window, Scene scene, long diffTimeMillis);
}
