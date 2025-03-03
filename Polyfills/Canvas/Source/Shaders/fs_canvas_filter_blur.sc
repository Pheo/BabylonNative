$input v_color0, v_texcoord0

#include "./common.sh"

SAMPLER2D(s_filterTexColor, 0);

uniform vec4 u_filterParams;
#define u_blur_scale    u_filterParams.xy
#define u_compose_mult  u_filterParams.z
#define u_compose_alpha u_filterParams.w

void main() {
  vec4 texColor = texture2D(s_filterTexColor, v_texcoord0.xy);
  gl_FragColor = v_color0 * texColor * vec4(1.0, 1.0, 1.0, u_compose_alpha);
}
