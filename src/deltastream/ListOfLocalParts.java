/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deltastream;

import java.net.DatagramPacket;
import java.util.BitSet;
 
import java.util.NavigableMap;
import java.util.TreeMap;

/**
 *
 * @author petter
 */
public class ListOfLocalParts {
    public int packageOffset; //oldest part nr
    public BitSet _bitlistParts;
    NavigableMap<Long, Part> _tableOfParts;
    
    
    public ListOfLocalParts(){
        _bitlistParts = new BitSet();
        _tableOfParts = new TreeMap();
    }
    
    public synchronized void UpdateBitlistParts(byte[] byteArrayParts, int packageOffset){
        _bitlistParts = BitSet.valueOf(byteArrayParts);
        this.packageOffset = packageOffset;
    }
    
    public synchronized boolean HasPart(int packageNr){
        boolean hasPart;
        
        try{
            hasPart = _bitlistParts.get(packageNr-packageOffset);
        }
        catch(Exception ee){
            return false;//TODO fix
        }
            
        
        return hasPart; 
    }
    
    public synchronized void PutPart(Part part){
        
        if(!_tableOfParts.containsKey(part.key)){
            _tableOfParts.put(part.key, part);
            if(Deltastream._config.serverPartBufferSize<_tableOfParts.size()){
                _tableOfParts.remove(_tableOfParts.firstKey());//remove oldest part
            }
        }
    }
}
