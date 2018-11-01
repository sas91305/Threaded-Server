/*
 * This is a generic client class that asks for the username that they would like
and stores it along with the socket into the online HashMap
 */
package serverproject;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client 
{
    public static void main(String[] args) throws IOException
    {
        Socket client=new Socket("localhost",1234);
        PrintWriter toServer=new PrintWriter(client.getOutputStream(), true);
        Scanner fromServer=new Scanner(client.getInputStream());
        Scanner input=new Scanner(System.in);
        
        while(true)
        {
            System.out.println("Connected:");
            System.out.println("Enter the username you would like");
            String username=input.nextLine();
            toServer.println(username);//Sends username entered by tlhe client
            System.out.println();
            
            System.out.println(fromServer.nextLine());//Online client list
            System.out.println();
            
            System.out.println(fromServer.nextLine());//Chat instructions
            System.out.println();
            
            String message=input.nextLine();
            toServer.println(message);//Beginning message to another client
            
            if(fromServer.hasNextLine())
            {
                System.out.println("Message from client: ");
                String firstMessage=fromServer.nextLine();
                System.out.println(firstMessage);
            }
        }
    }
}

