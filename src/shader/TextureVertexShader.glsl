#version 400 core

in vec3 position;
in vec2 uv;

out vec2 pass_uv;

uniform mat4 transform;
uniform mat4 projection;
uniform mat4 view;

void main() {
    gl_Position = projection * view * transform * vec4(position, 1);
    pass_uv = uv;
}