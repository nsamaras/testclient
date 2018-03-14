package com.app;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.ClassNotFoundException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.UUID;

import com.testclient.app.MessagesUtil;
public class SocketServerExample {

	//static ServerSocket variable
    private static ServerSocket server;
    //socket server port on which it will listen
    private static int port = 5000;
    
    public static void main(String args[]) throws IOException, ClassNotFoundException, InterruptedException{
        //create the socket server object
        server = new ServerSocket(port);
        //keep listens indefinitely until receives 'exit' call or program terminates
        while(true){
            System.out.println("Waiting for client request");
            //creating socket and waiting for client connection
            Socket socket = server.accept();
            
            //read from socket to ObjectInputStream object
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            //convert ObjectInputStream object to String
            MessageBean message = (MessageBean) ois.readObject();
            UUID uuid = MessagesUtil.getUuid();
            message.setUuid(uuid);
            message.setServerMessage(MessagesUtil.getServerHelloMsg(uuid));
            System.out.println("Client Msg :"+message.getClientMessage());
            
            //create ObjectOutputStream object
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            
            if(message.getClientMessage().equalsIgnoreCase(MessagesUtil.getClientEndMsg())) {
            	message.setServerMessage(MessagesUtil.getServerEndMsg(message.getName(), 30));
            } else {
                message.setServerMessage(MessagesUtil.getServerResponceMsg(message.getName()));
            }
            oos.writeObject(message);

        }
    }
}
