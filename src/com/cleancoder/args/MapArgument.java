package com.cleancoder.args;

import java.util.HashMap;

import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

import static com.cleancoder.args.ErrorCode.*;

public class MapArgument implements ArgumentInterface {
    
	private Map < String, String > map;

    MapArgument(){
    	map = new HashMap < > ();
    }
    
    public void set(Iterator < String > currentArgument) throws ArgsException {
    	splitIntoMapEntries(currentArgument);
    }
    

    public static Map<String, String> getValue(ArgumentInterface Object) {
    	return isValidMapArgumentMarshaler(Object) ? getMap(Object) : new HashMap< >();
    }
    

    void splitIntoMapEntries(Iterator < String > currentArgument) throws ArgsException {
    	String[] mapEntries = currentArgument.next().split(",");
    	splitEachMapEntryAndInsertInMap(mapEntries);
    }
    
    
    void splitEachMapEntryAndInsertInMap(String[] entries) throws ArgsException {
    	try{
    		for(String entry : entries) {
	    		String[] entryComponents = entry.split(":");
	            if (entryComponents.length != 2)
	                throw new ArgsException(MALFORMED_MAP);
	            map.put(entryComponents[0], entryComponents[1]);
    		}
    	}
    	catch(NoSuchElementException e){
    		throw new ArgsException(MISSING_MAP);
    	}
    }
 
    
    private static Map<String,String> getMap(ArgumentInterface Object) {
    	 return ((MapArgument) Object).map;
    }
    
    private static boolean isValidMapArgumentMarshaler(ArgumentInterface Object) {
    	if(Object != null && Object instanceof MapArgument)
    		return true;
    	return false;
    }
    
    
    
    
    
    
}