#version 150

// USER
uniform float h;

uniform sampler2D tex0;
uniform sampler2D tex1;

// IN
in vec2 tex_coord;

// OUT
out vec4 frgcolor;

void main()
{	
	if(h == 0.0){
    	frgcolor = texture2D(tex0, tex_coord);
    }
    else{
    	frgcolor = texture2D(tex1, tex_coord);
    }
}