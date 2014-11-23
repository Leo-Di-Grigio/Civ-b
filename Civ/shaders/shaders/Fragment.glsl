#version 110

uniform float mapHeight;

uniform sampler2D texWater;
uniform sampler2D texLand;

varying vec2 Texcoord;

void main(void)
{
	if(mapHeight < 1.0){
		gl_FragColor = texture2D(texWater, Texcoord);
	}
	else{
		gl_FragColor = texture2D(texLand, Texcoord);
	}
}