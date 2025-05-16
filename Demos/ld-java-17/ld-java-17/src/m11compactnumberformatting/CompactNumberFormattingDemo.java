package m11compactnumberformatting;

import java.text.NumberFormat;
import java.util.Locale;

public class CompactNumberFormattingDemo {
    public static void main(String[] args) {
        // Compact Number Formatting allows format numbers in a human-readable format
        // Use case : Display large numbers in financial apps
        System.out.println("Basic Formatting");
        demoBasicFormatting();
    }

    private static void demoBasicFormatting() {
        // Format 1000 to 1K and similarly  1M, 1B
        NumberFormat numberFormat = NumberFormat.getCompactNumberInstance(
                Locale.US, NumberFormat.Style.SHORT);
        // K represents thousands (Kilo) - 1000 become 1K
        System.out.println(numberFormat.format(1000));
        // M represents millions (Million) - 1000000 become 1M
        System.out.println(numberFormat.format(1000000));
        // B represents billions (Billion) - 1000 become 1B
        System.out.println(numberFormat.format(1000000000));

        // IP
        // 1500 to 1.5K
        // 23456 23.46K
        // 100000 to 100K
        // Try trillion too.
    }
}
