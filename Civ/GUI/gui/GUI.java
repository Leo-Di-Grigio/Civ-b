package gui;

import java.awt.Graphics;
import java.io.IOException;
import java.util.HashMap;

import javax.media.opengl.GL3;

import misc.Enums;
import misc.Environment;
import misc.Log;
import render.Drawble;
import tasks.Task;

public class GUI implements Drawble {

	private HashMap<String, GuiElement> gui;
	
	// data
	protected String selectedElementTitlte;
	
	public GUI() {
		gui = new HashMap<String, GuiElement>();
	}
	
	public void add(String title, GuiElement element){
		gui.put(title, element);
	}
	
	public GuiElement get(String title){
		if(gui.containsKey(title)){
			return gui.get(title);
		}
		else{
			Log.err("Gui Element \"" + title + "\" is not found");
			return null;
		}
	}
	
	public void remove(String title){
		if(gui.containsKey(title)){
			gui.remove(title);
		}
	}

	public String checkCollision(){
		for(String key: gui.keySet()){
			if(gui.get(key).checkCollision()){
				return key;
			}
		}
		return null;
	}
	
	public void selectionSelect(String title){
		if(selectedElementTitlte != null && selectedElementTitlte.compareTo(title) != 0){
			gui.get(selectedElementTitlte).setSelected(false);
			cursorShow(true);
		}
		
		gui.get(title).setSelected(true);
		cursorShow(false);
		selectedElementTitlte = title;
	}
	
	public void selectionReset() {
		if(selectedElementTitlte != null){
			gui.get(selectedElementTitlte).setSelected(false);
			selectedElementTitlte = null;
			cursorShow(true);
		}
	}
	
	public boolean click() throws IOException{ // return true if any script executed, return false if no scripts
		if(selectedElementTitlte != null){
			GuiElement element = gui.get(selectedElementTitlte);
			
			if(element != null && element.script != null){
				element.script.execute(new Task(Enums.Task.MOUSE_RELEASED, element));
			}
			return true;
		}
		else{
			return false;
		}
	}
	
	public void updatePosition(){
		for(GuiElement element: gui.values()){
			element.updateDrawPosition(0, 0, Environment.frameSizeX, Environment.frameSizeY);
		}
	}
	
	@Override
	public void draw(Graphics g) {
		for(GuiElement element: gui.values()){
			if(element.visible){
				element.draw(g);
			}
		}
	}

	@Override
	public void draw(GL3 gl) {
		for(GuiElement element: gui.values()){
			if(element.visible){
				element.draw(gl);
			}
		}
	}

	public void cursorShow(boolean visible) {
		if(gui.containsKey("cursor")){
			GuiElement cursor = get("cursor");
			cursor.setVisible(visible);
		}
	}
}
