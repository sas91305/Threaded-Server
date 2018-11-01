/*
 * ClientThread is used to allow multiple threads to connect, their information
is added to the online HashMap and then the chatting instructions are sent.
 */
package serverproject;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Scanner;

public class ClientThread extends Thread
{
    private Socket client;
    private PrintWriter out;
    private Scanner in;
    private HashMap<String, Socket> online;
    
    ClientThread(Socket c, HashMap<String, Socket> o) throws Exception
    {
        client=c;
        out=new PrintWriter(client.getOutputStream(), true);
        in=new Scanner(client.getInputStream());
        online=o;
    }
    
    public void run()
    {
        out.println(online);//Sends online client list
        
        out.println("Type recipient’s user name/message to "
                + "send/your user name for chatting or"
                + " Type recipient’s user name/quit for quitting chat "
                + "with a friend ");//Chat instructions
        while(true)
        {
            
        String s=in.nextLine();//Beginning message from client
        //while(true)
        //{
            String[] firstMessage=s.split("/");//splits message by "/"
            
                Socket chosenSocket=online.get(firstMessage[0]);//retrieves socket from username given
                System.out.println("Chosen Socket: "+chosenSocket.toString());
                try//Needs IOException to create PrintWriter with OutputStream
                {
                    PrintWriter toClient=new PrintWriter(chosenSocket.getOutputStream(), true);
                    System.out.println("Message to "+firstMessage[2]+": " + firstMessage[1]);
                    toClient.println(firstMessage[1]+" From: "+ firstMessage[2]);//first message
                } 
                catch(IOException ex) 
                {
                }
            }
    }
}
    
    

