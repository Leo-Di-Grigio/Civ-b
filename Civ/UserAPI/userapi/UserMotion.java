package userapi;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import gamecycle.UserAPI;

public class UserMotion implements MouseMotionListener {

	@Override
	public void mouseDragged(MouseEvent e) {
		UserAPI.reciveEvent(UserAPI.Event.MOUSE_DRAGGED, e);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		UserAPI.reciveEvent(UserAPI.Event.MOUSE_MOVED, e);
	}

}
