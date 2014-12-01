package shaders.list;

import java.io.IOException;
import java.nio.FloatBuffer;

import javax.media.opengl.GL2;

import shaders.Shader;

public class shader_GameMap extends Shader {

	private int mvMatrix;
	private int mpMatrix;
	private int texCoord;
	private int tex0;
	private int tex1;
	
	public shader_GameMap(GL2 gl, String title, String vertexShader, String fragmentShader) throws IOException {
		super(gl, title, vertexShader, fragmentShader);
		
		mvMatrix = gl.glGetUniformLocation(this.getProgram(), "MVMatrix");
		mpMatrix = gl.glGetUniformLocation(this.getProgram(), "MPMatrix");
		
		texCoord = gl.glGetAttribLocation(this.getProgram(), "in_coord");
		
		tex0 = gl.glGetUniformLocation(this.getProgram(), "tex0");
		tex1 = gl.glGetUniformLocation(this.getProgram(), "tex1");
	}
	
	public void prepeare(GL2 gl){
		// set textures
		gl.glUniform1i(tex0, 0);
        gl.glUniform1i(tex1, 1);
        
        // set matrix
        FloatBuffer modelMatrix = FloatBuffer.allocate(16);
		FloatBuffer projMatrix = FloatBuffer.allocate(16);
		
		gl.glGetFloatv(GL2.GL_MODELVIEW_MATRIX,  modelMatrix);
		gl.glGetFloatv(GL2.GL_PROJECTION_MATRIX, projMatrix);
		
		gl.glUniformMatrix4fv(mvMatrix, 1, false, modelMatrix);
		gl.glUniformMatrix4fv(mpMatrix, 1, false, projMatrix);
	}
	
	public int getTexCoord(){
		return texCoord;
	}
}
