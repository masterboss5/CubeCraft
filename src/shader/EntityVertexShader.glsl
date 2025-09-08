#version 460 core
#extension GL_NV_gpu_shader5 : enable

layout(location = 0) in vec3 position;
layout(location = 1) in vec2 uv;

// Per-instance transform matrix (mat4 = 4 vec4s)
layout(location = 3) in vec4 modelRow0;
layout(location = 4) in vec4 modelRow1;
layout(location = 5) in vec4 modelRow2;
layout(location = 6) in vec4 modelRow3;

layout(std430, binding = 0) buffer texturebuffer {
    uint64_t values[]; // 6 texture pointers per instance
};

out vec2 pass_uv;
flat out uint64_t pass_texturePointer;

uniform mat4 view;
uniform mat4 projection;

void main() {
    // Compute face index within this instance
    int localVertexID = gl_VertexID % 24;
    int faceIndex = localVertexID / 4;

    // Compute global index into SSBO
    int index = gl_InstanceID * 6 + faceIndex;
    uint64_t texturePointer = values[index];

    // Apply model transform
    mat4 model = mat4(modelRow0, modelRow1, modelRow2, modelRow3);
    gl_Position = projection * view * model * vec4(position, 1.0);

    pass_uv = uv;
    pass_texturePointer = texturePointer;
}