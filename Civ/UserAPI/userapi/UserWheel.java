package userapi;

import gamecycle.UserAPI;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class UserWheel implements MouseWheelListener {

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		UserAPI.reciveEvent(UserAPI.Event.WHEEL_MOVED, e);
	}
}
