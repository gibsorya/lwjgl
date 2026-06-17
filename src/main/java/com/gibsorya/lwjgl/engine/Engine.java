package com.gibsorya.lwjgl.engine;

import com.gibsorya.lwjgl.engine.graph.*;
import com.gibsorya.lwjgl.engine.scene.*;

public class Engine {
    public static final int TARGET_UPS = 30;
    private final IAppLogic appLogic;
    private final Window window;
    private Render render;
    private boolean running;
    private Scene scene;
    private int targetFps;
    private int targetUps;

    public Engine(String windowTitle, Window.WindowOptions opts, IAppLogic appLogic) {
        window = new Window(windowTitle, opts, () -> {
            resize();
            return null;
        });
        targetFps = opts.fps;
        targetUps = opts.ups;
        this.appLogic = appLogic;
        render = new Render();
        scene = new Scene();
        appLogic.init(window, scene, render);
        running = true;
    }

    public void start() {
        running = true;
        run();
    }

    public void stop() {
        running = false;
    }

    private void run() {
        long initTime = System.currentTimeMillis();
        float timeU = 1000.0f / targetUps; // Max elapsed time between updates in ms
        float timeR = targetFps > 0 ? 1000.0f / targetFps : 0; // Max elapsed time between render calls in ms
        float deltaUpdate = 0;
        float deltaFps = 0;

        long updateTime = initTime;
        while(running && !window.windowShouldClose()) {
            window.pollEvents();
            long now = System.currentTimeMillis(); // Current time in ms
            deltaUpdate += (now - initTime) / timeU; // Elapsed time between update calls
            deltaFps += (now - initTime) / timeR; // Elapsed time between render calls

            // Process user input if we have passed max elapsed time between renders (or relay in vsync)
            if(targetFps <= 0 || deltaFps >= 1) {
                appLogic.input(window, scene, now - initTime);
            }

            // Update game state if we have passed max update elapsed time
            if(deltaUpdate >= 1) {
                long diffTimeMillis = now - updateTime;
                appLogic.update(window, scene, diffTimeMillis);
                updateTime = now;
                deltaUpdate--;
            }

            // Trigger render call when we passed max elapsed time for rendering (or relay in vsync)
            if (targetFps <= 0 || deltaFps >= 1) {
                render.render(window, scene);
                deltaFps--;
                window.update();
            }
            initTime = now;
        }

        cleanup();
    }

    private void cleanup() {
        appLogic.cleanup();
        render.cleanup();
        scene.cleanup();
        window.cleanup();
    }

    private void resize() {

    }
}
