#version 400 core

in vec2 pass_uv;

out vec4 outputColor;

uniform sampler2D textureSampler;

void main() {
    outputColor = texture(textureSampler, pass_uv);
}