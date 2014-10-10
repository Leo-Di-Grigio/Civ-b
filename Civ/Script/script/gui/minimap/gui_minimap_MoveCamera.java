package script.gui.minimap;

import gui.elements.GuiElementMinimap;
import misc.Environment;
import misc.Log;
import script.gui.ScriptGui;
import tasks.Task;

public class gui_minimap_MoveCamera extends ScriptGui {
	
	@Override
	public void execute(Task task){
		Log.debug("gui_minimap_MoveCamera " + task.type);
		GuiElementMinimap minimap = (GuiElementMinimap)task.data;
		
		int mouseX = Environment.mouseX;
		int mouseY = Environment.mouseY;
		int halfW = Environment.width/64;
		int halfH = Environment.height/64;
		
		int drawX = minimap.getDrawX();
		int drawY = minimap.getDrawY();
		int sizeX = minimap.getSizeX();
		int sizeY = minimap.getSizeY();
		
		int mapX = minimap.getMapSizeX();
		int mapY = minimap.getMapSizeY();
		
		if(mouseX > drawX + 2 && mouseX < drawX + sizeX - 2 &&
		   mouseY > minimap.getDrawY() + 2 && mouseY < drawY + sizeY - 2)
		{
			int x = (int)((mouseX - drawX - 2) / minimap.getScaleFactorX() ) - halfW;
			int y = (int)((mouseY - drawY - 2) / minimap.getScaleFactorY() ) - halfH;
			
			if(x <= 0){
				Environment.cameraX = Math.abs(mapX + x);
			}
			else{
				Environment.cameraX = x;
			}

			if(y <= 0){
				Environment.cameraY = 0;
			}
			else{
				if(mapY - 2*halfH > y){
					Environment.cameraY = y;
				}
				else{
					Environment.cameraY = mapY - 2*halfH - 1;
				}
			}
		}
	}
}
