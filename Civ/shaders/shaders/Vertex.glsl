#version 150

uniform mat4 MVMatrix;
uniform mat4 MPMatrix;

in vec3 in_vertex;
in vec2 in_coord;

out vec2 tex_coord;

void main(){
	vec4 v = vec4( in_vertex, 1.0 );
    gl_Position = MPMatrix * MVMatrix * v;
    
    // out
    tex_coord = in_coord;
}