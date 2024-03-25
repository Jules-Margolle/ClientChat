package com.julesmargolle.ClientChat;

public class App 
{
    public static void main( String[] args )
    {
        Client client = new Client("192.0.0.2", 8585);
        client.start();
    }
}
