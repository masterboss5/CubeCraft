#version 400 core

in vec3 position;

out vec3 color;

uniform mat4 transform;
uniform mat4 projection;
uniform mat4 view;

void main(void)
{
    gl_Position = projection * view * transform * vec4(position, 1);
    color = vec3(position.x + 1, position.y + 0.5, position.z + 0);
}