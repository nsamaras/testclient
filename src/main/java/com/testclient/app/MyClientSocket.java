package com.testclient.app;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class MyClientSocket {

	private Socket socket;
    private Scanner scanner;
    private MyClientSocket(InetAddress serverAddress, int serverPort) throws Exception {
        this.socket = new Socket(serverAddress, serverPort);
        this.scanner = new Scanner(System.in);
    }
    private void start() throws IOException {
    	System.out.println("start client: " );
    	String input;
    	long start = System.currentTimeMillis();
    	long end = start + 60*1000; // 60 seconds * 1000 ms/sec
        while (System.currentTimeMillis() < end) {
        	this.socket.close();
        	System.out.println("end connection");
            input = scanner.nextLine();                       
        }
    }
    
    public static void main(String[] args) throws Exception {
        MyClientSocket client = new MyClientSocket(InetAddress.getByName("127.0.0.1"), Integer.parseInt("49322"));
        System.out.println("\r\nConnected to Server: " + client.socket.getInetAddress());
        client.start(); 
    }
}
