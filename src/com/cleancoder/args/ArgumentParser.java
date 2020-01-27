package com.cleancoder.args;

import java.util.*;

import static com.cleancoder.args.ErrorCode.*;

public class ArgumentParser {
    private Map < Character, ArgumentInterface > characterToObjectMap;
    private Set < Character > argsFound;
    private ListIterator < String > currentArgument;

    public ArgumentParser(String schema, String[] args) throws ArgsException {
    	characterToObjectMap = new HashMap < Character, ArgumentInterface > ();
        argsFound = new HashSet < Character > ();
        
        parseSchema(schema);
        parseArgumentStrings(Arrays.asList(args));
    }

    private void parseSchema(String schema) throws ArgsException {
        for (String element: schema.split(","))
            if (element.length() > 0)
                parseSchemaElement(element.trim());
    }

    private void parseSchemaElement(String element) throws ArgsException {
        char elementId = element.charAt(0);
        String elementTail = element.substring(1);
        
        validateSchemaElementId(elementId);
        insertElementIncharacterToObjectMap(elementId,elementTail);
    }
    
    private void insertElementIncharacterToObjectMap(char elementId,String elementTail) throws ArgsException {

    	switch(elementTail)
    	{
    		case "":
    			characterToObjectMap.put(elementId, new BooleanArgument());
    			break;
    		case "*":
            	characterToObjectMap.put(elementId, new StringArgument());
            	break;
    		case "#":
            	characterToObjectMap.put(elementId, new IntegerArgument());
            	break;
    		case "##":
            	characterToObjectMap.put(elementId, new DoubleArgument());
            	break;
    		case "[*]":
            	characterToObjectMap.put(elementId, new StringArrayArgument());
            	break;
    		case "&":
            	characterToObjectMap.put(elementId, new MapArgument());
            	break;
    		default:
                throw new ArgsException(INVALID_ARGUMENT_FORMAT, elementId, elementTail);
    	}
     }


    private void validateSchemaElementId(char elementId) throws ArgsException {
        if (!Character.isLetter(elementId))
            throw new ArgsException(INVALID_ARGUMENT_NAME, elementId, null);
    }

    private void parseArgumentStrings(List < String > argsList) throws ArgsException {
        for (currentArgument = argsList.listIterator(); currentArgument.hasNext();) {
            String argString = currentArgument.next();
            
            if (argString.startsWith("-") && argString.length()==2) {
                parseArgumentCharacters(argString.substring(1));
            } else {
            	throw new ArgsException(INVALID_ARGUMENT_FORMAT,argString);
            }
        }
    }
   
    private void parseArgumentCharacters(String argChars) throws ArgsException {
        for (int i = 0; i < argChars.length(); i++)
            parseArgumentCharacter(argChars.charAt(i));
    }

    private void parseArgumentCharacter(char argChar) throws ArgsException {
        ArgumentInterface argumentInterfaceObject = characterToObjectMap.get(argChar);
        if (argumentInterfaceObject == null) {
            throw new ArgsException(UNEXPECTED_ARGUMENT, argChar, null);
        } else {
            argsFound.add(argChar);
            setArgument(argumentInterfaceObject,argChar); 
        }
    }
    
   
    void setArgument(ArgumentInterface argumentInterfaceObject,char argChar) throws ArgsException {
    	try {
    		argumentInterfaceObject.set(currentArgument);
        } catch (ArgsException e) {
            e.setErrorArgumentId(argChar);
            throw e;
        }
    }
    


    public boolean has(char arg) {
        return argsFound.contains(arg);
    }
   
    public int nextArgument() {
    	return currentArgument.nextIndex();
    }
    
    
    public boolean getBoolean(char arg) {
        return BooleanArgument.getValue(characterToObjectMap.get(arg));
    }

    public String getString(char arg) {
        return StringArgument.getValue(characterToObjectMap.get(arg));
    }

    public int getInt(char arg) {
        return IntegerArgument.getValue(characterToObjectMap.get(arg));
    }

    public double getDouble(char arg) {
        return DoubleArgument.getValue(characterToObjectMap.get(arg));
    }

    public String[] getStringArray(char arg) {
        return StringArrayArgument.getValue(characterToObjectMap.get(arg));
    }

    public Map < String, String > getMap(char arg) {
        return MapArgument.getValue(characterToObjectMap.get(arg));
    }

	public boolean getValue(char c) {
		// TODO Auto-generated method stub
		return false;
	}
}