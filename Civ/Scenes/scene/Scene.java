package scene;

import java.awt.Graphics;
import java.io.IOException;

import javax.media.opengl.GL2;

import com.jogamp.opengl.util.awt.TextRenderer;

import scenedata.SceneData;
import tasks.Task;
import tasks.TasksPool;
import misc.Enums;

abstract public class Scene {
	
	protected Enums.Scene type;
	protected SceneData data;
	protected TasksPool tasks;
	
	public Scene(Enums.Scene type, SceneData data){
		this.type = type;
		this.data = data;
		this.tasks = new TasksPool();
	}
	
	public Enums.Scene getType(){
		return type;
	}
	
	public void update() throws IOException{
		data.update(tasks);
	}
	
	public void addTask(Task e){
		tasks.add(e);
	}
	
	public void drawGui(Graphics g, long tic){
		data.drawGui(g, tic);
	}
	
	public void clearTasksPool() {
		tasks.clear();
	}

	public void drawGui(GL2 gl, TextRenderer textrender) {
		data.drawGui(gl);
	}

	abstract public void draw(Graphics g, long tic);
	abstract public void draw(GL2 gl, TextRenderer textrender);
}
