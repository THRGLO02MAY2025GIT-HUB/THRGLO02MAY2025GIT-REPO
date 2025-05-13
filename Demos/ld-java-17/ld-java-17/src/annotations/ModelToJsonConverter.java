package annotations;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

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
//        Expected Output :  {"firstName":"John", "lastName":"rambo", "successfulTransactionFormatted":"50"}
        Class<?> clazz = object.getClass();
        // Populate the map with fields
        Map<String, String> jsonElementMap = new HashMap<>();
        for(Field field : clazz.getDeclaredFields()){
           field.setAccessible(true);
           if(field.isAnnotationPresent(JsonElement.class)){
//               jsonElementMap.put(field.getName(), (String)field.get(object));
               jsonElementMap.put(getKey(field), (String)field.get(object));
           }
        }
        System.out.println("DEBUG : " + jsonElementMap);

        Set<Map.Entry<String,String>> jsonElementEntrySet = jsonElementMap.entrySet();
        String jsonString = jsonElementEntrySet
                .stream()
                .map(entry -> "\"" + entry.getKey() + "\":\"" + entry.getValue() + "\"")
                .collect(Collectors.joining(","));
        System.out.println("DEBUG : " + jsonString);
       return "Expected output : " + "{" + jsonString + "}";
    }
    private String getKey(Field field) {
      // Write the logic actually get the key="successfulTransactionFormatted"
      String value = field.getAnnotation(JsonElement.class).key();
      if(value.isEmpty()){
          return field.getName();
      } else {
          return value;
      }
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
