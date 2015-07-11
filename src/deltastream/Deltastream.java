/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deltastream;

/**
 *
 * @author petter
 */
public class Deltastream{
    static public ListOfLocalParts _listOfLocalParts;
    static public Broadcast _broadcast;
    static public Config _config;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        _config = new Config();
        _broadcast = new Broadcast();
        _listOfLocalParts = new ListOfLocalParts();
        ServerUDPrx _serverUDPrx = new ServerUDPrx();
        
        Thread _serverUDPrxThread = new Thread(_serverUDPrx,"Broadcast Source UDP Rx Server");
        _serverUDPrxThread.start();

         
        
    }
    
}
