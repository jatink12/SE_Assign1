package com.cleancoder.args;

import java.util.Iterator;

import java.util.NoSuchElementException;

import static com.cleancoder.args.ErrorCode.MISSING_STRING;

public class StringArgument implements ArgumentInterface {
    private String stringValue;

    StringArgument(){
    	stringValue = "";
    }
    
    public void set(Iterator < String > currentArgument) throws ArgsException {
        try {
            stringValue = currentArgument.next();
        } catch (NoSuchElementException e) {
            throw new ArgsException(MISSING_STRING);
        }
    }

    public static String getValue(ArgumentInterface Object) {
    	return isValidStringArgumentMarshaler(Object) ? getStringValue(Object) : "";
    }
    
    private static String getStringValue(ArgumentInterface Object) {
    	 return ((StringArgument) Object).stringValue;
    }
    
    private static boolean isValidStringArgumentMarshaler(ArgumentInterface Object) {
    	if(Object != null && Object instanceof StringArgument)
    		return true;
    	return false;
    }
    
}