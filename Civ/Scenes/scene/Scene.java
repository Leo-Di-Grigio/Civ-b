package scene;

import java.awt.Graphics;
import java.io.IOException;

import javax.media.opengl.GL3;

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
	
	public void drawGui(Graphics g){
		data.drawGui(g);
	}
	
	public void drawGui(GL3 gl){
		data.drawGui(gl);
	}

	public void clearTasksPool() {
		tasks.clear();
	}
	
	abstract public void draw(Graphics g);
	abstract public void draw(GL3 gl);
}
