#version 400 core
#extension GL_NV_gpu_shader5 : enable

layout(location = 0) in vec3 position;
layout(location = 1) in vec2 uv;
layout(location = 2) in uint64_t texturePointer;

out vec2 pass_uv;
flat out uint64_t pass_texturePointer;

uniform mat4 transform;
uniform mat4 projection;
uniform mat4 view;

void main() {
    gl_Position = projection * view * transform * vec4(position, 1);
    pass_texturePointer = texturePointer;
    pass_uv = uv;
}