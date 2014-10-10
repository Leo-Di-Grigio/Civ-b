package main;

import java.io.IOException;

import control.GUI;
import server.Server;

public class Main {

	public static void main(String [] args) throws IOException {
		new Thread(new Server()).start();
		new Thread(new GUI()).start();
	}
}
