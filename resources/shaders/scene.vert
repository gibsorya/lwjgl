#version 330

layout (location = 0) in vec3 inPos;
layout (location = 1) in vec3 inCol;

out vec3 outColor;

uniform mat4 transform;

void main()
{
    gl_Position = transform * vec4(inPos, 1.0);
    outColor = inCol;
}