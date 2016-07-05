#ifdef GL_ES
    precision mediump float;
#endif

uniform vec3 color_ambiente;

varying vec4 v_color;
varying vec2 v_texCoords;
uniform sampler2D u_texture;
uniform mat4 u_projTrans;

void main() {
        vec4 color_difuso = texture2D(u_texture, v_texCoords);
        vec3 salida=color_difuso.rgb*color_ambiente;   
        gl_FragColor = vec4(salida, color_difuso.a);
}
