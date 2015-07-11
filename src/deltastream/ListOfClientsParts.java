/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deltastream;

import java.util.BitSet;

/**
 *
 * @author petter
 */
public class ListOfClientsParts {
    public int packageOffset;
    public BitSet _bitlistParts;
    
    public ListOfClientsParts(){
        _bitlistParts = new BitSet();
        
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
    
    
    
    
}
