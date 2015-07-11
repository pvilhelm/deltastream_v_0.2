/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deltastream;

import java.net.URI;
import java.util.Date;

/**
 *
 * @author petter
 */
public class Client {
    public final URI _clientAddress;
    public final Date _firstContact;
    
    public double averageDLSpeed;
    public double averageULSpeed;
     
    
    public Client(URI _clientAddress){
        this._clientAddress = _clientAddress; 
        _firstContact = new Date();
    }
    
} 
