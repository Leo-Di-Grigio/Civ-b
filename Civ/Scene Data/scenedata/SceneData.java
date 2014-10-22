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
		while(tasks.hasNext()){
			Task task = tasks.poll();
			
			if(task != null){
				task.sceneGui = this.gui;
				
				if(subscriber != null){
					task = subscriber.preexecute(task, subscriberAddData); // this script will be executed before sceneData receive task
				}
				if(task != null && !task.blocked){ // subscriber can block normal task execution 
					execute(task);
				}
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
