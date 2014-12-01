package shaders;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import javax.media.opengl.GL2;

import misc.Const;
import misc.Log;

public class Shader {
	
	private String title;
	
	private int vertexShaderProgram;
	private int fragmentShaderProgram;
	private int shaderprogram;
	    
	private String [] vsrc;
	private String [] fsrc;
	   
	public Shader(GL2 gl, String title, String vertexShader, String fragmentShader) throws IOException{
		this.title = title;
		init(gl, Const.glSahders + vertexShader, Const.glSahders + fragmentShader);
	}

	private void init(GL2 gl, String vertexShader, String fragmentShader) throws IOException{
		vsrc = loadShader(vertexShader);
		fsrc = loadShader(fragmentShader);
		
		vertexShaderProgram = gl.glCreateShader(GL2.GL_VERTEX_SHADER);
		fragmentShaderProgram = gl.glCreateShader(GL2.GL_FRAGMENT_SHADER);
        
		gl.glShaderSource(vertexShaderProgram, 1, vsrc, null, 0);
		gl.glCompileShader(vertexShaderProgram);
		gl.glShaderSource(fragmentShaderProgram, 1, fsrc, null, 0);
		gl.glCompileShader(fragmentShaderProgram);
		
		vsrc = null;
		fsrc = null;
		
		shaderprogram = gl.glCreateProgram();
		gl.glAttachShader(shaderprogram, vertexShaderProgram);
		gl.glAttachShader(shaderprogram, fragmentShaderProgram);
		gl.glLinkProgram(shaderprogram);
		gl.glValidateProgram(shaderprogram);
		
		
		loadCheck(gl);
	}

	private String[] loadShader( String filePath ) throws IOException{
		StringBuilder sb = new StringBuilder();
		
		File file = new File(filePath);
		InputStream is =  new FileInputStream(file);
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String line = null;        

		while ((line = br.readLine()) != null){
			sb.append(line);
			sb.append('\n');
		}
		is.close();
		
		//Log.debug("Shader is:\n" + sb.toString()); // print shader code
		return new String[] { sb.toString() };
	}
	
	private void loadCheck(GL2 gl){
		IntBuffer intBuffer = IntBuffer.allocate(1);
		gl.glGetProgramiv(shaderprogram, GL2.GL_LINK_STATUS, intBuffer);
	        
		if (intBuffer.get(0) != GL2.GL_TRUE){
			gl.glGetProgramiv(shaderprogram, GL2.GL_INFO_LOG_LENGTH, intBuffer);
			int size = intBuffer.get(0);
			
			System.err.println("Program link error: ");
			if (size > 0){
				ByteBuffer byteBuffer = ByteBuffer.allocate(size);
				gl.glGetProgramInfoLog(shaderprogram, size, intBuffer, byteBuffer);
				
				for (byte b : byteBuffer.array()){
					Log.err("" + b);
				}
			}
			else{
				Log.err("Shader loading Unknown error");
			}
			System.exit(1);
		}
	}
	    
	public void bind(GL2 gl){
		gl.glUseProgram(shaderprogram);
	}

	public void unbind( GL2 gl ){
		gl.glUseProgram(0);
	}

	public int getProgram() {
		return shaderprogram;
	}
	
	public String getTitle(){
		return title;
	}
}

