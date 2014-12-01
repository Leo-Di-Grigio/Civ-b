package main;

import java.io.IOException;

import misc.Tools;
import network.Server;
import control.GUI;

public class Main {

	public static void main(String [] args) throws IOException {
		new Tools();
		new Thread(new Server()).start();
		new Thread(new GUI()).start();
	}
}
