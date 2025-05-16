package m12textblocks;

public class TextBlocksDemo {
    public static void main(String[] args) {
        // A multi-line string literal
        // avoid the need for escape sequences
        // format strings automatically in a predictable way
        // developer comfort not having to struggle with tabs

        String textBlock1 = """
                Text blocks are multi-line
                string literals with automatic
                formatting support
                """;

        System.out.println("Basic Text Block : \n" + textBlock1);

        String textBlockWithQuotes = """
                "The text blocks can contain 'single'
                and "double" quotes freely
                """;
        System.out.println("Text Block with Quotes : \n" + textBlockWithQuotes);

        String indentation = """
                    This line is indented
                This line isn't
                    Back to indent
                """;
        System.out.println("Indent : \n" + indentation);

        String escaped = """
                You can use \nfor new lines \t\t\t with 3 tabs
                \t for tabs....
                """;
        System.out.println("Escaped : \n" + escaped);

        String unicode = """
                Unicode allows representation of :
                 • Letters : A-Z, а-я, 汉字
                 • Symbols : ©, ®, ™
                 • Emojis : 😊,🙏,🧑‍💻 
                 • Currency : €, ¥, £, ₹
                """;
        System.out.println("Unicode : \n" + unicode);

        String optimized = """
                Text blocks are optimized
                at compile time for better performance
                """;
        System.out.println("Optimized : \n" + optimized);

        String whitespace = """
                      Leading spaces are based
                              the closing delimiter position
                   trailing spaces are preserved too    
           """;
        System.out.println("Whitespace : \n" + whitespace);

        String amount = "1000";
        String formatted = """
                Balance: $%s
                """.formatted(amount);
        System.out.println("String interpolation : " + formatted);

        String escapingDelimiters = """
                You can include \"\"\" by 
                escapting them with \"""
                """;
        System.out.println("Escaping delimiters : \n" + escapingDelimiters);

        // FinTech Use case
        String json = """
                {
                    "transaction" : {
                        "amount" : 100.00,
                        "currency" : "USD"
                    },
                """;
        System.out.println("JSON : \n" + json);

        System.out.println("Using with String Builder");
        StringBuilder builder = new StringBuilder();
        builder.append(json);
        System.out.println(builder.toString());

        json = """
                    "transaction" : {
                        "amount" : 105.00,
                        "currency" : "INR"
                    },
                """;
        builder.append(json);
        System.out.println("Added Transaction " + builder.toString());

        builder.append("""
                    "transaction" : {
                        "amount" : 1055.00,
                        "currency" : "EUR"
                    }
                }   
                """);

        System.out.println("Added one more Transaction \n" + builder.toString());

        // Pointers :
        // No limitation on no of lines
        // Maximum size is that of string
        
    }
}
