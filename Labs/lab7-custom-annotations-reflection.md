# Lab 7: Custom Annotations and Reflection in Java
30 Minutes

In this lab, you will explore how to use custom annotations and reflection in Java to implement a JSON serialization mechanism. The provided code snippet demonstrates the use of annotations and reflection to validate, initialize, and convert a Java object into a JSON string.

## Objective

- Understand how to use custom annotations in Java.
- Learn how to use reflection to inspect and manipulate objects at runtime.
- Implement a JSON serialization mechanism using annotations and reflection.

---

## Code Overview

The `ModelToJsonConverter` class is responsible for converting a Java object into a JSON string. It performs the following tasks:

1. **Serialization Check**: Ensures the object is annotated with `@JsonSerializable`.
2. **Model Initialization**: Invokes methods annotated with `@Init` to prepare the object for serialization.
3. **JSON Conversion**: Converts the object's fields into a JSON string.

### Key Methods

1. **`checkIfSerializable(Object object)`**
    - Validates if the object is not null and is annotated with `@JsonSerializable`.
    - Throws `JsonSerializationException` if the object fails validation.

2. **`initializeModel(Object object)`**
    - Uses reflection to find and invoke methods annotated with `@Init`.

3. **`getJsonString(Object object)`**
    - Placeholder for logic to convert the object's fields into a JSON string.

4. **`getKey(Field field)`**
    - Placeholder for logic to retrieve the JSON key for a given field.

5. **`convertToJson(Object object)`**
    - Combines the above methods to perform the full serialization process.

---

## Lab Instructions

### Step 1: Add Annotations to Your Model Class

1. Create a model class (e.g., `Customer`).
2. Annotate the class with `@JsonSerializable`.
3. Annotate any initialization methods with `@Init`.
4. Add fields to the class and annotate them with `@JsonElement` if you want to specify custom JSON keys.


---

### Step 2: Implement `getJsonString` Method

1. Use reflection to iterate over the fields of the object.
2. Create a map of field names (or keys) and their values.
3. Convert the map into a JSON string.

---

### Step 3: Implement `getKey` Method

1. Use reflection to retrieve the key for a field.
2. If the field has a custom annotation (e.g., `@JsonElement`), use its value as the key.

---

### Step 4: Test the Serialization

1. Create an instance of your model class and populate its fields.
2. Use the `ModelToJsonConverter` to serialize the object.
3. Verify the output matches the expected JSON format.



Expected Output:
```json
Expected Output: {"firstName":"John", "lastName":"Rambo", "successfulTransactionFormatted":"50"}
```

---

## Deliverables

1. A fully implemented `ModelToJsonConverter` class.
2. A model class annotated with `@JsonSerializable` and `@Init`.
3. A working main method that demonstrates the serialization process.

---

## Code Template to Start With
```java
package annotations;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;

public class ModelToJsonConverter {

    /**
     * Checks if the object is serializable.
     * Throws JsonSerializationException if the object is null or not annotated with @JsonSerializable.
     */
    private void checkIfSerializable(Object object) {
        System.out.println(">>>>>>>>>> Serialization Check!");
        if (Objects.isNull(object)) {
            throw new JsonSerializationException("Serializing null object.");
        }

        // Reflect upon the object and check if it contains metadata about serialization
        Class<?> clazz = object.getClass();
        boolean serializable = clazz.isAnnotationPresent(JsonSerializable.class);
        if (!serializable) {
            throw new JsonSerializationException(
                "Json Serialization not possible on " + clazz.getSimpleName() + 
                " class. For serialization, annotate the class with @JsonSerializable."
            );
        }
    }

    /**
     * Initializes the model by invoking methods annotated with @Init.
     */
    private void initializeModel(Object object) throws InvocationTargetException, IllegalAccessException {
        System.out.println(">>>>>>>>>> Initializing Model with proper Case!");
        Class<?> clazz = object.getClass();

        // Reflect the methods and invoke those annotated with @Init
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Init.class)) {
                method.setAccessible(true);
                method.invoke(object);
            }
        }
    }

    /**
     * Converts the object's fields into a JSON string.
     * @return JSON string representation of the object.
     */
    private String getJsonString(Object object) throws IllegalAccessException {
        // Write code here : Create a Map containing the JSON field and the value, and return a JSON string
        // Expected Output: {"firstName":"John", "lastName":"Rambo", "successfulTransactionFormatted":"50"}
        return null;
    }

    /**
     * Retrieves the JSON key for a given field.
     * @return The key as a string.
     */
    private String getKey(Field field) {
        // Write code here :  the logic to actually get the key, e.g., "successfulTransactionFormatted"
        return null;
    }

    /**
     * Combines all steps to convert an object to a JSON string.
     */
    public String convertToJson(Object object) throws JsonSerializationException {
        try {
            checkIfSerializable(object);
            initializeModel(object);
            return getJsonString(object);
        } catch (Exception exception) {
            System.out.println("\n\n****** " + exception.getMessage() + " ******\n");
            throw new JsonSerializationException(exception.getMessage());
        }
    }
}
```
```java
package annotations;

public class App {
    public static void main(String[] args) {
        Customer customer = new Customer("john","rambo","50");
        System.out.println(customer);
        ModelToJsonConverter modelToJsonConverter = new ModelToJsonConverter();
        String jsonString = modelToJsonConverter.convertToJson( customer );
        System.out.println(jsonString);
    }
}
```