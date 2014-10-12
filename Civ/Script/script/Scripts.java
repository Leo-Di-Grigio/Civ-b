package script;

import java.awt.event.KeyEvent;
import java.awt.event.MouseWheelEvent;

import script.userapi.api_MouseClicked;
import script.userapi.api_MouseDragged;
import script.userapi.api_MouseEntered;
import script.userapi.api_MouseExited;
import script.userapi.api_MouseMove;
import script.userapi.api_MousePressed;
import script.userapi.api_MouseReleased;
import script.userapi.api_CanvasHidden;
import script.userapi.api_CanvasMoved;
import script.userapi.api_CanvasResized;
import script.userapi.api_CanvasShow;
import script.userapi.api_KeyPressed;
import script.userapi.api_KeyReleased;
import script.userapi.api_KeyTyped;
import script.userapi.api_MouseWheelMoved;
import misc.Log;

public class Scripts {
	
	public Scripts(){
		Log.msg("Scripts loaded");
	}

	// UserAPI Scripts
	// MOUSE
	public static void mouseMoved(int x, int y){
		api_MouseMove.execute(x, y);
	}
	
	public static void mouseDragged(int x, int y){
		api_MouseDragged.execute(x, y);
	}
	
	public static void mouseExited(){
		api_MouseExited.execute();
	}
	
	public static void mouseEntered(){
		api_MouseEntered.execute();
	}
	
	public static void mousePressed(int x, int y){
		api_MousePressed.execute(x, y);
	}
	
	public static void mouseClicked(int x, int y){
		api_MouseClicked.execute(x, y);
	}
	
	public static void mouseReleased(int x, int y){
		api_MouseReleased.execute(x, y);
	}
	
	// MOUSE WHEEL
	public static void mouseWheelMoved(MouseWheelEvent e){
		api_MouseWheelMoved.execute(e);
	}
	
	// KEYBOARD
	public static void keyPressed(KeyEvent e){
		api_KeyPressed.execute(e);
	}
	
	public static void keyReleased(KeyEvent e){
		api_KeyReleased.execute(e);
	}
	
	public static void keyTyped(KeyEvent e){
		api_KeyTyped.execute(e);
	}
	
	// CANVAS
	public static void canvasHidden(){
		api_CanvasHidden.execute();
	}
	
	public static void canvasShown(){
		api_CanvasShow.execute();
	}
	
	public static void canvasMoved(){
		api_CanvasMoved.execute();
	}
	
	public static void canvasResized(){
		api_CanvasResized.execute();
	}
}
