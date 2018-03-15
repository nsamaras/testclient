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
import java.util.Scanner;
 
public class Client {
	private static Socket socket;

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

	            //Get the return message from the server
	            InputStream is = socket.getInputStream();
	            InputStreamReader isr = new InputStreamReader(is);
	            BufferedReader br = new BufferedReader(isr);
	            String message = br.readLine();
	            System.out.println("Message received from the server : " +message);
	        }
	        catch (IOException exception)
	        {
	            //System.out.println("Server is still offline");/*This should only print once*/
	        }
	    }
	}
}
