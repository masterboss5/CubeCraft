#version 400 core

in vec3 position;

out vec3 color;

uniform vec3 colorValue;

uniform mat4 transform;
uniform mat4 projection;
uniform mat4 view;

void main(void)
{
    gl_Position = projection * view * transform * vec4(position, 1);
    color = vec3((position.x + 1)/50, (position.y + 0.5)/50, (position.z + 0)/50);
//    color = vec3(colorValue);
}