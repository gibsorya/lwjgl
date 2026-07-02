#version 330

in vec3 outColor;
out vec4 fragColor;

in vec2 outTextCoord;
uniform sampler2D ourTexture;

void main()
{
    fragColor = texture(ourTexture, outTextCoord);
}