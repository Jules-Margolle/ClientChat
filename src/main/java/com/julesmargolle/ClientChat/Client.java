package com.julesmargolle.ClientChat;

import java.net.Socket;

public class Client 
{
    private Socket socket;
    private IOCommands io;
    
    Client(String ip, int port)
    {
        try
        {
            this.socket = new Socket(ip, port);
            this.io = new IOCommands(this.socket);
        }
        catch(Exception e)
        {
            System.err.println("Client creation failed : " + e.getMessage());   
        }
    }

    void start()
    {
        io.toScreen(io.fromNetwork());

        while(!log());

        while(true)
        {
            String input = io.fromScreen();

            io.toNetwork(input);

            String serverResponse = io.fromNetwork();

            if(serverResponse.startsWith("OK"))
            {
                io.toScreen(serverResponse);
                if(input.equals("MESSAGES"))
                {
                    int numberOfMessages = Integer.parseInt(serverResponse.substring(serverResponse.indexOf(":") + 1, serverResponse.indexOf(" ")));
                    if(numberOfMessages > 0)
                    {
                        for(int i = 0; i < numberOfMessages; i++)
                        {
                            String message = io.fromNetwork();
                            io.toScreen(message);
                        }
                    }
                }
                else if(input.equals("QUIT"))
                {
                    quitCommand();
                    break;
                }
                
            }
            else
            {
                io.toScreen(serverResponse);
            }
        }
    }

    boolean log()
    {
        String input = io.fromScreen();
        io.toNetwork(input);
        String serverResponse = io.fromNetwork();
        
        if(serverResponse.startsWith("OK"))
        {
            io.toScreen(serverResponse);
            return true;
        }
        else
        {
            io.toScreen(serverResponse);
            return false;
        }
    }

    void quitCommand()
    {
        try
        {
            this.socket.close();
        }
        catch(Exception e)
        {
            System.err.println("Client quit failed : " + e.getMessage());
        }
        
        io.toScreen("Bye bye !");
    }
}
