/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deltastream;

import java.util.Hashtable;

/**
 *
 * @author petter
 */
public class ListOfClients {
    Hashtable<String,Client> clientHashtable;
    
    ListOfClients(Broadcast broadcast){
        //allClients = new Client[1000];//waste of space ...
        clientHashtable = new Hashtable(1400);
         
    }

}
