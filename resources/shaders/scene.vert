#version 330

layout (location = 0) in vec3 inPos;
layout (location = 1) in vec3 inCol;

out vec3 outColor;

uniform mat4 projection;
uniform mat4 model;

void main()
{
    gl_Position = projection * model * vec4(inPos, 1.0);
    outColor = inCol;
}