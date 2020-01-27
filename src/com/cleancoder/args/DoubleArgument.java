package com.cleancoder.args;

import static com.cleancoder.args.ErrorCode.*;

import java.util.*;

public class DoubleArgument implements ArgumentInterface {
	
	private double doubleValue;
	
	DoubleArgument(){
		doubleValue = 0;
	}
	
    public void set(Iterator < String > currentArgument) throws ArgsException {
        String parameter = null;
        try {
            parameter = currentArgument.next();
            doubleValue = Double.parseDouble(parameter);
        } catch (NoSuchElementException e) {
            throw new ArgsException(MISSING_DOUBLE);
        } catch (NumberFormatException e) {
            throw new ArgsException(INVALID_DOUBLE, parameter);
        }
    }

    
    public static double getValue(ArgumentInterface Object) {
    	return isValidDoubleArgumentObject(Object) ? getDoubleValue(Object) : 0.0;
    }
    
    private static double getDoubleValue(ArgumentInterface Object) {
    	 return ((DoubleArgument)Object).doubleValue;
    }
    
    private static boolean isValidDoubleArgumentObject(ArgumentInterface Object) {
    	if(Object != null && Object instanceof DoubleArgument)
    		return true;
    	return false;
    }
    
    
    
}