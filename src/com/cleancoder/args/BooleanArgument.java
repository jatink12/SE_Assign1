package com.cleancoder.args;

import java.util.Iterator;

public class BooleanArgument implements ArgumentInterface {
    private boolean booleanValue;

    BooleanArgument(){
    	booleanValue = false;
    }
    
    public void set(Iterator < String > currentArgument) throws ArgsException {
        booleanValue = true;
    }

    public static boolean getValue(ArgumentInterface Object) {
    	return isValidBooleanArgumentObject(Object) ? getBooleanValue(Object) : false;

    }
    
    private static boolean getBooleanValue(ArgumentInterface Object) {
    	return ((BooleanArgument)Object).booleanValue;
    	
    }
 
    private static boolean isValidBooleanArgumentObject(ArgumentInterface Object) {
    	if(Object != null && Object instanceof BooleanArgument)
    		return true;
    	return false;
    }
    
    
}