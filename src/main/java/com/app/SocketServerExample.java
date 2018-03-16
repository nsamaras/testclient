package com.app;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.testclient.app.MessagesUtil;

public class SocketServerExample extends Thread {

	// static ServerSocket variable
	private static ServerSocket server;
	// socket server port on which it will listen
	private static int port = 5000;
	private static List<MessageBean> beans = new ArrayList<>();

	@Override
	public void run() {
		try {
			server = new ServerSocket(port);
			MessageBean bean = new MessageBean();
			while (true) {
				Socket socket = server.accept();
				// create ObjectOutputStream object
				ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
				newClientOnServer(bean);
				
				if (bean.getName() == null) {
					bean.setServerMessage("HI, I'M " + bean.getUuid());
				} else if("BYE".equals(bean.getClientMessage())) {
					bean.setServerMessage(MessagesUtil.getServerEndMsg(bean.getName(), 30));
				} else {
					bean.setServerMessage("HI " + bean.getName());
				} 
				oos.writeObject(bean);

				// read from socket to ObjectInputStream object
				ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
				// convert ObjectInputStream object to String
				bean = (MessageBean) ois.readObject();
				bean.setServerMessage("HI " + bean.getName());
				oos.writeObject(bean);

			}
		} catch (Exception e) {

		}

	}

	private void newClientOnServer(MessageBean bean) {
		if(bean.getUuid() == null) {
			bean.setUuid(MessagesUtil.getUuid());
			beans.add(bean);
		} 
	}
	
	

}
