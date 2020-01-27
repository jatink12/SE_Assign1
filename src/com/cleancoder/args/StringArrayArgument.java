package com.cleancoder.args;

import static com.cleancoder.args.ErrorCode.*;

import java.util.*;

public class StringArrayArgument implements ArgumentInterface {
    private List < String > strings;

    StringArrayArgument(){
    	strings = new ArrayList < String > ();
    }
    
    public void set(Iterator < String > currentArgument) throws ArgsException {
        try {
            strings.add(currentArgument.next());
        } catch (NoSuchElementException e) {
            throw new ArgsException(MISSING_STRING);
        }
    }
    
    public static String[] getValue(ArgumentInterface Object) {
    	return isValidStringArrayArgumentMarshaler(Object) ? getStringArrayValue(Object) : new String[0];
    }
    
    private static String[] getStringArrayValue(ArgumentInterface Object) {
    	 return ((StringArrayArgument) Object).strings.toArray(new String[0]);
    }
    
    private static boolean isValidStringArrayArgumentMarshaler(ArgumentInterface Object) {
    	if(Object != null && Object instanceof StringArrayArgument)
    		return true;
    	return false;
    }
    
}