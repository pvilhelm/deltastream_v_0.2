/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deltastream;

import java.util.Random;

 
 

/**
 *
 * @author petter
 */
public class Config {
    //this is a global conig class

    /**
     *
     */
    public static int inputStreamPort = 1081;  //the port listening the extrenal stream to be broadcasted

    /**
     *
     */
    public static int SamplingPeriod = 100;  //time in ms for sampling the input stream

    /**
     *
     */
    public static int timeLimit = 60*1000;   //how old a part is allowed to be in the broadcast

    /**
     *
     */
    public static int nOfParts = timeLimit/SamplingPeriod+10; //how many parts to save (internal use)

    /**
     *
     */
    public static int clientServerSocketPort = 1082;   //the port for generic com with clients

    /**
     *
     */
    public static int genericStreamBufferSize = 1000000; //the buffer size in bytes for some streams

    /**
     *
     */
    public static int clientOutputServerSocketPort = 1084 ;

    public static int serverPartBufferSize = 1000;

    /**
     *
     */
    public static int localUDPOutputStreamPort = 4444;

    /**
     *
     */
    public static int remoteUDPOutputStreamPort = 4444;

    /**
     *
     */
    public static String remoteUDPOutputStreamIP = "127.0.0.1";
    
    /**
     * 
     */
    
    public static double UDPBroadcastWaitCoeff = 0.8;
    
    public static String rootNodeIP = "";
    public static int rootNodePort = inputStreamPort;
    
    
    Config(){
        //arguments go here TODO
    }
    
    Broadcast CreateBroadcast(){
        Broadcast broadcast = new Broadcast();
        
        broadcast.timeLimit = timeLimit; //hur gamla parts får bli
        Random rand = new Random();
        
        broadcast.listOfClients = new ListOfClients(broadcast);
        
         
        
        //setup DSA signatures
        
        
        return broadcast;        
    }
}
