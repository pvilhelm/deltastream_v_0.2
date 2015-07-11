/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deltastream;

import java.net.ServerSocket;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.util.concurrent.BlockingQueue;

/**
 *
 * @author petter
 */
public class Broadcast {
    int timeLimit;
    byte broadcastId[]; //the broadcast Id
    int latestPkgNr; //the lastest package number made
    short latestFrameNr; 
    int frameSize = 10;
    
    
    ListOfClients listOfClients;
    
    
    Config _config;
    
    public Broadcast(){
        
        broadcastId = new byte[5];
        for (int i = 0;i<broadcastId.length;i++){
            broadcastId[i]=1;
        }
    }
}
