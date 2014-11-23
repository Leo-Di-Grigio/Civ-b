#version 110

varying vec2 Texcoord;

void main( void )
{
	gl_Position = projection * view * model * vert;
	Texcoord    = gl_MultiTexCoord0.xy;
}