package scenedata;

import java.awt.Graphics;
import java.io.IOException;

import javax.media.opengl.GL3;

import gui.GUI;
import tasks.Task;
import tasks.TasksPool;

abstract public class SceneData {
	
	protected GUI gui;
	
	public SceneData(GUI gui){
		this.gui = gui;
	}
	
	public void update(TasksPool tasks) throws IOException{
		while(tasks.hasNext()){
			Task task = tasks.poll();
			
			if(task != null){
				execute(task);
			}
		}
		
		tasks.swap();
	}
	
	public void drawGui(Graphics g){
		gui.draw(g);
	}
	
	public void drawGui(GL3 gl){
		gui.draw(gl);
	}
	
	abstract public void execute(Task task) throws IOException; 
}
