package runcanthide;

import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Input {

	private static ArrayList<Integer> Keys;
	private static ArrayList<Boolean> KeyPressed;
	private static ArrayList<Boolean> KeyLastState;	// true is down, false is up

	public static boolean IsKeyPressed(int Key) {
		if (Keys.contains(Key)) {
			int Pos = Keys.indexOf(Key);
			if (KeyPressed.get(Pos) && !KeyLastState.get(Pos)) {
				KeyLastState.set(Pos, true);
				return true;
			}
		}
		return false;
	}

	public static boolean IsKeyReleased(int Key) {
		if (Keys.contains(Key)) {
			int Pos = Keys.indexOf(Key);
			if (!KeyPressed.get(Pos) && KeyLastState.get(Pos)) {
				KeyLastState.set(Pos, false);
				return true;
			}
		}
		return false;
	}

	public static boolean IsKeyDown(int Key) {
		if (Keys.contains(Key)) {
			return KeyPressed.get(Keys.indexOf(Key));
		} else {
			Keys.add(Key);
			KeyPressed.add(false);
			KeyLastState.add(false);
			SetKey(Key, false);
			return false;
		}
	}

	private static void SetKey(int KeyCode, boolean Up) {
		int Pos = Keys.indexOf(KeyCode);
		KeyLastState.set(Pos, KeyPressed.get(Pos));
		KeyPressed.set(Pos, Up);
	}

	public static void SetUpInput() {
		Keys = new ArrayList<>(32);
		KeyPressed = new ArrayList<>(32);
		KeyLastState = new ArrayList<>(32);
		KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {
			@Override
			public boolean dispatchKeyEvent(KeyEvent e) {
				switch (e.getID()) {
					case KeyEvent.KEY_PRESSED:
						if (Keys.contains(e.getKeyCode())) {
							SetKey(e.getKeyCode(), true);
						} else {
							Keys.add(e.getKeyCode());
							KeyPressed.add(true);
							KeyLastState.add(false);
						}
						break;
					case KeyEvent.KEY_RELEASED:
						SetKey(e.getKeyCode(), false);
						break;
				}
				return false;
			}
		});
	}
}
