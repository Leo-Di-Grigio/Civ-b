package gui.elements;

import java.awt.Graphics;
import java.util.HashMap;

import javax.media.opengl.GL3;

import script.gui.ScriptGui;
import script.gui.pane.gui_pane_MouseRelease;
import misc.Environment;
import misc.Log;
import gui.GuiElement;

public class GuiElementPane extends GuiElement {

	private HashMap<String, GuiElement> elements;
	
	public GuiElementPane() {
		super();
		this.elements = new HashMap<String, GuiElement>();
		this.script = new gui_pane_MouseRelease();
	}
	
	public void addElement(String title, GuiElement element){
		elements.put(title, element);
	}
	
	public GuiElement getElement(String title){
		if(elements.containsKey(title)){
			return elements.get(title);
		}
		else{
			Log.err("Element " + title + " is not found");
			return null;
		}
	}
	
	public void remove(String title){
		if(elements.containsKey(title)){
			elements.remove(title);
		}
		else{
			Log.err("Element " + title + " is not found");
		}
	}
	
	public String getCollisionElement(){
		for(String key: elements.keySet()){
			if(elements.get(key).checkCollision()){
				return key;
			}
		}
		return null;
	}
	
	@Override
	public void setScript(ScriptGui script) {
		// null
	}
	
	@Override
	public void updateDrawPosition(int xPos, int yPos, int w, int h) {
		// update pane position
		super.updateDrawPosition(0, 0, Environment.frameSizeX, Environment.frameSizeY);
		
		for(GuiElement element: elements.values()){
			element.updateDrawPosition(drawX, drawY, sizeX, sizeY);
		}
	}
	
	@Override
	public void draw(Graphics g) {
		if(visible){
			if(selected){
				g.drawImage(textureSelected, drawX, drawY, sizeX, sizeY, null);
			}
			else{
				g.drawImage(textureNormal, drawX, drawY, sizeX, sizeY, null);
			}
			
			for(GuiElement element: elements.values()){
				element.draw(g);
			}
		}
	}

	@Override
	public void draw(GL3 gl) {
		
	}
}
