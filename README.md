# SE_Assignement1
SOFTWARE ENGINEERING

Assignment – 1 (Clean The Code)

M.Tech CSE
Roll No : 2019201043

1) **EXPLANATION OF IMPLEMENTED FEATURES[EXISTING]**:

      - ArgsMain is basically an StringParser which parse the argument String based on Schema

      - Schema Terminologies:<br/>
          character - boolean argument<br/>
          character*	- String argument<br/>
          character# - Integer argument<br/>
          character## - Double argument<br/>
          character[*]  - String array<br/>
          character&  - Map<br/>

      - ArgsMain creates ArgumentParser class object which parse the schema and Input argument string.

      - While Parsing the schema correspoding to each character and type, object of correspoding Argument type class is created and inserted in map.

      - For each type of data type, Classes with set and get methods implemented using Interface
      ex: for boolean, booleanArgument class with member as boolean variable.

      - While pasing argument string, corresponding reference of object is pulled from map and value set to that object.

      - Example:
        Schema: “x,y*” Arguments: “-x -y IIITH”
        1) Parse schema and create objects i.e,
        x->&BooleanArgument obj 
        y->&StringArgument obj
        2) Parse argument string
        x->pull reference object correspoding to Boolean and set TRUE
        y->pull reference object correspoding to String and set value = nextOfCurrentArgument i.e IIITH.

2) **INSTRUCTIONS**:

      - Instructions on requirements:<br/>
          - Java for Ubuntu/Windows<br/>
          - Java IDE (Eclipse / NetBeans)<br/>
          - Junit Jar<br/>


      - Instructions on Execution:<br/>
          - ArgsMain.java takes two arguments Schema and arguments.<br/>
          - Schema is specified in ArgsMain.<br/>
          - Execute ArgsMains as Java application with String of args corresponding to schema as command line argument.<br/> 
          - Example: Schema = “x,y*”, Arguments = “-x alpha”.<br/>


      - Instruction on Unit Testing:<br/>
          - Exception Testing:<br/>
            - To check all exceptions are working fine or not.<br/>
            - Run ArgsExceptionTest.java as Junit Test<br/>

          - Argument Testing:<br/>
            - Test With different possible schemas and argument patterns.<br/>
            - To check Unit tests in ArgsTest run ArgsTest.java as Junit Test.<br/>


3) **CHANGES MADE**:
      
      - ***Addition of Constructors***:

          - Data members of classes are not initialized in the constructor.

          - Change: All the members of the class are initialiazed in constructor of the class for the following classes.
            - BooleanArgument
            - DoubleArgument
            - IntegerArgument
            - MapArgument
            - StringArgument	
            - StringArrayArgument
          
          - Example:

          - Before:<br/>
          ![](images/1without_cons.png)<br/>
          - After:<br/>
          ![](images/2wit_cons.png)
          <br/>
          
          
      - ***Making One Function do one Task***:

          - In ArgumentParser Class, in parseSchemaElement task of adding letter corresponding to type of argument and corresponding object should be in separate function.

            - Change: This task is given to separate method insertElementInCharacterToObjectMap();

          - In Classes BooleanArgument, IntegerArgument, DoubleArgument, StringArgument, StringArrayArgument getValue functions is doing more than one task that should be in separate functions.<br/>
            Example:<br/>
            In BooleanArgument Class getValue method does following things:<br/>
              Check validity of BooleanArgument Class object<br/>
              If Valid Return Boolean value after typecasting else return false</br>

            - Change: Tasks are splitted in functions 

          - Before:<br/>
          ![](images/3Before_separate_set.png)<br/>
          - After:<br/>
          ![](images/4.After_separate_set.png)
          <br/>
          
          Here the code is more readable as splitted in functions.
          
          
    - ***Changing Variables/Methods Name/Class Name***:
            
      - Change: Variable names, method names, Class name are change to be more understandable.

        - Changing Variable Name:<br/>
            Example:<br/>
              private Map <character,ArgumentInterface> marshalers<br/> 
              changed to<br/>
              private Map <character,ArgumentInterface> characterToObjectMap<br/>

        - Changing Class Names:<br/>
            Example:<br/>
                  Args is changed to ArgumentParser<br/>
                  ArgumentMarshaler is changed to ArgumentInterface<br/>
                  BooleanArgumentMarshaler is changed to BooleanArgument<br/>
                  DoubleArgumentMarshaler is changed to DoubleArgument<br/>

      Similarly other changed are made.<br/>
      
   - ***Adding Try Catch Blocks to new function***:
      
      - Example:<br/>
          Here in else part separate function(setArgument) call made to set currentArgument.<br/>
          (ArgumentInterface is previously ArgumentMarshaler).<br/>
          
       Before:<br/>
       ![](images/5try_catch_before.png)<br/>
       After:<br/>
       ![](images/6try2.png)
       <br/>


   - ***Switch Case instead of If Else***:

       Before:<br/>
            ![](images/7before_switch.png)<br/>
       After:<br/>
            ![](images/8after_switch2.png)
       <br/>

   - ***Correction in Code***:

      - Example: Schema: “x” and Arguments:”-xy”
          For this test Case the arguments are not valid because of two letters and it should throw “INVALID ARGUMENT FORMAT” exception.
          Corrects Arguments should be like : “-x”
          To correct this, length of the string after ‘-’ must be one, that check is added.

      - After Adding check:
      ![](images/9check.png)<br/>


   - ***Correction in Test Cases***:

      - In case of extra/invalid argument format, code throws exception so in test case error code compared against “INVALID_ARGUMENT” to check working of code, earlier it was not there.
      - Changes made in following Test Cases:
        testExtraArguments
        testExtraArgumentsThatLookLikeFlags
        testSpacesInFormat 

        Example:
         Before:<br/>
              ![](images/10test_before.png)<br/>
            - After:<br/>
              ![](images/11test_after.png)
            <br/>

