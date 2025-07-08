#version 420 core

in vec2 pass_uv;

out vec4 outputColor;

//uniform sampler2D textureSampler;

layout(binding = 0) uniform sampler2DArray textureSampler;


void main() {
    outputColor = texture(textureSampler, vec3(pass_uv, 0));

//    outputColor = vec4(pass_uv, 0, 0);
}