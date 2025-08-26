#version 420 core
#extension GL_NV_gpu_shader5: enable

out vec4 outputColor;

in vec2 pass_uv;
flat in uint64_t pass_texturePointer;

void main() {
    //    outputColor = vec4(1, 1, 1, 0);
    outputColor = texture(sampler2D(pass_texturePointer), pass_uv);
}