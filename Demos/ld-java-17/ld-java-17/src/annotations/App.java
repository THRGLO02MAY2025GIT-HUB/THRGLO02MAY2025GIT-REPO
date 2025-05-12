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
