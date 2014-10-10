package gamecycle;

import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import script.Scripts;

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
				Scripts.keyPressed(e);
				break;
				
			case KEY_RELEASED:
				Scripts.keyReleased(e);
				break;
				
			case KEY_TYPED:
				Scripts.keyTyped(e);
				break;
				
			default: break;
		}
	}
	
	public static void reciveEvent(UserAPI.Event type, MouseEvent e){
		
		switch(type){
			case MOUSE_MOVED:
				Scripts.mouseMoved(e.getX(), e.getY());
				break;
				
			case MOUSE_DRAGGED:
				Scripts.mouseDragged(e.getX(), e.getY());
				break;
			
			case MOUSE_EXITED:
				Scripts.mouseExited();
				break;
			
			case MOUSE_ENTERED:
				Scripts.mouseEntered();
				break;
			
			case MOUSE_PRESSED:
				Scripts.mousePressed(e.getX(), e.getY());
				break;
				
			case MOUSE_CLICKED:
				Scripts.mouseClicked(e.getX(), e.getY());
				break;
				
			case MOUSE_RELEASED:
				Scripts.mouseReleased(e.getX(), e.getY());
				break;
				
			default: break;
		}
	}
	
	public static void reciveEvent(UserAPI.Event type, MouseWheelEvent e){
		if(type == UserAPI.Event.WHEEL_MOVED){
			Scripts.mouseWheelMoved(e);
		}
	}
	
	public static void reciveEvent(UserAPI.Event type, ComponentEvent e){
		switch(type){
			case COMPONENT_HIDDEN:
				Scripts.canvasHidden();
				break;	
				
			case COMPONENT_SHOWN:
				Scripts.canvasShown();
				break;
				
			case COMPONENT_MOVED:
				Scripts.canvasMoved();
				break;
				
			case COMPONENT_RESIZED:
				Scripts.canvasResized();
				break;
				
			default: break;
		}
	}
}
