package com.cleancoder.args;

import static com.cleancoder.args.ErrorCode.*;

import java.util.*;

public class IntegerArgument implements ArgumentInterface {
	
	private int intValue;
	
    IntegerArgument(){
    	intValue = 0;
    }
    
    public void set(Iterator < String > currentArgument) throws ArgsException {
        String parameter = null;
        try {
            parameter = currentArgument.next();
            intValue = Integer.parseInt(parameter);
        } catch (NoSuchElementException e) {
            throw new ArgsException(MISSING_INTEGER);
        } catch (NumberFormatException e) {
            throw new ArgsException(INVALID_INTEGER, parameter);
        }
    }

    public static int getValue(ArgumentInterface am) {
    	return isValidIntegerArgumentMarshaler(am) ? getIntegerValue(am) : 0;
    }
    
    private static int getIntegerValue(ArgumentInterface am) {
    	 return ((IntegerArgument) am).intValue;
    }
    
    private static boolean isValidIntegerArgumentMarshaler(ArgumentInterface am) {
    	if(am != null && am instanceof IntegerArgument)
    		return true;
    	return false;
    }
    
}