package scenedata;

import java.awt.Graphics;
import java.io.IOException;

import javax.media.opengl.GL3;

import gui.GUI;
import script.Script;
import tasks.Task;
import tasks.TasksPool;

abstract public class SceneData {
	
	protected GUI gui;
	
	// subscriber - you can load this script for temporary execution 
	protected Script subscriber;
	protected Object subscriberAddData;
	
	public SceneData(GUI gui){
		this.gui = gui;
	}
	
	public void update(TasksPool tasks) throws IOException{
		Task [] arr = tasks.getPool();
		tasks.clear();
		
		for(int i = 0; i < arr.length; ++i){			
			if(arr[i] != null){
				arr[i].sceneGui = this.gui;
				
				if(subscriber != null){
					arr[i] = subscriber.preexecute(arr[i], subscriberAddData); // this script will be executed before sceneData receive task
				}
				if(arr[i] != null && !arr[i].blocked){ // subscriber can block normal task execution 
					execute(arr[i]);
				}
			}
		}
	}
	
	public void drawGui(Graphics g){
		gui.draw(g);
	}
	
	public void drawGui(GL3 gl){
		gui.draw(gl);
	}
	
	public void subscriberAdd(Script subscriber,  Object subscriberAddData){
		this.subscriberAddData = subscriberAddData;
		this.subscriber = subscriber;
	}
	
	public void subscriberRemove(){
		this.subscriber = null;
		this.subscriberAddData = null;
	}
	
	abstract public void execute(Task task) throws IOException;
}
