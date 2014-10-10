package userapi;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import gamecycle.UserAPI;

public class UserCanvasListener implements ComponentListener {

	@Override
	public void componentHidden(ComponentEvent e) {
		UserAPI.reciveEvent(UserAPI.Event.COMPONENT_HIDDEN, e);

	}

	@Override
	public void componentMoved(ComponentEvent e) {
		UserAPI.reciveEvent(UserAPI.Event.COMPONENT_MOVED, e);
	}

	@Override
	public void componentResized(ComponentEvent e) {
		UserAPI.reciveEvent(UserAPI.Event.COMPONENT_RESIZED, e);
	}

	@Override
	public void componentShown(ComponentEvent e) {
		UserAPI.reciveEvent(UserAPI.Event.COMPONENT_SHOWN, e);
	}
}
