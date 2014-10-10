package scenedata;

import java.awt.Graphics;

import javax.media.opengl.GL3;

import gui.GUI;
import tasks.Task;
import tasks.TasksPool;

abstract public class SceneData {
	
	protected GUI gui;
	
	public SceneData(GUI gui){
		this.gui = gui;
	}
	
	public void update(TasksPool tasks){
		while(tasks.hasNext()){
			Task task = tasks.poll();
			
			if(task != null){
				execute(task);
			}
		}
	}
	
	public void drawGui(Graphics g){
		gui.draw(g);
	}
	
	public void drawGui(GL3 gl){
		gui.draw(gl);
	}
	
	abstract public void execute(Task task); 
}
