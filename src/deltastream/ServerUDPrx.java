/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deltastream;

import java.io.InputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Socket;

/**
 *
 * @author petter
 */
public class ServerUDPrx implements Runnable{
    DatagramSocket serverSocket;
    
    
    @Override 
    public void run(){
        
        Socket serverUDPrxSocket;           //the stream connection


        try{      
            serverSocket = new DatagramSocket(Config.inputStreamPort);
            serverSocket.setReceiveBufferSize(100000);
            System.out.println("Waiting for Inpout Stream on local port"+Config.inputStreamPort);
        }
        catch(Exception ee){
            System.out.println("Couln't accept inpu udp stream connection: " + ee);
            return; 
        }
        
        byte[] buffer = new byte[1500];
    
        DatagramPacket packet;
        for(;;){

            try{
                packet = new DatagramPacket(buffer, buffer.length);
                serverSocket.receive(packet);
            }
            catch(Exception ee){
                System.out.println("Couln't read server UDP rx: "+ee);
                break;
            }
            
            Part _receivedPart = new Part();
            _receivedPart.MakeOriginalPart(packet);//make part of rxed UDP datagram
            Deltastream._listOfLocalParts.PutPart(_receivedPart);
            
        }
    }
}
