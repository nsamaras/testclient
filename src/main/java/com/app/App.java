package com.app;

public class App {

	public static void main(String... s) {
		new SocketServerExample().start();
		for(int i=0; i<3; i++){
			new SocketClientExample().start();
		}
		
	}
}
