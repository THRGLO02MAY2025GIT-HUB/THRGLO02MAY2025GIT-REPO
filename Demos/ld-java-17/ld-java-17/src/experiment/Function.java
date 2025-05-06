package experiment;

@FunctionalInterface
public interface Function<T,R> {
    public abstract  R apply(T t);
    public static  Object apply1(Object t){
        return new Object();
    }
    public default  Object apply2(Object t){
        return new Object();
    }
}