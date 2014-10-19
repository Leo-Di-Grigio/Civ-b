package gui;

import java.awt.Graphics;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.media.opengl.GL3;

import misc.Enums;
import misc.Environment;
import misc.Log;
import render.Drawble;
import tasks.Task;

public class GUI implements Drawble {

	private TreeMap<GuiLayer, HashMap<String, GuiElement>> guiLayers;
	private TreeSet<GuiElement> selectedSet;
	
	// data
	protected String selectedElementTitlte;
	protected String focusedElementTitle;
	
	// Layer sorter
	protected class SortLayers implements Comparator<GuiElement> {
		@Override
		public int compare(GuiElement a, GuiElement b) {
			return a.layer.compareTo(b.layer);
		}
	}
	
	public GUI() {
		guiLayers = new TreeMap<GuiLayer, HashMap<String, GuiElement>>();
		selectedSet  = new TreeSet<GuiElement>(new SortLayers());
	}
	
	public void add(GuiElement element){
		if(!guiLayers.containsKey(element.layer)){
			guiLayers.put(element.layer, new HashMap<String, GuiElement>());
		}
		
		guiLayers.get(element.layer).put(element.title, element);
	}
	
	public GuiElement get(String title){
		if(title != null){
			for(HashMap<String, GuiElement> gui: guiLayers.values()){
				if(gui.containsKey(title)){
					return gui.get(title);
				}
			}
		
			Log.err("Gui Element \"" + title + "\" is not found");
			return null;
		}
		else{
			return null;
		}
	}
	
	public void remove(String title){
		for(HashMap<String, GuiElement> gui: guiLayers.values()){
			if(gui.containsKey(title)){
				gui.remove(title);
			}
		}
	}

	public String checkCollision(){
		selectedSet.clear();
		
		for(HashMap<String, GuiElement> gui: guiLayers.values()){
			for(GuiElement element: gui.values()){
				if(element.checkCollision()){
					if(focusedElementTitle == null){
						selectedSet.add(element);
					}
					else{
						if(element.title.compareTo(focusedElementTitle) == 0){
							selectedSet.add(element);
						}
						else{
							continue;
						}
					}
				}
			}
		}
		
		if(selectedSet.isEmpty()){
			return null;
		}
		else{
			return selectedSet.pollLast().title; // get last element - that element placed in highest layer
		}
	}
	
	public void selectionSelect(String title){
		GuiElement element = this.get(title);
		
		if(element != null){
			if(selectedElementTitlte != null && selectedElementTitlte.compareTo(title) != 0){
				selectionReset();
			}
			else{
				element.setSelected(true);
				cursorShow(false);
				selectedElementTitlte = title;
			}
		}
	}
	
	public void selectionReset() {
		GuiElement element = this.get(selectedElementTitlte);
		
		if(element != null) {
			element.setSelected(false);
			cursorShow(true);
			selectedElementTitlte = null;
		}
	}
	
	public boolean click() throws IOException { // return true if any script executed, return false if no scripts
		if(selectedElementTitlte != null){
			GuiElement element = null;
			
			element = this.get(selectedElementTitlte);

			if(element != null && element.script != null){
				if(focusedElementTitle == null){
					element.script.execute(new Task(Enums.Task.MOUSE_RELEASED, element, this));
				}
				else{
					if(element.title.compareTo(focusedElementTitle) == 0){
						element.script.execute(new Task(Enums.Task.MOUSE_RELEASED, element, this));
					}
				}
			}
			return true;
		}
		else{
			return false;
		}
	}
	
	public void focus(String title){
		focusedElementTitle = title;
	}
	
	public void focusReset(){
		focusedElementTitle = null;
	}
	
	public void updatePosition(){
		for(HashMap<String, GuiElement> gui: guiLayers.values()){
			for(GuiElement element: gui.values()){
				element.updateDrawPosition(0, 0, Environment.frameSizeX, Environment.frameSizeY);
			}
		}
	}
	
	@Override
	public void draw(Graphics g) {
		for(HashMap<String, GuiElement> gui: guiLayers.values()){
			for(GuiElement element: gui.values()){
				if(element.visible){
					element.draw(g);
				}
			}
		}
	}

	@Override
	public void draw(GL3 gl) {
		for(HashMap<String, GuiElement> gui: guiLayers.values()){
			for(GuiElement element: gui.values()){
				if(element.visible){
					element.draw(gl);
				}
			}
		}
	}

	public void cursorShow(boolean visible) {
		for(HashMap<String, GuiElement> gui: guiLayers.values()){
			if(gui.containsKey("cursor")){
				GuiElement cursor = get("cursor");
				cursor.setVisible(visible);
				return;
			}
		}
	}
}
