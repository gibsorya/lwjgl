#version 330

layout (location = 0) in vec3 inPos;
layout (location = 1) in vec3 inCol;

out vec3 outColor;

uniform mat4 transform;
uniform mat4 modelMatrix;

void main()
{
    gl_Position = transform * modelMatrix * vec4(inPos, 1.0);
    outColor = inCol;
}