/*
 * Server with ServerSocket for clients to connect too. Also includes the
HashMap that the online servers are stored in
 */
package serverproject;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Scanner;

public class ServerProject 
{
    private static HashMap<String, Socket> onlinelist=new HashMap<>();
    
    public static void main(String[] args) 
    {
        try
        {
            ServerSocket server=new ServerSocket(1234);
            System.out.println("Server is running");
            
            while(true)
            {
                Socket client=server.accept();
                System.out.println("Client it connected");
                Scanner fromClient=new Scanner(client.getInputStream());
                String username=fromClient.nextLine();
                addClient(username, client);
                //Starts ClientThread
                ClientThread c=new ClientThread(client, onlinelist);
                c.start();
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    /**Method used to add online clients to the HashMap
     *
     * @param user
     * @param c
     */
    public static void addClient(String user, Socket c)
    {
        onlinelist.put(user, c);
    }
    
}