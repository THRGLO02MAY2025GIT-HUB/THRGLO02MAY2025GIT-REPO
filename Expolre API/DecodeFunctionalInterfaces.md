### Crating our own functional interface mimicking the Function interface available in Java 8 functional programming toolkit.

// Accept a single argument and perform an operation on it and return a result.
// This is a functional interface with a single abstract method.
// A functional interface can have multiple default methods and static methods.
// It can be used as the assignment target for a lambda expression or method reference.
// It is annotated with @FunctionalInterface to indicate that it is intended to be a functional interface.
// The @FunctionalInterface annotation is optional but helps to enforce the contract of a functional interface.
// If the interface does not satisfy the conditions of a functional interface, the compiler will generate an error.
```java
@FunctionalInterface
public interface Function<T,R> {
    
    R apply(T t);
}