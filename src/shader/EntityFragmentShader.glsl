#version 420 core
#extension GL_NV_gpu_shader5 : enable

in vec2 pass_uv;
flat in uint64_t pass_texturePointer;

out vec4 outputColor;

void main() {
    outputColor = texture(sampler2D(pass_texturePointer), pass_uv);
}