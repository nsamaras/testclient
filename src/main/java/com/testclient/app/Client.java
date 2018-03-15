package com.testclient.app;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;
 
public class Client {
	private static Socket socket;
	private static UUID uuid; 
	public static void main(String args[])
	{
	    Scanner input=new Scanner(System.in);
	    while(true)
	    {
	        try
	        {
	            String host = "localhost";
	            int port = 25000;
	            InetAddress address = InetAddress.getByName(host);
	            socket = new Socket(address, port);
	            //System.out.println("You're now connected to the Server"); /*this should only print once */
	            //Send the message to the server
	            OutputStream os = socket.getOutputStream();
	            OutputStreamWriter osw = new OutputStreamWriter(os);
	            BufferedWriter bw = new BufferedWriter(osw);

	            String msg;
	            msg=input.next();
	            String sendMessage = msg + "\n";
	            bw.write(sendMessage);
	            bw.flush();
	            System.out.println("Message sent to the server : "+sendMessage);
	            
	            if(getSessionId() == null) {
	            	UUID uuid = MessagesUtil.getUuid();
	            	setSessionId(uuid);
	            	System.out.println("UUID "+sendMessage);
	            } else {
	            	System.out.println("Has uuid ");
	            }
 	            
	            //Get the return message from the server
	            InputStream is = socket.getInputStream();
	            InputStreamReader isr = new InputStreamReader(is);
	            BufferedReader br = new BufferedReader(isr);
	            String message = br.readLine();
	            System.out.println("Message received from the server : " +message);
	            
	            if(message.contains("BYE")) {
	            	System.out.println("message contains BYE ");
	            	is.close();
	            	os.close();
					socket.close();
					break;
	            }
	        }
	        catch (IOException exception)
	        {
	            //System.out.println("Server is still offline");/*This should only print once*/
	        }
	    }
	}
	public static UUID getSessionId() {
		return uuid;
	}
	public static void setSessionId(UUID uuid) {
		Client.uuid = uuid;
	}
	
}
