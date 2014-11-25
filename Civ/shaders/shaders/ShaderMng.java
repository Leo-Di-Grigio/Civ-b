package shaders;

import java.io.IOException;
import java.util.HashMap;

import javax.media.opengl.GL2;

import shaders.list.shader_GameMap;
import misc.Const;
import misc.Log;

public class ShaderMng {
	
	private GL2 gl;
	private HashMap<String, Shader> shaders;
	
	public ShaderMng(GL2 gl) throws IOException{
		this.gl = gl;
		shaders = new HashMap<String, Shader>();
		loadShaders();
		System.gc();
		
		Log.debug("Assets GL shaders loaded: " + shaders.size());
	}
	
	private void loadShaders() throws IOException{
		//
		shader_GameMap shaderGameMap = new shader_GameMap(gl, "gameMap", "vGameMap.glsl", "fGameMap.glsl");
		
		
		//
		shaders.put(Const.shaderGameMap, shaderGameMap);
	}

	public Shader getShader(String key) {
		return shaders.get(key);
	}
}
