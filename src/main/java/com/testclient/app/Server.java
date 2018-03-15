package com.testclient.app;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
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
	 public static void main(String[] args) throws IOException {
	 try
	    {
	        int port = 25000;
	        ServerSocket serverSocket = new ServerSocket(port);
	        //Server is running always. This is done using this while(true) loop
	        while(true)
	        {
	            //Reading the message from the client
	            socket = serverSocket.accept();
	            System.out.println("Client has connected!");
	            InputStream is = socket.getInputStream();
	            InputStreamReader isr = new InputStreamReader(is);
	            BufferedReader br = new BufferedReader(isr);
	            
	            String msg = br.readLine();
	            System.out.println("Message received from client is "+msg);

	            //Multiplying the number by 2 and forming the return message
	            String returnMessage;
	            try
	            {
	            	String returnValue;
	            	if("BYE".equals(msg)) {
	            		returnValue = MessagesUtil.getServerEndMsg(msg, 30);
	            	} else {
	            		returnValue = "HELLO "+ msg;
	            	}	                 
	                returnMessage = String.valueOf(returnValue) + "\n";
	            }
	            catch(Exception e)
	            {
	                //Input was not a number. Sending proper message back to client.
	                returnMessage = "Please send a proper number\n";
	            }

	            //Sending the response back to the client.
	            OutputStream os = socket.getOutputStream();
	            OutputStreamWriter osw = new OutputStreamWriter(os);
	            BufferedWriter bw = new BufferedWriter(osw);
	            bw.write(returnMessage);
	            System.out.println("Message sent to the client is "+returnMessage);
	            bw.flush();
	        }
	    }
	    catch (Exception e)
	    {
	        e.printStackTrace();
	    }
	 }
}