#version 400 core

layout(location = 0) in vec3 position;
layout(location = 1) in vec2 uv;
layout(location = 2) in int textureIndex;

out vec2 pass_uv;
flat out int pass_textureIndex;

uniform mat4 transform;
uniform mat4 projection;
uniform mat4 view;

void main() {
    gl_Position = projection * view * transform * vec4(position, 1);
    pass_uv = uv;
    pass_textureIndex = textureIndex;
}