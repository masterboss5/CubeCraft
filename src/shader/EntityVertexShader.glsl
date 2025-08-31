#version 460 core
#extension GL_NV_gpu_shader5 : enable

layout(location = 0) in vec3 position;
layout(location = 1) in vec2 uv;
layout(location = 2) in uint64_t texturePointer;

// Per-instance transform matrix (mat4 = 4 vec4s)
layout(location = 3) in vec4 modelRow0;
layout(location = 4) in vec4 modelRow1;
layout(location = 5) in vec4 modelRow2;
layout(location = 6) in vec4 modelRow3;

layout(std430, binding = 0) buffer texturebuffer {
    int64_t values[]; // or uint64_t for unsigned
};


out vec2 pass_uv;
flat out uint64_t pass_texturePointer;

uniform mat4 view;
uniform mat4 projection;

void main() {
    mat4 model = mat4(modelRow0, modelRow1, modelRow2, modelRow3);
    gl_Position = projection * view * model * vec4(position, 1.0);
    pass_uv = uv;
    pass_texturePointer = texturePointer;
}