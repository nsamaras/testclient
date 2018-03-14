package com.app;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import com.testclient.app.MessagesUtil;

public class SocketClientExample {

	public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException{
        //get the localhost IP address, if server is running on some other IP, you need to use that
        InetAddress host = InetAddress.getLocalHost();
        Socket socket = null;
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        
        for(int i=0; i<5;i++){
        	MessageBean message = new MessageBean();
        	message.setName(MessagesUtil.generateName());
            //establish socket connection to server
            socket = new Socket(host.getHostName(), 5000);
            //write to socket using ObjectOutputStream
            oos = new ObjectOutputStream(socket.getOutputStream());
            System.out.println("Sending request to Socket Server");
            if(i==4) {
            	message.setClientMessage(MessagesUtil.getClientEndMsg());
            	oos.writeObject(message);
            } else  {
            	message.setClientMessage(MessagesUtil.getClientHiMsg(message.getName()));
            	oos.writeObject(message);
            }
            //read the server response message
            ois = new ObjectInputStream(socket.getInputStream());
            message = (MessageBean) ois.readObject();
            System.out.println("Server Msg: " + message.getServerMessage());
            
            //close resources
            ois.close();
            oos.close();
            Thread.sleep(100);
        }
    }
}
