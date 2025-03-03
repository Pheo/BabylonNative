$input v_color0, v_texcoord0

#include "./common.sh"

SAMPLER2D(s_filterTexColor,  0);

uniform vec4 u_filterParams;
#define u_blur_scale    u_filterParams.xy
#define u_compose_mult  u_filterParams.z
#define u_compose_alpha u_filterParams.w

void main()
{
    vec4 color;
    color  = texture2D(s_filterTexColor, vec2(v_texcoord0.x-4.0*u_blur_scale.x, v_texcoord0.y-4.0*u_blur_scale.y))*0.05;
    color += texture2D(s_filterTexColor, vec2(v_texcoord0.x-3.0*u_blur_scale.x, v_texcoord0.y-3.0*u_blur_scale.y))*0.09;
    color += texture2D(s_filterTexColor, vec2(v_texcoord0.x-2.0*u_blur_scale.x, v_texcoord0.y-2.0*u_blur_scale.y))*0.12;
    color += texture2D(s_filterTexColor, vec2(v_texcoord0.x-1.0*u_blur_scale.x, v_texcoord0.y-1.0*u_blur_scale.y))*0.15;
    color += texture2D(s_filterTexColor, vec2(v_texcoord0.x+0.0*u_blur_scale.x, v_texcoord0.y+0.0*u_blur_scale.y))*0.16;
    color += texture2D(s_filterTexColor, vec2(v_texcoord0.x+1.0*u_blur_scale.x, v_texcoord0.y+1.0*u_blur_scale.y))*0.15;
    color += texture2D(s_filterTexColor, vec2(v_texcoord0.x+2.0*u_blur_scale.x, v_texcoord0.y+2.0*u_blur_scale.y))*0.12;
    color += texture2D(s_filterTexColor, vec2(v_texcoord0.x+3.0*u_blur_scale.x, v_texcoord0.y+3.0*u_blur_scale.y))*0.09;
    color += texture2D(s_filterTexColor, vec2(v_texcoord0.x+4.0*u_blur_scale.x, v_texcoord0.y+4.0*u_blur_scale.y))*0.05;

    gl_FragColor = color
        * vec4(u_compose_mult
             , u_compose_mult
             , u_compose_mult
             , u_compose_mult * u_compose_alpha
             );
}

