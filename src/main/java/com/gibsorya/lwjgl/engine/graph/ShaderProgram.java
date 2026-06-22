package com.gibsorya.lwjgl.engine.graph;

import com.gibsorya.lwjgl.engine.*;

import java.util.*;

import org.lwjgl.opengl.*;

import static org.lwjgl.opengl.GL20.*;

public class ShaderProgram {
    private final int programId;

    ShaderProgram(List<ShaderModuleData> shaderModuleDataList) {
        programId = glCreateProgram();

        if(programId == 0) {
            throw new RuntimeException("Could not create shader program!");
        }

        List<Integer> shaderModules = new ArrayList<>();
        shaderModuleDataList.forEach(s -> shaderModules.add(createShader(Utils.readFile(s.shaderFile), s.shaderType)));

        link(shaderModules);
    }

    public void bind() {
        glUseProgram(programId);
    }

    public void unbind() {
        glUseProgram(0);
    }

    public void cleanup() {
        unbind();
        if(programId != 0) {
            glDeleteProgram(programId);
        }
    }

    protected int createShader(String shaderCode, int shaderType) {
        int shaderId = glCreateShader(shaderType);
        if(shaderId == 0) {
            throw new RuntimeException("Error creating shader. Type: " + shaderType);
        }

        glShaderSource(shaderId, shaderCode);
        glCompileShader(shaderId);

        if(glGetShaderi(shaderId, GL_COMPILE_STATUS) == 0) {
            throw new RuntimeException("Error linking shader code: " + glGetShaderInfoLog(shaderId, 1024));
        }

        glAttachShader(programId, shaderId);

        return shaderId;
    }

    private void link(List<Integer> shaderModules) {
        glLinkProgram(programId);
        if(glGetProgrami(programId, GL_LINK_STATUS) == 0) {
            throw new RuntimeException("Error linking shader code: " + glGetProgramInfoLog(programId, 1024));
        }
        shaderModules.forEach(s -> glDetachShader(programId, s));
        shaderModules.forEach(GL30::glDeleteShader);
    }

    public void validate() {
        glValidateProgram(programId);
        if(glGetProgrami(programId, GL_VALIDATE_STATUS) == 0) {
            throw new RuntimeException("Error validating shader code: " + glGetProgramInfoLog(programId, 1024));
        }
    }

    public int getProgramId() {
        return programId;
    }

    public record ShaderModuleData(String shaderFile, int shaderType) {}
}
