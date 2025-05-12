package annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
//Declare an annotation with @interface
public @interface JsonSerializable {
}


// It is a class-level annotation
// Helps making a class eligible for serialization
