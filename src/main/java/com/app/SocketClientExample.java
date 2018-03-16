package com.app;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

import com.testclient.app.MessagesUtil;

public class SocketClientExample extends Thread {

	public void run() {
		try {
			// get the localhost IP address, if server is running on some other
			// IP, you need to use that
			InetAddress host = InetAddress.getLocalHost();
			Socket socket = null;
			ObjectOutputStream oos = null;
			ObjectInputStream ois = null;
			MessageBean bean = new MessageBean();
			Scanner input = new Scanner(System.in);
			while (true) {
				socket = new Socket(host.getHostName(), 5000);
				oos = new ObjectOutputStream(socket.getOutputStream());
				ois = new ObjectInputStream(socket.getInputStream());

				bean = (MessageBean) ois.readObject();
				System.out.println(bean.getServerMessage());

				if(MessagesUtil.getClientEndMsg().equals((bean.getServerMessage()))) {
					break;
				}
				String msg;
				msg = input.next();
				if(bean.getName() == null)
					bean.setName(msg);
				else {
					bean.setClientMessage(msg);
				} 
				
				oos.writeObject(bean);
			}
			ois.close();
        	oos.close();
			socket.close();
			input.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
