/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deltastream;

import java.net.DatagramPacket;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.util.Arrays;
//import deltastream.Deltastream;
import java.util.Date;

/**
 *
 * @author petter
 */
public class Part {
    
    long key;
    int pkgNr;
    short frameNr;
    Date _timeCreated; 
    DatagramPacket _UDPdatagram;
    InetSocketAddress _sourceSocketAddress;
    
    public void MakeOriginalPart(DatagramPacket _IncUDPdatagram){
        _sourceSocketAddress =  new InetSocketAddress(_IncUDPdatagram.getAddress(),_IncUDPdatagram.getPort());
        byte data[] = Arrays.copyOfRange(_IncUDPdatagram.getData(),0,_IncUDPdatagram.getLength());
        
        ByteBuffer _bytebuffer = ByteBuffer.allocate(data.length+20);
        _bytebuffer.put((byte) 0x55);
        _bytebuffer.put(Deltastream._broadcast.broadcastId);
        
        if(Deltastream._broadcast.latestFrameNr<Deltastream._broadcast.frameSize){
            _bytebuffer.putInt(pkgNr = Deltastream._broadcast.latestPkgNr);
            _bytebuffer.putShort(frameNr = ++Deltastream._broadcast.latestFrameNr);
        }
        else{
            _bytebuffer.putInt(pkgNr=++Deltastream._broadcast.latestPkgNr);
            _bytebuffer.putShort(Deltastream._broadcast.latestFrameNr=0);
            //TODO make signature datagram            
        }
        
        _bytebuffer.putLong(new Date().getTime());
        _bytebuffer.put(data);
        
        _UDPdatagram = new DatagramPacket(_bytebuffer.array(),_bytebuffer.array().length);
        
        key = (long)frameNr+(((long) pkgNr)<<16);
        
         
    }
}
