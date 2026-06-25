package com.gibsorya.lwjgl.engine.graph;

import static org.lwjgl.opengl.GL20.*;

import java.util.*;

import org.joml.*;
import org.lwjgl.system.*;

public class UniformsMap {
    private int programId;
    private Map<String, Integer> uniforms;

    public UniformsMap(int programId) {
        this.programId = programId;
        uniforms = new HashMap<>();
    }

    public void createUniform(String uniformName) {
        int uniformLocation = glGetUniformLocation(programId, uniformName);
        if(uniformLocation < 0) {
            throw new RuntimeException("Could not find uniform [" + uniformName + "] in shader program [" + programId + "]");
        }

        uniforms.put(uniformName, uniformLocation);
    }

    public void setUniform(String uniformName, Matrix4f value) {
        try(MemoryStack stack = MemoryStack.stackPush()) {
            Integer location = uniforms.get(uniformName);
            if(location == null) {
                throw new RuntimeException("Could not find uniform [" + uniformName + "]");
            }
            glUniformMatrix4fv(location.intValue(), false, value.get(stack.mallocFloat(16)));
        }
    }
}
