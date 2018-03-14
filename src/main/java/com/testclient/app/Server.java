package com.testclient.app;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.UUID;

public class Server {

	private static Socket socket;

	public static void main(String[] args) {
		
		String returnMessage;
		
		try {
			int port = 5000;
			ServerSocket serverSocket = new ServerSocket(port);
			System.out.println("Server Started and listening to the port 5000 \n");

			// Server is running always. This is done using this while(true) loop
			while (true) {
				// Reading the message from the client
				socket = serverSocket.accept();
				InputStream is = socket.getInputStream();
				InputStreamReader isr = new InputStreamReader(is);
				BufferedReader br = new BufferedReader(isr);
				UUID uuid = new GenerateUUID().getUuid();
				returnMessage = MessagesUtil.getServerHelloMsg(uuid);

				// HI, I’M <session-id>
				OutputStream os = socket.getOutputStream();
				OutputStreamWriter osw = new OutputStreamWriter(os);
				BufferedWriter bw = new BufferedWriter(osw);
				bw.write(returnMessage);
				bw.flush();
				System.out.println("Message sent to the client is " + returnMessage);
				
				// HI <name>
				InputStream is_2 = socket.getInputStream();
				InputStreamReader isr_2 = new InputStreamReader(is_2);
				BufferedReader br_2 = new BufferedReader(isr_2);
				String name = br_2.readLine();
				OutputStream os_2 = socket.getOutputStream();
				OutputStreamWriter osw_2 = new OutputStreamWriter(os_2);
				BufferedWriter bw_2 = new BufferedWriter(osw_2);
				bw_2.write(MessagesUtil.getServerResponceMsg(name));
				System.out.println(MessagesUtil.getServerResponceMsg(name));
				bw_2.flush();
			}
		} catch (Exception e) {
			returnMessage = MessagesUtil.getNotSupportedMsg();
		} finally {
			try {
				socket.close();
			} catch (Exception e) {
			}
		}
	}
}