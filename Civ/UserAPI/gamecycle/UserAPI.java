package gamecycle;

import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import script.ScriptsAPI;

public class UserAPI {
	
	public static enum Event {
		// mouse
		MOUSE_CLICKED,
		MOUSE_ENTERED,
		MOUSE_EXITED,
		MOUSE_PRESSED,
		MOUSE_RELEASED,
		
		MOUSE_DRAGGED,
		MOUSE_MOVED,
		
		// wheel
		WHEEL_MOVED,
		
		// keyboard
		KEY_PRESSED,
		KEY_RELEASED,
		KEY_TYPED,
		
		// canvas
		COMPONENT_HIDDEN,
		COMPONENT_MOVED,
		COMPONENT_RESIZED,
		COMPONENT_SHOWN;
	}
	
	public static void reciveEvent(UserAPI.Event type, KeyEvent e){
		switch(type){
			case KEY_PRESSED:
				ScriptsAPI.keyPressed(e);
				break;
				
			case KEY_RELEASED:
				ScriptsAPI.keyReleased(e);
				break;
				
			case KEY_TYPED:
				ScriptsAPI.keyTyped(e);
				break;
				
			default: break;
		}
	}
	
	public static void reciveEvent(UserAPI.Event type, MouseEvent e){
		
		switch(type){
			case MOUSE_MOVED:
				ScriptsAPI.mouseMoved(e.getX(), e.getY());
				break;
				
			case MOUSE_DRAGGED:
				ScriptsAPI.mouseDragged(e.getX(), e.getY());
				break;
			
			case MOUSE_EXITED:
				ScriptsAPI.mouseExited();
				break;
			
			case MOUSE_ENTERED:
				ScriptsAPI.mouseEntered();
				break;
			
			case MOUSE_PRESSED:
				ScriptsAPI.mousePressed(e.getX(), e.getY());
				break;
				
			case MOUSE_CLICKED:
				ScriptsAPI.mouseClicked(e.getX(), e.getY());
				break;
				
			case MOUSE_RELEASED:
				ScriptsAPI.mouseReleased(e.getX(), e.getY());
				break;
				
			default: break;
		}
	}
	
	public static void reciveEvent(UserAPI.Event type, MouseWheelEvent e){
		if(type == UserAPI.Event.WHEEL_MOVED){
			ScriptsAPI.mouseWheelMoved(e);
		}
	}
	
	public static void reciveEvent(UserAPI.Event type, ComponentEvent e){
		switch(type){
			case COMPONENT_HIDDEN:
				ScriptsAPI.canvasHidden();
				break;	
				
			case COMPONENT_SHOWN:
				ScriptsAPI.canvasShown();
				break;
				
			case COMPONENT_MOVED:
				ScriptsAPI.canvasMoved();
				break;
				
			case COMPONENT_RESIZED:
				ScriptsAPI.canvasResized();
				break;
				
			default: break;
		}
	}
}
