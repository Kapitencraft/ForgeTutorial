#version 150

uniform sampler2D DiffuseSampler;

in vec2 texCoord;
in vec2 oneTexel;

out vec4 fragColor;

void main() {
    vec4 InTexel = texture(DiffuseSampler, texCoord);

    float v = dot(vec3(.2126, .7152, .0722), InTexel.rgb);

    fragColor = vec4(v, v, v, 1.0);
}