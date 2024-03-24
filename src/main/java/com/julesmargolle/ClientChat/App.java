package com.julesmargolle.ClientChat;

public class App 
{
    public static void main( String[] args )
    {
        Client client = new Client("localhost", 8585);
        client.start();
    }
}
