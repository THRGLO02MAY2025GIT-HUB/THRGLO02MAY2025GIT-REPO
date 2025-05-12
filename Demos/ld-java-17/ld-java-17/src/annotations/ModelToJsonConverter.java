package annotations;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLOutput;
import java.util.Objects;

public class ModelToJsonConverter {
    // Check if the object is not nul, else
    // throw JsonSerializationException
   private void checkIfSerializable(Object object){
       System.out.println(">>>>>>>>>> Serialization Check!");
    if(Objects.isNull(object)){
        throw new JsonSerializationException("Serializing null object.");
    }
    // REFLECT UPON THE OBJECT (Customer) and find if it contains metadata about serialization
       // Get the class during runtime and check if it has JsonSerializable
    Class<?> clazz = object.getClass();
    boolean serializable = clazz.isAnnotationPresent(JsonSerializable.class);
    if(!serializable){
        throw new JsonSerializationException("Json Serialization not possible on  " + clazz.getSimpleName() + "class. For serialization annotate the class with JsonSerializable.");
    }
   }
   private void initializeModel(Object object) throws InvocationTargetException, IllegalAccessException {
       System.out.println(">>>>>>>>>> Initializing Model with proper Case!");
      Class<?> clazz = object.getClass();
      // REFLECT THE METHODS
       for(Method method : clazz.getDeclaredMethods()){
           if(method.isAnnotationPresent(Init.class)){
               method.setAccessible(true);
               method.invoke(object);
           }
       }
   }
    private String getJsonString(Object object) throws IllegalAccessException {
       // Lab : Create a Map containing the JSON field and the Value and return a JSON String
//        Customer{firstName='john', lastName='rambo', noOfSuccessfulTransactions='50'}
//        Expected Output :  {"firstName":"John", "lastName":"rambo", "successfulTransactionFormatted":"50"}
       return null;
    }
    private String getKey(Field field) {
       // Write the logic actually get the key="successfulTransactionFormatted"
      return null;
    }

    public String convertToJson(Object object) throws JsonSerializationException {
       try {

           checkIfSerializable(object);
           initializeModel(object);
           return getJsonString(object);
       }
       catch (Exception exception) {
           System.out.println("\n\n****** " + exception.getMessage() + " ******\n");
           throw new JsonSerializationException(exception.getMessage());
       }
    }
}
