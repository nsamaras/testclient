package com.testclient.app;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
 
public class Client
{
 
    private static Socket socket;
 
    public static void main(String args[])
    {
        try
        {
            String host = "localhost";
            int port = 5000;
            InetAddress address = InetAddress.getByName(host);
            socket = new Socket(address, port);
 
            //Send the message to the server
            OutputStream os = socket.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(osw);
 
            String firstMsgFromClient = "***********************\n";

            bw.write(firstMsgFromClient);
            bw.flush();
            System.out.println("Message sent to the server : "+firstMsgFromClient);
 
            //Get the return message from the server
            InputStream is = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String message = br.readLine();
            System.out.println("Server Msg : " +message);
            
            // HI, I’M <name>
            String name = MessagesUtil.generateName();
            String msg = MessagesUtil.getClientHiMsg(name);
            bw.write(msg);
            bw.flush();
            System.out.println("HI I'M " + name);
            
            
            
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        finally {
            //Closing the socket
            try {
            	System.out.println("Wating 30 sec");            	
//                socket.close();                
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
}