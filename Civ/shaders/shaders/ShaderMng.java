package shaders;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.media.opengl.GL2;

import misc.Const;
import misc.Log;


public class ShaderMng {
	private static GL2 gl;
	
	// shaders
	private static int shaderProgram;
	
	// uniform values
	private static int mapHeight;
	
	public ShaderMng(GL2 gl) throws IOException{
		ShaderMng.gl = gl;
		initShaders();
		
		Log.debug("Shaders loaded");
	}
	
	public static int getMapHeight(){
		return mapHeight;
	}
	
	private static void initShaders() throws IOException{
		int vertexShader = gl.glCreateShader(GL2.GL_VERTEX_SHADER);
		int fragmentShader = gl.glCreateShader(GL2.GL_FRAGMENT_SHADER);

		String vsrc = loadShader(Const.glSahders + "Vertex.glsl");
		gl.glShaderSource(vertexShader, 1, new String[] { vsrc }, (int[]) null, 0);
		gl.glCompileShader(vertexShader);

		String fsrc = loadShader(Const.glSahders + "Fragment.glsl");
		gl.glShaderSource(fragmentShader, 1, new String[] { fsrc }, (int[]) null, 0);
		gl.glCompileShader(fragmentShader);
		
		// init shaders work
		shaderProgram = gl.glCreateProgram();
		gl.glAttachShader(shaderProgram, vertexShader);
		gl.glAttachShader(shaderProgram, fragmentShader);
		gl.glLinkProgram(shaderProgram);
		gl.glValidateProgram(shaderProgram);
		gl.glUseProgram(shaderProgram);
		
		// create uniform links
		mapHeight = gl.glGetUniformLocation(shaderProgram, "mapHeight");
	}
		
	private static String loadShader(String shaderPath) throws IOException {
		Scanner in = new Scanner(new File(shaderPath));

		String shaderSourceCode = "";
		while(in.hasNext()){
			shaderSourceCode += in.nextLine() + "\n";
		}
		
		in.close();
		return shaderSourceCode;
	}
}
