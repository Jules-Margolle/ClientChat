package com.julesmargolle.ClientChat;

public class App 
{
    public static void main( String[] args )
    {
        Client client = new Client("192.168.1.14", 8585);
        client.start();
        
    }
}
