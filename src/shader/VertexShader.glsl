#version 400 core

layout(location = 0) in vec3 position;

uniform mat4 transform;
uniform mat4 projection;
uniform mat4 view;

void main(void) {
    gl_Position = projection * view * transform * vec4(position, 1);
}