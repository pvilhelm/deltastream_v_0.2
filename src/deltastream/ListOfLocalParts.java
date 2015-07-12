/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deltastream;

import java.net.DatagramPacket;
import java.util.BitSet;
import java.util.Iterator;
 
import java.util.NavigableMap;
import java.util.NavigableSet;
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
        this.UpdateBitlistParts();
    }
    
    public synchronized void UpdateBitlistParts(){
        //TODO enforce size of field to 1400 or w/e
        
        
        NavigableSet<Long> _keys = _tableOfParts.descendingKeySet();
        long firstPkgNr = _keys.last()>>>16;
        long lastPkgNr = _keys.first()>>>16;
        int pkgSpann = (int)lastPkgNr - (int) firstPkgNr;
         
        if(pkgSpann>Deltastream._config.serverPartBufferSize){
            pkgSpann = Deltastream._config.serverPartBufferSize;
        }
        if(pkgSpann < 0){
            int a = 2;}
        _bitlistParts = new BitSet(pkgSpann+1);
         
        for (Long  key: _keys){
            long pkgNrTmp = key>>16;
            if(pkgNrTmp-firstPkgNr <= pkgSpann)
                if(pkgNrTmp!=lastPkgNr)//TODO make so know its last before next comes!
                    _bitlistParts.set((int) (pkgNrTmp-firstPkgNr));
            
        }
        byte test[]=_bitlistParts.toByteArray();
    }
}
