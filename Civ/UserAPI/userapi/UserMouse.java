package userapi;

import gamecycle.UserAPI;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class UserMouse implements MouseListener {

	@Override
	public void mouseClicked(MouseEvent e) {
		UserAPI.reciveEvent(UserAPI.Event.MOUSE_CLICKED, e);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		UserAPI.reciveEvent(UserAPI.Event.MOUSE_ENTERED, e);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		UserAPI.reciveEvent(UserAPI.Event.MOUSE_EXITED, e);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		UserAPI.reciveEvent(UserAPI.Event.MOUSE_PRESSED, e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		UserAPI.reciveEvent(UserAPI.Event.MOUSE_RELEASED, e);
	}
}
