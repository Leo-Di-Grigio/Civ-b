#version 110

void main(void){
	gl_Position = projection * view * model * vert;
}