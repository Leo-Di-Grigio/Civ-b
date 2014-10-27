package gui.elements;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.media.opengl.GL3;

import main.Config;
import misc.Log;
import gui.GuiElement;

public class GuiElementChat extends GuiElement {
	
	protected ArrayList<String> chatLog;
	protected int scroll = 0;
	protected int viewLines = 15;
	
	public GuiElementChat(String titile) {
		super(titile);
		chatLog = new ArrayList<String>();
	}

	public void addLine(String line){
		if(chatLog.size() < Config.chatHistorySize){
			this.chatLog.add(line);
		}
		else{
			this.chatLog.remove(0);
			this.chatLog.add(line);
		}
	}
	
	public void scrollUp(){
		this.scroll--;
		if(scroll < 0){
			scroll = 0;
		}
	}
	
	public void scrollDown(){
		this.scroll++;
		if(scroll >= chatLog.size() - viewLines){
			scroll = chatLog.size() - viewLines;
		}
		Log.debug("down");
	}
	
	@Override
	public void draw(Graphics g) {
		if(visible){
			if(selected){
				g.drawImage(this.textureSelected, drawX, drawY, sizeX, sizeY, null);
			}
			else{
				g.drawImage(this.textureNormal, drawX, drawY, sizeX, sizeY, null);
			}
			
			g.setColor(Color.white);
			
			int line = chatLog.size() - viewLines - scroll;
			int lineMax = chatLog.size() - scroll;
			
			if(chatLog.size() != 0){
				if(line < 0){
					line = 0;
				}
				if(line >= chatLog.size()){
					line = chatLog.size() - 1;
				}
			
				if(lineMax > chatLog.size()){
					lineMax = chatLog.size();
				}
				if(lineMax < 0){
					lineMax = 0;
				}
			
				for(int lineShift = 1; line < lineMax; ++line, ++lineShift){
					g.drawString(chatLog.get(line), drawX, drawY + g.getFontMetrics().getHeight() * lineShift);
				}
			}
		}
	}

	@Override
	public void draw(GL3 gl) {

	}
}
