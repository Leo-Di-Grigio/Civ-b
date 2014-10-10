package userapi;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import gamecycle.UserAPI;

public class UserKey implements KeyListener {

	@Override
	public void keyPressed(KeyEvent e) {
		UserAPI.reciveEvent(UserAPI.Event.KEY_PRESSED, e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		UserAPI.reciveEvent(UserAPI.Event.KEY_RELEASED, e);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		UserAPI.reciveEvent(UserAPI.Event.KEY_TYPED, e);
	}

}
